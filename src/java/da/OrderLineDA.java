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
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class OrderLineDA {
     private String host = "jdbc:derby://localhost:1527/CMS";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "ORDERLINE";
    private String sqlInsertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?, ?, ?)";
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    
     public OrderLineDA(){
        createConnection();
    }
    
     
        public int addRecord(OrderLine a) {
        try {
            stmt = conn.prepareStatement(sqlInsertStr);
            stmt.setString(1,a.getCouponId());
            stmt.setString(2,a.getOrderId());
            stmt.setString(3,a.getMealId());
            stmt.setDate(4,a.getOrderRedeemDate());
            stmt.setString(5,a.getOrderStatus());
            stmt.setInt(6, a.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            return 1;
        }
        return 0;
        }
        
       public OrderLine getCurrentRecord() {
        OrderLine orderLine = null;
        try {
            orderLine = new OrderLine(rs.getString(1), rs.getString(2), rs.getString(3),rs.getDate(4),rs.getString(5),rs.getInt(6));
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return orderLine;
    }
    
      public ArrayList<OrderLine> getRecord() {
        ArrayList<OrderLine> orderLine = new ArrayList<OrderLine>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM ORDERLINE ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                orderLine.add(getCurrentRecord());
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return orderLine;
    }
      
       
      
      public ArrayList<OrderLine> getRecordLine() {
        ArrayList<OrderLine> orderLine = new ArrayList<OrderLine>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM ORDERLINE WHERE ORDERSTATUS = 'U' ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                orderLine.add(getCurrentRecord());
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return orderLine;
    }
      
      public ArrayList<OrderLine> getRecordWithOrderID(String OrderID) {
        ArrayList<OrderLine> orderLine = new ArrayList<OrderLine>();
        try {
            stmt = conn.prepareStatement(String.format("SELECT * FROM ORDERLINE WHERE ORDERID = '%s' ", OrderID));
            rs = stmt.executeQuery();
            while (rs.next()) {
                orderLine.add(getCurrentRecord());
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return orderLine;
    }
      
      public ArrayList<OrderLine> getSummaryReport() {
        ArrayList<OrderLine> orderLine = new ArrayList<OrderLine>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM ORDERLINE WHERE ORDERSTATUS = 'R' AND ORDERREDEEMDATE between '2019-04-01' AND '2019-04-30'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                orderLine.add(getCurrentRecord());
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return orderLine;
    }
      
      
      public ArrayList<OrderLine> getTotalExpenses() {
        ArrayList<OrderLine> orderLine = new ArrayList<OrderLine>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM ORDERLINE WHERE ORDERSTATUS = 'U' OR ORDERSTATUS = 'R'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                orderLine.add(getCurrentRecord());
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return orderLine;
    }
      
      public ArrayList<OrderLine> getTotalExpenses2() {
        ArrayList<OrderLine> orderLine = new ArrayList<OrderLine>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM ORDERLINE WHERE ORDERSTATUS = 'R'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                orderLine.add(getCurrentRecord());
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return orderLine;
    }
      
      
      
      
       public void UpdateOrderLine(String couponID){
        try{
            stmt=conn.prepareStatement("UPDATE ORDERLINE SET ORDERSTATUS = 'C' WHERE COUPONID = ? ");
            stmt.setString(1,couponID);
            stmt.executeUpdate();
        }catch(SQLException ex){
            ex.getMessage();
        }
    }
       
      public void ModifyOrderLine(int Quantity, String CouponID, Date date){
        try{
            stmt=conn.prepareStatement("UPDATE ORDERLINE SET QUANTITY = ?, ORDERREDEEMDATE = ? WHERE COUPONID = ? ");
            stmt.setInt(1, Quantity);
            stmt.setDate(2, date);
            stmt.setString(3,CouponID);
            stmt.executeUpdate();
        }catch(SQLException ex){
            ex.getMessage();
        }
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
