/*
 * Main.java
 *
 * Created on 24 November, 2009, 10:50 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package marketing;
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
 * @author AKHIL
 */
public class viewCustomers extends JPanel implements java.awt.event.ActionListener{
    JTable table;
    /** Creates a new instance of Main */
    public viewCustomers() {
        super();
       setOpaque(true);
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
        String[] colHeads={"customer id","company name","address","contact","category"};
       // JScrollPane jSPane=new JScrollPane(jPanel1);
        String query="select * from cust_dealer_detail";
        ResultSet rs;
        String[][] obj=new String[50][5];
        int count=0;
        try{
         rs=ConnectionService.select(query);
         //DateFormat df=new SimpleDateFormat("dd/mm/yyyy");
       
        while(rs.next()){
        String name1=rs.getString(1);
        String name2=rs.getString(2);
        String name3=rs.getString(3);
        String name4=rs.getString(4);
        String name5=rs.getString(5);
        obj[count][0]=name1;
        obj[count][1]=name2;
        obj[count][2]=name3;
        obj[count][3]=name4;
        obj[count][4]=name5;
        
        count++;
        }
          }
        catch(Exception e){javax.swing.JOptionPane.showMessageDialog(null,e.getMessage());};
        String[][] data=new String[count][5];
        for(int j=0;j<count;j++)
            for(int k=0;k<5;k++){
                data[j][k]=obj[j][k];
                }
       //for(int j=0;j<count;j++)
         //   for(int k=0;k<4;k++)
      DefaultTableModel tabModel=new DefaultTableModel(data,colHeads);                    
       // JTable table=new JTable(data,colHeads); 
           table=new JTable(tabModel)
          {
            public boolean isCellEditable(int row, int column)
           {
            return false;
           }
           };
           /*::{
              setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
 
           }::*/
     
     table.setPreferredScrollableViewportSize(new Dimension(500, 70));
     table.setFillsViewportHeight(true);
JScrollPane jSPane=new JScrollPane(table);
      // jPanel1.add(table);
        jSPane.setPreferredSize(new Dimension(5,5));
        // JLabel label1=new JLabel("braj",im,JLabel.LEFT);
         //jFrame.add(jSPane,BorderLayout.CENTER);
            add(jSPane);//,BorderLayout.CENTER);
         //jFrame.add(label1);
        //jFrame.setVisible(true);
       //setVisible(true);    
        JButton printButton = new JButton("Print");
        printButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        printButton.addActionListener(this);
        add(printButton);
        
        printButton.setSize(100,20);
        
    }
    
    /**
     * @param args the command line arguments
     */
   
      public void actionPerformed(java.awt.event.ActionEvent ignore) {
        MessageFormat header = new MessageFormat("Page {0,number,integer}");
        try {
            table.print(JTable.PrintMode.FIT_WIDTH, header, null);
        } catch (java.awt.print.PrinterException e) {
            System.err.format("Cannot print %s%n", e.getMessage());
        }
    } 
    }
    

