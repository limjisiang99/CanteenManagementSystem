<%-- 
    Document   : staffViewMeals
    Created on : Mar 30, 2019, 2:19:04 AM
    Author     : NGO KIAN HEE
--%>

<%@page import="java.util.Base64"%>
<%@page import="control.MealControl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.Meal"%>
<%@page import="domain.StaffCanteenAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="staffHeaderFooter.css" type="text/css">

        <title>View Meals</title>
    </head>
    <style>     
        .thisPage{
            text-decoration:underline;
            font-family:Monotype Corsiva;
            color:#373738;
        }
        nav li{
            display: inline;
        }
        nav a{
            text-decoration: none;
            font-family:Monotype Corsiva;
            font-size:35px;
            color:#373738;
            padding-left:12px;
            padding-right:12px;
            margin-bottom: 10px;
            text-decoration:none;
        }
        nav a:hover{
           background:#c2cfd3;
        }
        .modifyButton{
            font-size: 15px;
        }
    </style>
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
                
                     <h1 class="title">View Meals</h1>
       <nav>
           <ul>
               <li><a  href="retrieveMeals">All</a></li>
               <li><a href="staffViewBreakfastMeals.jsp">Breakfast</a></li>
               <li><a  href="staffViewLunchMeals.jsp">Lunch</a></li>
               <li><a  class="thisPage" href="staffViewMealByPrice.jsp">View By Price</a></li>
           </ul>
       </nav>
              <div class="formCenter">
          <form method="get" action="staffViewMealByPriceServlet">
              <h1>View Meal By Price</h1>
              <table>  
                <p>Please enter the credit point range.</p>
              <tr>
                  <td>Starting Range:</td>
                  <td><input type="number" min=0 required name="startRange"></td>
              </tr>
              <tr>
                  <td>Ending Range:</td>
                  <td><input type="number" min=0 name="endRange" required><td>
              </tr>
              <tr>
                  <td>
                      <input type="submit" value="Submit">
                  </td>
              </tr>
          </table>
               <%
                     String Message = (String)request.getAttribute("notFoundMessage");
                     if(Message!=null){
                         out.println(Message);
                  }%>
               </form>
                 
                  
                     </div> 
                     <% 
                     ArrayList<Meal> arrayListMeal = (ArrayList<Meal>)request.getAttribute("viewMealByPrice");
                     String startRange = (String)request.getAttribute("startRange");
                     String endRange = (String)request.getAttribute("endRange");  
                    if(arrayListMeal!=null){
                       %>
                       <div class="displayMeals">
                           <table align="center">
                               <caption><h1>Price Range From <%=startRange%> Credit Point  To <%=endRange%> Credit Point </h1></caption>
                           <%
                    for(int i = 0 ; i <arrayListMeal.size(); i ++){%>
                      
                         <% 
                             byte[] mealPicture = arrayListMeal.get(i).getMealPicture();
                             String encode = Base64.getEncoder().encodeToString(mealPicture);
                             request.setAttribute("productImage", encode); %>
                       <td> 
                           <img src="data:image/jpeg;base64,${productImage}" width="300px" height="300px">  
                        </td>
                        <td><p><b>Meal ID : </b><%=arrayListMeal.get(i).getMealId()%></p>
                        <p><b>Meal Name : </b><%=arrayListMeal.get(i).getMealName()%></p>
                        <p><b>Meal Price : </b><%=String.format("%.2f",arrayListMeal.get(i).getMealPrice())%></p>
                        <p><b>Meal Calories : </b><%=String.format("%.2f",arrayListMeal.get(i).getCalories())%></p>
                        <p><b>Session : </b><%=arrayListMeal.get(i).getMealSession()%></p>
                        <p><b>Status : </b><%=arrayListMeal.get(i).getMealStatus()%></p>    
                        <p><%=arrayListMeal.get(i).getMealDesc()%></p>
                        <a class="modifyButton" href="modifyMeal.jsp?method=post&mealId=<%=arrayListMeal.get(i).getMealId()%>">Modify</a>
                        <a class="modifyButton" href="staffChangeMealStatus.jsp?method=post&mealId=<%=arrayListMeal.get(i).getMealId()%>">Status</a>
                        <a class="modifyButton" href="staffViewMealIngredient.jsp?method=post&mealId=<%=arrayListMeal.get(i).getMealId()%>">View Ingredient</a>
                       </td>
                       
                    <%  
                        if((i+1)%2==0){
                        out.println("</tr><tr></tr>");
                    }}
                    %>
                                </table>
                       </div><%
                 %>
                  

                
         
    
               
                <%}
          %>
        
                   
               
    </body>
</html>
