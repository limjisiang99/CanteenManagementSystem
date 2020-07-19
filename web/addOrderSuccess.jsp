<%-- 
    Document   : addOrderSuccess
    Created on : Apr 16, 2019, 9:00:42 AM
    Author     : Lim Ji-Siang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insufficient balance</title>
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
                       <div style="padding-left: 20px">
                   <h1>Order successfully added!!</h1>
                   <br>
                   
                   </div>
    </body>
</html>
