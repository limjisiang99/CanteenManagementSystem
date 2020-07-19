/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.Ingredient;
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
public class IngredientDA {
    private String host = "jdbc:derby://localhost:1527/CMS";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Ingredient";
    private Connection conn;
    private PreparedStatement stmt;
    private String sqlQueryStr = "SELECT * FROM " + tableName;
    private String sqlInsertStr = "INSERT INTO " + tableName + " VALUES(?,?,?)";
    private ResultSet rs;
    public IngredientDA() {
        createConnection();
    }
        public void addRecord(Ingredient ingredient) {
        try {
            stmt = conn.prepareStatement(sqlInsertStr);
            stmt.setString(1,ingredient.getIngredientId());
            stmt.setString(2,ingredient.getIngredientName());
            stmt.setString(3,ingredient.getIngredientDesc());
 
            stmt.executeUpdate();
            
        }catch (SQLException ex) {
            ex.getMessage();
            }
        }
        public int countRow(){
            int totalRow = 0;
            try{
            stmt = conn.prepareStatement("SELECT COUNT(*) FROM Meal ");
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                totalRow = Integer.parseInt(rs.getString("COUNT(*)"));
            }   
        }catch (SQLException ex) {
               ex.getMessage();
        }
           return totalRow;
        }
        public Ingredient getCurrentRecord() {
        Ingredient ingredient = null;
        try {
           ingredient = new Ingredient(rs.getString(1), rs.getString(2), rs.getString(3));
        
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return ingredient;
    }
        public ArrayList<Ingredient> getIngredient() {

        ArrayList<Ingredient> ingredient = new ArrayList<Ingredient>();
        try {
            stmt = conn.prepareStatement(sqlQueryStr);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ingredient.add(getCurrentRecord());
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return ingredient;
    } 
        private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
        } catch (SQLException ex) {
            
        }
    }
}
