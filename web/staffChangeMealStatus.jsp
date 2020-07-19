<%-- 
    Document   : staffChangeMealStatus
    Created on : Apr 9, 2019, 6:27:53 PM
    Author     : NGO KIAN HEE
--%>

<%@page import="domain.Meal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.StaffCanteenAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="staffHeaderFooter.css" type="text/css">

        <title>Change Meal Status</title>
    
    <style>
        .backButton a{
    text-decoration:none;
    border: none;
    outline: none;
    height: 45px;
    padding: 13px 17px;
    background: #a81c41;
    color:#fff;
    font-size: 18px;
    border-radius: 20px;
}
.backButton a:hover{
    cursor: pointer;
    background: #eda1b5;
    color:#000;
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
               <a class="active" href="StaffHomePage.jsp">Home</a>
               <a href="staffUpdateDetails.jsp">Update Personal Information</a>
               <a href="staffChangePassword.jsp">Change Password</a>
               <a href="StaffLogoutServlet">Logout</a>
               </div>  
        </div>
                   <div class="navBar">
                   <a href="addMeal.jsp">Add new meal</a>
                   <a href="retrieveMeals">Meal Management</a>
                   <a href="staffViewOrders.jsp">View Order</a>
                   <a href="TopUpCredit.jsp">Top Up Credit</a>
                   <a href="FoodRedeem.jsp">Food Redeem</a>
                   <a href="staffGenerateReport.jsp">Generate Report</a>
                   
               </div>
               <div class="formCenter">
    <form method="get" action="staffChangeMealStatusServlet">
        <h1>Meal Modify</h1>
    <table>
        <%  
            ArrayList<Meal> arrayListMeal = (ArrayList<Meal>)session.getAttribute("Meals");
            String mealId = request.getParameter("mealId");
            
            for(int i = 0 ; i <arrayListMeal.size(); i ++){
                if(mealId.equals(arrayListMeal.get(i).getMealId())){
        %>     
         <tr>
             <td>Meal ID:</td>
             <td><input type="hidden" value="<%=arrayListMeal.get(i).getMealId()%>" name="mealId">
                 <%=arrayListMeal.get(i).getMealId()%></td>
        </tr>
        <tr>
             <td>Meal Name:</td>
             <td><input type="hidden" value="<%=arrayListMeal.get(i).getMealName()%>" name="mealName">
                 <%=arrayListMeal.get(i).getMealName()%></td>
        </tr>
        <tr>
        <td>Meal Status:</td>
             <%if(arrayListMeal.get(i).getMealStatus().equals("Available")) {
                 %>
                  <td><input type="radio" value="<%=arrayListMeal.get(i).getMealStatus()%>" name="mealStatus" checked required>Available
                  <input type="radio" value="Unavailable" name="mealStatus" required>Unavailable</td>
                  <%
             }else{
                  %>
                  <td><input type="radio" value="Available" name="mealStatus" required>Available
                  <input type="radio" value="<%=arrayListMeal.get(i).getMealStatus()%>" name="mealStatus" checked required>Unavailable</td>
                  <%
            }%>
        <tr>
             <td><input type="submit" value="Update Status"required></td>
             <td><p class="backButton"><a href=retrieveMeals>Back</a></p></td>
        </tr>
        <%                
        }
    }
        %>
        </table>
                

             <% String message = (String)request.getAttribute("mealStatusMessage");
                if(message!=null)
                  out.println(message);   
             %>
    
    </form>
    </div>
    </body>
</html>
