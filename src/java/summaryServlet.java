/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import da.CartDA;
import da.MealDA;
import da.OrderLineDA;
import da.OrdersDA;
import domain.Cart;
import domain.Meal;
import domain.OrderLine;
import domain.Orders;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.servlet.http.HttpSession;
import da.StudentDA;
import domain.Student;



/**
 *
 * @author Lim Ji-Siang
 */
public class summaryServlet extends HttpServlet {

    private CartDA cartDA;
    private MealDA mealDA;
    private OrderLineDA orderLineDA;
    private OrdersDA ordersDA;
    private StudentDA studentDA; 
    
    
    public void init() throws ServletException{
        cartDA = new CartDA();
        mealDA= new MealDA();
        orderLineDA = new OrderLineDA();
        ordersDA = new OrdersDA();
        studentDA = new StudentDA();
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        String name=(String)session.getAttribute("username");
        ArrayList<Meal> arrayB = mealDA.getMeal();
         ArrayList<Cart> arrayL = cartDA.getCart(name);
         
         
         String couponID = "C1001";
         String orderID = "G1001";
         String status = "U";
         double totalPrice = 0;
         double totalCalories = 0;
         
     java.util.Date uDate = new java.util.Date();
     java.sql.Date sDate = new java.sql.Date(uDate.getTime());
               
        for(int i=0; i<arrayL.size();i++){
                       for(int y=0; y<arrayB.size();y++){
                           if(arrayL.get(i).getMealID().equals(arrayB.get(y).getMealId())){
         totalPrice = totalPrice + (arrayL.get(i).getQuantity() * arrayB.get(y).getMealPrice());
                  totalCalories = totalCalories + arrayB.get(y).getCalories();
                               
    }          
                       }
        }
        double points = studentDA.SgetPoints(name);
        
        if(points >= totalPrice){
        ArrayList<Orders> arrayN = ordersDA.getRecord();

        if(arrayN.isEmpty()){
            Orders O = new Orders(orderID,name,sDate,totalPrice,totalCalories);
            ordersDA.addRecord(O);
            
        }
        else{
            String orderID2 = arrayN.get(arrayN.size()-1).getOrderID();
            String orderID3 = orderID2.substring(1);
            int nextOrder = Integer.parseInt(orderID3) + 1;
            String newOrder = "G" + nextOrder;
            Orders k = new Orders(newOrder,name,sDate,totalPrice,totalCalories);
            ordersDA.addRecord(k);
        }
        
        
        ArrayList<OrderLine> arrayD = orderLineDA.getRecord();
            for(int i=0; i<arrayL.size();i++ ){
                Random random = new Random();
                int x = random.nextInt(9000) + 1000;
                String newCoupon = "C" + x;
          OrderLine m = new OrderLine(newCoupon,ordersDA.getOrderID(name),arrayL.get(i).getMealID(),arrayL.get(i).getCollectDate(),status,arrayL.get(i).getQuantity());
          orderLineDA.addRecord(m);
          out.println(newCoupon);
            out.println(ordersDA.getOrderID(name));
            out.println(arrayL.get(i).getMealID());
            out.println(arrayL.get(i).getCollectDate());
            out.println(status);
            out.println(arrayL.get(i).getQuantity());
                       }
           cartDA.deleteAllCart();
           //update credit point 
           double points2 = points - totalPrice;
           Student a = new Student(name,points2);
           studentDA.SupdateCreditPoints(a);
           
        response.sendRedirect("addOrderSuccess.jsp");
        
          }
        else{
            response.sendRedirect("InsufficientBalance.jsp");
        
    }
    }
}

    

    
          


    

