<%-- 
    Document   : staffViewIngredientNeed
    Created on : Apr 5, 2019, 3:11:17 AM
    Author     : NGO KIAN HEE
--%>

<%@page import="domain.Query"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.StaffCanteenAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="staffHeaderFooter.css" type="text/css">
        <title>Staff View Ingredient Need</title>
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
                   <a href="staffViewOrders.jsp">View Order</a>
                   <a class="active" href="staffViewIngredientNeed.jsp">View Ingredient Need</a>
                   <a href="TopUpCredit.jsp">Top Up Credit</a>
                   <a href="FoodRedeem.jsp">Food Redeem</a>
                   <a href="staffGenerateReport.jsp">Generate Report</a>
                   
               </div>
                 <div class="formCenter">
          <form method="get" action="staffViewIngredientNeed">
              <h1>View Total Ingredients</h1>
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
                       ArrayList<Query> ingredients= (ArrayList<Query>)request.getAttribute("ingredients");
                       if(ingredients != null){
                           out.println("<table align=center><caption>Total Ingredient Need<br>Order Date:"+request.getAttribute("orderDate2")+"</caption>");
                           int totalIngredient=0;
                            out.println("<tr><th>Ingredient ID</th><th>Ingredient Name</th><th>Quantity Need</th></tr>");
                           for(int i =0 ; i<ingredients.size() ; i++){
                        out.println("<tr><td>"+ingredients.get(i).getIngredientId()+"</td>");
                        out.println("<td>"+ingredients.get(i).getIngredientName()+"</td>");
                        out.println("<td>"+ingredients.get(i).getIngredientNeeded()+"</td></tr>");
                        //totalIngredient+=ingredients.get(i).getIngredientNeeded();
                        }
                            //out.println("<tr><td></td><td>Total Ingredient Needed:</td><td>"+totalIngredient+"</td></tr></table>");
                       }
                   %>
       </div>      
    </body>
</html>
