
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
public class deleteOrder extends JPanel 
                                implements ActionListener { 
    private JTable table;
    private JCheckBox rowCheck;
    private JCheckBox columnCheck;
    private JCheckBox cellCheck;
    private JButton delete;
    private ButtonGroup buttonGroup;
    private JTextArea output;
     MyTableModel tableModel;
    public deleteOrder() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
       String[] columnNames = {"Order Id",
                                        "Company Name",
                                        "Date",
                                        "Status","Category","Total Cost","Paid Cost"};   
       
     String query="select * from comanage.order";
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
		String name5=rs.getString(5);
		String name6=rs.getString(6);
		String name7=rs.getString(7);
        AddRow obj=new AddRow(); 
       
        obj.setOrderId(name1);
        obj.setCom(name2);
        obj.setDate(name3);
        obj.setStatus(name4);
		obj.setCat(name5);
		obj.setTotal(name6);
		obj.setPaid(name7);
        data.addElement(obj);
        count++;
        }
          }
        catch(Exception e){};
       tableModel =new MyTableModel(columnNames,data);
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(new AddRowListener());
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
        AddRow ra ;
    for (int c : table.getSelectedRows()) {
        
           ra=(AddRow)data.remove(c); 
           
           String query="delete from comanage.order where order_id='"+ra.getOrderId()+"'";
        int rs;
        
      
        try{
         rs=ConnectionService.update(query);
       
        if(rs==0)
        {
             javax.swing.JOptionPane.showMessageDialog(null,"data of row :"+(c+1)+" is not deleted from databases ");
           data.add(ra);
        }
          
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



    private class AddRowListener implements ListSelectionListener {
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
        public static final int company_name_INDEX = 0;
     public static final int COM_INDEX = 1;
     public static final int DATE_INDEX = 2;
         public static final int STATUS_INDEX = 3; 
    public static final int CAT_INDEX = 4; 
	public static final int TOTAL_INDEX = 5; 
	public static final int PAID_INDEX = 6; 
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
             AddRow record = (AddRow)data.get(row);
         switch (col) {
             case company_name_INDEX:
                return record.getOrderId();
             case COM_INDEX:
                return record.getCom();
             case DATE_INDEX:
                return record.getDate();
            case STATUS_INDEX:
                return record.getStatus();
				         case CAT_INDEX:
                return record.getCat();
             case TOTAL_INDEX:
                return record.getTotal();
            case PAID_INDEX:
                return record.getPaid();
    
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
       AddRow record = (AddRow)data.get(row);
         switch (column) {
            case company_name_INDEX:
                record.setOrderId((String)value);
                break;
             case COM_INDEX:
                record.setCom((String)value);
                break;
             case DATE_INDEX:
                record.setDate((String)value);
                break;
              case STATUS_INDEX:

                record.setStatus((String)value);
                break;
                   case CAT_INDEX:
                record.setCat((String)value);
             case TOTAL_INDEX:
                record.setTotal((String)value);
            case PAID_INDEX:
                record.setPaid((String)value);
         }
         fireTableCellUpdated(row, column);
     }


    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Disable boldface controls.
        UIManager.put("swing.boldMetal", Boolean.FALSE); 

        //Create and set up the window.
        JFrame frame = new JFrame("Raw_deleteStock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        deleteOrder newContentPane = new deleteOrder();
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
}
}
 class AddRow {
     protected String order_id;
     protected String company_name;
     protected String date;
     protected String status;
 protected String category;
 protected String total;
 protected String paid;

     public AddRow() {
        order_id = "";
        company_name= "";
         date= "";
        status= "";
		category="";
		total="";
		paid="";
     }

     public String getOrderId() {
         return order_id;
     }

     public void setOrderId(String order_id ) {
         this.order_id =order_id;
     }

     public String getCom() {
         return  company_name;
     }
   
     public String getDate() {
         return date;
     }

     public void setCom(String company_name) {
         this.company_name =company_name;
     }

     public String getStatus() {
         return status;
     }
      public void setStatus(String status) {
         this.status = status;
     }

     public void setDate(String date) {
         this.date = date;
     }
 
  public String getCat() {
         return category;
     }

     public void setCat(String category ) {
         this.category =category;
     }
public String getTotal() {
         return total;
     }
     public void setTotal(String total) {
         this.total =total;
     }
     public String getPaid() {
         return paid;
     }

     public void setPaid(String paid ) {
         this.paid =paid;
     }
 }