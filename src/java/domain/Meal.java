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
public class Meal implements Serializable {
    private String mealId;
    private String mealName;
    private double mealPrice;
    private String mealDesc;
    private double calories;
    private byte[] mealPicture;
    private String mealSession;
    private String mealStatus;
    
     public Meal(){
        
    }
     public Meal(String mealId, String mealName,double mealPrice, String mealDesc, double calories, byte[] mealPicture, String mealSession,String mealStatus){
        this.mealId=mealId;
        this.mealName=mealName;
        this.mealPrice=mealPrice;
        this.mealDesc=mealDesc;
        this.calories=calories;
        this.mealPicture=mealPicture;
        this.mealSession=mealSession;
        this.mealStatus=mealStatus;    
     }
        public Meal(String mealId, String mealName,double mealPrice, String mealDesc, double calories, /*byte[] mealPicture,*/  String mealSession,String mealStatus){
        this.mealId=mealId;
        this.mealName=mealName;
        this.mealPrice=mealPrice;
        this.mealDesc=mealDesc;
        this.calories=calories;
        //this.mealPicture=mealPicture;
        this.mealStatus=mealStatus;
        this.mealSession=mealSession;
     }
       public Meal(String mealId, String mealName){
           this.mealId = mealId;
           this.mealName = mealName;
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

    public double getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(double mealPrice) {
        this.mealPrice = mealPrice;
    }

    public String getMealDesc() {
        return mealDesc;
    }

    public void setMealDesc(String mealDesc) {
        this.mealDesc = mealDesc;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public byte[] getMealPicture() {
        return mealPicture;
    }

    public void setMealPicture(byte[] mealPicture) {
        this.mealPicture = mealPicture;
    }

    public String getMealStatus() {
        return mealStatus;
    }

    public void setMealStatus(String mealStatus) {
        this.mealStatus = mealStatus;
    }

    public String getMealSession() {
        return mealSession;
    }

    public void setMealSession(String mealSession) {
        this.mealSession = mealSession;
    }
    
   
}
