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
public class TopUpTransaction implements Serializable {
    private String topUpId;
    private String studUsername;
    private String staffUsername;
    private double amount;
    private Date date;
    public TopUpTransaction(){
        
    }
    public TopUpTransaction(String topUpId, String studUsername, String staffUsername, double amount, Date date) {
        this.topUpId = topUpId;
        this.studUsername = studUsername;
        this.staffUsername = staffUsername;
        this.amount = amount;
        this.date = date;
    }
    public String getTopUpId() {
        return topUpId;
    }
    
    public void setTopUpId(String topUpId) {
        this.topUpId = topUpId;
    }

    public String getStudUsername() {
        return studUsername;
    }

    public void setStudUsername(String studUsername) {
        this.studUsername = studUsername;
    }

    public String getStaffUsername() {
        return staffUsername;
    }

    public void setStaffUsername(String staffUsername) {
        this.staffUsername = staffUsername;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
