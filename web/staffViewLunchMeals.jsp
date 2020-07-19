<%-- 
    Document   : staffViewLunchMeals
    Created on : Apr 8, 2019, 7:36:47 PM
    Author     : USER
--%>

<%@page import="domain.StaffCanteenAccount"%>
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
        <title>JSP Page</title>
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
                <div class="displayMeals">
    
       
       <h1 class="title">View Meals</h1>
       <nav>
           <ul>
               <li><a href="retrieveMeals">All</a></li>
               <li><a href="staffViewBreakfastMeals.jsp">Breakfast</a></li>
               <li><a class="thisPage" href="staffViewLunchMeals.jsp">Lunch</a></li>
               <li><a href="staffViewMealByPrice.jsp">View By Price</a></li>
           </ul>
       </nav>
       
       <table>
           <tr>
                <% ArrayList<Meal> arrayListMeal = (ArrayList<Meal>)session.getAttribute("Meals");
                    int count = 0;
                    for(int i = 0 ; i <arrayListMeal.size(); i ++){
                            if(arrayListMeal.get(i).getMealSession().equals("Lunch")){ 
                                %>
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
                        count++;
                        if(count%2==0){
                        out.println("</tr><tr></tr>");
                    
                        }  }

                    }
                %>
                
         
    </table>
                </div>
    </body>
</html>
