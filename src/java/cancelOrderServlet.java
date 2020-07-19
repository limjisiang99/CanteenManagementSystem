/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import da.CartDA;
import da.OrderLineDA;
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
public class cancelOrderServlet extends HttpServlet {
     private OrderLineDA orderLineDA;
     private StudentDA studentDA;
    
    
public void init() throws ServletException {
        orderLineDA = new OrderLineDA();
        studentDA = new StudentDA();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String couponID = request.getParameter("couponID");
        double mealPrice = Double.parseDouble(request.getParameter("mealPrice"));
        int quantity = Integer.parseInt(request.getParameter("mealQuantity"));
        orderLineDA.UpdateOrderLine(couponID);
        response.sendRedirect("cancelOrder.jsp");
        HttpSession session = request.getSession(true);
        String username=(String)session.getAttribute("username");
        double points = studentDA.SgetPoints(username);
        double points2 = points + (mealPrice*quantity);
        Student student = new Student(username,points2);
        studentDA.SupdateCreditPoints(student);
        
    }
}
