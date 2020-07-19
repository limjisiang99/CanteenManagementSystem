
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NGO KIAN HEE
 */
@MultipartConfig(maxFileSize = 16177215)
public class ModifyMealServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String mealId = request.getParameter("mealId");
        String mealName = request.getParameter("mealName");
        String mealPrice = request.getParameter("mealPrice");
        String mealDesc = request.getParameter("mealDesc");
        String calories = request.getParameter("calories");
        String mealStatus = request.getParameter("mealStatus");
        //String mealPicture = request.getParameter("mealPicture");
        String mealSession = request.getParameter("mealSession");
        /*InputStream inputStream = null;
        Part filePart = request.getPart("mealPictures");
        if(filePart !=null){
            inputStream = filePart.getInputStream();
        }
        byte[] mealPicture = stream2Bytes(inputStream);
        */
        Meal meal = new Meal(mealId,mealName,Double.parseDouble(mealPrice),mealDesc,Double.parseDouble(calories),/*mealPicture,*/mealSession,mealStatus);
        mealControl.updateRecord(meal);
        ArrayList<Ingredient> arrayIngredient = ingredientControl.getIngredient();
        for(int i = 0 ; i < arrayIngredient.size();i++){
            out.println(request.getParameter(arrayIngredient.get(i).getIngredientId()));
            if(!request.getParameter(arrayIngredient.get(i).getIngredientId()).equals("0")){
                mealIngredientControl.deleteRecord(mealId,arrayIngredient.get(i).getIngredientId());
                int ingredientQty = Integer.parseInt(request.getParameter(arrayIngredient.get(i).getIngredientId()));
                mealIngredient = new MealIngredient(mealId,arrayIngredient.get(i).getIngredientId(),ingredientQty);
                mealIngredientControl.addRecord(mealIngredient);
            }
            else{
                mealIngredientControl.deleteRecord(mealId,arrayIngredient.get(i).getIngredientId());
            }
        }
        HttpSession session = request.getSession();
        session.setAttribute("Meals",mealControl.getMeals());
        request.setAttribute("modifyMealMsg","<font color=blue>Your meal have updated!</font>");
        request.getRequestDispatcher("/modifyMeal.jsp").forward(request, response);
    }
}