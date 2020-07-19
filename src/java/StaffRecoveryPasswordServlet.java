/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import control.StaffCanteenAccountControl;
import domain.StaffCanteenAccount;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author NGO KIAN HEE
 */
public class StaffRecoveryPasswordServlet extends HttpServlet {
 //private StaffCanteenAccountDA staffCanteenAccountDA;
    private StaffCanteenAccountControl staffCanteenAccountControl;
    public void init() throws ServletException {
        //staffCanteenAccountDA= new StaffCanteenAccountDA();
        staffCanteenAccountControl = new StaffCanteenAccountControl();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession(true);
        String staffUsername = request.getParameter("staffUsername");
        String staffIC = request.getParameter("staffIC");
        StaffCanteenAccount staff = staffCanteenAccountControl.getStaffDetails(staffUsername);
        if(staff==null){
               //session.setAttribute("errMsg", "Sorry, invalid username or ic.");
               //response.sendRedirect("staffRecoveryPassword.jsp");
               request.setAttribute("errMsg", "Sorry, invalid username or ic.");
               request.getRequestDispatcher("/staffRecoveryPassword.jsp").forward(request, response);
        }
        else if(staff.getStaffUsername().equals(staffUsername) && staff.getStaffIc().equals(staffIC)){
            //session.setAttribute("displayPw", staff.getStaffPassword());
            //response.sendRedirect("staffRecoveryPassword.jsp");
            request.setAttribute("displayPw", staff.getStaffPassword());
            request.getRequestDispatcher("/staffRecoveryPassword.jsp").include(request, response);
        }
        else{
            //session.setAttribute("errMsg", "Sorry, invalid username or ic.");
            //response.sendRedirect("staffRecoveryPassword.jsp");
            request.setAttribute("errMsg", "Sorry, invalid username or ic.");
            request.getRequestDispatcher("/staffRecoveryPassword.jsp").forward(request, response);
        }
    }
}
