/*
 * frame.java
 *
 * Created on 30 November, 2009, 8:02 PM
 */

/**
 *
 * @author  AKHIL
 */
package stock;
import java.awt.Color;
import java.awt.Dimension;
public class frame extends javax.swing.JFrame {
    
    /** Creates new form frame */
    public frame() {
        setSize(400,400);
        setPreferredSize(new Dimension(700,500));
  
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jMenuBar1 = new javax.swing.JMenuBar();
        raw_stock = new javax.swing.JMenu();
        r_view = new javax.swing.JMenuItem();
        r_delete = new javax.swing.JMenuItem();
        r_modify = new javax.swing.JMenuItem();
        r_insert = new javax.swing.JMenuItem();
        p_stock = new javax.swing.JMenu();
        p_view = new javax.swing.JMenuItem();
        p_insert = new javax.swing.JMenuItem();
        p_delete = new javax.swing.JMenuItem();
        p_modify = new javax.swing.JMenuItem();
        d_m_pro = new javax.swing.JMenu();
        d_m_p_view = new javax.swing.JMenuItem();
        d_m_p_insert = new javax.swing.JMenuItem();
        d_m_p_delete = new javax.swing.JMenuItem();
        d_m_p_modify = new javax.swing.JMenuItem();
        o_detail = new javax.swing.JMenu();
        o_d_view = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        raw_stock.setText("Raw Stock");
        r_view.setText("view");
        r_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_viewActionPerformed(evt);
            }
        });

        raw_stock.add(r_view);

        r_delete.setText("delete");
        r_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_deleteActionPerformed(evt);
            }
        });

        raw_stock.add(r_delete);

        r_modify.setText("modify");
        r_modify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_modifyActionPerformed(evt);
            }
        });

        raw_stock.add(r_modify);

        r_insert.setText("insert");
        r_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_insertActionPerformed(evt);
            }
        });

        raw_stock.add(r_insert);

        jMenuBar1.add(raw_stock);

        p_stock.setText("Product Stock");
        p_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_stockActionPerformed(evt);
            }
        });

        p_view.setText("view");
        p_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_viewActionPerformed(evt);
            }
        });

        p_stock.add(p_view);

        p_insert.setText("insert");
        p_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_insertActionPerformed(evt);
            }
        });

        p_stock.add(p_insert);

        p_delete.setText("delete");
        p_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_deleteActionPerformed(evt);
            }
        });

        p_stock.add(p_delete);

        p_modify.setText("modify");
        p_modify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_modifyActionPerformed(evt);
            }
        });

        p_stock.add(p_modify);

        jMenuBar1.add(p_stock);

        d_m_pro.setText("Daily Made Product");
        d_m_p_view.setText("view");
        d_m_p_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d_m_p_viewActionPerformed(evt);
            }
        });

        d_m_pro.add(d_m_p_view);

        d_m_p_insert.setText("insert");
        d_m_p_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d_m_p_insertActionPerformed(evt);
            }
        });

        d_m_pro.add(d_m_p_insert);

        d_m_p_delete.setText("delete");
        d_m_p_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d_m_p_deleteActionPerformed(evt);
            }
        });

        d_m_pro.add(d_m_p_delete);

        d_m_p_modify.setText("modify");
        d_m_p_modify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d_m_p_modifyActionPerformed(evt);
            }
        });

        d_m_pro.add(d_m_p_modify);

        jMenuBar1.add(d_m_pro);

        o_detail.setText("Order Detail");
        o_d_view.setText("view");
        o_d_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                o_d_viewActionPerformed(evt);
            }
        });

        o_detail.add(o_d_view);

        jMenuBar1.add(o_detail);

        setJMenuBar(jMenuBar1);

      /*  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );*/
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void d_m_p_modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d_m_p_modifyActionPerformed
// TODO add your handling code here:
         getContentPane().removeAll();
         Update_dailyStock o_d=new Update_dailyStock();
        add(o_d);
        o_d.setVisible(false);
        o_d.setVisible(true);
    }//GEN-LAST:event_d_m_p_modifyActionPerformed

    private void d_m_p_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d_m_p_deleteActionPerformed
// TODO add your handling code here:
        getContentPane().removeAll();
         Daily_deleteStock o_d=new Daily_deleteStock();
        add(o_d);
        o_d.setVisible(false);
        o_d.setVisible(true);
    }//GEN-LAST:event_d_m_p_deleteActionPerformed

    private void p_modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_modifyActionPerformed
// TODO add your handling code here:
         getContentPane().removeAll();
         Update_productStock o_d=new Update_productStock();
        add(o_d);
         o_d.setVisible(false);
        o_d.setVisible(true);  
    }//GEN-LAST:event_p_modifyActionPerformed

    private void r_modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_modifyActionPerformed
// TODO add your handling code here:
        getContentPane().removeAll();
         Update_rawStock o_d=new Update_rawStock();
        add(o_d);
       o_d.setVisible(false);
        o_d.setVisible(true);   
    }//GEN-LAST:event_r_modifyActionPerformed

    private void p_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_deleteActionPerformed
// TODO add your handling code here:
     getContentPane().removeAll();
         Product_deleteStock o_d=new Product_deleteStock();
        add(o_d);
        o_d.setVisible(false);
        o_d.setVisible(true);  
    }//GEN-LAST:event_p_deleteActionPerformed

    private void p_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_stockActionPerformed
// TODO add your handling code here:
        
    }//GEN-LAST:event_p_stockActionPerformed

    private void r_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_deleteActionPerformed
// TODO add your handling code here:
          getContentPane().removeAll();
         Raw_deleteStock o_d=new Raw_deleteStock();
        add(o_d);
        o_d.setVisible(false);
        o_d.setVisible(true);
    }//GEN-LAST:event_r_deleteActionPerformed

    private void d_m_p_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d_m_p_insertActionPerformed
           getContentPane().removeAll();
         Daily_insertStock o_d=new Daily_insertStock();
        add(o_d);
       o_d.setVisible(false);
        o_d.setVisible(true);
    }//GEN-LAST:event_d_m_p_insertActionPerformed

    private void o_d_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_o_d_viewActionPerformed
// TODO add your handling code here:
         getContentPane().removeAll();
         Order_Detail o_d=new Order_Detail();
        add(o_d);
        o_d.setVisible(false);
        o_d.setVisible(true);
        //repaint();
    }//GEN-LAST:event_o_d_viewActionPerformed

    private void d_m_p_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d_m_p_viewActionPerformed
// TODO add your handling code here:
          Daily_Product dmp=new  Daily_Product();
      getContentPane().removeAll();
        add(dmp);
        dmp.setVisible(false);
        dmp.setVisible(true);
    }//GEN-LAST:event_d_m_p_viewActionPerformed

    private void p_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_viewActionPerformed
// TODO add your handling code here:
        Product pro=new Product();
      getContentPane().removeAll();
        add(pro);
        pro.setVisible(false);
        pro.setVisible(true);
    }//GEN-LAST:event_p_viewActionPerformed

    private void r_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_viewActionPerformed
// TODO add your handling code here:
        Raw1 pa=new Raw1();
        
       // pa.setSize(800,400);
      getContentPane().removeAll();
        add(pa);
        pa.setVisible(false);
        pa.setVisible(true);
        
    }//GEN-LAST:event_r_viewActionPerformed
 private void r_insertActionPerformed(java.awt.event.ActionEvent evt) {                                         
// TODO add your handling code here:
       // InteractiveForm1 pa=new InteractiveForm1();
        Raw_insertStock pa=new Raw_insertStock();
       // pa.setSize(800,400);
      getContentPane().removeAll();
        add(pa);
        pa.setVisible(false);
        pa.setVisible(true);
    }  
private void p_insertActionPerformed(java.awt.event.ActionEvent evt) {                                         
// TODO add your handling code here:
        P_insertStock pa=new P_insertStock();
        
       // pa.setSize(800,400);
      getContentPane().removeAll();
        add(pa);
        pa.setVisible(false);
        pa.setVisible(true);
        
    }                                     
    
    /**
     * @param args the command line arguments
     */
   /* public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frame().setVisible(true);
            }
        });
    }*/
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem d_m_p_delete;
    private javax.swing.JMenuItem d_m_p_insert;
    private javax.swing.JMenuItem d_m_p_modify;
    private javax.swing.JMenuItem d_m_p_view;
    private javax.swing.JMenu d_m_pro;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem o_d_view;
    private javax.swing.JMenu o_detail;
    private javax.swing.JMenuItem p_delete;
    private javax.swing.JMenuItem p_insert;
    private javax.swing.JMenuItem p_modify;
    private javax.swing.JMenu p_stock;
    private javax.swing.JMenuItem p_view;
    private javax.swing.JMenuItem r_delete;
    private javax.swing.JMenuItem r_insert;
    private javax.swing.JMenuItem r_modify;
    private javax.swing.JMenuItem r_view;
    private javax.swing.JMenu raw_stock;
    // End of variables declaration//GEN-END:variables
    
}
