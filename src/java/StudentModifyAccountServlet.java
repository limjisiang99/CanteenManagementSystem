/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import da.StudentDA;
import domain.Student;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lim Ji-Siang
 */
@WebServlet(urlPatterns = {"/StudentLoginServlet"})
public class StudentModifyAccountServlet extends HttpServlet {

   private StudentDA studentDA;
   
 public void init() throws ServletException {
        
        studentDA = new StudentDA();
    }
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
       
        HttpSession session = request.getSession(true);
        String username=(String)session.getAttribute("username");
        String SecretQuestion = request.getParameter("Question");
        String SecretAnswer = request.getParameter("Answer");
        String Email = request.getParameter("Email");
        String ContactNumber = request.getParameter("contactnum");
 
 
        
        Student a = new Student(username,SecretQuestion,SecretAnswer,Email,ContactNumber);
        studentDA.SupdateInfo(a);
 
       
        session.setAttribute("Question", SecretQuestion);
        session.setAttribute("Answer", SecretAnswer);
        session.setAttribute("Email", Email);
        session.setAttribute("contactnum", ContactNumber);
 request.setAttribute("Success", "Account updated");
            request.getRequestDispatcher("/modifyAccount.jsp").forward(request, response);
}
}


    
