/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import control.StaffCanteenAccountControl;
import domain.Query;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NGO KIAN HEE
 */
public class staffViewIngredientNeed extends HttpServlet {
     private StaffCanteenAccountControl staffCanteenAccountControl;
    public void init() throws ServletException {    
       staffCanteenAccountControl = new StaffCanteenAccountControl();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
             PrintWriter out = response.getWriter();
        String orderDate = request.getParameter("orderDate");
        ArrayList<Query> totalIngredient = staffCanteenAccountControl.getTotalIngredientNeed(orderDate);
        if(!totalIngredient.isEmpty()){
                request.setAttribute("orderDate2", orderDate);
                request.setAttribute("ingredients",totalIngredient);
                request.getRequestDispatcher("/staffViewIngredientNeed.jsp").forward(request, response);
        }
        else{
            request.setAttribute("Message","Sorry no record found on "+orderDate);
            request.getRequestDispatcher("/staffViewIngredientNeed.jsp").forward(request, response);
        }
    }
}
