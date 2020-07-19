<%-- 
    Document   : modifyOrder
    Created on : Apr 3, 2019, 1:50:23 PM
    Author     : Lim Ji-Siang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify Order Page</title>
          <link rel="stylesheet" href="headerFooter.css" type="text/css">
        <link rel="stylesheet" href="orderDetails.css" type="text/css">
    </head>
    <body>
        <style>
        .productImageSize{
            width:300px;
            height:300px;
        }
        </style>
         <%  String name=(String)session.getAttribute("name"); %>
            <div class="header">
                <h1>Hot Café</h1>

               <div class="header-right">
                   <div class="dropbtn">Hi, <% out.print(name); %></div>
                   <a class= "active" href="StudentHome.jsp">Home</a>
                   <a href="ModifyAccount.jsp">Modify Account</a>
                    <a href="StudentChangePassword">Change Password</a>
                   <a class="cart" href="StudentReport.jsp">View Report</a>
                   <a href="LogoutServlet">Logout</a>
               </div>
            </div>
                   <div class="orderDetails">
          <table>
              <td style="border:#daeaf2 solid;">
                  <img src="./StudentOrderPicture/burger.jpg" class="productImageSize"><br>
                <p>Current start Date</p>
                <p>1/4/2019</p>
                <p>Current End Date</p>
                <p>2/4/2019</p>
                <p>New start date to be updated</p>
                <input name="date">
                <p>New End date to be updated</p>
                <input name="date"><br>
                <a><button>Submit</button></a>
                </td>
           </table>
                   </div>
                   
    </body>
</html>
