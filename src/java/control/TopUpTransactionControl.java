/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.TopUpTransactionDA;
import domain.TopUpTransaction;
import java.util.ArrayList;

/**
 *
 * @author NGO KIAN HEE
 */
public class TopUpTransactionControl {
    private TopUpTransactionDA topUpTransactionDA;
    public TopUpTransactionControl() {
        topUpTransactionDA = new TopUpTransactionDA();  
    }
     public void addRecord(TopUpTransaction transaction) {
         topUpTransactionDA.addRecord(transaction);
    }
     public ArrayList<TopUpTransaction> getTransactions(String startingDate, String endingDate) {
           return topUpTransactionDA.getTransactions(startingDate,endingDate);
    }
    public String getLastTopUpTransactionId(){
        return topUpTransactionDA.getLastTopUpTransactionId();
    }
}
