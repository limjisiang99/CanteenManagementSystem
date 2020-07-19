/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.MealIngredient;
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
public class MealIngredientDA {
       private String host = "jdbc:derby://localhost:1527/CMS";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "MealIngredient";
    private Connection conn;
    private PreparedStatement stmt;
    private String sqlQueryStr = "SELECT * FROM " + tableName;
    private String sqlInsertStr = "INSERT INTO " + tableName + " VALUES(?,?,?)";
    private ResultSet rs;
    public MealIngredientDA() {
        createConnection();
    }
        public void addRecord(MealIngredient mealIngredient) {
        try {
            stmt = conn.prepareStatement(sqlInsertStr);
            stmt.setString(1,mealIngredient.getMealId());
            stmt.setString(2,mealIngredient.getIngredientId());
            stmt.setInt(3,mealIngredient.getQuantityNeeded());
 
            stmt.executeUpdate();
            
        }catch (SQLException ex) {
            ex.getMessage();
            }
        }
        public void updateMealIngredient(MealIngredient mealIngredient) {
        try {
            stmt = conn.prepareStatement("UPDATE MealIngredient SET quantityNeeded = ? WHERE mealId = ? AND ingredientId = ?");
            stmt.setString(2,mealIngredient.getMealId());
            stmt.setString(3,mealIngredient.getIngredientId());
            stmt.setInt(1,mealIngredient.getQuantityNeeded());
            stmt.executeUpdate();
            
        }catch (SQLException ex) {
            ex.getMessage();
            }
        }
            public void deleteMealIngredient(String mealId,String ingredientId) {
        try {
            stmt = conn.prepareStatement("DELETE FROM MealIngredient WHERE mealId = ? AND ingredientId = ?");
            stmt.setString(1,mealId);
            stmt.setString(2,ingredientId);
            stmt.executeUpdate();
            
        }catch (SQLException ex) {
            ex.getMessage();
            }
        }
        public MealIngredient getCurrentRecord() {
        MealIngredient ingredient = null;
        try {
           ingredient = new MealIngredient(rs.getString(1), rs.getString(2), rs.getInt(3));
        
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return ingredient;
    }
        public ArrayList<MealIngredient> getMealIngredient() {

        ArrayList<MealIngredient> mealIngredient = new ArrayList<MealIngredient>();
        try {
            stmt = conn.prepareStatement(sqlQueryStr);
            rs = stmt.executeQuery();
            while (rs.next()) {
                mealIngredient.add(getCurrentRecord());
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return mealIngredient;
    } 
        private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
        } catch (SQLException ex) {
            
            
        }
    }
}
