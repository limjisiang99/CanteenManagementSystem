<%-- 
    Document   : addMeal
    Created on : Mar 26, 2019, 9:22:21 PM
    Author     : NGO KIAN HEE
--%>
<%@page import="domain.StaffCanteenAccount"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.Ingredient"%>
<%@page import="control.IngredientControl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="staffHeaderFooter.css" type="text/css">

        <title>Add new meal</title>
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
                   <a class="active" href="addMeal.jsp">Add new meal</a>
                   <a href="retrieveMeals">Meal Management</a>
                   <a href="staffViewOrders.jsp">View Order</a>
                   <a href="staffViewIngredientNeed.jsp">View Ingredient Need</a>
                   <a href="TopUpCredit.jsp">Top Up Credit</a>
                   <a href="FoodRedeem.jsp">Food Redeem</a>
                   <a href="staffGenerateReport.jsp">Generate Report</a>
                   
               </div>
  
               <div class="formCenter">
                   
        <form method="post" action="AddMealServlet" enctype="multipart/form-data">
             <% String message = (String)request.getAttribute("mealAddedMsg");
                if(message!=null)
                  out.println("<br><font color=blue>" + message + "</font>");   
                %>
            <table>
                <h1>Add new meal</h1>
                <tr>
                    <td>Meal Name</td>
                    <td><input type="text" name="mealName" required="required"></td>
                </tr>
                <tr>
                    <td>Meal Price</td>
                    <td><input type="number" name="mealPrice" min=0 step="any" required="required"></td>
                </tr>
                <tr>
                    <td>Meal Description</td>
                    <td><input type="text" name="mealDesc" required="required"></td>
                </tr>
                <tr>
                    <td>Meal Calories</td>
                    <td><input type="number" name="calories" min=0 step="any" required="required"></td>
                </tr>
                <tr>
                    <td>Meal Picture</td>
                    <td><input type="file" name="mealPictures" required="required"></td>
                </tr>
                <tr>
                    <td>Meal Session</td>
                    <td><input type="radio" name="mealSession" value="Breakfast" required="required">Breakfast
                        <input type="radio" name="mealSession" value="Lunch" required="required">Lunch
                    </td>                 
                </tr>
                <tr>
                    <th><u>Ingredients</u></th>
                </tr>
                <%  IngredientControl ingredientControl = new IngredientControl();
                    ArrayList<Ingredient> arrayIngredient = ingredientControl.getIngredient();
                    for(int i = 0 ; i <arrayIngredient.size() ; i ++){
                %>
                <tr>
                    <td><%=arrayIngredient.get(i).getIngredientName()%></td>
                    <td><input type="number" name="<%=arrayIngredient.get(i).getIngredientId()%>" value=0 min=0></td>                        
                </tr>
                <%
                }%>
                
            </table>
              
            <input type="submit" value="Add">
           
        </form>
                  </div>
    </body>
</html>
