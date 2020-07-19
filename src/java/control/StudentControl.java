/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.StudentDA;
import domain.Student;
import java.util.ArrayList;
public class StudentControl {
   private StudentDA studentDA;
   
    public StudentControl() {
        studentDA = new StudentDA();  
    }
    public void updateRecord (Student student){
        studentDA.SupdatePassword(student);
    }
    public int addRecord(Student student) {
        return studentDA.Sinsert(student);
    }
  public String retrieveRecord(String a) {
        return studentDA.SgetRecord(a);
    }
  public Student getRecord(Student a){
      return studentDA.Sretrieve(a);
  }
}
