<%-- 
    Document   : modifyMeal
    Created on : Apr 1, 2019, 11:03:30 PM
    Author     : NGO KIAN HEE
--%>

<%@page import="control.MealIngredientControl"%>
<%@page import="domain.MealIngredient"%>
<%@page import="domain.MealIngredient"%>
<%@page import="domain.Ingredient"%>
<%@page import="control.IngredientControl"%>
<%@page import="domain.Meal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.StaffCanteenAccount"%>
<%@page import="domain.StaffCanteenAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="staffHeaderFooter.css" type="text/css">
        <title>Modify meal</title>
        <style>
        .backButton a{
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
.backButton a:hover{
    cursor: pointer;
    background: #eda1b5;
    color:#000;
}
    </style>
    </head>
     
    <body>
         <%
            StaffCanteenAccount staffCA =(StaffCanteenAccount)session.getAttribute("staff"); 
           // String name = (String)session.getAttribute()
        %>
        <div class="header">
                <h1>Hot Café</h1>
               <div class="header-right">
               <p>Welcome back, <%=staffCA.getStaffName()%></p>   
               <a class="active" href="StaffHomePage.jsp">Home</a>
               <a href="staffUpdateDetails.jsp">Update Personal Information</a>
               <a href="staffChangePassword.jsp">Change Password</a>
               <a href="StaffLogoutServlet">Logout</a>
               </div>  
        </div>
                   <div class="navBar">
                   <a href="addMeal.jsp">Add new meal</a>
                   <a href="retrieveMeals">Meal Management</a>
                   <a href="staffViewOrders.jsp">View Order</a>
                   <a href="TopUpCredit.jsp">Top Up Credit</a>
                   <a href="FoodRedeem.jsp">Food Redeem</a>
                   <a href="staffGenerateReport.jsp">Generate Report</a>
                   
               </div>
               <div class="formCenter">
    <form method="get" action="ModifyMealServlet">
        <h1>Meal Modify</h1>
    <table>
        <%  
            ArrayList<String> newList = new ArrayList<String>();
            IngredientControl ingredientControl = new IngredientControl();
            ArrayList<Ingredient> arrayIngredient = ingredientControl.getIngredient();
            MealIngredientControl mealIngredientControl = new MealIngredientControl();
            ArrayList<MealIngredient> arrayMealIngredient = mealIngredientControl.getMealIngredient();
            ArrayList<Meal> arrayListMeal = (ArrayList<Meal>)session.getAttribute("Meals");
            String mealId = request.getParameter("mealId");
            
            for(int i = 0 ; i <arrayListMeal.size(); i ++){
                if(mealId.equals(arrayListMeal.get(i).getMealId())){
        %>     
         <tr>
             <td>Meal ID:</td>
             <td><input type="hidden" value="<%=arrayListMeal.get(i).getMealId()%>" name="mealId">
                 <%=arrayListMeal.get(i).getMealId()%></td>
        </tr>
        <tr>
             <td>Meal Name:</td>
             <td><input type="text" value="<%=arrayListMeal.get(i).getMealName()%>" name="mealName" required></td>
        </tr>
        <tr>
             <td>Meal Price:</td>
             <td><input type="number" value="<%=arrayListMeal.get(i).getMealPrice()%>" min=0 name="mealPrice" step="any" required></td>
        </tr>
        <tr>
             <td>Meal Description:</td>
             <td><input type="text" value="<%=arrayListMeal.get(i).getMealDesc()%>" name="mealDesc" required></td>
        </tr>
        <tr>
             <td>Meal Calories:</td>
             <td><input type="number" value="<%=arrayListMeal.get(i).getCalories()%>" name="calories" step="any" required></td>
        </tr>
        <tr>
             <td>Meal Session:</td>
             <%if(arrayListMeal.get(i).getMealSession().equals("Breakfast")) {
                 %>
                  <td><input type="radio" value="<%=arrayListMeal.get(i).getMealSession()%>" name="mealSession" checked required>Breakfast
                  <input type="radio" value="Lunch" name="mealSession" required>Lunch</td>
                  <%
             }else{
                  %>
                  <td><input type="radio" value="Breakfast" name="mealSession" required>Breakfast
                  <input type="radio" value="<%=arrayListMeal.get(i).getMealSession()%>" name="mealSession" checked required>Lunch</td>
                  <%
            }%>
            
        </tr>
        <td>Meal Status:</td>
             <%if(arrayListMeal.get(i).getMealStatus().equals("Available")) {
                 %>
                  <td><input type="hidden" value="<%=arrayListMeal.get(i).getMealStatus()%>" name="mealStatus" checked required>Available
                  <%
             }else{
                  %>
                  <td>
                  <input type="hidden" value="<%=arrayListMeal.get(i).getMealStatus()%>" name="mealStatus" checked required>Unavailable</td>
                  <%
            }%>        <tr><th>Ingredients</th></tr>   
                <% 
                    for(int i2 = 0 ; i2 <arrayMealIngredient.size() ; i2++){
                        for(int i3 = 0  ; i3 < arrayIngredient.size() ; i3++){
                        if(arrayIngredient.get(i3).getIngredientId().equals(arrayMealIngredient.get(i2).getIngredientId())&& arrayMealIngredient.get(i2).getMealId().equals(mealId)){
                        
                        newList.add(arrayMealIngredient.get(i2).getIngredientId());//First i'll add all the current ingredientId into the list.
                %>                
                            <tr>
                            <td><%=arrayIngredient.get(i3).getIngredientName()%></td>
                            <td><input type="number" name="<%=arrayIngredient.get(i3).getIngredientId()%>" value="<%=arrayMealIngredient.get(i2).getQuantityNeeded()%>" min=0></td>                        
                            
                            </tr>
                <%
                     } 
                    }    
                %>                                
                <%
                    }
                  %>
                    <%
                    //I move all the ingredient ID into a arraylist.    
                    ArrayList<String> list1 = new ArrayList<String>();
                     for(int b = 0; b< arrayIngredient.size();b++){
                        list1.add(arrayIngredient.get(b).getIngredientId());
                    }
                    /*Then i will create a arraylist(union) contain of the newList(which is the current ingredientId displayed).
                    with the first arraylist(Ingredient ID list) i created.*/
                    ArrayList<String> union = new ArrayList<String>(list1); 
                    union.addAll(newList);
                    //After that, i create a arraylist which is just the intersection of both list.
                    ArrayList<String> intersection = new ArrayList<String>(list1);
                    //I retain all the value of the both arrayList with same Ingredient ID.
                    intersection.retainAll(newList);
                    //Then i remove duplicated(intersection ingredient Id) from the arrayList(union) with the intersection array list.
                    union.removeAll(intersection);
                    for(int c = 0 ; c <arrayIngredient.size() ; c++){
                        for(int d = 0 ; d < union.size(); d++){
                            if(arrayIngredient.get(c).getIngredientId().equals(union.get(d))){
                               %>
                               <tr>
                               <td><%=arrayIngredient.get(c).getIngredientName()%></td>
                               <td><input type="number" name="<%=arrayIngredient.get(c).getIngredientId()%>" value="0" min=0></td>                        
                               </tr>
                               <%
                            }
                        }
                    }
                %>
        <tr>
             <td><input type="submit" value="Update"required></td>
             <td></td><td><p class="backButton"><a href=retrieveMeals>Back</a></p></td>
        </tr>
        <%                
        }
    }
        %>
             <% String message = (String)request.getAttribute("modifyMealMsg");
                if(message!=null)
                  out.println(message);   
             %>
    </table>
    </form>
    </div>
    </body>
</html>
