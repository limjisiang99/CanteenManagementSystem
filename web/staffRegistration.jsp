<%-- 
    Document   : staffRegistration
    Created on : Mar 22, 2019, 1:46:31 AM
    Author     : NGO KIAN HEE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Staff Registration</title>
    
    <style>
        body{
    margin: 0;
    padding: 0;
    background-size: cover;
    background-position: center;
    font-family: sans-serif;
    height: auto;
    background-image: url("./staffFolder/loginBackgroundImage.png");
    
}

.loginbox{
    width: 50%;
    height: auto;
    background:rgb(224, 235, 235,0.4);
    color:darkblue;
    top: 405px;
    left: 50%;
    position: absolute;
    transform: translate(-50%,-50%);
    box-sizing: border-box;
    padding: 70px 30px;
}

.avatar{
    width: 100px;
    height: 100px;
    border-radius: 50%;
    position: absolute;
    top: -50px;
    left: calc(50% - 50px);
}

h1{
    margin: 0;
    padding: 0 0 20px;
    text-align: center;
    font-size: 22px;
}

.loginbox p{
    margin: 0;
    padding: 0;
    font-weight: bold;
}

.loginbox input{
    width: 100%;
    margin-bottom: 10px;
}

.loginbox input[type="text"], input[type="password"],input[type="email"],input[type="tel"]
{
    border: none;
    border-bottom: 1px solid #fff;
    background: transparent;
    outline: none;
    height: 40px;
    color: black;
    font-size: 16px;
}
.loginbox input[type="submit"],input[type="reset"]
{
    border: none;
    outline: none;
    height: 40px;
    background: #fb2525;
    color: #fff;
    font-size: 18px;
    border-radius: 20px;
}
.loginbox input[type="submit"]:hover ,input[type="reset"]:hover 
{
    cursor: pointer;
    background: #ffc107;
    color:#000;
}
.loginbox a{
    text-decoration: none;
    font-size: 20px;
    line-height: 30px;
    color: darkgrey;
}
.loginbox a:hover
{
    color: #ffc107;
}
    </style>
  
     <script>
        function AvoidSpace(event) {
            var k = event ? event.which : window.event.keyCode;
            if (k == 32) return false;
       }
       </script>
         </head>
    <body>
       
        <%--<div class="header">
                <h1>Hot Cafe</h1>
               <div class="header-right">
                   <a href="StudentLogin.jsp">Student</a>
                   <a class="active" href="staffLogin.jsp">Staff</a>
               </div>
        </div>  --%>     
       <div class="loginbox">
        <h1>Staff Registration</h1>
        <br>
         <% String errorMsg = (String)request.getAttribute("errorMessage");
                if(errorMsg!=null)
                    out.println("<font color=red>" + errorMsg + "</font>");  
                %>
               <br>
               <br>
              
           <form method="post" action="StaffRegistrationServlet">   
                <p>Staff ID</p>
                <input type="text" name="staffId" placeholder="Enter your Staff ID" required="required">
                <p>Username</p>
                <input type="text" name="staffUsername" placeholder="Enter your username" onkeypress="return AvoidSpace(event)" required="required">   
                <p>Name</p>
                <input type="text" name="staffName"  required="required">
                <p>IC</p>
                <input type="text" name="staffIC" pattern="[0-9]{12}" placeholder="990122101234" required="required">
                <%--<label style="font-size:12.5px;padding-left:20px">Eg: 990122101234  </label>--%>
                <p>Email</p>
                <input type="email" name="staffEmail" placeholder="sample@example.com" required="required">
                <p>Phone Number</p>
                <input type="tel" name="staffPhoneNo" pattern="[0-9]{10}" placeholder="0123456789" required="required">
                <%--<label style="font-size:12.5px;padding-left:20px">Eg: 0123456789  </label>--%>
                <p>Password</p>
                <input type="password" name="staffPassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required="required">
                <p>Re-enter password</p>
                <input type="password" name="staffPassword2" required="required">
                <input  type="submit" value="Register">
                <input  type="reset" value="Reset">
        </form>
                <a href="staffLogin.jsp">Back</a><br>
   
       
        </div>
    </body>
</html>
