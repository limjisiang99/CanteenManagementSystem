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
import control.StudentControl;
import domain.Student;
import java.util.ArrayList;
import da.StudentDA;
import static java.lang.System.out;
import javax.servlet.annotation.WebServlet;
/**
 *
 * @author Lim Ji-Siang
 */
@WebServlet(urlPatterns = {"/StudentLoginServlet"})
public class StudentLoginServlet extends HttpServlet {
  private StudentDA studentDA;
  
    public void init() throws ServletException {
        
        studentDA = new StudentDA();
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("username"); 
        String password = request.getParameter("password");      
         PrintWriter out = response.getWriter();
   
        Student a = new Student(username,password);
        Student b = studentDA.Sretrieve(a);
        if(b==null){
            request.setAttribute("ErrorMsg", "Login failed, either studentusername or password is wrong!!");
            request.getRequestDispatcher("/StudentLogin.jsp").forward(request, response);
         
        }
        else if((password.equals(b.getPassword()) && username.equals(b.getStudUsername()))){
            
            HttpSession session=request.getSession(true);
            session.setAttribute("username", b.getStudUsername());
            session.setAttribute("ID", b.getStudentId());
            session.setAttribute("password", b.getPassword());
            session.setAttribute("Question", b.getSecretQuestion());
            session.setAttribute("Answer", b.getSecretAnswer());
            session.setAttribute("Email", b.getEmail());
            session.setAttribute("contactnum", b.getContactNumber());
            response.sendRedirect("StudentHome.jsp");
        }
        else{
               request.setAttribute("ErrorMsg", "Login failed, either studentusername or password is wrong!!");
            request.getRequestDispatcher("/StudentLogin.jsp").forward(request, response);
        }
    }
}
