<%-- 
    Document   : StudentChangePassword
    Created on : Apr 6, 2019, 12:28:50 PM
    Author     : Lim Ji-Siang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change password student</title>
        <link rel="stylesheet" href="headerFooter.css" type="text/css">
    </head>
    <body>
         <%  String name =(String)session.getAttribute("username"); %>
            <div class="header">
                <h1>Hot Café</h1>

               <div class="header-right">
                   <div class="dropbtn">Hi, <% out.print(name); %></div>
                   <a class= "active" href="StudentHome.jsp">Home</a>
                   <a href="StudentDetails.jsp">View Account</a>
                   <a href="StudentChangePassword.jsp">Change Password</a>
                   <a class="cart" href="StudentReport.jsp">View Report</a>
                   <a href="LogoutServlet">Logout</a>
               </div>
            </div>
                   <form method="post" action="ChangePasswordServlet"> 
                <h1>Update Password</h1>
                <p>Current password</p>
                <input type="text" name="OldPassword" placeholder="Enter your current password" required="required">
                <p>New password</p>
                <input type="password" name="NewPassword" placeholder="Enter your new password" required="required">
                <p>Retype password</p>
                <input type="password" name="retypePassword" placeholder="Please retype your new password" required="required">
                 <% String errorMessage = (String)request.getAttribute("Fail");
                 String passMessage = (String)request.getAttribute("Success");
                 String errorMessage2 = (String)request.getAttribute("Fail2");
        if(errorMessage!=null)
           out.println("<p style=color:red>"+errorMessage+"</p>");
         if(passMessage!=null)
           out.println("<p style=color:black>"+passMessage+"</p>");
         if(errorMessage2!=null)
             out.println("<p style=color:red>"+errorMessage2+"</p>");
         
          %>
                <input type="submit" value="submit">
                   </form>
    </body>
</html>
