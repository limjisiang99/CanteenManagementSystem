/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import control.MealControl;
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
public class StaffLoginServlet extends HttpServlet {
    //private StaffCanteenAccountDA staffCanteenAccountDA;
    private StaffCanteenAccountControl staffCanteenAccountControl;
    private MealControl mealControl;

    public void init() throws ServletException {
        //staffCanteenAccountDA= new StaffCanteenAccountDA();
        staffCanteenAccountControl = new StaffCanteenAccountControl();
        mealControl = new MealControl();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        String staffUsername = request.getParameter("staffUsername");
        String staffPassword = request.getParameter("staffPassword");
        HttpSession session=request.getSession();
        StaffCanteenAccount staff = staffCanteenAccountControl.getStaffDetails(staffUsername);
        if(staff==null){
               request.setAttribute("errorMessage", "Sorry, invalid username or password.");
               request.getRequestDispatcher("/staffLogin.jsp").forward(request, response);
        }        
        else if(staff.getStaffUsername().equals(staffUsername) && staff.getStaffPassword().equals(staffPassword)){
            session.setAttribute("staff", staff);
            session.setAttribute("Meals", mealControl.getMeals());

            response.sendRedirect("StaffHomePage.jsp");
        }
        else{
            request.setAttribute("errorMessage", "Sorry, invalid username or password.");
            request.getRequestDispatcher("/staffLogin.jsp").forward(request, response);
        }
    }
}

