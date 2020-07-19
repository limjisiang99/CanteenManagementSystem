<%-- 
    Document   : staffUpdateDetails
    Created on : Mar 23, 2019, 5:18:35 PM
    Author     : NGO KIAN HEE
--%>
<%@page import="domain.StaffCanteenAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="staffHeaderFooter.css" type="text/css">

        <title>Update Account Details</title>
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
               <a  class="active" href="staffUpdateDetails.jsp">Update Personal Information</a>
               <a href="staffChangePassword.jsp">Change Password</a>
               <a href="StaffLogoutServlet">Logout</a>
               </div>  
        </div> 
        <div class="formCenter">
             
        <form method="post" action="StaffUpdateServlet">
            <h1>Staff Personal Details</h1>
             <table>
                 <tr>
                    <td>Name</td>
                    <td> <input type="text" name="staffName"  value="<%=staffCA.getStaffName()%>" required="required"></td>
                </tr>
                <tr>
                    <td>IC</td>
                    <td><input type="text" name="staffIC" pattern="[0-9]{12}" value="<%=staffCA.getStaffIc()%>" required="required">
                        <label style="font-size:12.5px;padding-left:20px">Eg: 990122101234</label></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="email" name="staffEmail" value="<%=staffCA.getStaffEmail()%>" required="required"></td>
                </tr>
                <tr>
                    <td>Phone Number</td>
                    <td><input type="tel" name="staffPhoneNo" pattern="[0-9]{10}" value="<%=staffCA.getStaffPhoneNo()%>" required="required">
                <label style="font-size:12.5px;padding-left:20px">Eg: 0123456789  </label></td>
                </tr>   
                <tr><td><input type="submit" value="Update"> </td></tr>
                 <br>
             
                </table>
                     <br><br>
                  <%String updateMsg = (String)session.getAttribute("updateMessage");
                  //String updateMsg = (String)request.getAttribute("updateMessage");
                  if(updateMsg!=null){
                   out.println("<font color=blue>"+updateMsg+"</font>");  
                   session.setAttribute("updateMessage",null);//Clear the attribute after display.
                }
                %>
        </form>
          
                 
                </div>
    </body>
</html>
