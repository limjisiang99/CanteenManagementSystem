<%-- 
    Document   : printCoupon
    Created on : Apr 10, 2019, 4:46:00 PM
    Author     : Lim Ji-Siang
--%>

<%@page import="java.util.Base64"%>
<%@page import="domain.Meal"%>
<%@page import="domain.OrderLine"%>
<%@page import="domain.Orders"%>
<%@page import="java.util.ArrayList"%>
<%@page import="da.OrdersDA"%>
<%@page import="da.OrderLineDA"%>
<%@page import="da.MealDA"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Generate Coupon</title>
        <link rel="stylesheet" href="headerFooter.css" type="text/css">
        <link rel="stylesheet" href="meal.css" type="text/css">
    </head>
    <script>
        function printDiv(divName){
         var printContents = document.getElementById(divName).innerHTML;   
            var originalContents = document.body.innerHTML;
            document.body.innerHTML = printContents;
            window.print();
            document.body.innerHTML = originalContents;
        }
    </script>
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
                       <div id="PrintCoupon">              
                       <table>
                       <%
                   MealDA mealDA = new MealDA();
                   OrderLineDA orderLineDA = new OrderLineDA();
                   OrdersDA ordersDA = new OrdersDA();
                    ArrayList<Orders> arrayL = ordersDA.getOrdersID(name);
                    ArrayList<OrderLine> arrayB = orderLineDA.getRecordLine();
                    ArrayList<Meal> arrayC = mealDA.getMeal();
                   int count = 0;
        
                   for(int i=0; i<arrayL.size();i++){
                       for(int y=0; y<arrayB.size();y++){
                           for(int s=0; s<arrayC.size(); s++){
                           if(arrayL.get(i).getOrderID().equals(arrayB.get(y).getOrderId()) && arrayB.get(y).getMealId().equals(arrayC.get(s).getMealId())){
                                byte[] productImage = arrayC.get(s).getMealPicture();
               String encode = Base64.getEncoder().encodeToString(productImage);
               request.setAttribute("productImage", encode);
                               %>
                   
                       <td style="border:#daeaf2 solid;">
                  <p style="font-size:30px;"><b><u><%=arrayC.get(s).getMealName()%></u></b></p>
                <img class=productImageSize src="data:image/jpeg;base64,${productImage}">
                  <p>coupon ID: <%=arrayB.get(y).getCouponId()%></p>
                  <p>Order ID: <%=arrayB.get(y).getOrderId()%> </p>
                  <p>Meal ID:  <%=arrayB.get(y).getMealId()%></p>
                <p>Collection Date: <%=arrayB.get(y).getOrderRedeemDate()%></p>
                <p>Quantity: <%=arrayB.get(y).getQuantity()%></p>
                
                
                </td>
                         <%
                          if((count+1) % 3==0){
                    out.println("<tr>");
                }
                 count++;
                           }
                       }
                   }
                   }
                   %>
                   </table>
    </div> 
                   <button onclick="printDiv('PrintCoupon')">Print Coupon</button>
    </body>
</html>
