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
import domain.OrderLine;
import domain.Orders;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class OrdersDA {
     private String host = "jdbc:derby://localhost:1527/CMS";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "ORDERS";
    private String sqlInsertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ? )";
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

   public OrdersDA(){
        createConnection();
    }

        public int addRecord(Orders a) {
        try {
            stmt = conn.prepareStatement(sqlInsertStr);
            stmt.setString(1,a.getOrderID());
            stmt.setString(2,a.getUserName());
            stmt.setDate(3,a.getOrderDate());
            stmt.setDouble(4,a.getTotalPrice());
            stmt.setDouble(5,a.getTotalCalories());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            return 1;
        }
        return 0;
        }
  
     public Orders getCurrentRecord() {
        Orders orders = null;
        try {
            orders = new Orders(rs.getString(1), rs.getString(2), rs.getDate(3),rs.getDouble(4),rs.getDouble(5));
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return orders;
    }
    
     public void UpdateTotalPrice(String OrderID, double Price, double Calories){
        try{
            stmt=conn.prepareStatement("UPDATE ORDERS SET TOTALPRICE = ?, TOTALCALORIES = ? WHERE ORDERID = ? ");
            stmt.setDouble(1,Price);
            stmt.setDouble(2, Calories);
            stmt.setString(3, OrderID);
            stmt.executeUpdate();
        }catch(SQLException ex){
            ex.getMessage();
        }
    }
      public ArrayList<Orders> getRecord() {
        ArrayList<Orders> orders = new ArrayList<Orders>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM ORDERS ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(getCurrentRecord());
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return orders;
    }
      
      public ArrayList<Orders> getOrdersID(String a) {
        ArrayList<Orders> orders = new ArrayList<Orders>();
        try {
            stmt = conn.prepareStatement(String.format("SELECT * FROM ORDERS WHERE STUDUSERNAME = '%s'",a ));
            rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(getCurrentRecord());
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return orders;
    }
      
      
      
      
     public ArrayList<Orders> getTransactionReport(String a) {
        ArrayList<Orders> orders = new ArrayList<Orders>();
        try {
            stmt = conn.prepareStatement(String.format("SELECT * FROM ORDERS WHERE STUDUSERNAME = '%s' AND ORDERDATE between '2019-01-01' AND '2019-06-30'",a));
            rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(getCurrentRecord());
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return orders;
    }

        public String getOrderID(String a){//this is for sign up student
        
        try{
            stmt = conn.prepareStatement(String.format("SELECT MAX(ORDERID) FROM ORDERS WHERE STUDUSERNAME = '%s'",a));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
               return rs.getString(1);
            }
        }catch (SQLException ex) {
           // JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
      
        public double getTotalPrice(String a){//this is for sign up student
        
        try{
            stmt = conn.prepareStatement(String.format("SELECT * FROM ORDERS WHERE ORDERID = '%s'",a));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
               return rs.getDouble(4);
            }
        }catch (SQLException ex) {
           // JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }
        
        public double getTotalCalories(String a){//this is for sign up student
        
        try{
            stmt = conn.prepareStatement(String.format("SELECT * FROM ORDERS WHERE ORDERID = '%s'",a));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
               return rs.getDouble(5);
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
