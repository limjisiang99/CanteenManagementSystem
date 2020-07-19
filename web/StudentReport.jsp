<%-- 
    Document   : StudentReport
    Created on : Apr 10, 2019, 5:58:13 PM
    Author     : Lim Ji-Siang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                           <tr><th>Menu</th></tr>
                           <tr>
                               <td> <a href="transactionReport.jsp">Transaction Report</a></td>
                           </tr>
                           <tr>
                               <td><a href="summaryReport.jsp">Summary Report</a></td>
                           </tr>
                           <tr>
                               <td><a href="printCoupon.jsp">Exception Report</a></td>
                           </tr>
                       </table>
                   </div>
    </body>
</html>
