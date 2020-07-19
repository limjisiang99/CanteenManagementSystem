<%-- 
    Document   : StaffDisplayReport
    Created on : Mar 31, 2019, 10:03:18 PM
    Author     : NGO KIAN HEE
--%>

<%@page import="domain.MealCancel"%>
<%@page import="domain.PopularMeal"%>
<%@page import="domain.OrderLine"%>
<%@page import="domain.Meal"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="domain.TopUpTransaction"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="staffHeaderFooter.css" type="text/css">
        
        <title>Report</title>
        <script>
        
            function printDiv(divName){
			var printContents = document.getElementById(divName).innerHTML;
			var originalContents = document.body.innerHTML;
			document.body.innerHTML = printContents;
			window.print();
			document.body.innerHTML = originalContents;
		}
        
     </script>
     <style>
         body{
             background-color:white;
         }
        table{
              border-collapse: collapse;  
        }
        th{
            border-left:1px solid black;
            border-right:1px solid black;  
            border-top:1px solid black;
            border-bottom:1px solid black;
            padding-right:15px;
        }
        .lineLeftRight td{
            padding-top:10px;
            border-left:1px solid black;
            border-right:1px solid black;
        }
        .lineLeftRightSplit td{
            padding-top:10px;
            border-left:1px solid black;
            border-right:1px solid black;
            border-bottom:1px solid black;
        }
        .cancelMealBorderBottom th{
            border-top:0px;
            border-left:0px;
            border-right:0px;
            border-bottom:1px solid black;
            text-align:left;
            padding-top:10px;
        }
        td{
             padding-bottom:6px;
        }
        .cancelMealBorderTop td{
            border-top:1px solid black;
            padding-top:10px;
        }
        .totalTopLine {
            padding-top:0px;
            padding-bottom:0px;
            border-top:1px solid black;
        }
        .gotLeftRight{
            border-left:1px solid black;
            border-right:1px solid black; 
           
        }
        .gotLeftRightTop{
            border-left:1px solid black;
            border-right:1px solid black;
            border-top:1px solid black;
           
        }
        .noLeftRight{
            border-left:0px;
            border-right:0px;
        }
        .topBottomLine{
            border-top:1px solid black;
            border-bottom:1px solid black;      
        }
        .topLine{
            border-top:1px solid black;
        }
        
        .bottomLine{
            border-bottom:1px solid black;
        }
        .gotLeftRightBottomTop{
            border-top:1px solid black;
            border-left:1px solid black;
            border-right:1px solid black;
            border-bottom:1px solid black;
        }
        .rightBottomTop{
            border-top:1px solid black;
            border-right:1px solid black;
            border-bottom:1px solid black;
        }
        .rightOnly{
            border-right:1px solid black;
        }
        .rightBottomOnly{
            border-left:0px solid black;
            border-right:1px solid black;
            border-bottom:1px solid black;
        }
</style>
    </head>
        
    <body>
        <div id="printReport">
            <%DateFormat changeFormat = new SimpleDateFormat("dd-MM-yyyy");%>
             <table align="center">
                <caption><h1>Hot Café Sdn Bhd</h1><b><u><%=request.getAttribute("reportName")%></u></b>
                    <br><b>Generate on : </b><u><%=request.getAttribute("generateDate")%></u>
                    <br><b>Starting Date: </b><u><%=changeFormat.format((Date)request.getAttribute("startingDate"))%></u> <b>Ending Date : </b><u><%=changeFormat.format((Date)request.getAttribute("endingDate"))%></u>
                </caption>
            <%String reportType = (String)request.getAttribute("reportType");
            if(reportType.equals("transactionReport")){
              ArrayList<TopUpTransaction> transactionReport = (ArrayList<TopUpTransaction>)request.getAttribute("report");
                if(transactionReport!=null){
            %>
            
           
                <tr class="title">
                    <th>Date:</th><th>Transaction ID</th><th>Student Username</th><th>Handle by:</th><th>Top Up Amount(RM)</th>
                </tr>
                      <%
                         double subTotal=0;
                         double grandTotal=0;
                         Date tempDate = new Date();
                         Date tempDate2 = transactionReport.get(0).getDate();
                         
                         String updatedDateFormat = null;
                          for(int i =0 ;i <transactionReport.size();i++){
                              if(!tempDate2.equals(transactionReport.get(i).getDate())){
                                 out.println("<tr class=lineLeftRightSplit><td></td> <td></td> <td></td>");
                                 out.println("<td><b>Subtotal :</b></td><td class=subtotal><b><u>" + subTotal +"</u></b></td></tr>");
                                 subTotal=0;
                                 tempDate2 =transactionReport.get(i).getDate(); 
                              }
                              //If the date output String is same. Then it will print.
                              //Eg: inside the tempDate the output will be "Wed Apr 03 00:44:22 SGT 2019". But the database output format wil be "2019-04-01" 
                              if(tempDate.equals(transactionReport.get(i).getDate())){
                                  out.println("<tr class=lineLeftRight><td></td>");
                              }else{
                                  updatedDateFormat = changeFormat.format(transactionReport.get(i).getDate());
                                  out.println("<tr class=lineLeftRight>");
                                  out.println("<td><b>"+updatedDateFormat+"</b></td>");
                                  tempDate=transactionReport.get(i).getDate();
                              }
                              %>
                              <td><%=transactionReport.get(i).getTopUpId()%></td>
                              <td><%=transactionReport.get(i).getStudUsername()%></td>
                              <td><%=transactionReport.get(i).getStaffUsername()%></td>
                              <td><%=String.format("%.2f",transactionReport.get(i).getAmount())%></td>
                              <%
                                  out.println("</tr>");
                                  grandTotal+=transactionReport.get(i).getAmount();
                                  if(tempDate2.equals(transactionReport.get(i).getDate())){
                                       subTotal+=transactionReport.get(i).getAmount();   
                                  }                  
                          }
                      %>
                <tr class="lineLeftRightSplit">
                      <td></td> <td></td> <td></td> 
                      <td>
                          <b>SubTotal:</b>
                      </td>
                      <td><b><u><%=String.format("%.2f",subTotal)%></u></b></td>
                </tr>
                <tr class="lineLeftRightSplit">
                      <td></td> <td></td> <td></td>
                      <td>
                          <b>Grand Total:</b>
                      </td>
                      <td><b><u><%=String.format("%.2f",grandTotal)%></u></b></td>
                </tr>
                <tr>
                      <td><br>---------------------<br>
                              End of Report
                      </td>    
                </tr>
                 <%--<tr>
                      <td>  <input type="submit" onclick="myFunction()" value="Print now"></td>
                       <td>  <a class="modifyButton" href="staffGenerateReport.jsp">Back</a></td> 
                </tr>--%>
             </table>
          <%
                }
            }else if(reportType.equals("exceptionReport")){
                //int totalCancelled = 0;
                int grandTotalCancelled = 0;
                /*out.println("<table align=center>");
                out.println("<caption><h1>Hot Café Sdn Bhd</h1><b><u>Meal Cancellation Report</u></b><br>"
                            +"<b>Generate on:</b><u>"+session.getAttribute("generateDate") +
                            "</u><br><b>Starting Date:</b><u>"+session.getAttribute("startingDate")
                            +"</u><b>Ending Date :</b><u>"+session.getAttribute("endingDate")+"</u></caption>");*/
                out.println("<tr class=cancelMealBorderBottom><th>Meal ID</th><th>Meal Name</th><th>Quantity Cancelled</th></tr>");
                ArrayList<MealCancel> cancelledMeals = (ArrayList<MealCancel>)request.getAttribute("report");
                //ArrayList<OrderLine> orderLines = (ArrayList<OrderLine>)request.getAttribute("orderLines");
                Date startingDate = (Date)request.getAttribute("startingDate");
                Date endingDate = (Date)request.getAttribute("endingDate");
               // Date orderRedeemDate = null;
                for(int i =0; i< cancelledMeals.size();i++){
                    out.println("<tr>");
                    out.println("<td>"+cancelledMeals.get(i).getMealId()+"</td>"
                               +"<td>"+cancelledMeals.get(i).getMealName()+"</td>" 
                               +"<td>"+cancelledMeals.get(i).getQuantityCancelled()+"</td></tr>");
                   grandTotalCancelled+= cancelledMeals.get(i).getQuantityCancelled();
                }
                out.println("<tr class=cancelMealBorderTop><td></td><td><b>Grand Total </b></td><td>"+grandTotalCancelled+"</td></tr>");
                out.println("<tr><td><b>"+cancelledMeals.get(0).getMealId()+"," +cancelledMeals.get(0).getMealName()+" </b>has the highest meal cancellation.</tr>");
                out.println("<tr><td><b>"+cancelledMeals.get((cancelledMeals.size()-1)).getMealId()+"," +cancelledMeals.get((cancelledMeals.size()-1)).getMealName()+" </b>has the least meal cancellation.</tr>");
                out.println("<tr><td><br>---------------------<br>End of Report</td></tr>");
                //out.println("<tr><td><input type=submit onclick=myFunction() value='Print now'></td><td> <a class=modifyButton href=staffGenerateReport.jsp>Back</a></td></tr>");
                
                out.println("</table>");
            }else if(reportType.equals("summaryReport")){
                String tempMealId = "";
                Date tempDate = new Date();
                double subTotal = 0;
                double grandTotal = 0;
                int quantityOrdered =0;
                int grandTotalQty = 0;
                int number = 1;
                /*out.println("<table align=center>");
                out.println("<caption><h1>Hot CafÃ©</h1>Popular Meal Report<br>"
                            +"Generate on:"+session.getAttribute("generateDate") +
                            "<br>Starting Date:"+session.getAttribute("startingDate")
                            +"Ending Date:"+session.getAttribute("endingDate")+"</caption>");*/
                out.println("<tr><th>No</th><th class=noLeftRight>Meal ID</th><th class=noLeftRight>Meal Name</th><th class=noLeftRight>Unit Price</th><th class=noLeftRight>Date Redeem</th><th class=noLeftRight>Quantity</th><th class=rightBottomOnly>Subtotal(RM)</th></tr>");
                ArrayList<Meal> popularMeals = (ArrayList<Meal>)request.getAttribute("report");
                ArrayList<PopularMeal> popularMeals2 = (ArrayList<PopularMeal>)request.getAttribute("popularMeals2");
                Date startingDate = (Date)request.getAttribute("startingDate");
                Date endingDate = (Date)request.getAttribute("endingDate");
              
                for(int i =0; i< popularMeals.size();i++){
                   for(int i2 = 0; i2< popularMeals2.size();i2++){
                      
                       if(popularMeals.get(i).getMealId().equals(popularMeals2.get(i2).getMealId())){
                             out.println("<tr>");
                            if(tempMealId.equals(popularMeals2.get(i2).getMealId())){
                                out.println("<td class=gotLeftRight></td>"
                                             +"<td></td>"
                                             +"<td></td>"
                                             +"<td></td>"
                                             +"<td>"+popularMeals2.get(i2).getOrderRedeemDate()+"</td>"
                                             +"<td>"+popularMeals2.get(i2).getQuantityOrdered()+"</td>"
                                             +"<td class=rightOnly>"+String.format("%.2f",popularMeals2.get(i2).getSubTotal())+"</td>");
                                            subTotal+=popularMeals2.get(i2).getSubTotal();
                                            quantityOrdered+=popularMeals2.get(i2).getQuantityOrdered();
                            }else{
                                 subTotal = 0;
                                 quantityOrdered = 0; 
                                 out.println("<td class=gotLeftRightTop>" + number + "</<td>"
                                             +"<td class=totalTopLine>"+popularMeals.get(i).getMealId()+"</td>"
                                             +"<td class=totalTopLine>"+popularMeals.get(i).getMealName()+"</td>"
                                             +"<td class=totalTopLine>"+String.format("%.2f",popularMeals.get(i).getMealPrice())+"</td>"
                                               
                                             +"<td class=totalTopLine>"+popularMeals2.get(i2).getOrderRedeemDate()+"</td>"
                                             +"<td class=totalTopLine>"+popularMeals2.get(i2).getQuantityOrdered()+"</td>"
                                             +"<td class=rightOnly>"+String.format("%.2f",popularMeals2.get(i2).getSubTotal())+"</td>");
                                 tempMealId = popularMeals.get(i).getMealId();
                                 
                                 subTotal+=popularMeals2.get(i2).getSubTotal();
                                 quantityOrdered+=popularMeals2.get(i2).getQuantityOrdered();
                                 number++;
                            }
                            grandTotal+=popularMeals2.get(i2).getSubTotal();
                            grandTotalQty+=popularMeals2.get(i2).getQuantityOrdered();
                            out.println("</tr>");    
                        }
                    }
                    out.println("<tr ><td class=gotLeftRight></td><td></td><td></td><td></td><td class=totalTopLine><b>Total:</b></td><td class=totalTopLine><u>"+quantityOrdered+"</u></td><td class=rightBottomTop><u>"+String.format("%.2f",subTotal)+"</u></td></tr>");
                }
                out.println("<tr><td class=gotLeftRightBottomTop></td><td class=topBottomLine></td><td class=topBottomLine></td><td class=topBottomLine></td><td class=topBottomLine><b>GrandTotal:</b></td><td class=topBottomLine><u>"+grandTotalQty+"</u></td><td class=rightBottomTop><u>"+String.format("%.2f",grandTotal)+"</u></td></tr>");
                
                //out.println("</div>");
                //out.println("<tr><td><button onclick=printDiv('print')>Print now</button></td><td> <a class=modifyButton href=staffGenerateReport.jsp>Back</a></td></tr>");
               // out.println("</table>");
               out.println("</table>");
               out.println("<table align=center>");
               out.println("<tr><td><b>"+popularMeals.get(0).getMealId()+"," +popularMeals.get(0).getMealName()+"</b> has the highest meal ordered quantity.</tr>");
               out.println("<tr><td><b>"+popularMeals.get((popularMeals.size()-1)).getMealId()+"," +popularMeals.get((popularMeals.size()-1)).getMealName()+" </b>has the least meal ordered quantity.</tr>");
               out.println("<tr><td><br>---------------------------------<br>End of Report</td></tr>");
               out.println("</table>");
            }
             
          %>
          
             </div>
             <table align="center">
                 <tr><td><button class="modifyButton" onclick="printDiv('printReport')">Print now</button></td>
                 <td><a class=modifyButton href=staffGenerateReport.jsp>Back</a></td></tr>
             </table>
    </body>
</html>
