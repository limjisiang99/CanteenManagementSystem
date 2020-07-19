/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import control.MealControl;
import control.StaffCanteenAccountControl;
import control.TopUpTransactionControl;
import domain.Meal;
import domain.MealCancel;
import domain.OrderLine;
import domain.PopularMeal;
import domain.TopUpTransaction;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author NGO KIAN HEE
 */
public class ReportServlet extends HttpServlet {
    private TopUpTransactionControl transactionControl;
    private MealControl mealControl;
    private StaffCanteenAccountControl staffCanteenAccoutnControl;
    public void init() throws ServletException {
        transactionControl = new TopUpTransactionControl();
        mealControl = new  MealControl();
        staffCanteenAccoutnControl = new StaffCanteenAccountControl();
    }
    public String formatDatePattern(String date)throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = format.parse(date);
        DateFormat changeFormat = new SimpleDateFormat("dd-MM-yyyy");
       return changeFormat.format(date2); 
    }
    String pattern = "dd-MM-yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    private String generateDate = simpleDateFormat.format(new Date());
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter(); 
            String reportType = request.getParameter("reportType");
            String startingDate = request.getParameter("startingDate");
            String endingDate = request.getParameter("endingDate");
            
            if(reportType.equals("transactionReport")){
               ArrayList<TopUpTransaction> transaction = transactionControl.getTransactions(startingDate,endingDate);
               
               if(transaction.isEmpty()){
                   request.setAttribute("reportMessage","<font color=red><br>Sorry, no record found for <br><br>Starting Date:" +startingDate +"<br>Ending Date:" +endingDate+"</font>");
                   request.getRequestDispatcher("/staffGenerateReport.jsp").forward(request, response);
               }
               else{
                   //HttpSession session = request.getSession();
                   request.setAttribute("report",transaction);
                   request.setAttribute("reportType",reportType);
                   request.setAttribute("reportName","Top Up Transaction Report"); 
                   try{
                        Date utilStartingDate = new SimpleDateFormat("yyyy-MM-dd").parse(startingDate);
                        Date utilEndingDate = new SimpleDateFormat("yyyy-MM-dd").parse(endingDate);
                        java.sql.Date sqlStartingDate = new java.sql.Date(utilStartingDate.getTime());
                        java.sql.Date sqlEndingDate = new java.sql.Date(utilEndingDate.getTime());
                        request.setAttribute("startingDate",sqlStartingDate);
                        request.setAttribute("endingDate",sqlEndingDate);
                   }catch(Exception ex){
                    
                   }
                   request.setAttribute("generateDate",generateDate);
                   request.getRequestDispatcher("/StaffDisplayReport.jsp").forward(request, response);
               }      
            }else if(reportType.equals("exceptionReport")){
                ArrayList<MealCancel> cancelledMeals = mealControl.getCancelledMeal(startingDate,endingDate);
                //ArrayList<OrderLine> orderLines = staffCanteenAccoutnControl.getOrderLine();
                 if(cancelledMeals.isEmpty()){
                   request.setAttribute("reportMessage","<font color=red><br>Sorry, no record found for <br><br>Starting Date:" +startingDate +"<br>Ending Date:" +endingDate+"</font>");
                   request.getRequestDispatcher("/staffGenerateReport.jsp").forward(request, response);
                   //response.sendRedirect("staffGenerateReport.jsp");
               }
               else{
                   //HttpSession session = request.getSession();
                   request.setAttribute("report",cancelledMeals);
                   request.setAttribute("reportType",reportType);
                   //request.setAttribute("orderLines",orderLines);
                   request.setAttribute("reportName","Meal Cancellation Report");

                   try{
                        Date utilStartingDate = new SimpleDateFormat("yyyy-MM-dd").parse(startingDate);
                        Date utilEndingDate = new SimpleDateFormat("yyyy-MM-dd").parse(endingDate);
                        java.sql.Date sqlStartingDate = new java.sql.Date(utilStartingDate.getTime());
                        java.sql.Date sqlEndingDate = new java.sql.Date(utilEndingDate.getTime());
                        request.setAttribute("startingDate",sqlStartingDate);
                        request.setAttribute("endingDate",sqlEndingDate);
                   }catch(Exception ex){
                    
                   }
                   request.setAttribute("generateDate",generateDate);
                   request.getRequestDispatcher("/StaffDisplayReport.jsp").forward(request, response);
               } 
            }
            else if(reportType.equals("summaryReport")){
                ArrayList<Meal> popularMeals = mealControl.getPopularMeal(startingDate,endingDate);
                ArrayList<PopularMeal> popularMeals2 = mealControl.getPopularMeal2(startingDate, endingDate);
                 if(popularMeals.isEmpty() || popularMeals2.isEmpty()){
                   request.setAttribute("reportMessage","<font color=red><br>Sorry, no record found for <br><br>Starting Date:" +startingDate +"<br>Ending Date:" +endingDate+"</font>");
                   request.getRequestDispatcher("/staffGenerateReport.jsp").forward(request, response);
               }
               else{
                   //HttpSession session = request.getSession();
                   request.setAttribute("report",popularMeals);
                   request.setAttribute("reportType",reportType);
                   request.setAttribute("popularMeals2",popularMeals2);
                   request.setAttribute("reportName","Popular Meal Report");
                   try{
                        Date utilStartingDate = new SimpleDateFormat("yyyy-MM-dd").parse(startingDate);
                        Date utilEndingDate = new SimpleDateFormat("yyyy-MM-dd").parse(endingDate);
                        java.sql.Date sqlStartingDate = new java.sql.Date(utilStartingDate.getTime());
                        java.sql.Date sqlEndingDate = new java.sql.Date(utilEndingDate.getTime());
                        request.setAttribute("startingDate",sqlStartingDate);
                        request.setAttribute("endingDate",sqlEndingDate);
                   }catch(Exception ex){
                    
                   }
                   request.setAttribute("generateDate",generateDate);
                   request.getRequestDispatcher("/StaffDisplayReport.jsp").forward(request, response);
               }     
            }
            /*try{
            if(reportType.equals("transactionReport")){
                ArrayList<TopUpTransaction> transaction = transactionControl.getTransactions(startingDate,endingDate);
                for(int i = 0; i <transaction.size();i++){
                    if(transaction.get(i).getDate().compareTo(convertDate(startingDate))>=0 && transaction.get(i).getDate().compareTo(convertDate(endingDate)) <= 0){
                    }
                }
            }
              }catch(Exception ex){
                
            }*/
            
    }
}
