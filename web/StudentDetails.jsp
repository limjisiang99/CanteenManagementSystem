<%-- 
    Document   : modifyAccount
    Created on : Apr 4, 2019, 4:55:02 PM
    Author     : Lim Ji-Siang
--%>

<%@page import="domain.Student"%>
<%@page import="da.StudentDA"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>StudentDetails</title>
        <link rel="stylesheet" type="text/css" href="headerFooter.css">
    </head>
    <body>
         <%  
             StudentDA studentda = new StudentDA();
            String name=(String)session.getAttribute("username");
            String ID =(String)session.getAttribute("ID");
            String question =(String)session.getAttribute("Question");
            String answer =(String)session.getAttribute("Answer");
            String email=(String)session.getAttribute("Email");
            String contactNum=(String)session.getAttribute("contactnum");
            Student a = new Student(name,question,answer,email,contactNum);
            Student student = studentda.Sretrieve(a);
         %>
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
                   <form>
         <h1 class="name"><% out.println(name); %>'s Account</h1>
            <br>
            <p>Username</p>
            <p><% out.println(name); %></p>
            <p>Student ID</p>
            <p><% out.println(ID); %></p>
            <p>Secret Question</p>
            <p><%out.println(question); %></p>
            <p>Secret Answer</p>
            <p><%out.println(answer); %></p>
            <p>Email</p>
            <p><%out.println(email); %></p>
            <p>contact Number</p>
            <p><%out.println(contactNum); %></p>
            <p>Credit Points</p>
            <p><%out.println(student.getCreditPoint()); %></p>
            <br>
            <button><a href="modifyAccount.jsp">Modify account</a></button>
                   </form>
        
        
    </body>
</html>
