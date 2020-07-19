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

public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
            HttpSession session = request.getSession(true); 
            
            session.setAttribute("username", null);
            session.setAttribute("ID", null);
            session.setAttribute("password", null);
            session.setAttribute("Question", null);
            session.setAttribute("Answer", null);
            session.setAttribute("Email", null);
            session.setAttribute("contactnum", null);
            
            response.sendRedirect("StudentLogin.jsp");
    }
}

