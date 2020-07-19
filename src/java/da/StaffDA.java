/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author NGO KIAN HEE
 */
public class StaffDA {
    private String host = "jdbc:derby://localhost:1527/CMS";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Staff";
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    public StaffDA() {
        createConnection();
    }
       
    public int getStaffRecord(String staffId){
         int found = 0;
        try{
            stmt = conn.prepareStatement("SELECT * FROM Staff WHERE staffId =?");
            stmt.setString(1,staffId);
            rs = stmt.executeQuery();           
            if(rs.next()) {
                found = 1;
            }
        }catch (SQLException ex) {
        }
        return found;
    }
     private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
        } catch (SQLException ex) {
            
        }
    }
}
