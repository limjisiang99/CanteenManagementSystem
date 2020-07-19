/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author NGO KIAN HEE
 */
public class MealIngredient implements Serializable {
    private String mealId;
    private String ingredientId;
    private int quantityNeeded;
    
    public MealIngredient(){
        
    }
    public MealIngredient(String mealId,String ingredientId,int quantityNeeded){
        this.mealId = mealId;
        this.ingredientId = ingredientId;
        this.quantityNeeded = quantityNeeded;
    }
    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(String ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getQuantityNeeded() {
        return quantityNeeded;
    }

    public void setQuantityNeeded(int quantityNeeded) {
        this.quantityNeeded = quantityNeeded;
    }
    
  
    
}
