/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import control.StaffCanteenAccountControl;
import control.TopUpTransactionControl;
import domain.StaffCanteenAccount;
import domain.Student;
import domain.TopUpTransaction;
import java.io.IOException;
import java.io.PrintWriter;
//import java.text.SimpleDateFormat;
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
public class TopUpTransactionServlet extends HttpServlet {
    private TopUpTransactionControl transactionControl;
    private StaffCanteenAccountControl staffCanteenAccountControl;
    
   public void init() throws ServletException {
        transactionControl = new TopUpTransactionControl();
        staffCanteenAccountControl = new StaffCanteenAccountControl();
    }
    //String pattern = "dd-MM-yyyy";
    //SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    Date date = new Date();
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    //private String date = simpleDateFormat.format(new Date());
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        StaffCanteenAccount staffCA = (StaffCanteenAccount)session.getAttribute("staff");
        String studUsername = request.getParameter("studUsername");
        String amount = request.getParameter("amount");
        
        if(staffCanteenAccountControl.topUpCredit(studUsername,Double.parseDouble(amount)) == true){
            String lastTopUpTransactionId = transactionControl.getLastTopUpTransactionId();
            String nextTopUpTransactionId = null;
            if(lastTopUpTransactionId==null)
                nextTopUpTransactionId ="T1001";
            else{
                nextTopUpTransactionId = String.format("T%d",(Integer.parseInt(lastTopUpTransactionId.substring(1,5)) + 1));
            }
            TopUpTransaction transaction = new TopUpTransaction(nextTopUpTransactionId,studUsername,staffCA.getStaffUsername(),Double.parseDouble(amount),sqlDate);
            transactionControl.addRecord(transaction);
            request.setAttribute("topUpMessage", "<font color=blue>Process succesfully!!!</font>");
            Student studInfo = staffCanteenAccountControl.getStudInfo(studUsername);
            request.setAttribute("topUpAmount",amount);
            request.setAttribute("studInfo",studInfo);
            request.getRequestDispatcher("/TopUpCredit.jsp").forward(request, response);
        }else{
            request.setAttribute("topUpMessage", "<font color=red>Sorry , incorrect student username.</font>");
            request.getRequestDispatcher("/TopUpCredit.jsp").forward(request, response);
        }
           
        
    }
}
