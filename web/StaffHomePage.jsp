<%-- 
    Document   : StaffHomePage
    Created on : Mar 24, 2019, 6:01:08 PM
    Author     : NGO KIAN HEE
--%>

<%@page import="domain.StaffCanteenAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="staffHeaderFooter.css" type="text/css">

        <title>Staff Home Page</title>
        <style>
        th{
            text-decoration:underline;
            font-family:Monotype Corsiva;
            font-size:35px;
            color:#373738;
        }
        td a{
            text-decoration: none;
            font-family:Monotype Corsiva;
            font-size:26px;
            color:#373738;
        }
        td a:hover{
           background:#c2cfd3;
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
          <div>
              <table class="tableDesign">
                  <tr><th>Menu</th></tr>
                  <tr>
                      <td><a href="addMeal.jsp">Add meal</a></td>
                  </tr>
                  <tr>
                      <td><a href="retrieveMeals">Meal Management</a></td>
                  </tr>
                  <tr>
                      <td><a href="staffViewOrders.jsp">View Order</a></td>
                  </tr>
                  <tr>
                      <td><a href="staffViewIngredientNeed.jsp">View Ingredient Need</a></td>
                  </tr>
                  <tr>
                      <td><a href="TopUpCredit.jsp">Top Up Student Credit</a></td>
                  </tr>
                  <tr>
                      <td><a href="FoodRedeem.jsp">Food Redeem</a></td>
                  </tr>
                  <tr>
                      <td><a href="staffGenerateReport.jsp">Generate report</a></td>
                  </tr>
              </table>
          </div>
    </body>
</html>
