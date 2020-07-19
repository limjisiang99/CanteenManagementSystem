/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author Lim Ji-Siang
 */
public class Student implements Serializable{

    private String StudUsername;
    private String StudentId;
    private String Password;
    private double CreditPoint;
    private String SecretQuestion;
    private String SecretAnswer;
    private String Email;
    private String ContactNumber;

    public Student(String StudUsername, String StudentId, String Password, double CreditPoint, String SecretQuestion, String SecretAnswer, String Email, String ContactNumber) {
        this.StudUsername = StudUsername;
        this.StudentId = StudentId;
        this.Password = Password;
        this.CreditPoint = CreditPoint;
        this.SecretQuestion = SecretQuestion;
        this.SecretAnswer = SecretAnswer;
        this.Email = Email;
        this.ContactNumber = ContactNumber;
    }

    public Student(String StudUsername, String Password){
        this.StudUsername = StudUsername;
        this.Password = Password;
    }
    public Student(String StudUsername, double CreditPoint){
        this.StudUsername = StudUsername;
        this.CreditPoint = CreditPoint; 
    }
    
    public Student(String StudUsername){
        this.StudUsername = StudUsername;
    }
    
    public Student(String StudUsername, String SecretQuestion, String SecretAnswer){
        this.StudUsername = StudUsername;
        this.SecretQuestion = SecretQuestion;
        this.SecretAnswer = SecretAnswer;
        
    }
    public Student(String StudUsername, String SecretQuestion, String SecretAnswer, String Email, String ContactNumber){
        this.StudUsername = StudUsername;
        this.SecretQuestion = SecretQuestion;
        this.SecretAnswer = SecretAnswer;
        this.Email = Email;
        this.ContactNumber = ContactNumber;
    }
    
    
    public String getStudUsername() {
        return StudUsername;
    }

    public void setStudUsername(String StudUsername) {
        this.StudUsername = StudUsername;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String StudentId) {
        this.StudentId = StudentId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public double getCreditPoint() {
        return CreditPoint;
    }

    public void setCreditPoint(double CreditPoint) {
        this.CreditPoint = CreditPoint;
    }

    public String getSecretQuestion() {
        return SecretQuestion;
    }

    public void setSecretQuestion(String SecretQuestion) {
        this.SecretQuestion = SecretQuestion;
    }

    public String getSecretAnswer() {
        return SecretAnswer;
    }

    public void setSecretAnswer(String SecretAnswer) {
        this.SecretAnswer = SecretAnswer;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String ContactNumber) {
        this.ContactNumber = ContactNumber;
    }
    
}


