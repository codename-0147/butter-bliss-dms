/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Piyumi
 */
public class returnDamage extends javax.swing.JPanel {

    private HomeOU home;
    private static HashMap<String,String> loadTypes = new HashMap<>();
    private static HashMap<String,String> loadDis = new HashMap<>();
    private static HashMap<String,String> loadReason = new HashMap<>();
    private static Connection con;
    
    public returnDamage(HomeOU home) {
        //Connect();
        initComponents();
        this.home = home;
        loadProduct();
        loadTable();
        loadDistributor();
        loadReason();
        //loadStatus();
        generateReportID();
        
    }
    
    private void generateReportID(){
        String id = ""+ System.currentTimeMillis();
        jTextField1.setText(String.valueOf(id));
    
    }
    
    private void loadProduct(){
    
          try {
            ResultSet resultset = MySQL.executeSearch("SELECT * FROM `product`  ");
           
                      Vector<String> vector = new Vector<>();
                      vector.add("Select");  
            
            while (resultset.next()) {
                
                vector.add(resultset.getString("name"));
                loadTypes.put(resultset.getString("name"),resultset.getString("id"));
                
                 DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
              //  jComboBox1.setModel(model);
               
            }
       } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
     
     private void loadDistributor(){
    
          try {
            ResultSet resultset = MySQL.executeSearch("SELECT * FROM `distributor`  ");
            
           
                      Vector<String> vector = new Vector<>();
                      vector.add("Select");  
            
            while (resultset.next()) {
                
                vector.add(resultset.getString("name"));
                loadDis.put(resultset.getString("name"),resultset.getString("id"));
                
                 DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
                jComboBox2.setModel(model);
               
            }
       } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
     
//     public void Connect(){
//     
//         try {
//              Class.forName("com.mysql.cj.jdbc.Driver");
//                 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/butter_bliss_db","root","#C0N47@mysql");
//        
//             
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     
//     
//     }
     
//     PreparedStatement pst;
//     PreparedStatement pst1;
//     ResultSet rs1;
//     ResultSet rs2;
     
      private void loadStatus(){
    
//          try {
//              
//             
//              pst = con.prepareStatement("SELECT COUNT(`status`) FROM `returns` WHERE `status` ='Pending' ");
//              pst1 = con.prepareStatement("SELECT COUNT(`returns`.`status`) FROM `returns` WHERE `status` ='Received' ");
//              
//             ResultSet rs1 = pst.executeQuery();
//             
//              while (rs1.next()) {
//                 int pending = rs1.getInt(1);
//               //   jFormattedTextField2.setText(String.valueOf(pending));
//              }
//              
//              ResultSet rs2 = pst1.executeQuery();
//             
//              while (rs2.next()) {
//                 int pendi = rs2.getInt(1);
//                 // jTextField2.setText(String.valueOf(pendi));
//              }
//              
//               rs1 = pst.exequteQuery();
//               rs2 = pst1.exequteSearch(sql2);
//             String pending = String.valueOf(MySQL2.exequteSearch("SELECT COUNT(`returns`.`status`) FROM `returns` WHERE `status` ='Pending';  ") );
//             String received = String.valueOf( MySQL2.exequteSearch("SELECT COUNT(`returns`.`status`) FROM `returns` WHERE `status` ='Received';  "));
//             
//             
//             jTextField2.setText(received);
//            
//             jTextField2.setText(received);
//            
//       } catch (Exception e) {
//            e.printStackTrace();
//        }
    
    }
     
     
     
     private void loadReason(){
    
          try {
            ResultSet resultset = MySQL.executeSearch("SELECT * FROM `reason`  ");
           
                      Vector<String> vector = new Vector<>();
                      vector.add("Select");  
            
            while (resultset.next()) {
                
                vector.add(resultset.getString("reason"));
                loadReason.put(resultset.getString("reason"),resultset.getString("id"));
                
                 DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
                jComboBox3.setModel(model);
               
            }
       } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
     private void loadTable(){
         
         try {
              ResultSet resultset = MySQL.executeSearch("SELECT * FROM `return_invoice` INNER JOIN `distributor` ON `distributor`.`id` = "
                      + " `return_invoice`.`distributor_id` INNER JOIN `reason` ON `reason`.`id` = `return_invoice`.`reason_id`"
                      + " INNER JOIN `return_invoice_items` ON `return_invoice_items`.`return_invoice_id` = `return_invoice`.`id`"
                      + " INNER JOIN `w_product` ON `w_product`.`id` = `return_invoice_items`.`w_product_id` ");
           
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            model.setRowCount(0);
            
            while (resultset.next()) {
                
                Vector<String> vector = new Vector<>();
                vector.add(resultset.getString("return_invoice.id"));
                vector.add(resultset.getString("w_product.name"));
                vector.add(resultset.getString("return_invoice_items.qty"));
                
                vector.add(resultset.getString("reason.reason"));
                
                
                model.addRow(vector);
                loadStatus();
            }
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox3 = new javax.swing.JComboBox<>();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(930, 655));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(65, 36, 8));
        jLabel20.setText("Return Overview");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(73, 31, 10));
        jLabel21.setText("Return ID ");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(73, 31, 10));
        jLabel22.setText("Distributor Name");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(73, 31, 10));
        jLabel23.setText("Date ");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-close-24.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(73, 31, 10));
        jLabel26.setText("Delivery");

        jButton3.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jButton3.setText("Add Return");
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(73, 31, 10));
        jLabel25.setText("Reason");

        jPanel12.setBackground(new java.awt.Color(245, 219, 200));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Return ID", "Product", "Quantity", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167))
        );

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jFormattedTextField1.setText("Pending");
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setText("VIEW");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setText("PRINT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(894, 894, 894)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(4, 4, 4)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel22)
                                .addGap(4, 4, 4)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel23))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(22, 22, 22)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jLabel26)
                                .addGap(64, 64, 64)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(560, 560, 560)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel26))
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // Distributor
        
        
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        this.home.removereturnDamage();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Add Return
        
//       String returnId = String.valueOf(jTextField1.getText()); 
//       String distributor = String.valueOf(jComboBox2.getSelectedItem()); 
//       String dateTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        String reason = String.valueOf(jComboBox3.getSelectedItem());
//        String qty = jFormattedTextField1.getText();
//        
//        
//         if(distributor.equals("Select")) {
//             JOptionPane.showMessageDialog(this, "Please Select Distributor.","Warning",JOptionPane.WARNING_MESSAGE);
//        }else if(dateTime.isEmpty()) {
//             JOptionPane.showMessageDialog(this, "Please Select Date.","Warning",JOptionPane.WARNING_MESSAGE);
//        }else if(reason.equals("Select")) {
//             JOptionPane.showMessageDialog(this, "Please Select Reason.","Warning",JOptionPane.WARNING_MESSAGE);
//        }else if(qty.isEmpty()) {
//             JOptionPane.showMessageDialog(this, "Please Enter Delivery.","Warning",JOptionPane.WARNING_MESSAGE);
//         }else {
//            try {
//                ResultSet resultset = MySQL2.exequteSearch("SELECT * FROM `return_invoice` WHERE "
//                        + " AND `id` = '"+returnId+"' ");
//                
//                if (resultset.next()) {
//                    
//                   JOptionPane.showMessageDialog(this, "This Return Item Already Registered.","Warning",JOptionPane.WARNING_MESSAGE);
//                } else {
//                    
//                    
//                }
//                Date date = new Date();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                
//                MySQL2.exequteIUD("INSERT INTO `return_invoice`(`id`,`date`,`outlet_id`,`distributor_id`,`delivery_id`,`reason_id`,"
//                        + "`return_invoice_status_id`)"
//                        + " VALUES('"+returnId+"',"+sdf.format(date)+"' , '1','1','1',"
//                                + " '"+loadDis.get(distributor)+"','"+loadReason.get(reason)+"' )");
//                
//                JOptionPane.showMessageDialog(null, "Data Saved Successfully!");
//               loadStatus();
//                loadTable();
//                reset();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        
//         
//        
//        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       
//         int row = jTable1.getSelectedRow();
//        String ss = ""+jTable1.getSelectedRow();
//        
//          String product = String.valueOf(jTextField1.getText()); 
//       String distributor = String.valueOf(jComboBox2.getSelectedItem()); 
//       String dateTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        String reason = String.valueOf(jComboBox3.getSelectedItem());
//        String qty = jFormattedTextField1.getText();
//        
//          jTextField1.setText(String.valueOf(jTable1.getValueAt(row, 1)));
//          jComboBox2.setSelectedItem(String.valueOf(jTable1.getValueAt(row, 2)));
//          jComboBox3.setSelectedItem(String.valueOf(jTable1.getValueAt(row, 4)));
//        //  jDateChooser1.setDate(sdf..valueOf(jTable1.getValueAt(row, 3)));
//         jFormattedTextField1.setText(String.valueOf(jTable1.getValueAt(row, 5)));
//         
//         
//         if (evt.getClickCount() == 2) {
//            int row1 = jTable1.getSelectedRow();
//             String product1 = String.valueOf(jTable1.getValueAt(row1, 1));
//             String returnID = String.valueOf(jTable1.getValueAt(row1, 0));
//             String status = String.valueOf(jTable1.getValueAt(row1, 6));
//            
//             changeStatuss addressView = new changeStatuss(this,true,product1,returnID,status);
//             addressView.setVisible(true);
//             loadStatus();
//             loadTable();
//             reset();
//        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        
       
        //jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jFormattedTextField1.setText("");


    }
}
