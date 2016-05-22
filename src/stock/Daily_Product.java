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
public class Daily_Product extends JPanel {
    
    /** Creates a new instance of Main */
    public Daily_Product() {
         DefaultTableModel tabModel;
         setLayout(new GridLayout());
       setSize(50,10);
      
        String[] colHeads={"product_id","date","quantity","scrape"};
       
        String query="select * from daily_made_product";
        ResultSet rs;
        String[][] obj=new String[20][4];
        int count=0;
        try{
         rs=ConnectionService.select(query);
         DateFormat df=new SimpleDateFormat("dd/mm/yyyy");
       
        while(rs.next()){
        String name1=rs.getString(1);
        String name4=rs.getString(4);
        String name3=rs.getString(3);
        String name2=df.format(rs.getDate(2));
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
                        
         tabModel=new DefaultTableModel(data,colHeads);
           JTable table = new JTable(tabModel)
{
public boolean isCellEditable(int row, int column)
{
return false;
}
};
     JScrollPane jSPane=new JScrollPane(table);
    
        jSPane.setPreferredSize(new Dimension(5,5));
        add(jSPane,BorderLayout.CENTER);
       
        
        
            }
    
    /**
     * @param args the command line arguments
     */
   
       
    }
    

