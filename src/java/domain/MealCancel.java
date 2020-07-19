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
public class MealCancel implements Serializable{
    private String mealId;
    private String mealName;
    private int quantityCancelled;
    public MealCancel(){
        
    }
    public MealCancel(String mealId,String mealName,int quantityCancelled){
        this.mealId=mealId;
        this.mealName=mealName;
        this.quantityCancelled=quantityCancelled;
        
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getQuantityCancelled() {
        return quantityCancelled;
    }

    public void setQuantityCancelled(int quantityCancelled) {
        this.quantityCancelled = quantityCancelled;
    }
    
}
