<%-- 
    Document   : staffLogin
    Created on : Mar 24, 2019, 1:50:40 AM
    Author     : NGO KIAN HEE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="staffHeaderFooter.css" type="text/css">
        <title>Staff Login</title>
    </head>
    <body>
        <div class="header">
                <h1>Hot Café</h1>
               <div class="header-right">
                   <a href="StudentLogin.jsp">Student</a>
                   <a class="active" href="staffLogin.jsp">Staff</a>
               </div>
        </div>       
        <div class="formCenter">
        <form method="post" action="StaffLoginServlet">
                <h1>Login Here!</h1>
                <p>Username</p>
                <input type="text" name="staffUsername" placeholder="username" required="required"/>
                <p>Password</p>
                <input type="password" name="staffPassword"  placeholder="password" required="required"/>
                <br><br>
                <input class="button" type="submit" value="Login"/>
                 <br><br>
                <a href="staffRecoveryPassword.jsp">Forgot password</a><br>
                <a href="staffRegistration.jsp">Don't have an account? register here</a>
                <br><br>
                <% String errorMsg = (String)request.getAttribute("errorMessage");
                if(errorMsg!=null)
                  out.println("<font color=red>" + errorMsg + "</font>");   
                %>
        </form>
       </div>
    </body>
</html>
