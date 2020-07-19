<%-- 
    Document   : TransactionReportDate
    Created on : Apr 11, 2019, 10:01:17 PM
    Author     : Lim Ji-Siang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TransactionReportDate</title>
         <link rel="stylesheet" href="headerFooter.css" type="text/css">
    </head>
    <style>
        th{
            text-decoration:underline;
            font-family:Monotype Corsiva;
            font-size:35px;
            color:#373738;
        }
        td a{
            text-decoration: underline;
            font-family:Monotype Corsiva;
            font-size:26px;
            color:#373738;
        }
        td a:hover{
            
            background:#c2cfd3;
        }
        .tableDesign {
    margin-left:auto;
    margin-right:auto;
    margin-top: 20px;
    border:2.5px solid #afa0a4;
    
    
    
}
    </style>
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
                   <div>
                       <table class="tableDesign">
                           <tr><td>Enter your date of transaction report</td></tr>
                           <tr></tr>
                           <tr>
                               <td>Starting Date</td>
                           </tr>
                           <tr>
                               <td><input type="date" name="date1" required></td>
                           </tr>
                           <tr>
                               <td>Ending Date</td>
                           </tr>
                           <tr>
                               <td><input type="date" name="date2" required></td>
                           </tr>
                           <tr>
                               <td> <a href="transactionReport.jsp?method=post&datex=date1&datey=date2"><button>Submit</button></a> </td>
                           </tr>
                       </table>
                   </div>
    </body>
</html>
