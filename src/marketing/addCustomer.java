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

 public class addCustomer extends JPanel {
 public static final String[] columnNames ={"cust_id","company_name","address","contact","category",""};

     protected JTable table;
    protected JButton butt;
     protected JScrollPane scroller;
     protected InteractiveTableModel3 tableModel;

     public addCustomer() {
         initComponent();
     }

     public void initComponent() {
         setSize(800,900);
         tableModel = new InteractiveTableModel3(columnNames);
         tableModel.addTableModelListener(new addCustomer.InteractiveTableModel3Listener());
         table = new JTable();
         table.setModel(tableModel);
         table.setSurrendersFocusOnKeystroke(true);
         if (!tableModel.hasEmptyRow()) {
             tableModel.addEmptyRow();
         }

         scroller = new javax.swing.JScrollPane(table);
         table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
         TableColumn hidden = table.getColumnModel().getColumn(InteractiveTableModel3.HIDDEN_INDEX);
         hidden.setMinWidth(2);
         hidden.setPreferredWidth(2);
         hidden.setMaxWidth(2);
         hidden.setCellRenderer(new InteractiveRenderer(InteractiveTableModel3.HIDDEN_INDEX));

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
addCust audioRecord = (addCust)dataVector.get(i);
if(audioRecord.getCust()==""||audioRecord.getCom()==""||audioRecord.getAdd()==""||audioRecord.getCont()==""||audioRecord.getCat()=="")
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

String query="insert into cust_dealer_detail values('"+audioRecord.getCust()+"','"+audioRecord.getCom()+"','"+audioRecord.getAdd()+"','"+audioRecord.getCont()+"','"+audioRecord.getCat()+"')";
        int rs;
        
        
        try{
         rs=ConnectionService.update(query);
        if(rs==0)
            javax.swing.JOptionPane.showMessageDialog(null,"query of row :"+(i+1)+" is not inserted into databases due to wrong formate of any field");
          
        else
            javax.swing.JOptionPane.showMessageDialog(null,"query of row  :"+(i+1)+" is  inserted into databases successfully");
        }catch(Exception e){javax.swing.JOptionPane.showMessageDialog(null,e);};





}

}
 addCust audioRecord = (addCust)dataVector.get(dataVector.size() - 1);
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
                 if ((addCustomer.this.tableModel.getRowCount() - 1) == row &&
                    !addCustomer.this.tableModel.hasEmptyRow())
                 {
                     addCustomer.this.tableModel.addEmptyRow();
                 }

                 highlightLastRow(row);
             }

             return c;
         }
     }

    class InteractiveTableModel3Listener implements TableModelListener {
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

  /*   public static void main(String[] args) {
         try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
             JFrame frame = new JFrame("Interactive Form");
             frame.addWindowListener(new WindowAdapter() {
                 public void windowClosing(WindowEvent evt) {
                     System.exit(0);
                 }
             });
             frame.getContentPane().add(new addCustomer());
             frame.pack();
             frame.setVisible(true);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }*/
 }
 class addCust {
     protected String cust_id;
     protected String company_name;
     protected String address;
     protected String contact;
	 protected String category;
     public addCust() {
         cust_id = "";
         company_name = "";
         address = "";
          contact= "";
		  category= "";

     }

     public String getCust() {
         return cust_id;
     }
     public void setCust(String cust_id) {
         this.cust_id = cust_id;
     }
     public String getCom() {
         return company_name;
     }
	 public void setCom(String company_name) {
         this.company_name =company_name ;
     }

     public String getAdd() {
         return address;
     }
    public void setAdd(String address) {
         this.address =address ;
     }
     
	 public String getCont() {
         return contact;
     }
	 public void setCont(String contact) {
         this.contact = contact;
     }
public String getCat() {
         return category;
     }
     public void setCat(String category) {
         this.category = category ;
     }

     
     

     
 }
 class InteractiveTableModel3 extends AbstractTableModel {
     public static final int CUSTOMER_INDEX = 0;
     public static final int COMPANY_INDEX = 1;
     public static final int ADDRESS_INDEX = 2;
         public static final int CONTACT_INDEX = 3; 
		 public static final int CATEGORY_INDEX = 4; 
    public static final int HIDDEN_INDEX = 5;

     protected String[] columnNames;
     protected Vector dataVector;

     public InteractiveTableModel3(String[] columnNames) {
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
		     case CUSTOMER_INDEX:
             case COMPANY_INDEX:
             case ADDRESS_INDEX:
              case CONTACT_INDEX:
			  case CATEGORY_INDEX:
                return String.class;
             default:
                return Object.class;
         }
     }

     public Object getValueAt(int row, int column) {
         addCust record = (addCust)dataVector.get(row);
         switch (column) {
		
             case CUSTOMER_INDEX:
                return record.getCust();
             case COMPANY_INDEX:
                return record.getCom();
             case ADDRESS_INDEX:
                return record.getAdd();
             case CONTACT_INDEX:
			    return record.getCont();
			 case CATEGORY_INDEX:
           
                return record.getCat();
             default:
                return new Object();
         }
     }

     public void setValueAt(Object value, int row, int column) {
         addCust record = (addCust)dataVector.get(row);
         switch (column) {
               case CUSTOMER_INDEX:
                record.setCust((String)value);
                break;
             case COMPANY_INDEX:
                record.setCom((String)value);
                break;
             case ADDRESS_INDEX:
                record.setAdd((String)value);
                break;
              case CONTACT_INDEX:

                record.setCont((String)value);
                break;
			case CATEGORY_INDEX:
                record.setCat((String)value);
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
         addCust audioRecord = (addCust)dataVector.get(dataVector.size() - 1);
         if (audioRecord.getCust().trim().equals("") &&
            audioRecord.getCom().trim().equals("") &&
              audioRecord.getAdd().trim().equals("") &&
            audioRecord.getCont().trim().equals("")&&
            audioRecord.getCat().trim().equals(""))
         {
            return true;
         }
         else return false;
     }

     public void addEmptyRow() {
         dataVector.add(new addCust());
         fireTableRowsInserted(
            dataVector.size() - 1,
            dataVector.size() - 1);
     }
      public void removeRow(int i) {
        dataVector.removeElementAt(i);
        
     }
 }



