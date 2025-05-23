/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.io.InputStream;
import model.MySQL;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author barth
 */
public class WOrders extends javax.swing.JPanel {
    private WHome home;

    /**
     * Creates new form Overview
     */
    public WOrders(WHome home) {
        initComponents();
        this.home = home;
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        ordersTable.setDefaultRenderer(Object.class, renderer);
        orderItemsTable.setDefaultRenderer(Object.class, renderer);
        loadOrders();
    }
    
    private void loadOrders() {
        try {
            String query = "SELECT * FROM `order` INNER JOIN `outlet` ON `order`.`outlet_id` = `outlet`.`id` "
                    + "INNER JOIN `order_status` ON `order`.`order_status_id` = `order_status`.`id` "
                    + "INNER JOIN `order_type` ON `order`.`order_type_id` = `order_type`.`id`";
            
            if (sortByComboBox.getSelectedItem().equals("Delivered Outlet ASC")) {
                query += " WHERE `order_status`.`name` = 'Delivered' ORDER BY `outlet`.`name` ASC";
            }else if (sortByComboBox.getSelectedItem().equals("Delivered Outlet DESC")) {
                query += " WHERE `order_status`.`name` = 'Delivered' ORDER BY `outlet`.`name` DESC";
            }else if (sortByComboBox.getSelectedItem().equals("Pending Outlet ASC")) {
                query += " WHERE `order_status`.`name` = 'Pending' ORDER BY `outlet`.`name` ASC";
            }else if (sortByComboBox.getSelectedItem().equals("Pending Outlet DESC")) {
                query += " WHERE `order_status`.`name` = 'Pending' ORDER BY `outlet`.`name` DESC";
            }else if (sortByComboBox.getSelectedItem().equals("Delivered Date ASC")) {
                query += " WHERE `order_status`.`name` = 'Delivered' ORDER BY `order`.`date` ASC";
            }else if (sortByComboBox.getSelectedItem().equals("Delivered Date DESC")) {
                query += " WHERE `order_status`.`name` = 'Delivered' ORDER BY `order`.`date` DESC";
            }else if (sortByComboBox.getSelectedItem().equals("Pending Date ASC")) {
                query += " WHERE `order_status`.`name` = 'Pending' ORDER BY `order`.`date` ASC";
            }else if (sortByComboBox.getSelectedItem().equals("Pending Date DESC")) {
                query += " WHERE `order_status`.`name` = 'Pending' ORDER BY `order`.`date` DESC";
            }else if (sortByComboBox.getSelectedItem().equals("Normal Outlet ASC")) {
                query += " WHERE `order_type`.`type` = 'Normal' ORDER BY `outlet`.`name` ASC";
            }else if (sortByComboBox.getSelectedItem().equals("Normal Outlet DESC")) {
                query += " WHERE `order_type`.`type` = 'Normal' ORDER BY `outlet`.`name` DESC";
            }else if (sortByComboBox.getSelectedItem().equals("Special Outlet ASC")) {
                query += " WHERE `order_type`.`type` = 'Special' ORDER BY `outlet`.`name` DESC";
            }else if (sortByComboBox.getSelectedItem().equals("Special Outlet DESC")) {
                query += " WHERE `order_type`.`type` = 'Special' ORDER BY `outlet`.`name` DESC";
            }
            
            ResultSet resultSet = MySQL.executeSearch(query);
            DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();
            model.setRowCount(0);
            
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("order.id"));
                vector.add(resultSet.getString("outlet.name"));
                vector.add(resultSet.getString("order.date"));
                vector.add(resultSet.getString("order_type.type"));
                vector.add(resultSet.getString("order_status.name"));
                model.addRow(vector);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void reset() {
        ordersTable.clearSelection();
        DefaultTableModel model = (DefaultTableModel) orderItemsTable.getModel();
        model.setRowCount(0);
        sortByComboBox.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        closeLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ordersTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        orderItemsTable = new javax.swing.JTable();
        viewButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        sortByComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        resetButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(94, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Order Requests");

        jLabel5.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(94, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Order Items");

        closeLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/close-icon.png"))); // NOI18N
        closeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeLabelMouseClicked(evt);
            }
        });

        ordersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Outlet", "Date", "Type", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ordersTable.getTableHeader().setReorderingAllowed(false);
        ordersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ordersTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ordersTable);

        orderItemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Product Weight", "Quantity", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderItemsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(orderItemsTable);

        viewButton.setBackground(new java.awt.Color(245, 219, 200));
        viewButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        viewButton.setForeground(new java.awt.Color(0, 0, 0));
        viewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/view-icon.png"))); // NOI18N
        viewButton.setText("View");
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel1.setText("Sort By :");

        sortByComboBox.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        sortByComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending Date DESC", "Pending Date ASC", "Delivered Date DESC", "Delivered Date ASC", "Pending Outlet ASC", "Pending Outlet DESC", "Delivered Outlet ASC", "Delivered Outlet DESC", "Normal Outlet ASC", "Normal Outlet DESC", "Special Outlet ASC", "Special Outlet DESC" }));
        sortByComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sortByComboBoxItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(66, 45, 22));
        jLabel2.setText("Orders");

        resetButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        resetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset-icon.png"))); // NOI18N
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        printButton.setBackground(new java.awt.Color(245, 219, 200));
        printButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        printButton.setForeground(new java.awt.Color(0, 0, 0));
        printButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/print-icon.png"))); // NOI18N
        printButton.setText("Print");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeLabel)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(resetButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sortByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(viewButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(printButton)))
                        .addContainerGap(43, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeLabel)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(sortByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resetButton)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewButton)
                    .addComponent(printButton))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void closeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseClicked
        this.home.removeOrders();
    }//GEN-LAST:event_closeLabelMouseClicked

    private void ordersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersTableMouseClicked
        int row = ordersTable.getSelectedRow();
        
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `order` INNER JOIN `order_items` "
                    + "ON `order`.`id` = `order_items`.`order_id` INNER JOIN `w_product` "
                    + "ON `order_items`.`w_product_id` = `w_product`.`id`"
                    + "WHERE `order`.`id` = '"+String.valueOf(ordersTable.getValueAt(row, 0))+"'");
            
            DefaultTableModel model = (DefaultTableModel) orderItemsTable.getModel();
            model.setRowCount(0);
            
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("w_product.name"));
                vector.add(resultSet.getString("w_product.weight"));
                vector.add(resultSet.getString("order_items.qty"));
                vector.add(resultSet.getString("w_product.price"));
                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_ordersTableMouseClicked

    private void sortByComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sortByComboBoxItemStateChanged
        loadOrders();
    }//GEN-LAST:event_sortByComboBoxItemStateChanged

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        reset();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewButtonActionPerformed
        try {
            int row = ordersTable.getSelectedRow();
        
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select an order", "Warning", JOptionPane.WARNING_MESSAGE);
            }else {
                InputStream path = this.getClass().getResourceAsStream("/reports/bb_w_order_report.jasper");
                HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("Parameter1", String.valueOf(ordersTable.getValueAt(row, 0)));
                parameters.put("Parameter2", String.valueOf(ordersTable.getValueAt(row, 1)));
                parameters.put("Parameter3", String.valueOf(ordersTable.getValueAt(row, 2)));
                parameters.put("Parameter4", String.valueOf(ordersTable.getValueAt(row, 3)));
                parameters.put("Parameter5", String.valueOf(ordersTable.getValueAt(row, 4)));

                JRTableModelDataSource dataSource = new JRTableModelDataSource(orderItemsTable.getModel());
                JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);
                JasperViewer.viewReport(report, false);
                reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_viewButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        try {
            int row = ordersTable.getSelectedRow();
            
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select an order", "Warning", JOptionPane.WARNING_MESSAGE);
            }else {
                InputStream path = this.getClass().getResourceAsStream("/reports/bb_w_order_report.jasper");
                HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("Parameter1", String.valueOf(ordersTable.getValueAt(row, 0)));
                parameters.put("Parameter2", String.valueOf(ordersTable.getValueAt(row, 1)));
                parameters.put("Parameter3", String.valueOf(ordersTable.getValueAt(row, 2)));
                parameters.put("Parameter4", String.valueOf(ordersTable.getValueAt(row, 3)));
                parameters.put("Parameter5", String.valueOf(ordersTable.getValueAt(row, 4)));

                JRTableModelDataSource dataSource = new JRTableModelDataSource(orderItemsTable.getModel());
                JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);
                JasperPrintManager.printReport(report, false);
                reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_printButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel closeLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable orderItemsTable;
    private javax.swing.JTable ordersTable;
    private javax.swing.JButton printButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JComboBox<String> sortByComboBox;
    private javax.swing.JButton viewButton;
    // End of variables declaration//GEN-END:variables
}
