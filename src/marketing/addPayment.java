package marketing;
import java.awt.event.WindowAdapter;
 import java.awt.event.WindowEvent;
 import java.awt.BorderLayout;
 import java.awt.Component;
 import java.awt.FlowLayout;
 import javax.swing.event.TableModelEvent;
 import javax.swing.event.TableModelListener;
 import javax.swing.table.DefaultTableCellRenderer;
 import javax.swing.table.TableColumn;
 import javax.swing.JButton;
 import javax.swing.JFrame;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 import javax.swing.JScrollPane;
 import javax.swing.JTable;
 import javax.swing.JTextField;
 import javax.swing.UIManager;
 import javax.swing.*;
 import java.util.Collection;
 import java.util.Vector;
 import javax.swing.table.DefaultTableModel; 
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.text.*;
import stock.ConnectionService;

 public class addPayment extends JPanel{
 public static final String[] columnNames ={"payment id","order id","date","payment",""};

     protected JTable table;
    protected JButton butt;
     protected JScrollPane scroller;
     protected InteractiveTableModel2 tableModel;

     public addPayment() {
         setSize(900,900);
         initComponent();
     }

     public void initComponent() {
         
         tableModel = new InteractiveTableModel2(columnNames);
         tableModel.addTableModelListener(new addPayment.InteractiveTableModel2Listener());
         table = new JTable();
         table.setModel(tableModel);
         table.setSurrendersFocusOnKeystroke(true);
         if (!tableModel.hasEmptyRow()) {
             tableModel.addEmptyRow();
         }

         scroller = new javax.swing.JScrollPane(table);
         table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
         TableColumn hidden = table.getColumnModel().getColumn(InteractiveTableModel2.HIDDEN_INDEX);
         hidden.setMinWidth(2);
         hidden.setPreferredWidth(2);
         hidden.setMaxWidth(2);
         hidden.setCellRenderer(new InteractiveRenderer(InteractiveTableModel2.HIDDEN_INDEX));

    //     setLayout(new BorderLayout());
      //   add(scroller, BorderLayout.CENTER);
    //     setLayout(new GridLayout());
           scroller.setSize(500,500);
         add(scroller);
           butt= new JButton("add");
          butt.setSize(20,10);
         add(butt);
           butt.addActionListener(new java.awt.event.ActionListener() 
           {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
     }
             private void updateActionPerformed(java.awt.event.ActionEvent evt) {                                        
// TODO add your handling code here:
insertQuery(tableModel.getVector());
clearTable();

//initComponent();
    }
void insertQuery(Vector dataVector)
{
    
    
    int numrows = tableModel.getRowCount(); 
 //javax.swing.JOptionPane.showMessageDialog(null," numof total field field    "+numrows);
    for(int i = numrows - 2; i >=0; i--)   {	
AddPay audioRecord = (AddPay)dataVector.get(i);
if(audioRecord.getPayid()==""||audioRecord.getOrder()==""||audioRecord.getDate()==""||audioRecord.getPayment()=="")
{
 /*String query="insert into raw_stock values('"+audioRecord.getPro()+"','"+audioRecord.getCom()+"','"+audioRecord.getQun()+"',"+audioRecord.getDate()+")";
        int rs;
        
        
        try{
         rs=ConnectionService.update(query);
        if(rs==0)
            javax.swing.JOptionPane.showMessageDialog(null,"query of row :"+i+" is not inserted into databases due to wrong formate of any field");
          }
        catch(Exception e){};*/
javax.swing.JOptionPane.showMessageDialog(null,"query of row :"+(i+1)+" is not inserted into databases due to missing of some field");

}
else
{
   // javax.swing.JOptionPane.showMessageDialog(null,"query of row :"+i+" is not inserted into databases due to missing of some field");

String query="insert into payment_detail values('"+audioRecord.getPayid()+"','"+audioRecord.getOrder()+"','"+audioRecord.getDate()+"','"+audioRecord.getPayment()+"')";
        int rs;
        
        
        try{
         rs=ConnectionService.update(query);
        if(rs==0)
            javax.swing.JOptionPane.showMessageDialog(null,"query of row :"+(i+1)+" is not inserted into databases due to wrong formate of any field");
          
        else
            javax.swing.JOptionPane.showMessageDialog(null,"query of row  :"+(i+1)+" is  inserted into databases successfully");
        }catch(Exception e){javax.swing.JOptionPane.showMessageDialog(null,e.getMessage());};





}

}
 AddPay audioRecord = (AddPay)dataVector.get(dataVector.size() - 1);
}
public  void clearTable(){   
//TableModel model = this.getModel();    
int numrows = tableModel.getRowCount();   
 for(int i = numrows - 1; i >=0; i--)   {	
tableModel.removeRow(i);
}
 tableModel.addEmptyRow();

    }
     public void highlightLastRow(int row) {
         int lastrow = tableModel.getRowCount();
         if (row == lastrow - 1) {
             table.setRowSelectionInterval(lastrow - 1, lastrow - 1);
         } else {
             table.setRowSelectionInterval(row + 1, row + 1);
         }

         table.setColumnSelectionInterval(0, 0);
     }
 

     class InteractiveRenderer extends DefaultTableCellRenderer {
         protected int interactiveColumn;

         public InteractiveRenderer(int interactiveColumn) {
             this.interactiveColumn = interactiveColumn;
         }

         public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row,
            int column)
         {
             Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
             if (column == interactiveColumn && hasFocus) {
                 if ((addPayment.this.tableModel.getRowCount() - 1) == row &&
                    !addPayment.this.tableModel.hasEmptyRow())
                 {
                     addPayment.this.tableModel.addEmptyRow();
                 }

                 highlightLastRow(row);
             }

             return c;
         }
     }

    class InteractiveTableModel2Listener implements TableModelListener {
         public void tableChanged(TableModelEvent evt) {
             if (evt.getType() == TableModelEvent.UPDATE) {
                 int column = evt.getColumn();
                 int row = evt.getFirstRow();
                Object  name=table.getValueAt(row,column);
                 System.out.println("row: " + row + " column: " + column+"value:"+name.toString());
                 table.setColumnSelectionInterval(column + 1, column + 1);
                 table.setRowSelectionInterval(row, row);
             }
         }
     }

  /* public static void main(String[] args) {
         try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
             JFrame frame = new JFrame("Interactive Form");
             frame.addWindowListener(new WindowAdapter() {
                 public void windowClosing(WindowEvent evt) {
                     System.exit(0);
                 }
             });
             frame.getContentPane().add(new addPayment());
             frame.pack();
             frame.setVisible(true);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }*/
 }
 class AddPay {
     protected String payment_id;
     protected String order_id;
     protected String date;
     protected String payment;
     public AddPay() {
         payment_id= "";
         order_id = "";
         date = "";
         payment = "";
          
     }
      public String getPayid() {
         return payment_id;
     }
     public void setPayid(String payment_id) {
         this.payment_id = payment_id;
     }
     public String getOrder() {
         return order_id;
     }
     public void setOrder(String order_id) {
         this.order_id = order_id;
     }
     public String getDate() {
         return date;
     }
	 public void setDate(String date) {
         this.date =date;
     }

     public String getPayment() {
         return payment;
     }
    public void setPayment(String payment) {
         this.payment =payment ;
     }
     
	 
 }
 class InteractiveTableModel2 extends AbstractTableModel {
     public static final int PAYMENT_ID_INDEX = 0;
     public static final int ORDER_INDEX = 1;
     public static final int DATE_INDEX= 2;
     public static final int PAYMENT_INDEX = 3;
         public static final int HIDDEN_INDEX = 4;

     protected String[] columnNames;
     protected Vector dataVector;

     public InteractiveTableModel2(String[] columnNames) {
         this.columnNames = columnNames;
         dataVector = new Vector();
     }

     public String getColumnName(int column) {
         return columnNames[column];
     }
      public Vector getVector() {
         return dataVector;
     }

     public boolean isCellEditable(int row, int column) {
         if (column == HIDDEN_INDEX) return false;
         else return true;
     }

     public Class getColumnClass(int column) {
         switch (column) {
                  case PAYMENT_ID_INDEX:
		     case ORDER_INDEX:
             case DATE_INDEX:
             case PAYMENT_INDEX:
                return String.class;
             default:
                return Object.class;
         }
     }

     public Object getValueAt(int row, int column) {
         AddPay record = (AddPay)dataVector.get(row);
         switch (column) {
		 case PAYMENT_ID_INDEX:  
                     return record.getPayid();
             case ORDER_INDEX:
                return record.getOrder();
             case DATE_INDEX:
                return record.getDate();
             case PAYMENT_INDEX:
                return record.getPayment();
            
             default:
                return new Object();
         }
     }

     public void setValueAt(Object value, int row, int column) {
         AddPay record = (AddPay)dataVector.get(row);
         switch (column) {
              case PAYMENT_ID_INDEX:  
                   record.setPayid((String)value);
                   break;
               case ORDER_INDEX:
                record.setOrder((String)value);
                break;
             case DATE_INDEX:
                record.setDate((String)value);
                break;
             case PAYMENT_INDEX:
                record.setPayment((String)value);
                break;
              	
             default:
                System.out.println("invalid index");
         }
         fireTableCellUpdated(row, column);
     }

     public int getRowCount() {
         return dataVector.size();
     }

     public int getColumnCount() {
         return columnNames.length;
     }

     public boolean hasEmptyRow() {
         if (dataVector.size() == 0) return false;
         AddPay audioRecord = (AddPay)dataVector.get(dataVector.size() - 1);
         if (audioRecord.getPayid().trim().equals("") &&
                 audioRecord.getOrder().trim().equals("") &&
            audioRecord.getDate().trim().equals("") &&
              audioRecord.getPayment().trim().equals("")
            )
         {
            return true;
         }
         else return false;
     }

     public void addEmptyRow() {
         dataVector.add(new AddPay());
         fireTableRowsInserted(
            dataVector.size() - 1,
            dataVector.size() - 1);
     }
      public void removeRow(int i) {
        dataVector.removeElementAt(i);
        
     }
 }



