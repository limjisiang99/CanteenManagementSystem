<%-- 
    Document   : staffViewOrders
    Created on : Apr 4, 2019, 1:21:59 AM
    Author     : NGO KIAN HEE
--%>

<%@page import="domain.Query"%>
<%@page import="domain.Order"%>
<%@page import="domain.OrderLine"%>
<%@page import="domain.Meal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.StaffCanteenAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="staffHeaderFooter.css" type="text/css">
        <title>View Total Meals Order</title>
        <style>
        .displayOrder{
            margin-top:20px;
            margin-left:auto;
            margin-right:auto;
        }
        .displayOrder table{
            text-align: center;
            border:1px solid #000;
        }
    </style>
    </head>
    
    <body>
        
       <%
            StaffCanteenAccount staffCA =(StaffCanteenAccount)session.getAttribute("staff"); 
           // String name = (String)session.getAttribute()
        %>
        <div class="header">
                <h1>Hot Café</h1>
               <div class="header-right">
               <p>Welcome back, <%=staffCA.getStaffName()%></p>   
               <a href="StaffHomePage.jsp">Home</a>
               <a href="staffUpdateDetails.jsp">Update Personal Information</a>
               <a href="staffChangePassword.jsp">Change Password</a>
               <a href="StaffLogoutServlet">Logout</a>
               </div>  
        </div>
                   <div class="navBar">
                   <a href="addMeal.jsp">Add new meal</a>
                   <a href="retrieveMeals">Meal Management</a>
                   <a class="active" href="staffViewOrders.jsp">View Order</a>
                   <a href="staffViewIngredientNeed.jsp">View Ingredient Need</a>
                   <a href="TopUpCredit.jsp">Top Up Credit</a>
                   <a href="FoodRedeem.jsp">Food Redeem</a>
                   <a href="staffGenerateReport.jsp">Generate Report</a>
                   
               </div>
                 <div class="formCenter">
          <form method="get" action="staffViewOrders">
              <h1>View Total Meals Order</h1>
              <table>  
                <p>Please select a date.</p>
              <tr>
                  <td>Order Date:</td>
                  <td><input type="date" required name="orderDate"></td>
              </tr>
              <tr>
                  <td>
                      <input type="submit" value="View">
                  </td>
              </tr>
          </table>
                  <% String Message = (String)request.getAttribute("Message");
                           if(Message!= null){
                           out.println("<font color=red>"+Message+"</font>");
                       }%>
          </form>
               </div>
               <div class="displayOrder">
                   <%
                       ArrayList<Meal> mealProfit = (ArrayList<Meal>)session.getAttribute("Meals");
                       ArrayList<Query> totalMealsOrder= (ArrayList<Query>)request.getAttribute("orders");
                       if(totalMealsOrder != null){
                           out.println("<table align=center><caption><b>Total Meals Order</b><br>Order Date:<u>"+request.getAttribute("orderDate2")+"</u></caption>");
                           int totalOrdered=0;
                            out.println("<tr><th>Meal ID</th><th>Meal Name</th><th>Quantity Ordered</th></tr>");
                           for(int i =0 ; i<totalMealsOrder.size() ; i++){
                        out.println("<tr><td>"+totalMealsOrder.get(i).getMealId()+"</td>");
                        out.println("<td>"+totalMealsOrder.get(i).getMealName()+"</td>");
                        out.println("<td>"+totalMealsOrder.get(i).getQuantityOrdered()+"</td></tr>");
                        totalOrdered+=totalMealsOrder.get(i).getQuantityOrdered();
                        }
                             out.println("<tr><td></td><td><b>Total Ordered:</b></td><td><u>"+totalOrdered+"</u></td></tr></table>");
                       }
                       
                        if(totalMealsOrder != null){
                           out.println("<br>");
                           out.println("<table align=center><caption><b><u>Total Profit</b></u></caption>");
                           double profit = 0;
                           int totalOrdered=0;
                            out.println("<tr><th>Meal ID</th><th>Meal Name</th><th>Unit Price</th><th>Qty Ordered</th><th>Profit(RM)</th></tr>");
                           for(int b = 0 ; b <mealProfit.size();b++){
                               for(int i =0 ; i<totalMealsOrder.size() ; i++){
                                   if(mealProfit.get(b).getMealId().equals(totalMealsOrder.get(i).getMealId())){
                                        out.println("<tr><td>"+totalMealsOrder.get(i).getMealId()+"</td>");
                                        out.println("<td>"+totalMealsOrder.get(i).getMealName()+"</td>");
                                        out.println("<td>"+mealProfit.get(b).getMealPrice()+"</td>");
                                        out.println("<td>"+totalMealsOrder.get(i).getQuantityOrdered()+"</td>");
                                        out.println("<td>"+mealProfit.get(b).getMealPrice()*totalMealsOrder.get(i).getQuantityOrdered()+"</td></tr>");
                                        totalOrdered+=totalMealsOrder.get(i).getQuantityOrdered();
                                        profit+=mealProfit.get(b).getMealPrice()*totalMealsOrder.get(i).getQuantityOrdered();
                                   }
                               }
                             
                            }
                           out.println("<tr><td></td><td></td><td></td><td><b>Grand Total : </b></td><td><u>"+profit+"</u></td></tr></table>");
                        }
                            
                       
                       
                       /*int subQuantity = 0;
                       int grandTotalQty = 0;
                       String tempMeal = null;
                       
                       ArrayList<Meal> arrayListMeal = (ArrayList<Meal>)session.getAttribute("Meals");
                       ArrayList<Order> orders = (ArrayList<Order>)request.getAttribute("orders");
                       ArrayList<OrderLine> orderLineDetails = (ArrayList<OrderLine>)request.getAttribute("orderLineDetails");
                       String orderDate = (String)request.getAttribute("orderDate2");
                       if(orders!=null){                       
                       out.println("<h1>"+orderDate+"</h1>");
                       for(int i = 0 ; i < arrayListMeal.size() ; i++){
                           for(int i2 = 0 ; i2 < orders.size(); i2 ++){
                               for(int i3 = 0; i3 < orderLineDetails.size(); i3++){
                                   if(orders.get(i2).getOrderId().equals(orderLineDetails.get(i3).getOrderId())){
                                       if(orderLineDetails.get(i3).getMealId().equals(arrayListMeal.get(i).getMealId())){
                                           out.println("<p>Meal ID:"+arrayListMeal.get(i).getMealId()+"</p>");
                                           out.println("<p>Quantity:"+orderLineDetails.get(i3).getQuantity()+"</p>");
                                           if(arrayListMeal.get(i).getMealId().equals(orderLineDetails.get(i3).getMealId())){
                                               subQuantity+=orderLineDetails.get(i3).getQuantity();
                                               grandTotalQty +=orderLineDetails.get(i3).getQuantity();
                                           }
                                           else{
                                               out.println("<p>"+subQuantity+"</p>");
                                               subQuantity =0;
                                           }
                                       }
                                   }
                               }
                           }
                       }
                       }*/
                   
                   %>
       </div>      
    </body>
</html>
