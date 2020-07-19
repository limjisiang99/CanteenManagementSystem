/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.StaffCanteenAccountDA;
import domain.OrderLine;
import domain.Query;
import domain.StaffCanteenAccount;
import domain.Student;
import java.util.ArrayList;
/**
 *
 * @author NGO KIAN HEE
 */
public class StaffCanteenAccountControl {
    private StaffCanteenAccountDA staffDA;
    public StaffCanteenAccountControl() {
        staffDA = new StaffCanteenAccountDA();  
    }
    public void updateStaffDetails(String staffName, String staffIc,String staffEmail ,String staffPhoneNo,String staffUsername){
        staffDA.updateStaffDetails(staffName,staffIc,staffEmail,staffPhoneNo,staffUsername);
    }
    public void changePassword( String password,String staffUsername){
        staffDA.changePassword(password,staffUsername);
    }
    public void addRecord(StaffCanteenAccount staffCA) {
         staffDA.addRecord(staffCA);
    }
    public StaffCanteenAccount getStaffDetails(String staffUsername) {
        return staffDA.getStaffDetails(staffUsername);
    }
      public int getStaffUsername(String staffUsername) {
        return staffDA.getStaffUsername(staffUsername);
    }  
      public int getStaffId(String staffId) {
        return staffDA.getStaffId(staffId);
    }
    public boolean topUpCredit(String studUsername , double amount){
         return staffDA.topUpCredit(studUsername, amount);
    }
    public Student getStudInfo(String studUsername) {
        return staffDA.getStudInfo(studUsername);
    }
     public boolean updateCouponStatus(String couponId, String currenDate){
         return staffDA.updateCouponStatus(couponId,currenDate);
     }
     public ArrayList<OrderLine> getOrderLine(){
         return staffDA.getOrderLine();
     }
     public OrderLine getOrderLine(String couponId){
         return staffDA.getOrderLineDetail(couponId);
     }
     public ArrayList<Query> getTotalMealsOrder(String orderDate){
         return staffDA.getTotalMealOrderQuery(orderDate);
     }
     public ArrayList<Query> getTotalIngredientNeed(String orderDate){
         return staffDA.getTotalIngredientNeedQuery(orderDate);
     }
     
  
}
