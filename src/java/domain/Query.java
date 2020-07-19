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
public class Query implements Serializable{
    private String MealId;
    private String MealName;
    private int quantityOrdered;
    private String ingredientId;
    private String ingredientName;
    private int ingredientNeeded;
    //private String extra;
    public Query() {
        
    }
    public Query(String MealId, String MealName, int quantityOrdered){
        this.MealId=MealId;
        this.MealName=MealName;
        this.quantityOrdered=quantityOrdered;
    }
    /*public Query(String ingredientId , String ingredientName , int ingredientNeed , String extra){
        this.ingredientId=ingredientId;
        this.ingredientName=ingredientName;
        this.ingredientNeeded=ingredientNeed;
        this.extra=extra;
    }*/

    public String getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(String ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public int getIngredientNeeded() {
        return ingredientNeeded;
    }

    public void setIngredientNeeded(int ingredientNeeded) {
        this.ingredientNeeded = ingredientNeeded;
    }

    public String getMealId() {
        return MealId;
    }

    public void setMealId(String MealId) {
        this.MealId = MealId;
    }

    public String getMealName() {
        return MealName;
    }

    public void setMealName(String MealName) {
        this.MealName = MealName;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }
}
