<%-- 
    Document   : staffChangePassword
    Created on : Mar 24, 2019, 6:17:56 PM
    Author     : NGO KIAN HEE
--%>

<%@page import="domain.StaffCanteenAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <link rel="stylesheet" href="staffHeaderFooter.css" type="text/css">
      

        <title>Staff Change Password</title>
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
               <a class="active" href="staffChangePassword.jsp">Change Password</a>
               <a href="StaffLogoutServlet">Logout</a>
               </div>  
        </div> 
      
        <div class="formCenter">    
        <form method="post" action="StaffChangePasswordServlet">   
        <h1>Change password</h1>    
                <p>Current Password</p>
                <input type="password" name="currentPassword" required="required">
                <p>New Password</p>
                <input type="password" name="staffPassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required="required">
                <p>Re-enter new password</p>
                <input type="password" name="staffPassword2" required="required">
                <br><br>

                <input type="submit" value="Change">
                 <br><br>

        <%String changePwMessage = (String)request.getAttribute("changePwMessage");
                if(changePwMessage!=null){
                     out.println(changePwMessage);
                }
              
          %>
        </form>

          <br>
         
         
        </div>
      
    </body>
</html>
