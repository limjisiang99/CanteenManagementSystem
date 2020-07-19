/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.Cart;
import da.CartDA;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

@MultipartConfig(maxFileSize = 16177215)
public class cartServlet extends HttpServlet {

      private CartDA cartDA;
      public void init() throws ServletException {
        cartDA = new CartDA();
    }
      
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        String name=(String)session.getAttribute("username");
        String mealId = request.getParameter("mealID");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String collectDate = request.getParameter("collectdate");
        Date date = Date.valueOf(collectDate);
                    Cart a = new Cart(name,mealId,date,quantity);
                    cartDA.addRecord(a);
                    response.sendRedirect("orderSummary.jsp");
    }
}
