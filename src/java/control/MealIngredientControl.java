/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.MealIngredientDA;
import domain.MealIngredient;
import java.util.ArrayList;

/**
 *
 * @author NGO KIAN HEE
 */
public class MealIngredientControl {
    private MealIngredientDA mealIngredientDA;
    public MealIngredientControl() {
        mealIngredientDA = new MealIngredientDA();  
    }
     public void addRecord(MealIngredient mealIngredient) {
         mealIngredientDA.addRecord(mealIngredient);
    }
      public ArrayList<MealIngredient> getMealIngredient() {
           return mealIngredientDA.getMealIngredient();
    }
      public void updateRecord(MealIngredient mealIngredient){
          mealIngredientDA.updateMealIngredient(mealIngredient);
      }
       public void deleteRecord(String mealId,String ingredientId){
          mealIngredientDA.deleteMealIngredient(mealId,ingredientId);
      }
}
