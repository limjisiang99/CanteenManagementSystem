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
public class Cart implements Serializable{
    private String username;
    private String mealID;
    private int quantity;
    private Date collectDate;

    public Cart(String username, String mealID, Date collectDate,int quantity) {
        this.username = username;
        this.mealID = mealID;
        this.collectDate = collectDate;
        this.quantity = quantity;
        
    }
    public Cart(String mealID, Date collectDate){
        this.mealID = mealID;
        this.collectDate = collectDate;        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMealID() {
        return mealID;
    }

    public void setMealID(String mealID) {
        this.mealID = mealID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }
    
    
}
