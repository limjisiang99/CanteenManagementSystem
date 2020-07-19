<%-- 
    Document   : ModifyOrdersMadeDetails
    Created on : Apr 12, 2019, 9:57:10 AM
    Author     : Lim Ji-Siang
--%>

<%@page import="java.util.Base64"%>
<%@page import="domain.Meal"%>
<%@page import="domain.OrderLine"%>
<%@page import="java.util.ArrayList"%>
<%@page import="da.MealDA"%>
<%@page import="da.OrderLineDA"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ModifyOrdersMadeDetails</title>
        <link rel="stylesheet" href="headerFooter.css" type="text/css">
        <link rel="stylesheet" href="meal.css" type="text/css">
    </head>
    <body>
        <style>
     
    .productImageSize{
            width:300px;
            height:300px;
        }
    </style>
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
                   <form method="post" action="modifyOrdersServlet">
               
                       <%
                   String mealID = request.getParameter("mealId");
                   String couponID = request.getParameter("couponID");
                   MealDA mealDA = new MealDA();
                   OrderLineDA orderLineDA = new OrderLineDA();
                    ArrayList<OrderLine> arrayB = orderLineDA.getRecordLine();
                    ArrayList<Meal> arrayC = mealDA.getMeal();
                   int count = 0;
        
                       for(int y=0; y<arrayB.size();y++){
                           for(int s=0; s<arrayC.size(); s++){
                           if(arrayB.get(y).getCouponId().equals(couponID) && arrayC.get(s).getMealId().equals(mealID)){
                                byte[] productImage = arrayC.get(s).getMealPicture();
               String encode = Base64.getEncoder().encodeToString(productImage);
               request.setAttribute("productImage", encode);
                               %>
                             <table>   
                       <td style="border:#daeaf2 solid;">
                  <p style="font-size:30px;"><b><u><%=arrayC.get(s).getMealName()%></u></b></p>
                <img class=productImageSize src="data:image/jpeg;base64,${productImage}">
                  <p>Order ID: <%=arrayB.get(y).getOrderId()%> </p>
                  <p>coupon ID: <%=arrayB.get(y).getCouponId()%></p>
                  <p>Meal ID:  <%=arrayB.get(y).getMealId()%></p>
                <p>Current Collection Date: <%=arrayB.get(y).getOrderRedeemDate()%></p>
                <p>Current Quantity: <%=arrayB.get(y).getQuantity()%></p>
                Quantity: <input type="number" name="quantity" value=1 min=1 max=99>
               <input type="hidden" name="couponID" value="<%=couponID%>">
               <input type="hidden" name="orderID" value="<%=arrayB.get(y).getOrderId()%>">
               <input type="hidden" name="oldQuantity" value="<%=arrayB.get(y).getQuantity()%>">
               <input type="hidden" name="mealID" value="<%=arrayB.get(y).getMealId()%>">
               <p>New collection date: <input type="date" name="newDate"></p> 
                </td>
                         <%
                          if((count+1) % 4==0){
                    out.println("<tr>");
                }
                 count++;
                           }
                       }
                   }
                   %>
                             </table>  
                   <input type="submit" value="submit">
                   </form>

    </body>
</html>
