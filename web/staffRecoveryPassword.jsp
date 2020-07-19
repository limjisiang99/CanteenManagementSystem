<%-- 
    Document   : staffRecoveryPassword
    Created on : Mar 25, 2019, 4:47:36 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="staffHeaderFooter.css" type="text/css">
        <title>Recovery Password</title>
    </head>
    <body>
       
       <div class="header">
                <h1>Hot Café</h1>
               <div class="header-right">
                   <a href="StudentLogin.jsp">Student</a>
                   <a href="staffLogin.jsp">Staff</a>
               </div>
        </div>
        <div class="formCenter">
         <form method="post" action="StaffRecoveryPasswordServlet">   
             <h1>Staff Recovery Password</h1>
             <table>
                 <tr><td>Username</td>
                     <td><input type="text" name="staffUsername" required="required"></td>
                 </tr>
                 <tr><td>IC</td>  
                     <td><input type="text" name="staffIC" required="required"></td>
                 </tr> 
            </table>
         <input type="submit" value="Submit">
         <br><br>
            <%  //String errMsg = (String)session.getAttribute("errMsg");
            String errMsg = (String)request.getAttribute("errMsg");
            //String displayPw = (String)session.getAttribute("displayPw");
            String displayPw = (String)request.getAttribute("displayPw");
                if(errMsg!=null){
                  out.println("<font color=red>" + errMsg + "</font>");  
                  //session.setAttribute("errMsg",null);
                }
                if(displayPw !=null){
                  out.println("Your password is <font color=blue>" + displayPw +"</font>");
                  out.println("<br><a href =staffLogin.jsp>Login now!</a>");
                  //session.setAttribute("displayPw",null);
                } 
          %>
         </form>
        </div>
       
        
    </body>
</html>
