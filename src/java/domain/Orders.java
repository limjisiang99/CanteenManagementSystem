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
 * @author Lim Ji-Siang
 */
public class Orders implements Serializable {
    private String OrderID;
    private String userName;
    private Date OrderDate;
    private double totalPrice;
    private double totalCalories;

    public Orders(String OrderID, String userName, Date OrderDate, double totalPrice, double totalCalories) {
        this.OrderID = OrderID;
        this.userName = userName;
        this.OrderDate = OrderDate;
        this.totalPrice = totalPrice;
        this.totalCalories = totalCalories;
    }
    public Orders(String userName){
        this.userName = userName;
        
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    public String getUserName() {
        return userName;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getTotalCalories() {
        return totalCalories;
    }
    
    
    
    
    
    
    
    
}


