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
public class OrderLine implements Serializable{
    private String couponId;
    private String orderId;
    private String mealId;
    private Date orderRedeemDate;
    private String orderStatus;
    private int quantity;
    public OrderLine(){
        
    }
    public OrderLine(String couponId , String orderId, String mealId, Date orderRedeemDate, String orderStatus , int quantity){
        this.couponId=couponId;
        this.orderId=orderId;
        this.mealId=mealId;
        this.orderRedeemDate=orderRedeemDate;
        this.orderStatus=orderStatus;
        this.quantity=quantity;
    }
    public OrderLine(int quantity, String couponId){
        this.quantity=quantity;
        this.couponId=couponId;
    }
    
    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public Date getOrderRedeemDate() {
        return orderRedeemDate;
    }

    public void setOrderRedeemDate(Date orderRedeemDate) {
        this.orderRedeemDate = orderRedeemDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
   
}
