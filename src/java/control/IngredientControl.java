/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.IngredientDA;
import domain.Ingredient;
import java.util.ArrayList;

/**
 *
 * @author NGO KIAN HEE
 */
public class IngredientControl {
    private IngredientDA ingredientDA;
    public IngredientControl() {
        ingredientDA = new IngredientDA();  
    }
     public void addRecord(Ingredient ingredient) {
         ingredientDA.addRecord(ingredient);
    }
     public ArrayList<Ingredient> getIngredient() {
           return ingredientDA.getIngredient();
    }
}
