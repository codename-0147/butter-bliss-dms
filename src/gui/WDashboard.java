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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        closeLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pendingOrdersPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pendingOrdersCountLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        chartTypeComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        chartLoadingPanel = new javax.swing.JPanel();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton2.setBackground(new java.awt.Color(252, 171, 77));
        jButton2.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Returns Processed");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(111, 48, 2)));

        jButton3.setBackground(new java.awt.Color(252, 171, 77));
        jButton3.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("QC Failures");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(111, 48, 2)));

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(94, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("0");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(94, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("0");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

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

        jButton1.setBackground(new java.awt.Color(245, 219, 200));
        jButton1.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Distributor Dashboard");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(pendingOrdersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chartTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeLabel)
                    .addComponent(jLabel6))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(pendingOrdersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(chartTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartLoadingPanel;
    private javax.swing.JComboBox<String> chartTypeComboBox;
    private javax.swing.JLabel closeLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel pendingOrdersCountLabel;
    private javax.swing.JPanel pendingOrdersPanel;
    // End of variables declaration//GEN-END:variables
}
