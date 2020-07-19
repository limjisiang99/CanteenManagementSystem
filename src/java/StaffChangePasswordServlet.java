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
 * @author USER
 */
public class StaffChangePasswordServlet extends HttpServlet {
    private StaffCanteenAccountControl staffCanteenAccountControl;
    public void init() throws ServletException {
        staffCanteenAccountControl= new StaffCanteenAccountControl();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession(true);
        String currentPassword = request.getParameter("currentPassword");
        String staffPassword = request.getParameter("staffPassword");
        String staffPassword2 = request.getParameter("staffPassword2");
        StaffCanteenAccount staffCA = (StaffCanteenAccount)session.getAttribute("staff");
        //StaffCanteenAccount staffCA = staffCanteenAccountDA.getStaffDetails(staffUsername);
        if(staffCA.getStaffPassword().equals(currentPassword)){
           if(staffPassword.equals(staffPassword2)){
               staffCanteenAccountControl.changePassword(staffPassword,staffCA.getStaffUsername());
              
               //UPDATE THE SESSION WITH NEW STAFF INFORMATION
               staffCA = staffCanteenAccountControl.getStaffDetails(staffCA.getStaffUsername());
               request.setAttribute("changePwMessage","<font color=blue>Your password successfully changed!</font>");
               request.getRequestDispatcher("/staffChangePassword.jsp").forward(request, response);
            }
            else{
               request.setAttribute("changePwMessage","<font color=red>Your password is not matched!</font>");
               request.getRequestDispatcher("/staffChangePassword.jsp").forward(request, response);
           }
        }
        else{
            request.setAttribute("changePwMessage","<font color=red>Your current password is incorrect!</font>");
            request.getRequestDispatcher("/staffChangePassword.jsp").forward(request, response);

        }
    }
}
