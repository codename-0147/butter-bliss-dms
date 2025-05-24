/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import model.MySQL;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author barth
 */
public class WDashboard extends javax.swing.JPanel {
    private WHome home;

    /**
     * Creates new form Overview
     */
    public WDashboard(WHome home) {
        initComponents();
        this.home = home;
        loadPendingOrdersCount();
        loadOutletProcessedReturnsCount();
        loadSupplierProcessedReturnsCount();
        loadLineChart();
    }
    
    private void loadPendingOrdersCount() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT COUNT(*) FROM `order` INNER JOIN `order_status` "
                    + "ON `order`.`order_status_id` = `order_status`.`id` WHERE `order_status`.`name` = 'Pending'");
            
            if (resultSet.next()) {
                pendingOrdersCountLabel.setText(resultSet.getString("COUNT(*)"));
            }else {
                pendingOrdersCountLabel.setText("0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadOutletProcessedReturnsCount() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT COUNT(*) FROM `return_invoice` "
                    + "INNER JOIN `collect_return_stock_status` "
                    + "ON `return_invoice`.`collect_return_stock_status_id` = `collect_return_stock_status`.`id` "
                    + "WHERE `collect_return_stock_status`.`name` = 'Collected'");
            
            if (resultSet.next()) {
                outProcRetCountLabel.setText(resultSet.getString("COUNT(*)"));
            }else {
                outProcRetCountLabel.setText("0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadSupplierProcessedReturnsCount() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT COUNT(*) FROM `supplier_return_invoice` "
                    + "INNER JOIN `supplier_return_invoice_status` "
                    + "ON `supplier_return_invoice`.`supplier_return_invoice_status_id` = `supplier_return_invoice_status`.`id` "
                    + "WHERE `supplier_return_invoice_status`.`name` = 'Processed'");
            
            if (resultSet.next()) {
                supProcRetCountLabel.setText(resultSet.getString("COUNT(*)"));
            }else {
                supProcRetCountLabel.setText("0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadLineChart() {
        try {
            if (chartTypeComboBox.getSelectedItem().equals("Sales")) {
                ResultSet resultSet = MySQL.executeSearch("SELECT SUM(`w_invoice`.`total_price`-`discount`), "
                    + "MONTH(DATE(`w_invoice`.`date`)) FROM `w_invoice` WHERE YEAR(DATE(`w_invoice`.`date`)) = YEAR(CURDATE()) "
                    + "GROUP BY MONTH(DATE(`w_invoice`.`date`))");

                DefaultCategoryDataset dataset = new DefaultCategoryDataset();

                while (resultSet.next()) {
                    Double amount = Double.parseDouble(resultSet.getString("SUM(`w_invoice`.`total_price`-`discount`)"));
                    String month = "";

                    if (resultSet.getString("MONTH(DATE(`w_invoice`.`date`))").equals("1")) {
                        month = "January";
                    }else if (resultSet.getString("MONTH(DATE(`w_invoice`.`date`))").equals("2")) {
                        month = "February";
                    }else if (resultSet.getString("MONTH(DATE(`w_invoice`.`date`))").equals("3")) {
                        month = "March";
                    }else if (resultSet.getString("MONTH(DATE(`w_invoice`.`date`))").equals("4")) {
                        month = "April";
                    }else if (resultSet.getString("MONTH(DATE(`w_invoice`.`date`))").equals("5")) {
                        month = "May";
                    }else if (resultSet.getString("MONTH(DATE(`w_invoice`.`date`))").equals("6")) {
                        month = "June";
                    }else if (resultSet.getString("MONTH(DATE(`w_invoice`.`date`))").equals("7")) {
                        month = "July";
                    }else if (resultSet.getString("MONTH(DATE(`w_invoice`.`date`))").equals("8")) {
                        month = "August";
                    }else if (resultSet.getString("MONTH(DATE(`w_invoice`.`date`))").equals("9")) {
                        month = "September";
                    }else if (resultSet.getString("MONTH(DATE(`w_invoice`.`date`))").equals("10")) {
                        month = "October";
                    }else if (resultSet.getString("MONTH(DATE(`w_invoice`.`date`))").equals("11")) {
                        month = "November";
                    }else if (resultSet.getString("MONTH(DATE(`w_invoice`.`date`))").equals("12")) {
                        month = "December";
                    }

                    dataset.addValue(amount, "Sales", month);
                }

                JFreeChart chart = ChartFactory.createLineChart(
                "Monthly Sales",
                "Month",
                "Sales",
                dataset);

                ChartPanel chartPanel = new ChartPanel(chart);
                chartLoadingPanel.removeAll();
                chartLoadingPanel.add(chartPanel, BorderLayout.CENTER);
                chartLoadingPanel.validate();
            }else if (chartTypeComboBox.getSelectedItem().equals("Order Trends")) {
                ResultSet resultSet = MySQL.executeSearch("SELECT COUNT(*), MONTH(DATE(`date`)) FROM `order` "
                        + "WHERE YEAR(DATE(`date`)) = YEAR(CURDATE()) GROUP BY MONTH(DATE(`date`))");

                DefaultCategoryDataset dataset = new DefaultCategoryDataset();

                while (resultSet.next()) {
                    Double count = Double.parseDouble(resultSet.getString("COUNT(*)"));
                    String month = "";

                    if (resultSet.getString("MONTH(DATE(`date`))").equals("1")) {
                        month = "January";
                    }else if (resultSet.getString("MONTH(DATE(`date`))").equals("2")) {
                        month = "February";
                    }else if (resultSet.getString("MONTH(DATE(`date`))").equals("3")) {
                        month = "March";
                    }else if (resultSet.getString("MONTH(DATE(`date`))").equals("4")) {
                        month = "April";
                    }else if (resultSet.getString("MONTH(DATE(`date`))").equals("5")) {
                        month = "May";
                    }else if (resultSet.getString("MONTH(DATE(`date`))").equals("6")) {
                        month = "June";
                    }else if (resultSet.getString("MONTH(DATE(`date`))").equals("7")) {
                        month = "July";
                    }else if (resultSet.getString("MONTH(DATE(`date`))").equals("8")) {
                        month = "August";
                    }else if (resultSet.getString("MONTH(DATE(`date`))").equals("9")) {
                        month = "September";
                    }else if (resultSet.getString("MONTH(DATE(`date`))").equals("10")) {
                        month = "October";
                    }else if (resultSet.getString("MONTH(DATE(`date`))").equals("11")) {
                        month = "November";
                    }else if (resultSet.getString("MONTH(DATE(`date`))").equals("12")) {
                        month = "December";
                    }

                    dataset.addValue(count, "Orders", month);
                }

                JFreeChart chart = ChartFactory.createLineChart(
                "Monthly Orders",
                "Month",
                "Orders",
                dataset);

                ChartPanel chartPanel = new ChartPanel(chart);
                chartLoadingPanel.removeAll();
                chartLoadingPanel.add(chartPanel, BorderLayout.CENTER);
                chartLoadingPanel.validate();
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

        jPanel1 = new javax.swing.JPanel();
        closeLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pendingOrdersPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pendingOrdersCountLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        distDashboardButton = new javax.swing.JButton();
        chartTypeComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        pendingOrdersPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        outProcRetCountLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pendingOrdersPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        supProcRetCountLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        chartLoadingPanel = new javax.swing.JPanel();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        closeLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/close-icon.png"))); // NOI18N
        closeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeLabelMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(66, 45, 22));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Dashboard");

        pendingOrdersPanel.setBackground(new java.awt.Color(245, 219, 200));
        pendingOrdersPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(111, 48, 2)));
        pendingOrdersPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pendingOrdersPanelMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(94, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pending Orders");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        pendingOrdersCountLabel.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        pendingOrdersCountLabel.setForeground(new java.awt.Color(94, 0, 0));
        pendingOrdersCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pendingOrdersCountLabel.setText("0");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pending-orders-icon.png"))); // NOI18N

        javax.swing.GroupLayout pendingOrdersPanelLayout = new javax.swing.GroupLayout(pendingOrdersPanel);
        pendingOrdersPanel.setLayout(pendingOrdersPanelLayout);
        pendingOrdersPanelLayout.setHorizontalGroup(
            pendingOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingOrdersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pendingOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addGroup(pendingOrdersPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(pendingOrdersCountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pendingOrdersPanelLayout.setVerticalGroup(
            pendingOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingOrdersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(pendingOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pendingOrdersPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pendingOrdersCountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        distDashboardButton.setBackground(new java.awt.Color(245, 219, 200));
        distDashboardButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        distDashboardButton.setForeground(new java.awt.Color(0, 0, 0));
        distDashboardButton.setText("Distributor Dashboard");
        distDashboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                distDashboardButtonActionPerformed(evt);
            }
        });

        chartTypeComboBox.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        chartTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sales", "Order Trends" }));
        chartTypeComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chartTypeComboBoxItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(94, 0, 0));
        jLabel4.setText("Sales");

        pendingOrdersPanel1.setBackground(new java.awt.Color(245, 219, 200));
        pendingOrdersPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(111, 48, 2)));
        pendingOrdersPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pendingOrdersPanel1MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(94, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(" Out. Returns Processed");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        outProcRetCountLabel.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        outProcRetCountLabel.setForeground(new java.awt.Color(94, 0, 0));
        outProcRetCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outProcRetCountLabel.setText("0");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/outlet-returns-icon.png"))); // NOI18N

        javax.swing.GroupLayout pendingOrdersPanel1Layout = new javax.swing.GroupLayout(pendingOrdersPanel1);
        pendingOrdersPanel1.setLayout(pendingOrdersPanel1Layout);
        pendingOrdersPanel1Layout.setHorizontalGroup(
            pendingOrdersPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingOrdersPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pendingOrdersPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pendingOrdersPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(outProcRetCountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pendingOrdersPanel1Layout.setVerticalGroup(
            pendingOrdersPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingOrdersPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(pendingOrdersPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pendingOrdersPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(outProcRetCountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pendingOrdersPanel2.setBackground(new java.awt.Color(245, 219, 200));
        pendingOrdersPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(111, 48, 2)));
        pendingOrdersPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pendingOrdersPanel2MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(94, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Sup. Returns Processed");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        supProcRetCountLabel.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        supProcRetCountLabel.setForeground(new java.awt.Color(94, 0, 0));
        supProcRetCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        supProcRetCountLabel.setText("0");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/supplier-returns-icon.png"))); // NOI18N

        javax.swing.GroupLayout pendingOrdersPanel2Layout = new javax.swing.GroupLayout(pendingOrdersPanel2);
        pendingOrdersPanel2.setLayout(pendingOrdersPanel2Layout);
        pendingOrdersPanel2Layout.setHorizontalGroup(
            pendingOrdersPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingOrdersPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pendingOrdersPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pendingOrdersPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(supProcRetCountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pendingOrdersPanel2Layout.setVerticalGroup(
            pendingOrdersPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingOrdersPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(pendingOrdersPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pendingOrdersPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(supProcRetCountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chartTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(distDashboardButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(pendingOrdersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pendingOrdersPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pendingOrdersPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeLabel)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pendingOrdersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pendingOrdersPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(distDashboardButton)
                            .addComponent(chartTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addComponent(pendingOrdersPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        chartLoadingPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        chartLoadingPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(chartLoadingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(chartLoadingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void closeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseClicked
        this.home.removeDashboard();
    }//GEN-LAST:event_closeLabelMouseClicked

    private void pendingOrdersPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pendingOrdersPanelMouseClicked
        this.home.addOrders();
    }//GEN-LAST:event_pendingOrdersPanelMouseClicked

    private void chartTypeComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chartTypeComboBoxItemStateChanged
        loadLineChart();
    }//GEN-LAST:event_chartTypeComboBoxItemStateChanged

    private void distDashboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_distDashboardButtonActionPerformed
        HomeDB object = new HomeDB();
        object.setVisible(true);
    }//GEN-LAST:event_distDashboardButtonActionPerformed

    private void pendingOrdersPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pendingOrdersPanel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pendingOrdersPanel1MouseClicked

    private void pendingOrdersPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pendingOrdersPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pendingOrdersPanel2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartLoadingPanel;
    private javax.swing.JComboBox<String> chartTypeComboBox;
    private javax.swing.JLabel closeLabel;
    private javax.swing.JButton distDashboardButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel outProcRetCountLabel;
    private javax.swing.JLabel pendingOrdersCountLabel;
    private javax.swing.JPanel pendingOrdersPanel;
    private javax.swing.JPanel pendingOrdersPanel1;
    private javax.swing.JPanel pendingOrdersPanel2;
    private javax.swing.JLabel supProcRetCountLabel;
    // End of variables declaration//GEN-END:variables
}
