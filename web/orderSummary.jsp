<%-- 
    Document   : orderSummary
    Created on : Apr 7, 2019, 7:17:57 PM
    Author     : Lim Ji-Siang
--%>

<%@page import="java.util.Base64"%>
<%@page import="domain.Meal"%>
<%@page import="da.MealDA"%>
<%@page import="domain.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="da.CartDA"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="headerFooter.css" type="text/css">
        <link rel="stylesheet" href="meal.css" type="text/css">
        <title>Cart Summary</title>
    </head>
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
                   <form method="post" action="summaryServlet"> 
                   <table>
                   <%CartDA cartDA = new CartDA(); 
                   ArrayList<Cart> arrayL = cartDA.getCart(name);
                   MealDA mealDA = new MealDA();
            ArrayList<Meal> arrayB = mealDA.getMeal();
            double totalPrice = 0;
            double totalCalories = 0;
            int totalQuantities = 0;
            int count = 0;
                   for(int i=0; i<arrayL.size();i++){
                       for(int y=0; y<arrayB.size();y++){
                           if(arrayL.get(i).getMealID().equals(arrayB.get(y).getMealId())){
                byte[] productImage = arrayB.get(y).getMealPicture();
               String encode = Base64.getEncoder().encodeToString(productImage);
               request.setAttribute("productImage", encode);
                       %>           
              <td style="border:#daeaf2 solid;">
                  <p style="font-size:30px;"><b><u><%=arrayB.get(y).getMealName()%></u></b><p>
                <img class=productImageSize src="data:image/jpeg;base64,${productImage}">
                  <p>  Price: RM<%=arrayB.get(y).getMealPrice()%></p>
                <input type="hidden" name="mealID" value="<%=arrayL.get(i).getMealID()%>">
                Meal ID: <%=arrayL.get(i).getMealID()%> 
                <p>Quantity: <%=arrayL.get(i).getQuantity()%> </p>
                <p>Collection Date: <%=arrayL.get(i).getCollectDate()%></p>
                <a href="deleteCartServlet?method=get&mealId=<%=arrayL.get(i).getMealID()%>&collectDate=<%=arrayL.get(i).getCollectDate()%>">delete</a> 
                </td>      
                  <%
                  totalPrice = totalPrice + (arrayL.get(i).getQuantity() * arrayB.get(y).getMealPrice());
                  totalCalories = totalCalories + arrayB.get(y).getCalories();
                  totalQuantities += arrayL.get(i).getQuantity();
               if((count+1) % 4==0){
                    out.println("<tr>");
                }
                 count++;
                
                           }
                       }
                   }
            %>
           </table>
           <br>
           <p>Summary</p>
           <p>Total Points to be paid: <%=totalPrice %></p>
           <p>Total Calories in order: <%=totalCalories %></p>
           <p>Total Quantity of food ordered: <%=totalQuantities %></p>
           <br>
                       <input type="submit" value="submit">
                       <a href="BreakFastMeal.jsp">Continue Shopping</a>
    </form>
               
    </body>
</html>
