<%-- 
    Document   : modifyAccount
    Created on : Apr 4, 2019, 7:29:44 PM
    Author     : Lim Ji-Siang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify Account</title>
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
                   <form method="post" action="StudentModifyAccountServlet"> 
                   <div class="loginbox">
                   <h1>Modify <% out.print(name); %> Account</h1>
                   <br>
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
                <input type="email" name="Email" placeholder="Enter your Email" required="required">
                <p>Contact Number</p>
                <input type="text" name="contactnum" placeholder="Enter your contact number" required="required"></br>
                <br>
                <br>
                <input type="submit" value="modify">
                 <% String success = (String)request.getAttribute("Success");
        if(success !=null)
           out.println("<p style=color:black>"+success+"</p>");
          %>
                   </div>
                   </form>
    </body>
</html>
