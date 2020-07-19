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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lim Ji-Siang
 */
public class ForgetPasswordServlet extends HttpServlet {

    private StudentDA studentDA;
    public void init() throws ServletException {
        
        studentDA = new StudentDA();
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("username");
        String question = request.getParameter("Question");
        String answer = request.getParameter("answer");       
         PrintWriter out = response.getWriter();
        Student a = new Student(username,question,answer);
        Student b = studentDA.Sretrieve(a);
        
        if(b==null){
            request.setAttribute("ErrorMsg", "Input information doesnt match an account");
            request.getRequestDispatcher("/forgetPassword.jsp").forward(request, response);
        }
        if(username.equals(b.getStudUsername()) && question.equals(b.getSecretQuestion()) && answer.equals(b.getSecretAnswer())){
            request.setAttribute("password", b.getPassword());      
            request.getRequestDispatcher("/forgetPassword.jsp").forward(request, response);
        }
        else{
        request.setAttribute("ErrorMsg", "Input information doesnt match an account");
            request.getRequestDispatcher("/forgetPassword.jsp").forward(request, response);
        }
    }
}
