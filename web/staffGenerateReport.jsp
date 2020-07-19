<%-- 
    Document   : staffGenerateReport
    Created on : Apr 3, 2019, 1:40:28 AM
    Author     : NGO KIAN HEE
--%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="domain.TopUpTransaction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.StaffCanteenAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="staffHeaderFooter.css" type="text/css">
        <title>Report</title>
    

    <style>
        table{
              
              border-collapse: collapse;
        }
        .title th{
            border-left:1px solid black;
            border-right:1px solid black;  
            border-top:1px solid black;
            border-bottom:1px solid black;
        }
        .lineLeftRight td{
            padding-top:10px;
            border-left:1px solid black;
            border-right:1px solid black;
        }
        .lineLeftRightSplit td{
            padding-top:10px;
            border-left:1px solid black;
            border-right:1px solid black;
            border-bottom:1px solid black;
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
                   <a href="staffViewIngredientNeed.jsp">View Ingredient Need</a>
                   <a href="TopUpCredit.jsp">Top Up Credit</a>
                   <a href="FoodRedeem.jsp">Food Redeem</a>
                   <a class="active" href="staffGenerateReport.jsp">Generate Report</a>
                   
                   
               </div>
               <div class="formCenter">
          <form method="get" action="ReportServlet">
              <h1>Generate Report</h1>
              <table>  
                <p>Please select the type of report.</p>
                <tr>
                  <td>Report Type:</td>
                  <td><select name="reportType">
                          <option value="transactionReport">Top Up Transaction Report</option>
                          <option value="exceptionReport">Cancellation Meal Report</option>
                          <option value="summaryReport">Popular Meal Report</option>
                      </select></td>
              </tr>
              <tr>
                  <td>Starting date:</td>
                  <td><input type="date" required name="startingDate"></td>
              </tr>
              <tr>
                  <td>Ending date:</td>
                  <td><input type="date" name="endingDate" required><td>
              </tr>
              <tr>
                  <td>
                      <input type="hidden" name="generateDate" value="">
                      <input type="submit" value="Generate">
                  </td>
              </tr>
          </table>
                     <% String Message = (String)request.getAttribute("reportMessage");
                if(Message!=null){
                    out.println(Message);
                }
          %>
          </form>
                   
               </div>
    </body>
</html>


