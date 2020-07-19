<%-- 
    Document   : successfullyModifiedOrder
    Created on : Apr 12, 2019, 1:02:24 PM
    Author     : Lim Ji-Siang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>successfully modified order</title>
        <link rel="stylesheet" href="headerFooter.css" type="text/css">
    </head>
    <body>
        <%  String name =(String)session.getAttribute("username");%>
         
            <div class="header">
                <h1>Hot Café</h1>

               <div class="header-right">
                   <div class="dropbtn">Hi, <%out.println(name);%></div>
                   <a class= "active" href="StudentHome.jsp">Home</a>
                   <a href="StudentDetails.jsp">View Account</a>
                   <a href="StudentChangePassword.jsp">Change Password</a>
                   <a class="cart" href="StudentReport.jsp">View Report</a>
                   <a href="LogoutServlet">Logout</a>
               </div>
            </div>
    <p>Your order had been successfully modified</p>
                   
                   
                   
                   
    </body>
</html>
