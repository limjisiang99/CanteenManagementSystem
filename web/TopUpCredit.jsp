<%-- 
    Document   : TopUpCredit
    Created on : Mar 31, 2019, 1:00:18 AM
    Author     : NGO KIAN HEE
--%>

<%@page import="domain.Student"%>
<%@page import="domain.StaffCanteenAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="staffHeaderFooter.css" type="text/css">
        <title>Top Up Credit</title>
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
                   <a href="staffViewIngredientNeed.jsp">View Ingredient Need</a>
                   <a class="active"href="TopUpCredit.jsp">Top Up Credit</a>
                   <a href="FoodRedeem.jsp">Food Redeem</a>
                   <a href="staffGenerateReport.jsp">Generate Report</a>
                   
               </div>
        <div class="formCenter">     
        <form method="post" action="TopUpTransactionServlet">
             <h1>Top Up Credit</h1>
            <table>
                <tr>
                    <td>Student Username:</td>
                    <td><input type="text" name="studUsername" required></td>
                </tr>
                <tr>
                    <td>Top Up amount:</td>
                    <td><input type="number" name="amount" required></td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Top Up">
                    </td>
                </tr>
            </table>
           <% String topUpMessage = (String)request.getAttribute("topUpMessage");
              Student studInfo = (Student)request.getAttribute("studInfo");
                if(topUpMessage!=null){
                     out.println(topUpMessage);
                     if(studInfo != null){
                          out.println("<p><b>Top Up Information</b><br>");
                          out.println("-------------------------------</p>");
                          out.println("<p><b>Student Username : </b>" +studInfo.getStudUsername()+"</p>");
                          out.println("<p><b>Student Credit Point Before : </b>" +(studInfo.getCreditPoint()-Double.parseDouble ((String)request.getAttribute("topUpAmount")))+"</p>");
                          out.println("<p><b>Top Up Amount (RM) : </b>"+ Double.parseDouble ((String)request.getAttribute("topUpAmount"))+"</p>");
                          out.println("<p><b>Student Credit Point After : </b>"+studInfo.getCreditPoint()+"</p>");
                     }
                }
          %>
        </form>
    
          </div>
    </body>
</html>
