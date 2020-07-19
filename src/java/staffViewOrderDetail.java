/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import control.StaffCanteenAccountControl;
import domain.OrderLine;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class staffViewOrderDetail extends HttpServlet {

   private StaffCanteenAccountControl staffCanteenAccountControl;
    public void init() throws ServletException {    
       staffCanteenAccountControl = new StaffCanteenAccountControl();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String couponId = request.getParameter("couponId");
        OrderLine orderLineDetails = staffCanteenAccountControl.getOrderLine(couponId);
        if(orderLineDetails != null){
                request.setAttribute("orderLineDetail", orderLineDetails);
                request.getRequestDispatcher("/FoodRedeem.jsp").forward(request, response);
        }
        else{
            request.setAttribute("couponMessage","<font color=red>'"+couponId+"' Coupon ID is invalid</font>");
            request.getRequestDispatcher("/FoodRedeem.jsp").forward(request, response);
        }
    }

}
