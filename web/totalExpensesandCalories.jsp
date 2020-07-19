<%-- 
    Document   : totalExpensesandCalories
    Created on : Apr 14, 2019, 7:00:27 PM
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
        
        <h1>Total Expenses and total calories intake</h1>
        
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
                   ArrayList<Orders> arrayL = ordersDA.getOrdersID(name);
                    ArrayList<OrderLine> arrayB = orderLineDA.getTotalExpenses();
                    ArrayList<Meal> arrayC = mealDA.getMeal();
                    double expenses = 0;
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
                                expenses = expenses + (arrayC.get(s).getMealPrice() * arrayB.get(y).getQuantity());
                               }
                       }
                    }
                    }%>
                    
 
        </table>
                <p>The total expenses is RM<%=expenses %></p>    
                
                
                <table>
            <tr>
                <td>Meal ID</td>
                <td>Meal Name</td>
                <td>Calories per intake meal(Cal)</td>
                <td>Quantity of meal</td>
                <td>Date intake</td>
            </tr>
             <%
                   ArrayList<Orders> arrayE = ordersDA.getOrdersID(name);
                    ArrayList<OrderLine> arrayF = orderLineDA.getTotalExpenses2();
                    ArrayList<Meal> arrayG = mealDA.getMeal();
                    double totalCalories = 0;
                    
                    for(int i=0; i<arrayE.size();i++){
                       for(int y=0; y<arrayF.size();y++){
                           for(int s=0; s<arrayG.size(); s++){
                           if(arrayE.get(i).getOrderID().equals(arrayF.get(y).getOrderId()) && arrayF.get(y).getMealId().equals(arrayG.get(s).getMealId())){
                               %>
                                    <tr>
                               <td><%=arrayG.get(s).getMealId() %></td>
                               <td><%=arrayG.get(s).getMealName() %></td>
                               <td><%=arrayG.get(s).getCalories() %></td>
                               <td><%=arrayF.get(y).getQuantity() %></td>
                               <td><%=arrayF.get(y).getOrderRedeemDate() %></td>
                               </tr>
            <%
            totalCalories = totalCalories + (arrayG.get(s).getCalories() * arrayF.get(y).getQuantity());
                               }
                       }
                    }
                    }
            %>
            
        </table>
         <p>Total Calories intake is:<%=totalCalories %>kcal </p>
        
    </body>
</html>
