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
/**
 *
 * @author AKHIL
 */
public class Product extends JPanel{
    
    /** Creates a new instance of Main */
    public Product() {
       // JFrame jFrame=new JFrame("product_Detail");
        //jFrame.setLayout(new GridLayout());
        //jFrame.setSize(500,500);
        //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ImageIcon im=new ImageIcon("C:/Users/AKHIL/Pictures/God.jpg");
       // JPanel jPanel1=new JPanel();
       // jPanel1.setLayout(new GridLayout(50,0));
         setLayout(new GridLayout());
        setSize(50,10);
        String[] colHeads={"product_id","quantity","date_of_manufacturing"};
       // JScrollPane jSPane=new JScrollPane(jPanel1);
        String query="select * from product_stock";
        ResultSet rs;
        String[][] obj=new String[50][3];
        int count=0;
        try{
           
         rs=ConnectionService.select(query);
        
         DateFormat df=new SimpleDateFormat("dd/mm/yyyy");
       
        while(rs.next()){
        String name1=rs.getString(1);
         
        String name2=Integer.toString(rs.getInt(2));
        
      String name3=df.format(rs.getDate(3));
        
        obj[count][0]=name1;
       
        obj[count][1]=name2;
       
        obj[count][2]=name3;
        
        
        count++;
        }
          }
        catch(Exception e){};
        String[][] data=new String[count][4];
        for(int j=0;j<count;j++)
            for(int k=0;k<3;k++){
                data[j][k]=obj[j][k];
                }
       //for(int j=0;j<count;j++)
         //   for(int k=0;k<4;k++)
      DefaultTableModel tabModel=new DefaultTableModel(data,colHeads);                    
       // JTable table=new JTable(data,colHeads); 
          JTable table=new JTable(tabModel)
          {
            public boolean isCellEditable(int row, int column)
           {
            return false;
           }
           };
     JScrollPane jSPane=new JScrollPane(table);
     table.setFillsViewportHeight(true);

      // jPanel1.add(table);
        jSPane.setPreferredSize(new Dimension(5,5));
        // JLabel label1=new JLabel("braj",im,JLabel.LEFT);
         //jFrame.add(jSPane,BorderLayout.CENTER);
            add(jSPane,BorderLayout.CENTER);
         //jFrame.add(label1);
        //jFrame.setVisible(true);
       setVisible(true);        
        
    }
    
    /**
     * @param args the command line arguments
     */
   
       
    }
    

