
package marketing;

/*
 * Raw_deleteStock.java requires no other files.
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.sql.*;
import stock.ConnectionService;
public class deletePayment extends JPanel 
                                implements ActionListener { 
    private JTable table;
    private JCheckBox rowCheck;
    private JCheckBox columnCheck;
    private JCheckBox cellCheck;
    private JButton delete;
    private ButtonGroup buttonGroup;
    private JTextArea output;
     MyTableModel tableModel;
    public deletePayment() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
       String[] columnNames = {"Payment Id",
                                        "Order id",
                                        "Date",
                                        "Payment"};   
       
     String query="select * from payment_detail";
        ResultSet rs;
              
// String[] obj=new String[4];
        Vector data=new Vector();
        int count=0;
        try{
         rs=ConnectionService.select(query);
         DateFormat df=new SimpleDateFormat("dd/mm/yyyy");
       
        while(rs.next()){
        String name1=rs.getString(1);
        String name2=rs.getString(2);
        String name3=df.format(rs.getDate(3));
        String name4=rs.getString(4);
        Row1 obj=new Row1(); 
       obj.setPaymentId(name1);
        obj.setOrder(name2);
        obj.setDate(name3);
        obj.setPayment(name4);
        data.addElement(obj);
        count++;
        }
          }
        catch(Exception e){};
      tableModel =new MyTableModel(columnNames,data);
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(new Row1Listener());
        table.getColumnModel().getSelectionModel().
            addListSelectionListener(new ColumnListener());
        
        add(new JScrollPane(table));
       JButton delete=new JButton("Delete");
       add(delete);
        add(new JLabel("Selection Mode"));
        buttonGroup = new ButtonGroup();
        addRadio("Multiple Interval Selection").setSelected(true);
        addRadio("Single Selection");
        //addRadio("Single Interval Selection");
delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
    }
    private void deleteActionPerformed(java.awt.event.ActionEvent evt)
    {
        Vector data =tableModel.getData();
        Row1 ra ;
    for (int c : table.getSelectedRows()) {
        
           ra=(Row1)data.remove(c); 
           
           String query="delete from payment_detail where payment_id='"+ra.getPaymentId()+"'";/*+"'&&date='"+ra.getDate()+"'&&quantity='"ra.getQun()+"'&&scrape='"+ra.getScr()+"'";*/
        int rs;
        
      
        try{
         rs=ConnectionService.update(query);
       
        if(rs==0)
            javax.swing.JOptionPane.showMessageDialog(null,"data of row :"+(c+1)+" is not deleted from databases ");
          
        else
            javax.swing.JOptionPane.showMessageDialog(null,"data of row  :"+(c+1)+" is  deleted from databases successfully");
        }catch(Exception e){javax.swing.JOptionPane.showMessageDialog(null,e.getMessage());};
table.addNotify();
    }
    table.setVisible(false);
    table.setVisible(true);
    }
private JRadioButton addRadio(String text) {
        JRadioButton b = new JRadioButton(text);
        b.addActionListener(this);
        buttonGroup.add(b);
        add(b);
        return b;
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
         if ("Multiple Interval Selection" == command) { 
            table.setSelectionMode(
                    ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
         
            cellCheck.setEnabled(false);
        } else if ("Single Selection" == command) {
            table.setSelectionMode(
                    ListSelectionModel.SINGLE_SELECTION);
          
        }

     
    }



    private class Row1Listener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
          
        }
    }

    private class ColumnListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
        
        }
    }
    

    class MyTableModel extends AbstractTableModel {
        public static final int PAYMENT_ID_INDEX = 0;
     public static final int ORDER_ID_INDEX = 1;
     public static final int DATE_INDEX = 2;
         public static final int PAYMENT_INDEX = 3; 
    
        private String[] columnNames;
        private Vector data ;
        public MyTableModel(String[] columnNames,Vector data)
        {
        this.columnNames=columnNames;
        this.data=data;
        }
        public Vector getData() {
         return data;
         
     }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.size();
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
             Row1 record = (Row1)data.get(row);
         switch (col) {
             case PAYMENT_ID_INDEX:
                return record.getPaymentId();
             case ORDER_ID_INDEX:
                return record.getOrder();
             case DATE_INDEX:
                return record.getDate();
            case PAYMENT_INDEX:
                return record.getPayment();
                default:
                return new Object();
        }
        
        }
      
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }

      
         public void setValueAt(Object value, int row, int column) {
       Row1 record = (Row1)data.get(row);
         switch (column) {
            case PAYMENT_ID_INDEX:
                record.setPaymentId((String)value);
                break;
             case ORDER_ID_INDEX:
                record.setOrder((String)value);
                break;
             case DATE_INDEX:
                record.setDate((String)value);
                break;
              case PAYMENT_INDEX:

                record.setPayment((String)value);
                break;
           
         }
         fireTableCellUpdated(row, column);
     }


    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
   /* private static void createAndShowGUI() {
        //Disable boldface controls.
        UIManager.put("swing.boldMetal", Boolean.FALSE); 

        //Create and set up the window.
        JFrame frame = new JFrame("Raw_deleteStock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        deletePayment newContentPane = new deletePayment();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        try{javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
       
        }   
        });
    }
         catch(Exception e)
         {
         }
}*/
}
 class Row1 {
     protected String payment_id;
     protected String order_id;
     protected String date;
     protected String payment;
 

     public Row1() {
        payment_id = "";
        order_id= "";
         date= "";
        payment= "";
     }

     public String getPaymentId() {
         return payment_id;
     }

     public void setPaymentId(String payment_id ) {
         this.payment_id =payment_id;
     }

     public String getOrder() {
         return  order_id;
     }
   
     public String getDate() {
         return date;
     }

     public void setOrder(String order_id) {
         this.order_id =order_id;
     }

     public String getPayment() {
         return payment;
     }
      public void setPayment(String payment) {
         this.payment = payment;
     }

     public void setDate(String date) {
         this.date = date;
     }
 }