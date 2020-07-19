<%-- 
    Document   : OrderDetails
    Created on : Apr 3, 2019, 12:38:09 PM
    Author     : Lim Ji-Siang
--%>

<%@page import="java.util.Base64"%>
<%@page import="da.MealDA"%>
<%@page import="domain.Meal"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OrderDetails Page</title>
        <link rel="stylesheet" href="headerFooter.css" type="text/css">
        <link rel="stylesheet" href="orderDetails.css" type="text/css">
    </head>
    <body >
          <style>
     
    .productImageSize{
            width:300px;
            height:300px;
        }
    </style>
        <%  String name=(String)session.getAttribute("username"); %>
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
                   
                <div class="productDetails">
        <form method="post" action="cartServlet">
         
        <%String mealID = request.getParameter("mealId");%>
       
        <% 
            MealDA mealDA = new MealDA();
            ArrayList<Meal> arrayL = mealDA.getMeal();
            int count=0;
            for(int i=0; i<arrayL.size();i++){
                if(arrayL.get(i).getMealId().equals(mealID)){
               byte[] productImage = arrayL.get(i).getMealPicture();
               String encode = Base64.getEncoder().encodeToString(productImage);
               request.setAttribute("productImage", encode);
          %>     
          <table>
              <td style="border:#daeaf2 solid;">
                  <p style="font-size:30px;"><b><u><%=arrayL.get(i).getMealName()%></u></b><p>
                <img class=productImageSize src="data:image/jpeg;base64,${productImage}">
                <p>RM<%=arrayL.get(i).getMealPrice()%></p>
                <input type="hidden" name="mealID" value="<%=mealID%>">
                Quantity: <input type="number" name="quantity" value=1 min=1 max=99>
                <br>
                Collection Date: <input type="date" name="collectdate">
                </td>
           </table>
                <% count++;}
            }%>
            <input type="submit" value="submit">
            </form>
           </div>
                   
                  
    </body>
</html>
