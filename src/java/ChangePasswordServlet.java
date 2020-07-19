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
import domain.Student;
import da.StudentDA;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lim Ji-Siang
 */
public class ChangePasswordServlet extends HttpServlet {
private StudentDA studentDA;
   public void init() throws ServletException {
        studentDA = new StudentDA();
    }
    
    
    
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(true);
        String username=(String)session.getAttribute("username");
       String oldPassword = request.getParameter("OldPassword");
       String newPassword = request.getParameter("NewPassword");
       String retypePassword = request.getParameter("retypePassword");
      System. out.println(oldPassword);
       System.out.println(newPassword);
       System.out.println(retypePassword);
       System.out.println(username);
 String b = studentDA.SgetRecord(username);
          
           if(b.equals(oldPassword)){
            if(newPassword.compareTo(retypePassword)==0){
              Student m = new Student(username,newPassword);
            studentDA.SupdatePassword(m);
            session.setAttribute("username", username);
            session.setAttribute("password", newPassword);
           request.setAttribute("Success", "Password updated");
            request.getRequestDispatcher("StudentChangePassword.jsp").forward(request, response);
        }
            else
                request.setAttribute("Fail2","your new password and retype password must be the same!!");
            request.getRequestDispatcher("/StudentChangePassword.jsp").forward(request,response);
           }
           
        else 
             request.setAttribute("Fail", "PLease enter a correct current password!!");
            request.getRequestDispatcher("/StudentChangePassword.jsp").forward(request, response);
        }
        
        
        }

  
