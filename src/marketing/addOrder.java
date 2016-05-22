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

 public class addOrder extends JPanel{
 public static final String[] columnNames ={"order_id","company_name","date","status","category","total_cost","paid_cost",""};

     protected JTable table;
    protected JButton butt;
     protected JScrollPane scroller;
     protected InteractiveTableModel1 tableModel;

     public addOrder() {
         setSize(900,900);
         initComponent();
     }

     public void initComponent() {
         tableModel = new InteractiveTableModel1(columnNames);
         tableModel.addTableModelListener(new addOrder.InteractiveTableModel1Listener());
         table = new JTable();
         table.setModel(tableModel);
         table.setSurrendersFocusOnKeystroke(true);
         if (!tableModel.hasEmptyRow()) {
             tableModel.addEmptyRow();
         }

         scroller = new javax.swing.JScrollPane(table);
         table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
         TableColumn hidden = table.getColumnModel().getColumn(InteractiveTableModel1.HIDDEN_INDEX);
         hidden.setMinWidth(2);
         hidden.setPreferredWidth(2);
         hidden.setMaxWidth(2);
         hidden.setCellRenderer(new InteractiveRenderer(InteractiveTableModel1.HIDDEN_INDEX));

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
 //javax.swing.JOptionPane.showMessageDialog(null," num of total field field    "+numrows);
    for(int i = numrows - 2; i >=0; i--)   {	
AddOrd audioRecord = (AddOrd)dataVector.get(i);
if(audioRecord.getOrder()==""||audioRecord.getCom()==""||audioRecord.getDate()==""||audioRecord.getStatus()==""||audioRecord.getCat()==""||audioRecord.getTotal()==""||audioRecord.getPaid()=="")
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

String query="insert into comanage.order values('"+audioRecord.getOrder()+"','"+audioRecord.getCom()+"','"+audioRecord.getDate()+"','"+audioRecord.getStatus()+"','"+audioRecord.getCat()+"','"+audioRecord.getTotal()+"','"+audioRecord.getPaid()+"')";
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
 AddOrd audioRecord = (AddOrd)dataVector.get(dataVector.size() - 1);
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
                 if ((addOrder.this.tableModel.getRowCount() - 1) == row &&
                    !addOrder.this.tableModel.hasEmptyRow())
                 {
                     addOrder.this.tableModel.addEmptyRow();
                 }

                 highlightLastRow(row);
             }

             return c;
         }
     }

    class InteractiveTableModel1Listener implements TableModelListener {
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
             frame.getContentPane().add(new addOrder());
             frame.pack();
             frame.setVisible(true);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }*/
 }
 class AddOrd {
     protected String order_id;
     protected String company_name;
     protected String date;
     protected String status;
	 protected String category;
	 protected String total_cost;
	 protected String paid_cost;
     public AddOrd() {
         order_id = "";
         company_name = "";
         date = "";
          status= "";
		  category= "";
		  total_cost= "";
           paid_cost= "";
     }

     public String getOrder() {
         return order_id;
     }
     public void setOrder(String order_id) {
         this.order_id = order_id;
     }
     public String getCom() {
         return company_name;
     }
	 public void setCom(String company_name) {
         this.company_name =company_name ;
     }

     public String getDate() {
         return date;
     }
    public void setDate(String date) {
         this.date =date;
     }
     
	 public String getStatus() {
         return status;
     }
	 public void setStatus(String status) {
         this.status = status;
     }
public String getCat() {
         return category;
     }
     public void setCat(String category) {
         this.category =category ;
     }
public String getTotal() {
         return total_cost;
     }
     public void setTotal(String total_cost) {
         this.total_cost =total_cost ;
     }
public String getPaid() {
         return paid_cost;
     }
     public void setPaid(String paid_cost) {
         this.paid_cost =paid_cost ;
     }
     
     

     
 }
 class InteractiveTableModel1 extends AbstractTableModel {
     public static final int ORDER_INDEX = 0;
     public static final int COMPANY_INDEX = 1;
     public static final int DATE_INDEX = 2;
         public static final int STATUS_INDEX = 3; 
		 public static final int CATEGORY_INDEX = 4; 
		 public static final int TOTAL_COST_INDEX = 5;
public static final int PAID_COST_INDEX = 6;		 
    public static final int HIDDEN_INDEX = 7;

     protected String[] columnNames;
     protected Vector dataVector;

     public InteractiveTableModel1(String[] columnNames) {
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
		
		     case ORDER_INDEX:
             case COMPANY_INDEX:
             case DATE_INDEX:
              case STATUS_INDEX:
			  case CATEGORY_INDEX:
			  case TOTAL_COST_INDEX:
			  case PAID_COST_INDEX:
                return String.class;
             default:
                return Object.class;
         }
     }

     public Object getValueAt(int row, int column) {
         AddOrd record = (AddOrd)dataVector.get(row);
         switch (column) {
		
             
             case ORDER_INDEX:
                return record.getOrder();
             case COMPANY_INDEX:
                return record.getCom();
             case DATE_INDEX:
                return record.getDate();
             case STATUS_INDEX:
			    return record.getStatus();
			 case CATEGORY_INDEX:
            return record.getCat();
				case TOTAL_COST_INDEX:
           return record.getTotal();
				case PAID_COST_INDEX:
           return record.getPaid();
				
             default:
                return new Object();
         }
     }

     public void setValueAt(Object value, int row, int column) {
         AddOrd record = (AddOrd)dataVector.get(row);
         switch (column) {
               case ORDER_INDEX:
                record.setOrder((String)value);
                break;
             case COMPANY_INDEX:
                record.setCom((String)value);
                break;
             case DATE_INDEX:
                record.setDate((String)value);
                break;
              case STATUS_INDEX:

                record.setStatus((String)value);
                break;
			case CATEGORY_INDEX:
                record.setCat((String)value);
                break;
             	case TOTAL_COST_INDEX:

                record.setTotal((String)value);
                break;
				case PAID_COST_INDEX:

                record.setPaid((String)value);
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
         AddOrd audioRecord = (AddOrd)dataVector.get(dataVector.size() - 1);
         if (audioRecord.getOrder().trim().equals("") &&
            audioRecord.getCom().trim().equals("") &&
              audioRecord.getDate().trim().equals("") &&
            audioRecord.getStatus().trim().equals("")&&
            audioRecord.getCat().trim().equals("")&&
            audioRecord.getTotal().trim().equals("")&&
            audioRecord.getPaid().trim().equals(""))
         {
            return true;
         }
         else return false;
     }

     public void addEmptyRow() {
         dataVector.add(new AddOrd());
         fireTableRowsInserted(
            dataVector.size() - 1,
            dataVector.size() - 1);
     }
      public void removeRow(int i) {
        dataVector.removeElementAt(i);
        
     }
 }



