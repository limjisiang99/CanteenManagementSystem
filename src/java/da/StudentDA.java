/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

/**
 *
 * @author Lim Ji-Siang
 */

import domain.Student;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class StudentDA {
    private String host = "jdbc:derby://localhost:1527/CMS";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "STUDENTCANTEENACCOUNT";
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public StudentDA() {
        createConnection();
    }
 
     public Student Sretrieve(Student a){//this is for login student
        
        try{
            stmt = conn.prepareStatement(String.format("SELECT * FROM STUDENTCANTEENACCOUNT WHERE STUDUSERNAME = '%s'",a.getStudUsername()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                a = new Student(rs.getString(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
                return a;
            }
        }catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
       
       public String SgetRecord(String a){//this is for sign up student
        
        try{
            stmt = conn.prepareStatement(String.format("SELECT * FROM STUDENTCANTEENACCOUNT WHERE STUDUSERNAME = '%s'",a));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
               return rs.getString(3);
            }
        }catch (SQLException ex) {
           // JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public int Sinsert(Student a){
        try {
            stmt = conn.prepareStatement(String.format("INSERT INTO STUDENTCANTEENACCOUNT VALUES('%s','%s','%s',0,'%s','%s','%s','%s')",a.getStudUsername(),a.getStudentId(),a.getPassword(),a.getSecretQuestion(), a.getSecretAnswer(),a.getEmail(),a.getContactNumber()));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            return 1;
        }
        return 0;
    }
    
    public void SupdatePassword(Student a) {
        try {
            stmt = conn.prepareStatement(String.format("UPDATE STUDENTCANTEENACCOUNT SET PASSWORD = '%s' WHERE STUDUSERNAME = '%s'",a.getPassword(),a.getStudUsername()));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void SupdateInfo(Student a){
        try{
            stmt = conn.prepareStatement(String.format("UPDATE STUDENTCANTEENACCOUNT SET SECRETQUESTION = '%s', SECRETANSWER = '%s', EMAIL ='%s', CONTACTNUMBER ='%s' WHERE STUDUSERNAME = '%s'",a.getSecretQuestion(),a.getSecretAnswer(),a.getEmail(),a.getContactNumber(),a.getStudUsername()));
            stmt.executeUpdate();
        }catch (SQLException ex){
            //JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
}
}
     public void SupdateCreditPoints(Student a) {
       
        try {
        stmt = conn.prepareStatement("UPDATE STUDENTCANTEENACCOUNT SET CREDITPOINT = ? WHERE STUDUSERNAME = ?  ");
        stmt.setDouble(1,a.getCreditPoint());
        stmt.setString(2,a.getStudUsername());
        stmt.executeUpdate();
      
        }catch(SQLException ex){
         //JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
     }
    }
     
     public void SupdateCreditPoints2(String name, double creditPoint) {
       
        try {
        stmt = conn.prepareStatement("UPDATE STUDENTCANTEENACCOUNT SET CREDITPOINT = ? WHERE STUDUSERNAME = ?  ");
        stmt.setDouble(1,creditPoint);
        stmt.setString(2,name);
        stmt.executeUpdate();
      
        }catch(SQLException ex){
         //JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
     }
    }
     
     
     
     
     
     
    
    public double SgetPoints(String a){
        
        try{
            stmt = conn.prepareStatement(String.format("SELECT * FROM STUDENTCANTEENACCOUNT WHERE STUDUSERNAME = '%s'",a));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
               return rs.getDouble(4);
            }
        }catch (SQLException ex) {
           // JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
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
