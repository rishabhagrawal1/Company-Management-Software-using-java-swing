/*
 * Main.java
 *
 * Created on 25 November, 2009, 4:51 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package stock;
import java.awt.Color;
import javax.swing.*;
/**
 *
 * @author AKHIL
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       SwingUtilities.invokeLater(new Runnable(){
       public void run(){
      frame fr=new frame();
   
      fr.setVisible(true);
       }
       }
       
       );
       
    }  
    
}
