/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import control.StaffCanteenAccountControl;
import domain.Order;
import domain.OrderLine;
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
public class staffViewOrders extends HttpServlet {
    private StaffCanteenAccountControl staffCanteenAccountControl;
    public void init() throws ServletException {    
       staffCanteenAccountControl = new StaffCanteenAccountControl();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String orderDate = request.getParameter("orderDate");
        //out.println(orderDate);
        //ArrayList<Order> orders= staffCanteenAccountControl.getOrders(orderDate);
        //ArrayList<OrderLine> orderLineDetails = staffCanteenAccountControl.getOrderDetails(orderDate);
        //ArrayList<OrderLine> orderLineDetails = staffCanteenAccountControl.getOrderDetails();
        ArrayList<Query> totalMealsOrder = staffCanteenAccountControl.getTotalMealsOrder(orderDate);
        //out.println(totalMealsOrder.get(0).getMealId());//Check value of the value inside the array when doing.
        if(!totalMealsOrder.isEmpty()){
                request.setAttribute("orderDate2", orderDate);
                request.setAttribute("orders",totalMealsOrder);
                //request.setAttribute("orderLineDetails",totalMealsOrder ); 
                
                request.getRequestDispatcher("/staffViewOrders.jsp").forward(request, response);
        }
        else{
            request.setAttribute("Message","Sorry no record found on "+orderDate);
            request.getRequestDispatcher("/staffViewOrders.jsp").forward(request, response);
        }
    }

  

}
