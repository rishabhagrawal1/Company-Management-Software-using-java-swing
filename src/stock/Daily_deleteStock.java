
package stock;

/*
 *Daily_deleteStock.java requires no other files.
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
public class Daily_deleteStock extends JPanel 
                                implements ActionListener { 
    private JTable table;
    private JCheckBox rowCheck;
    private JCheckBox columnCheck;
    private JCheckBox cellCheck;
    private JButton delete;
    private ButtonGroup buttonGroup;
    private JTextArea output;
     MyTableModel tableModel;
    public Daily_deleteStock() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
       String[] columnNames = {"Product Id",
                                         "Date",
                                        "Quantity",
					"Scrape"
                                       };   
       
     String query="select * from daily_made_product";
        ResultSet rs;
              
// String[] obj=new String[4];
        Vector data=new Vector();
        int count=0;
        try{
         rs=ConnectionService.select(query);
         DateFormat df=new SimpleDateFormat("dd/mm/yyyy");
       
        while(rs.next()){
        String name1=rs.getString(1);
        String name4=rs.getString(4);
        String name3=rs.getString(3);
        String name2=df.format(rs.getDate(2));
        Row obj=new Row(); 
       
        obj.setPro(name1);
        obj.setDate(name2);
        obj.setQun(name3);
        obj.setScr(name2);
        data.addElement(obj);
        count++;
        }
          }
        catch(Exception e){};
      
       
       
       
       
       
         tableModel =new MyTableModel(columnNames,data);
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(new RowListener());
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
        Row ra ;
    for (int c : table.getSelectedRows()) {
        
           ra=(Row)data.remove(c); 
           
           String query="delete from daily_made_product where product_id='"+ra.getPro()+"'";
        int rs;
        
      
        try{
         rs=ConnectionService.update(query);
       
        if(rs==0)
        {
             javax.swing.JOptionPane.showMessageDialog(null,"data of row :"+(c+1)+" is not deleted from databases ");
              data.add(ra);
        }
          
        else
        { javax.swing.JOptionPane.showMessageDialog(null,"data of row  :"+(c+1)+" is  deleted from databases successfully");
         
        }
        }catch(Exception e){};


           
           
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



    private class RowListener implements ListSelectionListener {
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
        public static final int PRODUCT_INDEX = 0;
     public static final int SCRAPE_INDEX = 3;
     public static final int QUANTITY_INDEX = 2;
         public static final int DATE_INDEX = 1; 
    
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
             Row record = (Row)data.get(row);
         switch (col) {
             case PRODUCT_INDEX:
                return record.getPro();
            case DATE_INDEX:
                return record.getDate();
             case QUANTITY_INDEX:
                return record.getQun();
             
		     case SCRAPE_INDEX:
                return record.getScr();
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
       Row record = (Row)data.get(row);
         switch (column) {
            case PRODUCT_INDEX:
                record.setPro((String)value);
                break;
             case SCRAPE_INDEX:
                record.setScr((String)value);
                break;
             case QUANTITY_INDEX:
                record.setQun((String)value);
                break;
              case DATE_INDEX:

                record.setDate((String)value);
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
       Daily_deleteStock newContentPane = newDaily_deleteStock();
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
 class Row {
     protected String product_id;
     protected String company;
     protected String quantity;
     protected String date;
	 

     public Row() {
         product_id = "";
         company = "";
         quantity = "";
          date= "";
     }

     public String getPro() {
         return product_id;
     }

     public void setScr(String title) {
         this.company = title;
     }

     public String getQun() {
         return quantity;
     }
   
     public String getDate() {
         return date;
     }

     public void setPro(String artist) {
         this.product_id = artist;
     }

     public String getScr() {
         return company;
     }
      public void setQun(String artist) {
         this.quantity = artist;
     }

     public void setDate(String album) {
         this.date = album;
     }
 }