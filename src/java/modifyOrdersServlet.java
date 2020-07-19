/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import da.MealDA;
import da.OrderLineDA;
import da.OrdersDA;
import da.StudentDA;
import domain.Meal;
import domain.OrderLine;
import domain.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lim Ji-Siang
 */
public class modifyOrdersServlet extends HttpServlet {

      private OrderLineDA orderLineDA;
      private OrdersDA ordersDA;
      private MealDA mealDA;
      private StudentDA studentDA;
      
      public void init() throws ServletException {
        orderLineDA = new OrderLineDA();
        ordersDA = new OrdersDA();
        mealDA = new MealDA();
        studentDA = new StudentDA();
    }
      
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        String name=(String)session.getAttribute("username");
        String couponID = request.getParameter("couponID");
        String orderID = request.getParameter("orderID");
        int quantity = Integer.parseInt(request.getParameter("quantity")); 
        int oldQuantity = Integer.parseInt(request.getParameter("oldQuantity"));
        String collectDate = request.getParameter("newDate");
        String mealID = request.getParameter("mealID");
        Date date = Date.valueOf(collectDate);
                    OrderLine a = new OrderLine(quantity,couponID);
                    double points = studentDA.SgetPoints(name);

                    //ArrayList<Orders> arrayA = ordersDA.getOrdersID(name);
              ArrayList<OrderLine> arrayB = orderLineDA.getRecordWithOrderID(orderID);
                    ArrayList<Meal> arrayC = mealDA.getMeals();
                    ArrayList<Orders> arrayZ = ordersDA.getRecord();
                    double totalPrice = ordersDA.getTotalPrice(orderID);
                    double totalCalories = ordersDA.getTotalCalories(orderID);
                    
                    double price = 0;
                    double calories = 0;
                    int num2 = 0;
                    int found =0;
                     
                       for(int y=0; y<arrayC.size();y++){
                           if(arrayC.get(y).getMealId().equals(mealID)){
                               if(quantity > oldQuantity){
                                   if(points >= ((quantity * arrayC.get(y).getMealPrice()) - (oldQuantity * arrayC.get(y).getMealPrice()))){
                                      out.println(mealID);
                                       out.println(arrayC.get(y).getMealPrice());
                                     out.println(totalPrice);
                                     out.println((quantity * arrayC.get(y).getMealPrice()));
                                     out.println((oldQuantity * arrayC.get(y).getMealPrice()));
                                     totalPrice += (quantity * arrayC.get(y).getMealPrice()) - (oldQuantity * arrayC.get(y).getMealPrice());
                                     totalCalories += (quantity * arrayC.get(y).getCalories()) - (oldQuantity * arrayC.get(y).getCalories());
                                      out.println(totalPrice);
                                      out.println(orderID);
                                     ordersDA.UpdateTotalPrice(orderID, totalPrice,totalCalories);
                                    
                                   }
                                   else{
                                        num2++;
                                   }
                               }
                               else if(quantity < oldQuantity){
                                   totalPrice -= (oldQuantity * arrayC.get(y).getMealPrice()) - (quantity * arrayC.get(y).getMealPrice());
                                   totalCalories += (quantity * arrayC.get(y).getCalories()) - (oldQuantity * arrayC.get(y).getCalories());
                                   ordersDA.UpdateTotalPrice(orderID, totalPrice,totalCalories);
                                    found=1;
                               }
                           }
                           
                   
                       
                       }
                 
                 
                    int num1 = 0;
                     
                     for(int i = 0; i<arrayB.size();i++){
                     for(int y = 0; y<arrayC.size();y++){
                         if(arrayB.get(i).getCouponId().equals(couponID)&&arrayB.get(i).getMealId().equals(arrayC.get(y).getMealId())){
                             if(oldQuantity < quantity){
                                double price2 =  arrayC.get(y).getMealPrice();
                                int newQuantity = quantity - oldQuantity;                  
                                double price3 = price2 * newQuantity;
                                if(price3 > points){
                                    num1++;
                                }
                                else{
                                double newpoints = points - price3;
                                studentDA.SupdateCreditPoints2(name, newpoints);
                                }
                             }
                             if(oldQuantity > quantity){
                             double price2 = arrayC.get(y).getMealPrice();
                             int newQuantity = oldQuantity - quantity;
                             double price3 = price2 * newQuantity;
                             double newpoints = points + price3;
                             studentDA.SupdateCreditPoints2(name, newpoints);
                         }
                     }
    }
}
                     if(num1 == 1 || num2 == 1){
                         response.sendRedirect("InsufficientBalance.jsp");
                     }
                     else{
                         
                         orderLineDA.ModifyOrderLine(quantity,couponID,date);
              response.sendRedirect("successfullyModifiedOrder.jsp");       
                     }
}
}
