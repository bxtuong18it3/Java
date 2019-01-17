/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ahihi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ahihi {
 public void hienthi(String path){
     File s = new File(path);
     String[] name = s.list();
     if(name==null){
         System.out.println(" thu muc khong ton tai");
     }
     else{
         for( int i=0;i<name.length;i++){
             String Filename = name[i];
             System.out.println(Filename);
         }
     }
     
 }
    public void add(String parth){ 
        try {
        
        
        File s = new File(parth);
        s.getParentFile().mkdirs();
   
         s.createNewFile();
     } catch (IOException ex) {
         Logger.getLogger(Ahihi.class.getName()).log(Level.SEVERE, null, ex);
     
    }
    }
    public void delete(String parth){
       File s = new File(parth) ;
      if(s.exists()){
          System.out.println("file co ton tai");
          s.delete();
          System.out.println("xoa file thanh cong");
      }
      else{
          System.out.println("filen khong ton tai");
          System.out.println("xoa file khong thanh cong");
      }
    }
    public void write(String parth){
        try {
            FileWriter fs = new FileWriter(parth);
            fs.write(" ahahahahahah");
            fs.close();
        } catch (Exception e) {
          System.out.println(e);

        }   System.out.println("thanh cong...");
        
    }
    public void coppy(String parth){
        File s = new File(parth);
        File f = new File(parth);
        if(s.exists()){
            try {
                FileInputStream s1 = new FileInputStream(s);
                FileOutputStream f1 = new FileOutputStream(f);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Ahihi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static void main(String[] args) {
        Ahihi ahihi = new Ahihi();
        //ahihi.hienthi("D:\\buixuantuong.txd");
       // ahihi.add("D:\\buixuantuong.docx");
        //ahihi.delete("D:\\buixuantuong.txd");
         ahihi.write("D:\\buixuantuong.doc");
    }
    
}
