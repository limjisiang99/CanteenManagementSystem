/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import da.CartDA;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lim Ji-Siang
 */
public class deleteCartServlet extends HttpServlet {
     private CartDA cartDA;
    
    
public void init() throws ServletException {
        cartDA = new CartDA();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String mealID = request.getParameter("mealId");
        String collectDate = request.getParameter("collectDate");
        Date date = Date.valueOf(collectDate);
        cartDA.deleteCart(mealID, date);
        response.sendRedirect("orderSummary.jsp");
        
        
        
        
        
    }
}
