/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author NGO KIAN HEE
 */
public class PopularMeal implements Serializable  {
    private String mealId;
    private String mealName;
    private double mealPrice;
    private Date orderRedeemDate;
    private int quantityOrdered;
    private double subTotal;
    
    public PopularMeal(){
        
    }
    public PopularMeal(String mealId, String mealName, double mealPrice, Date orderRedeemDate , int quantityOrdered, double subTotal){
        this.mealId=mealId;
        this.mealName=mealName;
        this.mealPrice=mealPrice;
        this.orderRedeemDate=orderRedeemDate;
        this.quantityOrdered=quantityOrdered;
        this.subTotal=subTotal;
        
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

    public Date getOrderRedeemDate() {
        return orderRedeemDate;
    }

    public void setOrderRedeemDate(Date orderRedeemDate) {
        this.orderRedeemDate = orderRedeemDate;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    
    
}
