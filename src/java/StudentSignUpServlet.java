/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.Student;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import control.StudentControl;
import da.StudentDA;
import da.StudentSchoolDA;

/**
 *
 * @author Lim Ji-Siang
 */

public class StudentSignUpServlet extends HttpServlet {
    
    private StudentDA studentDA;
    private StudentSchoolDA studentSchoolDA;
    
    public void init() throws ServletException {
        studentDA = new StudentDA();
        studentSchoolDA = new StudentSchoolDA();
        
    }
    
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String UserName = request.getParameter("username");
        String StudentID = request.getParameter("ID");
        String password = request.getParameter("password");
        String SecretQuestion = request.getParameter("Question");
        String SecretAnswer = request.getParameter("Answer");
        String Email = request.getParameter("Email");
        String ContactNumber = request.getParameter("contactnum");
           
        
       
        String b = studentDA.SgetRecord(UserName);
        int a = studentSchoolDA.getCurrentRecord(StudentID);
       
        if(UserName.equals(b)|| a==0 ){
            request.setAttribute("ErrorMsg", "SignUp failed, Username chosen or student ID not found in database!! please select new username");
            request.getRequestDispatcher("StudentSignup.jsp").forward(request, response);
        }
        
        else
        {
            Student c = new Student(UserName,StudentID,password,'0',SecretQuestion,SecretAnswer,Email,ContactNumber);
            int i = studentDA.Sinsert(c);
             if(i==0)
                response.sendRedirect("SignupSuccess.jsp");
            
        }
        
        out.println("hi");
        
}
}
