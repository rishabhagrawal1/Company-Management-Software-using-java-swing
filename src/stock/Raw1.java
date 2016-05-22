/*
 * Main.java
 *
 * Created on 24 November, 2009, 10:50 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package stock;

import java.lang.Integer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.sql.*;
import java.text.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.*;
/**
 *
 * @author AKHIL
 */
public class Raw1 extends JPanel{
    JButton jb=new JButton("click here");
    DefaultTableModel tabModel;
    /** Creates a new instance of Main */
    public Raw1() {
        //JFrame jFrame=new JFrame("Raw_Material_Detail");
        setLayout(new GridLayout());
       setSize(50,10);
        //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ImageIcon im=new ImageIcon("C:/Users/AKHIL/Pictures/God.jpg");
       // JPanel jPanel1=new JPanel();
    //setLayout(new GridLayout(50,0));
        String[] colHeads={"product_id","company_name","quantity","date_of_purchaching"};
       // JScrollPane jSPane=new JScrollPane(jPanel1);
        String query="select * from raw_stock";
        ResultSet rs;
        String[][] obj=new String[20][4];
      //  Vector obj1=new Vector(); 
        int count=0;
        try{
         rs=ConnectionService.select(query);
         DateFormat df=new SimpleDateFormat("dd/mm/yyyy");
       
        while(rs.next()){
        String name1=rs.getString(1);
        String name2=rs.getString(2);
        String name3=Integer.toString(rs.getInt(3));
        String name4=df.format(rs.getDate(4));
        obj[count][0]=name1;
        obj[count][1]=name2;
        obj[count][2]=name3;
        obj[count][3]=name4;
        //obj1.addElement(obj);
        count++;
        }
          }
        catch(Exception e){};
        String[][] data=new String[count][4];
        for(int j=0;j<count;j++)
            for(int k=0;k<4;k++){
                data[j][k]=obj[j][k];
                }
       //for(int j=0;j<count;j++)
         //   for(int k=0;k<4;k++)
          tabModel=new DefaultTableModel(data,colHeads);       
        //JTable table=new JTable(data,colHeads);
       JTable table = new JTable(tabModel)
{
public boolean isCellEditable(int row, int column)
{
return false;
}
};


     JScrollPane jSPane=new JScrollPane(table);
       table.setFillsViewportHeight(true);

        //add(table);
        jSPane.setPreferredSize(new Dimension(5,5));
        // JLabel label1=new JLabel("braj",im,JLabel.LEFT);
         add(jSPane,BorderLayout.CENTER);
  //add(jb,BorderLayout.AFTER_LAST_LINE);
   //jb.setPreferredSize(new Dimension(20,20));
        //jFrame.add(label1);
        //jFrame.setVisible(true);
        
        
    }
    
    /**
     * @param args the command line arguments
     */
   
       
    }
    

