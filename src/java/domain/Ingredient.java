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
public class Ingredient implements Serializable {
    private String ingredientId;
    private String ingredientName;
    private String ingredientDesc;
    public Ingredient(){
        
    }
    public Ingredient(String ingredientId,String ingredientName,String ingredientDesc){
        this.ingredientId=ingredientId;
        this.ingredientName=ingredientName;
        this.ingredientDesc=ingredientDesc;
    }
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

    public String getIngredientDesc() {
        return ingredientDesc;
    }

    public void setIngredientDesc(String ingredientDesc) {
        this.ingredientDesc = ingredientDesc;
    }
    
   
}
