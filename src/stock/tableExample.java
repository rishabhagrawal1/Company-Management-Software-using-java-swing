 
/** 
* tableExample.java 
* This class shows how to create a simple JTable 
* using table model 
* 
* author Kanad Deshpande
*/ 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
import javax.swing.event.*;

class  tableExample implements ActionListener, TableModelListener
{
JFrame frame;
JTable table;
Vector rows,columns;
DefaultTableModel tabModel;
JScrollPane scrollPane;
JLabel lblMessage;
JButton cmdAdd,cmdDelete,cmdSetValue,cmdGetValue;
JPanel mainPanel,buttonPanel;
    public static void main(String[] args) 
    {
tableExample t=new tableExample();
    }

tableExample()
{
rows=new Vector();
columns= new Vector();
String[] columnNames = 
{ 
"ID", 
"Name",
"Class",
"SubClass",
"Rate",
"Quantity",
"Amount"
};
addColumns(columnNames);

tabModel=new DefaultTableModel();
tabModel.setDataVector(rows,columns);

table = new JTable(tabModel);
scrollPane= new JScrollPane(table);//ScrollPane

table.setRowSelectionAllowed(false);

table.getModel().addTableModelListener(this);

lblMessage=new JLabel("");


buttonPanel=new JPanel();
cmdAdd=new JButton("Add Row");
cmdDelete=new JButton("Delete") ;
cmdSetValue=new JButton("Set Value");
cmdGetValue=new JButton("Get Value");

buttonPanel.add(cmdAdd);
buttonPanel.add(cmdDelete);
buttonPanel.add(cmdSetValue);
buttonPanel.add(cmdGetValue);

cmdAdd.addActionListener(this);
cmdDelete.addActionListener(this);
cmdSetValue.addActionListener(this);
cmdGetValue.addActionListener(this);

mainPanel=new JPanel();
frame=new JFrame("Simple Table Example");
frame.setSize(800,600);
frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
mainPanel.setLayout(new BorderLayout());
mainPanel.add("Center",scrollPane);
mainPanel.add("South",buttonPanel);
mainPanel.setBackground(Color.white);
buttonPanel.setBackground(Color.white);
table.getParent().setBackground(Color.black);
frame.getContentPane().add(mainPanel);
frame.setVisible(true);

}
 public void addColumns(String[] colName)//Table Columns
{
for(int i=0;i<colName.length;i++)
columns.addElement((String) colName[i]);
}

public void addRow() //Add Row
{
Vector r=new Vector();
r=createBlankElement();
rows.addElement(r);
table.addNotify();

}

 public Vector createBlankElement() 
{
Vector t = new Vector();
t.addElement((String) " ");
t.addElement((String) " ");
t.addElement((String) " ");
t.addElement((String) " ");
t.addElement((String) " ");
t.addElement((String) " ");
t.addElement((String) " ");
return t;
}

 void deleteRow(int index) 
   {
     if(index!=-1)//At least one Row in Table
      { 
        rows.removeElementAt(index);
        table.addNotify();
       }

   }//Delete Row

 public void tableChanged(javax.swing.event.TableModelEvent source)     {
                 String msg="";
                 TableModel tabMod = (TableModel)source.getSource();
          switch (source.getType())
                   {
                       case TableModelEvent.UPDATE:
                       msg="Table Value Updated for  cell "+table.getSelectedRow()+","+table.getSelectedColumn()+"\nWhich is "+table.getValueAt(table.getSelectedRow(),table.getSelectedColumn()).toString();
              JOptionPane.showMessageDialog(null,msg,"Table Example",JOptionPane.INFORMATION_MESSAGE);
                break;

                   }

    }//Table Changed Method

public void selectCell(int row,int col)
    {
         if(row!=-1 && col !=-1)            
          {
          table.setRowSelectionInterval(row,row);
          table.setColumnSelectionInterval(col,col);
          }
    }

public void actionPerformed(ActionEvent source)
    {
         if (source.getSource()==(JButton) cmdAdd)
         {
             addRow();
         }
         if (source.getSource()==(JButton) cmdDelete)
         {
             deleteRow(table.getSelectedRow());
         }
         if (source.getSource()==(JButton) cmdSetValue)
         {
          String CName=JOptionPane.showInputDialog(null,"Enter Value to be set at Cell 0,2 ","Simple Table Example",JOptionPane.INFORMATION_MESSAGE);
              if(!CName.trim().equals("") && table.getRowCount()>0)
              {
                  selectCell(0,2);
                  table.setValueAt(CName,0,2);
              }
         }
         if (source.getSource()==(JButton) cmdGetValue)
         {
            if(table.getRowCount()>0)
             {
           String msg="Value At cell 0,0 is "+table.getValueAt(0,0).toString();
           JOptionPane.showMessageDialog(null,msg,"Table Example",JOptionPane.INFORMATION_MESSAGE);
             }
     }
    
    }//ActionList

}


