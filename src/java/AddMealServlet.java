/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import control.IngredientControl;
import control.MealControl;
import control.MealIngredientControl;
import domain.Ingredient;
import domain.Meal;
import domain.MealIngredient;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author NGO KIAN HEE
 */
@MultipartConfig(maxFileSize = 16177215)
public class AddMealServlet extends HttpServlet {
    private MealControl mealControl;
    private MealIngredientControl mealIngredientControl;
    private IngredientControl ingredientControl;
    private MealIngredient mealIngredient;

   public void init() throws ServletException {
        mealControl = new MealControl();
       
        mealIngredientControl = new MealIngredientControl();
        ingredientControl = new IngredientControl();
    }
   public static byte[] stream2Bytes(InputStream ins) {

    byte [] availableBytes = new byte [0];
    
      try {
          byte [] buffer = new byte[4096];
          ByteArrayOutputStream outs = new ByteArrayOutputStream();
          
          int read = 0;
          while ((read = ins.read(buffer)) != -1 ) {
            outs.write(buffer, 0, read);
          }
          
          ins.close();
          outs.close();
          availableBytes = outs.toByteArray();
          
      } catch (Exception e) { 
        e.printStackTrace();
      }
      
      return availableBytes;
  }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String mealName = request.getParameter("mealName");
        String mealPrice = request.getParameter("mealPrice");
        String mealDesc = request.getParameter("mealDesc");
        String calories = request.getParameter("calories");
        String mealPicture2 = request.getParameter("mealPictures");
        out.println(mealPicture2);
        String mealSession = request.getParameter("mealSession");
        InputStream inputStream = null;
        Part filePart = request.getPart("mealPictures");
        if(filePart !=null){
            inputStream = filePart.getInputStream();
        }
        byte[] mealPicture = stream2Bytes(inputStream);
        String lastMealId = mealControl.getLastMealId();
        String nextMealId =null;
        if(lastMealId==null)
           nextMealId="M1001";
        else{
           nextMealId= String.format("M%d",(Integer.parseInt(lastMealId.substring(1,5)) + 1));
        }
        Meal meal = new Meal(nextMealId,mealName,Double.parseDouble(mealPrice),mealDesc,Double.parseDouble(calories),mealPicture,mealSession,"Available");
        mealControl.addRecord(meal);
        ArrayList<Ingredient> arrayIngredient = ingredientControl.getIngredient();
        for(int i = 0 ; i < arrayIngredient.size();i++){
            out.println(request.getParameter(arrayIngredient.get(i).getIngredientId()));
            if(!request.getParameter(arrayIngredient.get(i).getIngredientId()).equals("0")){
                int ingredientQty = Integer.parseInt(request.getParameter(arrayIngredient.get(i).getIngredientId()));
                mealIngredient = new MealIngredient(nextMealId,arrayIngredient.get(i).getIngredientId(),ingredientQty);
                mealIngredientControl.addRecord(mealIngredient);
            }
        }
        request.setAttribute("mealAddedMsg","A new meal has added!");
        request.getRequestDispatcher("/addMeal.jsp").forward(request, response);
    }
}
