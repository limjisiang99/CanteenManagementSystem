<%-- 
    Document   : StudentLogin
    Created on : Mar 31, 2019, 12:57:03 PM
    Author     : Lim Ji-Siang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>StudentLogin</title>
        <link rel="stylesheet" href="headerFooter.css" type="text/css">
    </head>
    <body>
            <div class="header">
                <h1>Hot Café</h1>
               <div class="header-right">
                   <a class="active" href="StudentLogin.jsp">Student</a>
                   <a href="staffLogin.jsp">Staff</a>
               </div>
            </div>
           
            <div class="loginbox">
        
        <form method="post" action="StudentLoginServlet">
            <h1>Login Here!</h1>
            <p>Username</p>
            <input type="text" name="username" required placeholder="Enter Username" />
            <p>Password</p>
            <input type="password" name="password" required placeholder="Enter Password" />
            <br><br>
            <input type="submit" value="Login">
            <br><br>
           
         <a href="forgetPassword.jsp">Forgot password</a><br>
         <a href="StudentSignup.jsp">Dont have an account? register here</a>
         <br>
         <% String errorMessage = (String)request.getAttribute("ErrorMsg");
        if(errorMessage!=null)
           out.println("<p style=color:red>"+errorMessage+"</p>");
          %>
        </form>
    </div>
    </body>
</html>
