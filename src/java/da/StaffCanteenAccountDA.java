/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;


import domain.OrderLine;
import domain.Query;
import domain.StaffCanteenAccount;
import domain.Student;
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
public class StaffCanteenAccountDA {
    private String host = "jdbc:derby://localhost:1527/CMS";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "StaffCanteenAccount";
    private Connection conn;
    private PreparedStatement stmt;
    private String sqlQueryStr = "SELECT * FROM " + tableName;
    private String sqlInsertStr = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?)";
    private ResultSet rs;
    public StaffCanteenAccountDA() {
        createConnection();
    }
        public void addRecord(StaffCanteenAccount staffCA) {
        try {
            stmt = conn.prepareStatement(sqlInsertStr);
            stmt.setString(1,staffCA.getStaffUsername());
            stmt.setString(2,staffCA.getStaffId());
            stmt.setString(3,staffCA.getStaffPassword());
            stmt.setString(4,staffCA.getStaffName());
            stmt.setString(5,staffCA.getStaffIc());
            stmt.setString(6,staffCA.getStaffEmail());
            stmt.setString(7,staffCA.getStaffPhoneNo());
            stmt.executeUpdate();
            
        }catch (SQLException ex) {
            ex.getMessage();
            }
        }
        public StaffCanteenAccount getStaffDetails(String staffUsername) {
             StaffCanteenAccount staffCA = new StaffCanteenAccount();    
        try{
            stmt = conn.prepareStatement("SELECT * FROM StaffCanteenAccount WHERE staffUsername=? ");
            stmt.setString(1,staffUsername);
            ResultSet rs = stmt.executeQuery();
           
            if(rs.next()){
                staffCA = new StaffCanteenAccount(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                return staffCA;
            }   
        }catch (SQLException ex) {
               ex.getMessage();
        }
        return null;
    } 
        public void updateStaffDetails(String staffName,String staffIc , String staffEmail,String staffPhoneNo,String staffUsername){
            try{
               stmt = conn.prepareStatement("UPDATE StaffCanteenAccount SET StaffName = ? , staffIc = ?,staffEmail = ? ,staffPhoneNo = ? WHERE staffUsername = ?");
               stmt.setString(1,staffName);
               stmt.setString(2,staffIc);
               stmt.setString(3,staffEmail);
               stmt.setString(4,staffPhoneNo);
               stmt.setString(5,staffUsername);
               stmt.executeUpdate();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        public void changePassword(String staffPassword,String staffUsername){
            try{
               stmt = conn.prepareStatement("UPDATE StaffCanteenAccount SET staffPassword = ? WHERE staffUsername = ?");
               stmt.setString(1,staffPassword);
               stmt.setString(2,staffUsername);
               stmt.executeUpdate();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        public int getStaffId(String staffId){
         int found = 0;
        try{
            stmt = conn.prepareStatement("SELECT * FROM StaffCanteenAccount WHERE staffId =?");
            stmt.setString(1,staffId);
            rs = stmt.executeQuery();
           
            if(rs.next()) {
                found = 1;
            }
        }catch (SQLException ex) {
        }
        return found;
    }
         public int getStaffUsername(String staffUsername){
         int found = 0;
        try{
            stmt = conn.prepareStatement("SELECT * FROM StaffCanteenAccount WHERE staffUsername =?");
            stmt.setString(1,staffUsername);
            rs = stmt.executeQuery();
            if(rs.next()) {
                found = 1;
            }
        }catch (SQLException ex) {
        }
        return found;
    }
      /*public Student getStudent(String studUsername){
          StudentCanteenAccount student = null;
         try{
            
            stmt = conn.prepareStatement("SELECT * FROM StudentCanteenAccount WHERE studUsername = ?");
            stmt.setString(1,studUsername);
            rs = stmt.executeQuery();
            if(rs.next()) {
             student =  new StudentCanteenAccount(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(2),rs.getString(2));
             return student;
            }
        }catch (SQLException ex) {

        }
        return null;

    }*/
    public boolean topUpCredit(String studUsername,double amount){
         try{
            stmt = conn.prepareStatement("SELECT * FROM StudentCanteenAccount WHERE studUsername = ?");
            stmt.setString(1,studUsername);
            rs = stmt.executeQuery();
            if(rs.next()) {
                stmt = conn.prepareStatement("UPDATE StudentCanteenAccount SET creditPoint =  creditPoint + ?  WHERE studUsername = ?");
                stmt.setDouble(1,amount);
                stmt.setString(2,studUsername);
                stmt.executeUpdate();
            return true;
            }
        }catch (SQLException ex) {

        }
        return false;

    }
    public Student getStudInfo(String studUsername){
       Student stud = null;
        try{
            stmt = conn.prepareStatement("SELECT * FROM StudentCanteenAccount WHERE StudUsername = ?");                                 
            stmt.setString(1,studUsername);
            rs = stmt.executeQuery();
            if(rs.next()){
               stud = new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
            }
        }catch (SQLException ex) {
        }
        return stud;
    }
     public ArrayList<Query> getTotalIngredientNeedQuery(String orderDate){
        ArrayList<Query> totalIngredientNeeds = new ArrayList<Query>();
        try{
            stmt = conn.prepareStatement("SELECT MI.INGREDIENTID,I.INGREDIENTNAME,SUM(QUANTITY*QUANTITYNEEDED) "
                                        +"FROM   OrderLINE OL ,  MEAL M ,MEALINGREDIENT MI , INGREDIENT I "
                                        +"WHERE  OL.MEALID = M.MEALID AND M.MEALID = MI.MEALID AND MI.INGREDIENTID = I.INGREDIENTID AND orderStatus ='U' AND ORDERREDEEMDATE = ? "
                                        +"GROUP BY MI.INGREDIENTID,I.INGREDIENTNAME");
            stmt.setString(1,orderDate);
            rs = stmt.executeQuery();
            while(rs.next()){
                //Query totalIngredientNeed = new Query(rs.getString(1),rs.getString(2),rs.getInt(3));
                Query totalIngredientNeed = new Query();
                totalIngredientNeed.setIngredientId(rs.getString(1));
                totalIngredientNeed.setIngredientName(rs.getString(2));
                totalIngredientNeed.setIngredientNeeded(rs.getInt(3));
                totalIngredientNeeds.add(totalIngredientNeed);
            }
        }catch (SQLException ex) {
          
        }
        return totalIngredientNeeds;
    }
    public ArrayList<Query> getTotalMealOrderQuery(String orderDate){
        ArrayList<Query> totalMealsOrder = new ArrayList<Query>();
        
        try{
            stmt = conn.prepareStatement("SELECT OL.MEALID,M.MEALNAME,SUM(QUANTITY) "
                                        +"FROM  OrderLINE OL ,  MEAL M "
                                        +"WHERE OL.MEALID = M.MEALID AND orderStatus ='U' AND ORDERREDEEMDATE = ? "
                                        +"GROUP BY OL.MEALID,M.MEALNAME");
            stmt.setString(1,orderDate);
            rs = stmt.executeQuery();
            while(rs.next()){
                Query totalMealOrder = new Query(rs.getString(1),rs.getString(2),rs.getInt(3));
                totalMealsOrder.add(totalMealOrder);
            }
        }catch (SQLException ex) {
          
        }
        return totalMealsOrder;
    }
        
    public boolean updateCouponStatus(String couponId,String currentDate){
       
        try{
            stmt = conn.prepareStatement("SELECT * FROM OrderLine WHERE couponId = ? AND ORDERREDEEMDATE = ? AND ORDERSTATUS = 'U'");
            stmt.setString(1,couponId);
            stmt.setString(2,currentDate);
            rs = stmt.executeQuery();
            if(rs.next()){
                stmt = conn.prepareStatement("UPDATE OrderLine SET ORDERSTATUS = 'R' WHERE COUPONID = ? ");
                stmt.setString(1,couponId);
                stmt.executeUpdate();
                return true;
            }
        }catch (SQLException ex) {
        }
        return false;
    }
    public ArrayList<OrderLine> getOrderLine(){
        ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>();
        OrderLine orderLine = null;
        try{
            stmt = conn.prepareStatement("SELECT * FROM ORDERLINE");
         
            rs = stmt.executeQuery();
            while(rs.next()){
                orderLine = new OrderLine(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getInt(6));
                orderLines.add(orderLine);
            }
            
        }catch (SQLException ex) {
            ex.getMessage();
        }
         return orderLines;
    }
        public OrderLine getOrderLineDetail(String couponId){
        OrderLine orderLine = null;
        try{
            stmt = conn.prepareStatement("SELECT * FROM ORDERLINE WHERE couponId = ? AND orderStatus = 'U'");
            stmt.setString(1,couponId);
            rs = stmt.executeQuery();
            if(rs.next()){
                orderLine = new OrderLine(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getInt(6));
                
            }
            
        }catch (SQLException ex) {
            ex.getMessage();
        }
        return orderLine;
    }  
       
    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
        } catch (SQLException ex) {
            
        }
    }

    /*
        public Order getCurrentRecord2() {
        Order order = null;
        try {
           order = new Order(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDouble(4), rs.getDouble(5), rs.getDate(6),rs.getDate(7)); 
           
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return order;
    }
    
    }*/
}
