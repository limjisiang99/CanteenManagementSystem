<%-- 
    Document   : summaryReport
    Created on : Apr 10, 2019, 10:44:55 PM
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
        <title>Summary Report</title>
    </head>
    <body>
        <%  String name =(String)session.getAttribute("username");%>
        <h1>Summary Report</h1>
        <p>Nutrition intake report for April 2019</p>
        <table>
            <tr>
                <td>Meal ID</td>
                <td>Meal Name</td>
                <td>Calories per intake meal(Cal)</td>
                <td>Quantity of meal</td>
                <td>Date intake</td>
            </tr>
             <%
                   MealDA mealDA = new MealDA();
                   OrderLineDA orderLineDA = new OrderLineDA();
                   OrdersDA ordersDA = new OrdersDA();
                   ArrayList<Orders> arrayL = ordersDA.getOrdersID(name);
                    ArrayList<OrderLine> arrayB = orderLineDA.getSummaryReport();
                    ArrayList<Meal> arrayC = mealDA.getMeal();
                    double totalCalories = 0;
                    
                    for(int i=0; i<arrayL.size();i++){
                       for(int y=0; y<arrayB.size();y++){
                           for(int s=0; s<arrayC.size(); s++){
                           if(arrayL.get(i).getOrderID().equals(arrayB.get(y).getOrderId()) && arrayB.get(y).getMealId().equals(arrayC.get(s).getMealId())){
                               %>
                                    <tr>
                               <td><%=arrayC.get(s).getMealId() %></td>
                               <td><%=arrayC.get(s).getMealName() %></td>
                               <td><%=arrayC.get(s).getCalories() %></td>
                               <td><%=arrayB.get(y).getQuantity() %></td>
                               <td><%=arrayB.get(y).getOrderRedeemDate() %></td>
                               </tr>
            <%
            totalCalories = totalCalories + (arrayC.get(s).getCalories() * arrayB.get(y).getQuantity());
                               }
                       }
                    }
                    }
            %>
            
           
        </table>
         <p>Total Calories intake is:<%=totalCalories %>kcal </p>
    </body>
</html>
