<%-- 
    Document   : breakFast
    Created on : Apr 4, 2019, 8:44:49 PM
    Author     : Lim Ji-Siang
--%>

<%@page import="java.util.Base64"%>
<%@page import="da.MealDA"%>
<%@page import="control.MealControl"%>
<%@page import="domain.Meal"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Breakfast meal</title>
        <link rel="stylesheet" href="headerFooter.css" type="text/css">
        <link rel="stylesheet" href="meal.css" type="text/css">
    </head>
    <body>
        <%  String name=(String)session.getAttribute("username"); %>
          <div class="header">
                <h1>Hot Café</h1>

               <div class="header-right">
                   <div class="dropbtn">Hi, <% out.print(name); %></div>
                   <a class= "active" href="StudentHome.jsp">Home</a>
                   <a href="StudentDetails.jsp">View Account</a>
                   <a href="StudentChangePassword">Change Password</a>
                   <a class="cart" href="StudentReport.jsp">View Report</a>
                   <a href="LogoutServlet">Logout</a>
               </div>
            </div>
               <nav>
            <ul>
                <li><a   class="active"  href="BreakFastMeal.jsp">Breakfast</a></li>
                <li><a href="LunchMeal.jsp">Lunch</a></li>
            </ul>
        </nav>
        
        <table>
       
        <% MealDA mealDA = new MealDA();
            ArrayList<Meal> arrayL = mealDA.getMeal();
            int count=0;
            
            for(int i=0; i<arrayL.size();i++){
               if(arrayL.get(i).getMealSession().equals("Breakfast")){
                         
               out.println("<td><p class=mealName>"+ arrayL.get(i).getMealName() + "</p>");
               byte[] productImage = arrayL.get(i).getMealPicture();
               String encode = Base64.getEncoder().encodeToString(productImage);
               request.setAttribute("productImage", encode);
               %>
               <img class=productImageSize src="data:image/jpeg;base64,${productImage}">
               <p>Price: RM<%=arrayL.get(i).getMealPrice()%></p>     
               <p>Calories:<%=arrayL.get(i).getCalories()%></p>
               <p>Description:<%=arrayL.get(i).getMealDesc()%></p>
               <a href="OrderDetails.jsp?method=post&mealId=<%=arrayL.get(i).getMealId()%>"><button>Choose</button></a> 
              <%
               if((count+1) % 3==0){
                    out.println("<tr>");
                }
                 count++;
                }
            }
            %>
        </table>    
                   
    </body>
</html>
