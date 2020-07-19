<%-- 
    Document   : FoodRedeem
    Created on : Mar 31, 2019, 4:29:27 PM
    Author     : NGO KIAN HEE
--%>

<%@page import="domain.Meal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.OrderLine"%>
<%@page import="domain.StaffCanteenAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="staffHeaderFooter.css" type="text/css">

        <title>Food Redeem</title>
    </head>
    <style>
        .redeemButton a{
    text-decoration:none;
    border: none;
    outline: none;
    height: 45px;
    padding: 13px 17px;
    background: #a81c41;
    color:#fff;
    font-size: 18px;
    border-radius: 20px;
}
.redeemButton a:hover{
    cursor: pointer;
    background: #eda1b5;
    color:#000;
}
    </style>
    <body>
        <%
            StaffCanteenAccount staffCA =(StaffCanteenAccount)session.getAttribute("staff"); 
           // String name = (String)session.getAttribute()
        %>
        <div class="header">
                <h1>Hot Café</h1>
               <div class="header-right">
               <p>Welcome back, <%=staffCA.getStaffName()%></p>   
               <a href="StaffHomePage.jsp">Home</a>
               <a href="staffUpdateDetails.jsp">Update Personal Information</a>
               <a href="staffChangePassword.jsp">Change Password</a>
               <a href="StaffLogoutServlet">Logout</a>
               </div>  
        </div>
               <div class="navBar">
                   <a href="addMeal.jsp">Add new meal</a>
                   <a href="retrieveMeals">Meal Management</a>
                   <a href="staffViewOrders.jsp">View Order</a>
                   <a href="staffViewIngredientNeed.jsp">View Ingredient Need</a>
                   <a href="TopUpCredit.jsp">Top Up Credit</a>
                   <a class="active" href="FoodRedeem.jsp">Food Redeem</a>
                   <a href="staffGenerateReport.jsp">Generate Report</a>
                   
               </div>
               <div class="formCenter">
          <form method="get" action="staffViewOrderDetail">
                <h1>Food Redeem</h1>
              <table>
                  <tr>
                      <td>Coupon ID:</td>
                      <td><input type="text" name="couponId" required></td>
                  </tr>
                  <tr>
                      <td><input type="submit" value="View"</td>
                  </tr>
              </table>
                <% String Message = (String)request.getAttribute("couponMessage");
                           if(Message!= null){
                           out.println(Message);
                           }
                   OrderLine orderDetails = (OrderLine)request.getAttribute("orderLineDetail");
                   if(orderDetails != null){
                       ArrayList<Meal> arrayListMeal = (ArrayList<Meal>)session.getAttribute("Meals");
                       out.println("<p><b>Coupon Details<br>------------------------</b></p>");
                        for(int i = 0 ; i <arrayListMeal.size(); i ++){
                            if(arrayListMeal.get(i).getMealId().equals(orderDetails.getMealId())){
                                out.println("<p><b>Coupon ID : </b>"+orderDetails.getCouponId()+"</p>");
                                out.println("<p><b>Meal Name : </b>"+arrayListMeal.get(i).getMealName()+"</p>");
                                out.println("<p><b>Quantity Ordered : </b>"+orderDetails.getQuantity()+"</p>");
                                out.println("<p><b>Collect Date : </b>"+orderDetails.getOrderRedeemDate()+"</p>");
                                    %>
                                    <p class="redeemButton"><a  href="StaffFoodRedeemServlet?method=get&couponId=<%=orderDetails.getCouponId()%>">Redeem Now</a></p>
                            <%}
                        }
                    }%>
                    
          </form>
                   </div>
    </body>
</html>
