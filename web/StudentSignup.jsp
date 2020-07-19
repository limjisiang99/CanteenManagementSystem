<%-- 
    Document   : StudentSignup
    Created on : Apr 1, 2019, 4:28:47 PM
    Author     : Lim Ji-Siang
--%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Student Registration</title>
       
        <style>
       body{
    margin: 0;
    padding: 0;
    background-size: cover;
    background-position: center;
    font-family: sans-serif;
    height: auto;
    background-image: url("./staffFolder/loginBackgroundImage.png");
}

.loginbox{
    width: 50%;
    height: auto;
    background:rgb(224, 235, 235,0.4);
    color:darkblue;
    top: 405px;
    left: 50%;
    position: absolute;
    transform: translate(-50%,-50%);
    box-sizing: border-box;
    padding: 70px 30px;
}

.avatar{
    width: 100px;
    height: 100px;
    border-radius: 50%;
    position: absolute;
    top: -50px;
    left: calc(50% - 50px);
}

h1{
    margin: 0;
    padding: 0 0 20px;
    text-align: center;
    font-size: 22px;
}

.loginbox p{
    margin: 0;
    padding: 0;
    font-weight: bold;
}

.loginbox input{
    width: 100%;
    margin-bottom: 10px;
}

.loginbox input[type="text"], input[type="password"],input[type="email"],input[type="tel"]
{
    border: none;
    border-bottom: 1px solid #fff;
    background: transparent;
    outline: none;
    height: 40px;
    color: black;
    font-size: 16px;
}
.loginbox input[type="submit"]
{
    border: none;
    outline: none;
    height: 40px;
    background: #fb2525;
    color: #fff;
    font-size: 18px;
    border-radius: 20px;
}
.loginbox input[type="submit"]:hover
{
    cursor: pointer;
    background: #ffc107;
    color: #000;
}
.loginbox a{
    text-decoration: none;
    font-size: 20px;
    line-height: 30px;
    color: darkgrey;
}
.loginbox a:hover
{
    color: #ffc107;
}
    </style>
    </head>
    <body class="staff">
        
        <div class="loginbox">
             <h1>Student Registration</h1>
            <form method="post" action="StudentSignUpServlet">   
                <p>Username</p>
                <input type="text" name="username" placeholder="Enter your Student username" required="required">
                <p>Student ID</p>
                <input type="text" name="ID" placeholder="Enter your student ID" required="required">
                <p>password</p>
                <input type="password" name="password" placeholder="Enter your password" required="required">        
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
                <input type="text" name="Answer" placeholder="Enter your secret answer" required="required">
                <p>Email</p>
                <input type="email" name="Email" placeholder="Enter your Email with format xxx@gmail.com " required="required">
                <p>Contact Number</p>
                <input type="text" name="contactnum" placeholder="Enter your contact number" required="required"></br>
                <input type="submit" value="Register">
              
            </form>
             <br>
             <% String errmsg = (String)request.getAttribute("ErrorMsg");
             if(errmsg!=null){
                 out.println("<font color=red>" + errmsg + "</front>");
             }
             %>
             <br>
            <a href="StudentLogin.jsp">Click here to go back to StudentLogin</a><br>
            
        </div>
    </body>
</html>

