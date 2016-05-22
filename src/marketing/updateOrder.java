/*
 * updateOrder.java
 *
 * Created on 4 December, 2009, 9:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package marketing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
import javax.swing.event.*;
import stock.ConnectionService;
import javax.swing.JButton;
import java.lang.Integer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.sql.*;
import java.text.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import stock.ConnectionService;
import javax.swing.JButton;
import javax.swing.BoxLayout;

/**
 *
 * @author rishabh
 */
public class updateOrder extends JPanel implements TableModelListener {
      JTable table;
    /** Creates a new instance of updateOrder */
    public updateOrder() {
        super();
        
      // setOpaque(true);
       // JFrame jFrame=new JFrame("product_Detail");
        //jFrame.setLayout(new GridLayout());
        //jFrame.setSize(500,500);
        //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ImageIcon im=new ImageIcon("C:/Users/AKHIL/Pictures/God.jpg");
       // JPanel jPanel1=new JPanel();
       // jPanel1.setLayout(new GridLayout(50,0));
         //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
       //setSize(50,10);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
         String[] colHeads={"order id","company name","date","status","category","total cost","paid cost"};
       // JScrollPane jSPane=new JScrollPane(jPanel1);
        String query="select * from comanage.order";
        ResultSet rs;
        String[][] obj=new String[50][7];
        int count=0;
        try{
         rs=ConnectionService.select(query);
         //DateFormat df=new SimpleDateFormat("dd/mm/yyyy");
       DateFormat df=new SimpleDateFormat("dd/mm/yyyy");
        while(rs.next()){
        String name1=rs.getString(1);
        String name2=rs.getString(2);
        String name3=df.format(rs.getDate(3));
        String name7=Integer.toString(rs.getInt(7));
        String name6=Integer.toString(rs.getInt(6));
        String name5=rs.getString(5);
        String name4=rs.getString(4);
        obj[count][0]=name1;
        obj[count][1]=name2;
        obj[count][2]=name3;
        obj[count][3]=name4;
        obj[count][4]=name5;
        obj[count][5]=name6;
        obj[count][6]=name7;
        count++;
        }
          }
        catch(Exception e){};
        String[][] data=new String[count][7];
        for(int j=0;j<count;j++)
            for(int k=0;k<7;k++){
                data[j][k]=obj[j][k];
                }
       //for(int j=0;j<count;j++)
         //   for(int k=0;k<4;k++)
      DefaultTableModel tabModel=new DefaultTableModel(data,colHeads);                    
       // JTable table=new JTable(data,colHeads); 
           table=new JTable(tabModel);
         
     
     table.setPreferredScrollableViewportSize(new Dimension(500, 70));
     table.setFillsViewportHeight(true);
JScrollPane jSPane=new JScrollPane(table);
table.setRowSelectionAllowed(false);
table.getModel().addTableModelListener(this);
      // jPanel1.add(table);
        jSPane.setPreferredSize(new Dimension(5,5));
        // JLabel label1=new JLabel("braj",im,JLabel.LEFT);
         //jFrame.add(jSPane,BorderLayout.CENTER);
            add(jSPane);//,BorderLayout.CENTER);
         //jFrame.add(label1);
        //jFrame.setVisible(true);
       //setVisible(true);    
        
        
    }
    
   /*  public static void main(String[] args) 
    {
         JFrame j=new JFrame();
         j.add(new updateOrder());
         j.pack();
             j.setVisible(true);
     }*/
    public void tableChanged(javax.swing.event.TableModelEvent source)     {
                 String msg="";
                 String column="";
                 TableModel tabMod = (TableModel)source.getSource();
                 if(table.getSelectedColumn()==0)
                 {
                 column="order_id";
                 }
                 else if(table.getSelectedColumn()==1)
                 {
                 column="company_name";
                 }
                 else if(table.getSelectedColumn()==2)
                 {
                 column="date";
                 }
                 else if(table.getSelectedColumn()==3)
                 {
                 column="status";
                 }
                 else if(table.getSelectedColumn()==4)
                 {
                 column="category";
                 }
                 else if(table.getSelectedColumn()==5)
                 {
                 column="total_cost";
                 }
                 else if(table.getSelectedColumn()==6)
                 {
                 column="paid_cost";
                 }
          switch (source.getType())
                   {
                       case TableModelEvent.UPDATE:
                       String query1="update comanage.order set "+column+"='"+table.getValueAt(table.getSelectedRow(),table.getSelectedColumn()).toString()+"' where order_id='"+table.getValueAt(table.getSelectedRow(),0).toString()+"'"; 
                       try{
        int i=ConnectionService.update(query1);
         //DateFormat df=new SimpleDateFormat("dd/mm/yyyy");
      // DateFormat df=new SimpleDateFormat("dd/mm/yyyy");
       if(i==1)
       {
            JOptionPane.showMessageDialog(null,msg,"Table Example",JOptionPane.INFORMATION_MESSAGE);
//javax.swing.JOptionPane.showMessageDialog(null,"successful");
       }     
        }
        catch(Exception e){javax.swing.JOptionPane.showMessageDialog(null,e.getMessage());};    
                       
                       
                       
                       
                       msg="Table Value Updated for  cell "+table.getSelectedRow()+","+table.getSelectedColumn()+"\nWhich is "+table.getValueAt(table.getSelectedRow(),table.getSelectedColumn()).toString();
              
             // msg="Table Value Updated for  cell "+table.getSelectedRow()+"\nWhich is "+table.getValueAt(table.getSelectedRow(),0).toString();
              //JOptionPane.showMessageDialog(null,msg,"Table Example",JOptionPane.INFORMATION_MESSAGE);
                  
              break;

                   }

    }//Table Changed Method

   
    
    }
    



