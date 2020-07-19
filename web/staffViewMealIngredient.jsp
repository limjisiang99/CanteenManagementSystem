<%-- 
    Document   : staffViewMealIngredient
    Created on : Apr 8, 2019, 11:59:26 PM
    Author     : USER
--%>

<%@page import="domain.StaffCanteenAccount"%>
<%@page import="domain.MealIngredient"%>
<%@page import="control.IngredientControl"%>
<%@page import="domain.Ingredient"%>
<%@page import="control.MealIngredientControl"%>
<%@page import="java.util.Base64"%>
<%@page import="domain.Meal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="staffHeaderFooter.css" type="text/css">

        <title>Meal Ingredient</title>
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
                   <a class="active"href="retrieveMeals">Meal Management</a>
                   <a href="staffViewOrders.jsp">View Order</a>
                   <a href="staffViewIngredientNeed.jsp">View Ingredient Need</a>
                   <a href="TopUpCredit.jsp">Top Up Credit</a>
                   <a href="FoodRedeem.jsp">Food Redeem</a>
                   <a href="staffGenerateReport.jsp">Generate Report</a>
                   
               </div>
               <table align="center">
                   <caption class="title"><h1>View Meal Ingredient</h1></caption>  
                <% ArrayList<Meal> arrayListMeal = (ArrayList<Meal>)session.getAttribute("Meals");
                   IngredientControl ingredientControl = new IngredientControl();
                   ArrayList<Ingredient> arrayIngredient = ingredientControl.getIngredient();
                   MealIngredientControl mealIngredientControl = new MealIngredientControl();
                   ArrayList<MealIngredient> arrayMealIngredient = mealIngredientControl.getMealIngredient(); 
                   String mealId = request.getParameter("mealId");
                   for(int i = 0 ; i <arrayListMeal.size(); i ++){
                       if(arrayListMeal.get(i).getMealId().equals(mealId)){
                        byte[] mealPicture = arrayListMeal.get(i).getMealPicture();
                        String encode = Base64.getEncoder().encodeToString(mealPicture);
                        request.setAttribute("productImage", encode);%>
                        <tr><td><img src="data:image/jpeg;base64,${productImage}" width="350px" height="350px"></td></tr> 
                        <tr><td><p><b>Meal ID : </b><%=arrayListMeal.get(i).getMealId()%></p>
                        <p><b>Meal Name : </b><%=arrayListMeal.get(i).getMealName()%></p></td></tr> 
                <%  }
                 }%>
               
                   <%   out.println("<tr><td><b><u>Ingredient Needed</b></u></td><tr><td>");
                        for(int i2 = 0; i2 < arrayMealIngredient.size() ; i2++){
                             if(arrayMealIngredient.get(i2).getMealId().equals(mealId)){
                                for(int i3 = 0 ;i3 < arrayIngredient.size() ; i3++){
                                    if(arrayMealIngredient.get(i2).getIngredientId().equals(arrayIngredient.get(i3).getIngredientId())){
                                        out.println("<p><b>"+arrayIngredient.get(i3).getIngredientName()+" : </b>");
                                        out.println(arrayMealIngredient.get(i2).getQuantityNeeded()+"</p>");
                                        
                                    }
                                }
                            }
                        }
                        out.println("</td></tr>");
                    %>
                                       
               </table>
            <table align="center">
                 <tr>
                 <td> <a class=modifyButton href=retrieveMeals>Back</a></td></tr>
             </table>
                
    </body>
</html>
