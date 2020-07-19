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
    public class StaffCanteenAccount implements Serializable {
    private String staffUsername;
    private String staffId;
    private String staffPassword;
    private String staffName;
    private String staffIc;
    private String staffEmail;
    private String staffPhoneNo;
    public StaffCanteenAccount(){
        
    }
    public StaffCanteenAccount(String staffUsername,String staffId,String staffPassword, String staffName, String staffIc, String staffEmail, String staffPhoneNo){
        this.staffUsername=staffUsername;
        this.staffId=staffId;
        this.staffPassword=staffPassword;
        this.staffName=staffName;
        this.staffIc=staffIc;
        this.staffEmail=staffEmail;
        this.staffPhoneNo=staffPhoneNo;
    }
    public String getStaffUsername() {
        return staffUsername;
    }

    public void setStaffUsername(String staffUsername) {
        this.staffUsername = staffUsername;
    }
    
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffIc() {
        return staffIc;
    }

    public void setStaffIc(String staffIc) {
        this.staffIc = staffIc;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public String getStaffPhoneNo() {
        return staffPhoneNo;
    }

    public void setStaffPhoneNo(String staffPhoneNo) {
        this.staffPhoneNo = staffPhoneNo;
    }
  
}
