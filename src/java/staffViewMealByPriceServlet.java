/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import control.MealControl;
import domain.Meal;
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
public class staffViewMealByPriceServlet extends HttpServlet {

    private MealControl mealControl;
    
   public void init() throws ServletException {
       
        mealControl = new MealControl();
   }
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String startRange = request.getParameter("startRange");
        String endRange = request.getParameter("endRange");
        ArrayList<Meal> viewMealByPrice = null;
        viewMealByPrice = mealControl.viewMealByPrice(Double.parseDouble(startRange), Double.parseDouble(endRange));
        if(viewMealByPrice.isEmpty()){
            request.setAttribute("notFoundMessage","<font color=red>Sorry, no record found for <br>Start Range Price : "+startRange+"<br>End Range Price :"+endRange+"</font>");
            request.getRequestDispatcher("/staffViewMealByPrice.jsp").forward(request, response); 
        }
        else{
            request.setAttribute("viewMealByPrice",viewMealByPrice);
            request.setAttribute("startRange",startRange);
            request.setAttribute("endRange",endRange);
            request.getRequestDispatcher("/staffViewMealByPrice.jsp").forward(request, response); 
        }
        
    }
}
