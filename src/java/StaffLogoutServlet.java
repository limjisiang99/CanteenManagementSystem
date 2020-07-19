/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class StaffLogoutServlet extends HttpServlet {

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        session.setAttribute("staff",null);
        session.setAttribute("changePwMessage",null);
        session.setAttribute("errorMessage",null);
        session.setAttribute("updateMessage",null);
        session.setAttribute("changePwMessage",null);
        response.sendRedirect("staffLogin.jsp");
    
    }

  

}
