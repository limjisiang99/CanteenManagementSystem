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
 * @author Lim Ji-Siang
 */
public class StudentSchoolDA {
    private String host = "jdbc:derby://localhost:1527/CMS";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "STUDENTCANTEENACCOUNT";
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public StudentSchoolDA(){
        createConnection();
    }
     public int getCurrentRecord(String StudentID) {
        int found = 0;
        try {
        stmt = conn.prepareStatement("SELECT * FROM STUDENT WHERE STUDENTID = ? ");
        stmt.setString(1,StudentID);
        rs = stmt.executeQuery();
        if(rs.next()){
            found = 1;
        }
        }catch(SQLException ex){
         
     }
        return found;
    }
    

     private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
        } catch (SQLException ex) {
           // JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    
}
