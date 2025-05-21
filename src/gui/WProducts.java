/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import model.MySQL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author barth
 */
public class WProducts extends javax.swing.JPanel {
    private WHome home;

    /**
     * Creates new form Overview
     */
    public WProducts(WHome home) {
        initComponents();
        this.home = home;
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        productsTable.setDefaultRenderer(Object.class, renderer);
        stockTable.setDefaultRenderer(Object.class, renderer);
        loadProducts();
    }
    
    private void loadProducts() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `product` INNER JOIN `category` "
                    + "ON `product`.`category_id` = `category`.`id`");
            DefaultTableModel model = (DefaultTableModel)productsTable.getModel();
            model.setRowCount(0);
            
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("product.id"));
                vector.add(resultSet.getString("product.name"));
                vector.add(resultSet.getString("product.price"));
                vector.add(resultSet.getString("product.weight"));
                vector.add(resultSet.getString("category.name"));
                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadStock() {
        try {
            String query = "SELECT * FROM `w_stock` INNER JOIN `product` ON `w_stock`.`product_id` = `product`.`id` "
                    + "INNER JOIN `brand` ON `product`.`brand_id` = `brand`.`id` ";
            int row = productsTable.getSelectedRow();
            
            if (row != -1) {
                String productID = String.valueOf(productsTable.getValueAt(row, 0));
                query += "WHERE `w_stock`.`product_id` = '"+productID+"' ";
            }
            
            if (query.contains("WHERE")) {
                query += "AND ";
            }else {
                query += "WHERE ";
            }
            
            double priceFrom = 0;
            double priceTo = 0;
            
            if (!priceFromFormattedField.getText().isEmpty()) {
                priceFrom = Double.parseDouble(priceFromFormattedField.getText());
            }
            
            if (!priceToFormattedField.getText().isEmpty()) {
                priceTo = Double.parseDouble(priceToFormattedField.getText());
            }
            
            if (priceFrom > 0 && priceTo == 0) {
                query += "`w_stock`.`price` > '"+priceFrom+"' AND ";
            }else if (priceFrom == 0 && priceTo > 0) {
                query += "`w_stock`.`price` < '"+priceTo+"' AND ";
            }else if (priceFrom > 0 && priceTo > 0) {
                query += "`w_stock`.`price` > '"+priceFrom+"' AND `w_stock`.`price` < '"+priceTo+"' AND ";
            }
            
            Date expFrom = null;
            Date expTo = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            
            if (expFromDateChooser.getDate() != null) {
                expFrom = expFromDateChooser.getDate();
                query += "`w_stock`.`exp` > '"+format.format(expFrom)+"' AND ";
            }
            
            if (expToDateChooser.getDate() != null) {
                expTo = expToDateChooser.getDate();
                query += "`w_stock`.`exp` < '"+format.format(expTo)+"' ";
            }
            
            String sort = String.valueOf(sortByComboBox.getSelectedItem());
            query += "ORDER BY ";
            query = query.replace("WHERE ORDER BY ", "ORDER BY ");
            query = query.replace("AND ORDER BY ", "ORDER BY ");
            
            if (sort.equals("Stock ID ASC")) {
                query += "`w_stock`.`id` ASC";
            }else if (sort.equals("Stock ID DESC")) {
                query += "`w_stock`.`id` DESC";
            }else if (sort.equals("Product ID ASC")) {
                query += "`product`.`id` ASC";
            }else if (sort.equals("Product ID DESC")) {
                query += "`product`.`id` DESC";
            }else if (sort.equals("Brand ASC")) {
                query += "`brand`.`name` ASC";
            }else if (sort.equals("Brand DESC")) {
                query += "`brand`.`name` DESC";
            }else if (sort.equals("Name ASC")) {
                query += "`product`.`name` ASC";
            }else if (sort.equals("Name DESC")) {
                query += "`product`.`name` DESC";
            }else if (sort.equals("Selling Price ASC")) {
                query += "`w_stock`.`price` ASC";
            }else if (sort.equals("Selling Price DESC")) {
                query += "`w_stock`.`price` DESC";
            }else if (sort.equals("Quantity ASC")) {
                query += "`w_stock`.`qty` ASC";
            }else if (sort.equals("Quantity DESC")) {
                query += "`w_stock`.`qty` DESC";
            }
            
            ResultSet resultSet = MySQL.executeSearch(query);
            DefaultTableModel model = (DefaultTableModel)stockTable.getModel();
            model.setRowCount(0);
            
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("w_stock.id"));
                vector.add(resultSet.getString("product.id"));
                vector.add(resultSet.getString("brand.name"));
                vector.add(resultSet.getString("product.name"));
                vector.add(resultSet.getString("w_stock.price"));
                vector.add(resultSet.getString("qty"));
                vector.add(resultSet.getString("mfd"));
                vector.add(resultSet.getString("exp"));
                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //logger.log(Level.WARNING, "Exception", e);
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

        jPanel1 = new javax.swing.JPanel();
        closeLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productsTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        sortByComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        priceFromFormattedField = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        priceToFormattedField = new javax.swing.JFormattedTextField();
        findByPriceButton = new javax.swing.JButton();
        stockResetButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        expFromDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        expToDateChooser = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        stockTable = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        closeLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/close-icon.png"))); // NOI18N
        closeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeLabelMouseClicked(evt);
            }
        });

        productsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Price", "Weight", "Category"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productsTable.getTableHeader().setReorderingAllowed(false);
        productsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(productsTable);

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(66, 45, 22));
        jLabel3.setText("Products & Stock");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeLabel)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeLabel)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel4.setText("Sort By :");

        sortByComboBox.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        sortByComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Stock ID ASC", "Stock ID DESC", "Product ID ASC", "Product ID DESC", "Brand ASC", "Brand DESC", "Name ASC", "Name DESC", "Selling Price ASC", "Selling Price DESC", "Quantity ASC", "Quantity DESC" }));
        sortByComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sortByComboBoxItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel5.setText("Price From");

        priceFromFormattedField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        priceFromFormattedField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        priceFromFormattedField.setText("0");
        priceFromFormattedField.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel6.setText("To");

        priceToFormattedField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        priceToFormattedField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        priceToFormattedField.setText("0");
        priceToFormattedField.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        findByPriceButton.setBackground(new java.awt.Color(245, 219, 200));
        findByPriceButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        findByPriceButton.setForeground(new java.awt.Color(0, 0, 0));
        findByPriceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/search-icon.png"))); // NOI18N
        findByPriceButton.setText("Find");
        findByPriceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findByPriceButtonActionPerformed(evt);
            }
        });

        stockResetButton.setBackground(new java.awt.Color(245, 219, 200));
        stockResetButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        stockResetButton.setForeground(new java.awt.Color(0, 0, 0));
        stockResetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset-icon.png"))); // NOI18N
        stockResetButton.setText("Reset");
        stockResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockResetButtonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel7.setText("EXP From");

        expFromDateChooser.setDateFormatString("yyyy-MM-dd");

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel8.setText("To");

        expToDateChooser.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priceFromFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priceToFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sortByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(findByPriceButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stockResetButton)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(expFromDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(expToDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(priceFromFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(priceToFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(expFromDateChooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(expToDateChooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(findByPriceButton)
                    .addComponent(stockResetButton)
                    .addComponent(sortByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        stockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock ID", "Product ID", "Brand Name", "Product Name", "Selling Price", "Quantity", "MFD", "EXP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stockTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(stockTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void closeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseClicked
        this.home.removeInventory();
    }//GEN-LAST:event_closeLabelMouseClicked

    private void productsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsTableMouseClicked
        loadStock();
    }//GEN-LAST:event_productsTableMouseClicked

    private void sortByComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sortByComboBoxItemStateChanged
        //logger.log(Level.INFO, "ItemEvent");
        loadStock();
    }//GEN-LAST:event_sortByComboBoxItemStateChanged

    private void findByPriceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findByPriceButtonActionPerformed
        //logger.log(Level.INFO, "ActionEvent");
        loadStock();
    }//GEN-LAST:event_findByPriceButtonActionPerformed

    private void stockResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockResetButtonActionPerformed
        //logger.log(Level.INFO, "ActionEvent");
        sortByComboBox.setSelectedIndex(0);
        priceFromFormattedField.setText("0");
        priceToFormattedField.setText("0");
        expFromDateChooser.setDate(null);
        expToDateChooser.setDate(null);
        loadStock();
    }//GEN-LAST:event_stockResetButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel closeLabel;
    private com.toedter.calendar.JDateChooser expFromDateChooser;
    private com.toedter.calendar.JDateChooser expToDateChooser;
    private javax.swing.JButton findByPriceButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JFormattedTextField priceFromFormattedField;
    private javax.swing.JFormattedTextField priceToFormattedField;
    private javax.swing.JTable productsTable;
    private javax.swing.JComboBox<String> sortByComboBox;
    private javax.swing.JButton stockResetButton;
    private javax.swing.JTable stockTable;
    // End of variables declaration//GEN-END:variables
}
