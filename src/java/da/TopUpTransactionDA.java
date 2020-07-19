/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.TopUpTransaction;
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
public class TopUpTransactionDA {
    private String host = "jdbc:derby://localhost:1527/CMS";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "TopUpTransaction";
    private Connection conn;
    private PreparedStatement stmt;
    private String sqlQueryStr = "SELECT * FROM " + tableName;
    private String sqlInsertStr = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?)";
    private ResultSet rs;
    public TopUpTransactionDA() {
        createConnection();
    }
        public void addRecord(TopUpTransaction transaction) {
        try {
            stmt = conn.prepareStatement(sqlInsertStr);
            stmt.setString(1,transaction.getTopUpId());
            stmt.setString(2,transaction.getStudUsername());
            stmt.setString(3,transaction.getStaffUsername());
            stmt.setDouble(4,transaction.getAmount());
            stmt.setDate(5,transaction.getDate());
            stmt.executeUpdate();
            
        }catch (SQLException ex) {
            ex.getMessage();
            }
        }
        public String getLastTopUpTransactionId(){
            String lastTopUpTransactionId=null;
            try{
            stmt = conn.prepareStatement("SELECT * FROM TopUpTransaction");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
               lastTopUpTransactionId= rs.getString("TopUpId" );
            }   
        }catch (SQLException ex) {
               ex.getMessage();
        }
               return lastTopUpTransactionId;
        }
        public TopUpTransaction getCurrentRecord() {
        TopUpTransaction transaction = null;
        try {
           transaction = new TopUpTransaction(rs.getString(1), rs.getString(2), rs.getString(3),rs.getDouble(4),rs.getDate(5));
        
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return transaction;
    }
        
        public ArrayList<TopUpTransaction> getTransactions(String startingDate , String endingDate) {
        ArrayList<TopUpTransaction> transaction = new ArrayList<TopUpTransaction>();
        try {
            stmt = conn.prepareStatement("SELECT * FROM TopUpTransaction WHERE date between ? and ?");
            stmt.setString(1,startingDate);
            stmt.setString(2,endingDate);
            rs = stmt.executeQuery();
            while (rs.next()) {
                transaction.add(getCurrentRecord());
            }
         return transaction;

        } catch (SQLException ex) {
            ex.getMessage();
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


