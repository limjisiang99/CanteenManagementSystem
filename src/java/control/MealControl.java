/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.MealDA;
import domain.Meal;
import domain.MealCancel;
import domain.PopularMeal;
import java.util.ArrayList;

/**
 *
 * @author NGO KIAN HEE
 */
public class MealControl {
    private MealDA mealDA;
    public MealControl() {
        mealDA = new MealDA();  
    }
     public void addRecord(Meal meal) {
         mealDA.addRecord(meal);
    }
    public String getLastMealId(){
        return mealDA.getLastMealId();
    }
    public ArrayList getMeals(){
        return mealDA.getMeals();
    }
    public void updateRecord(Meal meal){
        mealDA.updateMeal(meal);
    }
    public void changeMealStatus(Meal meal){
        mealDA.changeMealStatus(meal);
    }
    public ArrayList<Meal> getPopularMeal(String startingDate,String endingDate){
         return mealDA.getPopularMealSet(startingDate, endingDate);
     }
    public ArrayList<PopularMeal> getPopularMeal2(String startingDate,String endingDate){
         return mealDA.getPopularMealSet2(startingDate, endingDate);
     }
    public ArrayList<MealCancel> getCancelledMeal(String startingDate,String endingDate){
        return mealDA.getCancelledMeal(startingDate, endingDate);
    }
    //marcus
    public ArrayList<Meal> retrieveRecord() {
        return mealDA.getMeal();
    }
    public ArrayList<Meal> viewMealByPrice(double startRange,double endRange){
        return mealDA.viewMealByPrice(startRange,endRange);
    }
}
