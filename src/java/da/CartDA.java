package da;
import domain.Cart;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartDA {
private String host = "jdbc:derby://localhost:1527/CMS";
private String user = "nbuser";
private String password = "nbuser";
private String tableName = "CART";
private Connection conn;
private PreparedStatement stmt;
private String sqlQueryStr = "SELECT * FROM " + tableName;
private String sqlInsertStr = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?)";
private ResultSet rs;
  
public CartDA() {
        try {
            conn = DriverManager.getConnection(host, user, password);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
    public Cart getCurrentRecord() {
        Cart cart = null;
        try {
            cart = new Cart(rs.getString(1),rs.getString(2),rs.getDate(3),rs.getInt(4));
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return cart;
    }
        public int addRecord(Cart a) {
        try {
            stmt = conn.prepareStatement(sqlInsertStr);
            stmt.setString(1,a.getUsername());
            stmt.setString(2,a.getMealID());
            stmt.setDate(3,a.getCollectDate());
            stmt.setInt(4,a.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            return 1;
        }
        return 0;
        }
        
        public void updateCart(String username,String MealID,int quantity,Date collectDate){
            try{
               stmt = conn.prepareStatement("UPDATE CART SET QUANTITY = ? WHERE MEALID = ? , USERNAME = ?, OrderRedeemDate = ?");
               stmt.setInt(1,quantity);
               stmt.setString(2,MealID);
               stmt.setString(3,username);
               stmt.setDate(4, collectDate);
               stmt.executeUpdate();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        public ArrayList<Cart> getCart(String username) {
        ArrayList<Cart> cart = new ArrayList<Cart>();
        try {
            stmt = conn.prepareStatement(sqlQueryStr + " WHERE USERNAME ='" +username+ "'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                cart.add(getCurrentRecord());
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return cart;
    }
         
    
    public void deleteCart(String MealID, Date collectDate){
        try{
            stmt=conn.prepareStatement("DELETE FROM CART WHERE MEALID = ? AND ORDERREDEEMDATE = ?");
            stmt.setString(1,MealID);
            stmt.setDate(2, collectDate);
            stmt.executeUpdate();
        }catch(SQLException ex){
            ex.getMessage();
        }
    }
    
    public void deleteAllCart(){
        
        try{
            stmt=conn.prepareStatement("DELETE FROM CART ");
            stmt.executeUpdate();
        }catch(SQLException ex){
            ex.getMessage();
        }
        
    }
}