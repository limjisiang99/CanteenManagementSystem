/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import control.StaffCanteenAccountControl;
import da.StaffDA;
import domain.StaffCanteenAccount;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ngo Kian Hee
 */
public class StaffRegistrationServlet extends HttpServlet {
    private StaffDA staffDA;
    private StaffCanteenAccountControl staffCanteenAccountControl;
    public void init() throws ServletException {
        staffDA = new StaffDA();
        staffCanteenAccountControl = new StaffCanteenAccountControl();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String staffId = request.getParameter("staffId");
        String staffUsername = request.getParameter("staffUsername");
        String staffName = request.getParameter("staffName");
        String staffIC = request.getParameter("staffIC");
        String staffEmail = request.getParameter("staffEmail");
        String staffPhoneNo = request.getParameter("staffPhoneNo");
        String staffPassword = request.getParameter("staffPassword");
        String staffPassword2 = request.getParameter("staffPassword2");
        //1 is mean found. If staff is valid in the staff file and the staff should not be found in staff canteen account. 
        //Because one staff can only have one staff canteen account.
        if(staffDA.getStaffRecord(staffId)==1 && staffCanteenAccountControl.getStaffId(staffId)!=1){
                if(staffCanteenAccountControl.getStaffUsername(staffUsername)!=1){
                    if(staffPassword.equals(staffPassword2)){
                        StaffCanteenAccount staffDetails = new StaffCanteenAccount(staffUsername,staffId,staffPassword,staffName,staffIC,staffEmail,staffPhoneNo);
                        staffCanteenAccountControl.addRecord(staffDetails);
                        request.getRequestDispatcher("/StaffCASignUpSuccess.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("errorMessage", "Process failed, password is not match!");
                        request.getRequestDispatcher("/staffRegistration.jsp").forward(request, response);
                    }
                }
                else{
                    request.setAttribute("errorMessage", "Process failed, username is used!");
                    request.getRequestDispatcher("/staffRegistration.jsp").forward(request, response);
                }
        }
        else{
            request.setAttribute("errorMessage", "Process failed, Staff ID invalid!");
            request.getRequestDispatcher("/staffRegistration.jsp").forward(request, response);
        }   
    }
}
