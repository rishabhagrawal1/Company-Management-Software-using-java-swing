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
public class Order_Detail extends JPanel{
    
    /** Creates a new instance of Main */
    public Order_Detail() {
          DefaultTableModel tabModel;
         setLayout(new GridLayout());
       setSize(50,10);
       // JFrame jFrame=new JFrame("Raw_Material_Detail");
        //jFrame.setLayout(new GridLayout());
        //jFrame.setSize(500,500);
        //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ImageIcon im=new ImageIcon("C:/Users/AKHIL/Pictures/God.jpg");
       // JPanel jPanel1=new JPanel();
       // jPanel1.setLayout(new GridLayout(50,0));
        String[] colHeads={"order_id","product_id","name","quantity"};
       // JScrollPane jSPane=new JScrollPane(jPanel1);
        String query="select * from order_detail";
        ResultSet rs;
        String[][] obj=new String[50][4];
        int count=0;
        try{
         rs=ConnectionService.select(query);
         //DateFormat df=new SimpleDateFormat("dd/mm/yyyy");
       
        while(rs.next()){
        String name1=rs.getString(1);
        String name2=rs.getString(2);
        String name4=Integer.toString(rs.getInt(4));
        //String name3=df.format(rs.getDate(3));
        String name3=rs.getString(3);
        obj[count][0]=name1;
        obj[count][1]=name2;
        obj[count][2]=name3;
        obj[count][3]=name4;
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
         JTable table = new JTable(tabModel)
{
public boolean isCellEditable(int row, int column)
{
return false;
}
};
     JScrollPane jSPane=new JScrollPane(table);
      // jPanel1.add(table);
        jSPane.setPreferredSize(new Dimension(5,5));
         add(jSPane,BorderLayout.CENTER);
        // JLabel label1=new JLabel("braj",im,JLabel.LEFT);
        // jFrame.add(jSPane,BorderLayout.CENTER);
        //jFrame.add(label1);
        //jFrame.setVisible(true);
        
        
    }
    
    /**
     * @param args the command line arguments
     */
   
       
    }
    

