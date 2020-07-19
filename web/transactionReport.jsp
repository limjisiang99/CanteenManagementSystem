<%-- 
    Document   : transactionReport
    Created on : Apr 10, 2019, 7:05:24 PM
    Author     : Lim Ji-Siang
--%>

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
        <title>Transaction report</title>
    </head>
    <body>
        <%  String name =(String)session.getAttribute("username");%>
        
        <h1>Transaction Report</h1>
        <p>Meal transaction report from 1st January 2019 to 30 June 2019</p>
        <table>
            <tr>
                <td>Order ID</td>
                <td>Meal ID</td>
                <td>Meal Name</td>
                <td>price</td>
                <td>Quantity</td>
                <td>Order Date</td>
            </tr>
            <%
                   MealDA mealDA = new MealDA();
                   OrderLineDA orderLineDA = new OrderLineDA();
                   OrdersDA ordersDA = new OrdersDA();
                   ArrayList<Orders> arrayL = ordersDA.getTransactionReport(name);
                    ArrayList<OrderLine> arrayB = orderLineDA.getRecord();
                    ArrayList<Meal> arrayC = mealDA.getMeal();
                    
                    for(int i=0; i<arrayL.size();i++){
                       for(int y=0; y<arrayB.size();y++){
                           for(int s=0; s<arrayC.size(); s++){
                           if(arrayL.get(i).getOrderID().equals(arrayB.get(y).getOrderId()) && arrayB.get(y).getMealId().equals(arrayC.get(s).getMealId())){
                               %>
                               <tr>
                               <td><%=arrayL.get(i).getOrderID() %></td>
                               <td><%=arrayC.get(s).getMealId() %></td>
                               <td><%=arrayC.get(s).getMealName() %></td>
                               <td><%=arrayC.get(s).getMealPrice() %></td>
                               <td><%=arrayB.get(y).getQuantity() %></td>
                               <td><%=arrayL.get(i).getOrderDate() %></td>
                               </tr>
                               <%
                               }
                       }
                    }
                    }%>
 
        </table>
    </body>
</html>
