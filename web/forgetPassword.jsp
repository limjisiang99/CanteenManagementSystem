<%-- 
    Document   : forgetPassword
    Created on : Apr 2, 2019, 9:24:55 PM
    Author     : Lim Ji-Siang
--%>

<%@page import="domain.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>StudentForgetPassword</title>
        <link rel="stylesheet" type="text/css" href="headerFooter.css">
    </head>
    <body>
        <div class="header">
                <h1>Hot Café</h1>
               <div class="header-right">
                   <a class="active" href="StudentLogin.jsp">Student</a>
                   <a href="">Staff</a>
               </div>
            </div>
         <form method="post" action="ForgetPasswordServlet">
            <p>Username</p>
            <input type="text" name="username" required placeholder="Enter Username" />
             <p>Secret Question</p>
                <p></p>
                <br>
            <select name="Question">
                 <option value="color" selected="selected">What is your favourite color?</option>
                    <option value="Car">What is your favourite car?</option>
                    <option value="City">What is your favourite city?</option>
                    <option value="Shoe">What is your favourite shoe?</option>
                </select>
                <br><br><p>Secret Answer</p>
                <input type="text" name="answer" placeholder="Enter your secret answer" required="required">
            <br><br>
            <input type="submit" value="Retrieve Password">
            
              <br>
         <% 
            
            String errorMessage = (String)request.getAttribute("ErrorMsg");
            String urPass = (String)request.getAttribute("password");
       
        if(errorMessage!=null)
           out.println("<p style=color:red>"+errorMessage+"</p>");
        if(urPass !=null)
            out.println("Your password is ");
            if(urPass!=null){
            out.println("<p style=color:black>"+urPass+"</p>");
            }
          %>
         </form>
    </body>
</html>
