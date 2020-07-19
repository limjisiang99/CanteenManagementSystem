/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import control.StaffCanteenAccountControl;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NGO KIAN HEE
 */
public class StaffFoodRedeemServlet extends HttpServlet {
    private StaffCanteenAccountControl staffCanteenAccountControl;

   public void init() throws ServletException {
        staffCanteenAccountControl = new StaffCanteenAccountControl();
    }
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    private String currentDate = simpleDateFormat.format(new Date());
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
       String couponId = request.getParameter("couponId");
       boolean valid = staffCanteenAccountControl.updateCouponStatus(couponId,currentDate);
       out.println(currentDate);
       if(valid){
           request.setAttribute("couponMessage","<font color=blue>Process successfull. Coupon status has changed to redeemed.</font>");
           request.getRequestDispatcher("/FoodRedeem.jsp").forward(request, response);
       }
       else{
           request.setAttribute("couponMessage","<font color=red>Sorry the coupon is not redeem today.</font>");
           request.getRequestDispatcher("/FoodRedeem.jsp").forward(request, response);
       }
    }
}
