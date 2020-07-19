/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.Meal;
import domain.MealCancel;
import domain.PopularMeal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author NGO KIAN HEE
 */
public class MealDA {
    private String host = "jdbc:derby://localhost:1527/CMS";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "MEAL";
    private Connection conn;
    private PreparedStatement stmt;
    private String sqlQueryStr = "SELECT * FROM " + tableName;
    private String sqlInsertStr = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?)";
    private ResultSet rs;
    public MealDA() {
        createConnection();
    }
        public void addRecord(Meal meal) {
        try {
            stmt = conn.prepareStatement(sqlInsertStr);
            stmt.setString(1,meal.getMealId());
            stmt.setString(2,meal.getMealName());
            stmt.setDouble(3,meal.getMealPrice());
            stmt.setString(4,meal.getMealDesc());
            stmt.setDouble(5,meal.getCalories());
            stmt.setBytes(6,meal.getMealPicture());
            stmt.setString(7,meal.getMealSession());
            stmt.setString(8,meal.getMealStatus());
            stmt.executeUpdate();
            
        }catch (SQLException ex) {
            ex.getMessage();
            }
        }
        public void updateMeal(Meal meal) {
        try {
            stmt = conn.prepareStatement("UPDATE Meal SET mealName = ? , mealPrice = ? , mealDesc = ? , calories = ? , mealStatus = ? , mealSession = ? WHERE mealId = ?");
            stmt.setString(7,meal.getMealId());
            stmt.setString(1,meal.getMealName());
            stmt.setDouble(2,meal.getMealPrice());
            stmt.setString(3,meal.getMealDesc());
            stmt.setDouble(4,meal.getCalories());
            //stmt.setBytes(6,meal.getMealPicture());
            stmt.setString(5,meal.getMealStatus());
            stmt.setString(6,meal.getMealSession());
            stmt.executeUpdate();
            
        }catch (SQLException ex) {
            ex.getMessage();
            }
        }
        public void changeMealStatus(Meal meal) {
        try {
            stmt = conn.prepareStatement("UPDATE Meal SET  mealStatus = ? WHERE mealId = ?");
            stmt.setString(2,meal.getMealId());          
            stmt.setString(1,meal.getMealStatus());
            stmt.executeUpdate();
            
        }catch (SQLException ex) {
            ex.getMessage();
            }
        }
        public String getLastMealId(){
            String lastMealId=null;
            try{
            stmt = conn.prepareStatement("SELECT * FROM MEAL");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
               lastMealId= rs.getString(1);
            }   
        }catch (SQLException ex) {
               ex.getMessage();
        }
               return lastMealId;
        }
        public Meal getCurrentRecord() {
        Meal meal = null;
        try {
           meal = new Meal(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getDouble(5), rs.getBytes(6), rs.getString(7), rs.getString(8));
        
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return meal;
    }
        
        public ArrayList<Meal> getMeals() {

        ArrayList<Meal> meal = new ArrayList<Meal>();
        try {
            stmt = conn.prepareStatement(sqlQueryStr);
            rs = stmt.executeQuery();
            while (rs.next()) {
                meal.add(getCurrentRecord());
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return meal;
    }
        //marcus
        public ArrayList<Meal> getMeal() {
        ArrayList<Meal> product = new ArrayList<Meal>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM MEAL WHERE MEALSTATUS = 'Available'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                product.add(getCurrentRecord());
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return product;
    } 
          //getCurrentRecord2 just didn't store Image
         public Meal getCurrentRecord2() {
        Meal meal = null;
        try {
           meal = new Meal(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getDouble(5),rs.getString(6),rs.getString(7));
        
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return meal;
    }
        public ArrayList<Meal> getPopularMealSet(String startingDate , String endingDate){
            ArrayList<Meal> popularMeals = new ArrayList<Meal>();
           
         try {
            stmt = conn.prepareStatement("SELECT OL.MEALID,M.MEALNAME , M.MEALPRICE,M.MEALDESC,M.CALORIES,M.MEALSTATUS,M.MEALSESSION "+ 
                                         "FROM OrderLine OL, Meal M "+ 
                                         "WHERE OL.mealId = M.mealId AND OL.ORDERSTATUS = 'R' AND OrderRedeemdate BETWEEN ? AND ? "+
                                         "GROUP BY OL.MEALID,M.MEALNAME , M.MEALPRICE, M.MEALDESC, M.CALORIES,M.MEALSTATUS,M.MEALSESSION "+ 
                                         "ORDER BY SUM(OL.QUANTITY) DESC");
            stmt.setString(1,startingDate);
            stmt.setString(2,endingDate);
            rs = stmt.executeQuery();
            while (rs.next()) {
                popularMeals.add(getCurrentRecord2());
            }
            return popularMeals;
        } catch (SQLException ex) {
          
        }
       return null;
    }
        public ArrayList<PopularMeal> getPopularMealSet2(String startingDate , String endingDate){
            ArrayList<PopularMeal> popularMeals = new ArrayList<PopularMeal>();
            PopularMeal popularMeal = null;
           
         try {
            stmt = conn.prepareStatement("SELECT OL.MEALID,M.MEALNAME , M.MEALPRICE,OL.ORDERREDEEMDATE,SUM(OL.QUANTITY),(SUM(OL.QUANTITY)*M.MEALPRICE) "+ 
                                         "FROM OrderLine OL, Meal M "+ 
                                         "WHERE OL.mealId = M.mealId AND OL.ORDERSTATUS = 'R' AND OrderRedeemdate BETWEEN ? AND ? "+
                                         "GROUP BY OL.MEALID,M.MEALNAME , M.MEALPRICE,OL.ORDERREDEEMDATE "+ 
                                         "ORDER BY OL.ORDERREDEEMDATE");
            stmt.setString(1,startingDate);
            stmt.setString(2,endingDate);
            rs = stmt.executeQuery();
            while (rs.next()) {
               popularMeal = new PopularMeal(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getDate(4),rs.getInt(5),rs.getDouble(6));
               popularMeals.add(popularMeal);
            }
            return popularMeals;
        } catch (SQLException ex) {
          
        }
       return null;
    }
        
        public ArrayList<MealCancel> getCancelledMeal(String startingDate , String endingDate){
            ArrayList<MealCancel> mealsCancelled = new ArrayList<MealCancel>();
            MealCancel mealCancelled = null;
         try {
            stmt = conn.prepareStatement("SELECT OL.MEALID,M.MEALNAME ,SUM(OL.QUANTITY) "+ 
                                         "FROM OrderLine OL, Meal M "+ 
                                         "WHERE OL.mealId = M.mealId AND OL.ORDERSTATUS = 'C' AND OrderRedeemdate BETWEEN ? AND ? "+
                                         "GROUP BY OL.MEALID,M.MEALNAME "+ 
                                         "ORDER BY SUM(OL.QUANTITY) DESC");
            stmt.setString(1,startingDate);
            stmt.setString(2,endingDate);
            rs = stmt.executeQuery();
            while (rs.next()) {
                mealCancelled = new MealCancel(rs.getString(1),rs.getString(2),rs.getInt(3));
                mealsCancelled.add(mealCancelled);
            }
            return mealsCancelled;
        } catch (SQLException ex) {
          
        }
       return null;
    }
        public ArrayList<Meal> viewMealByPrice(double startRange , double endRange){
            ArrayList<Meal> meals = new ArrayList<Meal>();
            Meal meal = null;
         try {
            stmt = conn.prepareStatement("SELECT * FROM MEAL WHERE MEALPRICE BETWEEN ? AND ?");
            stmt.setDouble(1,startRange);
            stmt.setDouble(2,endRange);
            rs = stmt.executeQuery();
            while (rs.next()) {
                meal = new Meal(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getDouble(5), rs.getBytes(6), rs.getString(7), rs.getString(8));
                meals.add(meal);
            }
            return meals;
        } catch (SQLException ex) {
          
        }
       return null;
    }
        private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
        } catch (SQLException ex) {
            
        }
    }
        
}
