/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import control.StaffCanteenAccountControl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.StaffCanteenAccount;
import javax.servlet.http.HttpSession;
/**
 *
 * @author NGO KIAN HEE
 */
public class StaffUpdateServlet extends HttpServlet {
    
    private StaffCanteenAccountControl staffCanteenAccountControl;
    
    public void init() throws ServletException {
        
        staffCanteenAccountControl = new StaffCanteenAccountControl();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession(true);
        String staffName = request.getParameter("staffName");
        String staffIC = request.getParameter("staffIC");
        String staffEmail = request.getParameter("staffEmail");
        String staffPhoneNo = request.getParameter("staffPhoneNo");
        StaffCanteenAccount staffCA = (StaffCanteenAccount)session.getAttribute("staff");
        staffCanteenAccountControl.updateStaffDetails(staffName, staffIC, staffEmail, staffPhoneNo,staffCA.getStaffUsername());
        //request.setAttribute("updateMessage", "Your account details is updated!");
        //request.getRequestDispatcher("/staffUpdateDetails.jsp").forward(request, response);    
        
        //UPDATE THE SESSION WITH NEW STAFF INFORMATION
        staffCA = staffCanteenAccountControl.getStaffDetails(staffCA.getStaffUsername());
        session.setAttribute("staff",staffCA);
        session.setAttribute("updateMessage", "Your account details is updated!");
        response.sendRedirect("staffUpdateDetails.jsp");
}
}
