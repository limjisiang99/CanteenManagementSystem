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
public class Order  implements Serializable{
    private String orderId;
    private String studUsername;
    private Date orderDate;
    private double totalPrice;
    private double totalCalories;
    //private Date startDate;
    //private Date finishDate;
    
    public Order(){
    }
    public Order(String orderId, String studUsername , Date orderDate , double totalPrice , double totalCalories /*, Date startDate, Date finishDate */){
        this.orderId=orderId;
        this.studUsername=studUsername;
        this.orderDate=orderDate;
        this.totalPrice=totalPrice;
        this.totalCalories=totalCalories;
        //this.startDate=startDate;
        //this.finishDate=finishDate;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStudUsername() {
        return studUsername;
    }

    public void setStudUsername(String studUsername) {
        this.studUsername = studUsername;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    /*public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }*/
   
}
