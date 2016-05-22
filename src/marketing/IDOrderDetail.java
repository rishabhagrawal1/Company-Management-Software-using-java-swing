/*
 * IDOrderDetail.java
 *
 * Created on 2 December, 2009, 9:42 PM
 */

package marketing;
import javax.swing.JButton;
import java.lang.Integer;
import javax.swing.*;
import javax.swing.JPanel;
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
 * @author  rishabh
 */
public class IDOrderDetail extends javax.swing.JPanel {
     JTable table;
      String[][] data;
       
    /** Creates new form IDOrderDetail */
    public IDOrderDetail() {
        
        initComponents();
        
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jb = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        view = new javax.swing.JButton();

        jb.setText("please insert here order id to see related order detail");

        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });

        view.setText("view");
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jb)
                        .addGap(68, 68, 68)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(view)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(view)
                .addContainerGap(180, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
// TODO add your handling code here:
       
     //   SubIDOrder sub=new SubIDOrder(id.getText());
        
      if(id.getText()!=null&&id.getText()!="")
        {
          printTable(id.getText());

    }   
    }//GEN-LAST:event_viewActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed
    void printTable(String id)
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
     String[] colHeads={"order id","product id","product name","quantity"};
       String query="select * from order_detail where order_id='"+id+"'";
        ResultSet rs;
        String[][] obj=new String[50][4];
        int count=0;
        try{
         rs=ConnectionService.select(query);
        // javax.swing.JOptionPane.showMessageDialog(null,"hihi");
         //DateFormat df=new SimpleDateFormat("dd/mm/yyyy");
       //DateFormat df=new SimpleDateFormat("dd/mm/yyyy");
        while(rs.next()){
        String name1=rs.getString(1);
        String name2=rs.getString(2);
        String name3=rs.getString(3);
        String name4=Integer.toString(rs.getInt(4));
        //javax.swing.JOptionPane.showMessageDialog(null,"ram"+name1+""+name2+""+name3);
        
        obj[count][0]=name1;
        obj[count][1]=name2;
        obj[count][2]=name3;
        obj[count][3]=name4;
        count++;
        }
          }
        catch(Exception e){};
         data=new String[count][4];
        for(int j=0;j<count;j++)
            for(int k=0;k<4;k++){
                data[j][k]=obj[j][k];
                }
       
     DefaultTableModel tabModel=new DefaultTableModel(data,colHeads);                    
       // JTable table=new JTable(data,colHeads); 
           table=new JTable(tabModel)
          {
            public boolean isCellEditable(int row, int column)
           {
            return false;
           }
           };    
          table.setPreferredScrollableViewportSize(new Dimension(500, 70));
     table.setFillsViewportHeight(true);
JScrollPane jSPane=new JScrollPane(table);
//javax.swing.JOptionPane.showMessageDialog(null,"jspane");
      // jPanel1.add(table);
        jSPane.setPreferredSize(new Dimension(50,50));
        // JLabel label1=new JLabel("braj",im,JLabel.LEFT);
         //jFrame.add(jSPane,BorderLayout.CENTER);
        //removeAll();
        //JPanel jp=new JPanel();
        //javax.swing.JOptionPane.showMessageDialog(null,"removeAll");
        removeAll();   
        add(jSPane);//,BorderLayout.CENTER);
        setVisible(false);
        setVisible(true);
          //  javax.swing.JOptionPane.showMessageDialog(null,"remojs");
         //jFrame.add(label1);
        //jFrame.setVisible(true);
       //setVisible(true);    
        JButton printButton = new JButton("Print");
        printButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        printButton.addActionListener(new ActionListener(){public void actionPerformed(java.awt.event.ActionEvent ignore) 
        {
        MessageFormat header = new MessageFormat("Page {0,number,integer}");
        try {
            table.print(JTable.PrintMode.FIT_WIDTH, header, null);
            } catch (java.awt.print.PrinterException e) {
            System.err.format("Cannot print %s%n", e.getMessage());
              }
        }});
        add(printButton);
        //add(jp);
        printButton.setSize(100,20);
        //javax.swing.JOptionPane.showMessageDialog(null,"set");
    
    setVisible(false);
    setVisible(true);
    
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField id;
    private javax.swing.JLabel jb;
    private javax.swing.JButton view;
    // End of variables declaration//GEN-END:variables
    
}