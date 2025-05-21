/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.MySQL;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author chath
 */
public class DashboardOU extends javax.swing.JPanel {

    private HomeOU home;

    /**
     * Creates new form Dashboard
     */
    public DashboardOU(HomeOU home) {
        initComponents();
        this.home = home;
        setTodayOrders();
        setPendingOrders();
        setTodaySales();
        setOutofStock();
        setMostPopularProduct();
        setBest();

    }

    private void setTodayOrders() {

        try {

            java.sql.Date todayDate = new java.sql.Date(System.currentTimeMillis());
            String todayDateString = todayDate.toString();

            String query = "SELECT COUNT(*) AS order_count FROM `order` WHERE `date` = '" + todayDateString + "'";

            ResultSet resultSet = MySQL.executeSearch(query);

            if (resultSet.next()) {

                int count = resultSet.getInt("order_count");
                String buttonText = "<html><div style='text-align:left; padding-left:10px;'>"
                        + "Today Orders<br>"
                        + "<div style='font-size:16px; text-align:left; padding-left:10px;'>" + count + "</div></div></html>";
                jButton10.setText(buttonText);

            } else {
                jButton10.setText("<html><div style='text-align:left; padding-left:10px;'>"
                        + "Today Orders<br><span style='font-size:16px; text-align:left; padding-left:10px;'>0</span></div></html>");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void setPendingOrders() {
        try {

            String query = "SELECT COUNT(*) AS pending_count FROM `order` "
                    + "WHERE `order_status_id` = (SELECT id FROM `order_status` WHERE name = 'Pending')";

            ResultSet resultSet = MySQL.executeSearch(query);

            if (resultSet.next()) {
                int count = resultSet.getInt("pending_count");
                String buttonText = "<html><div style='text-align:left; padding-left:10px;'>"
                        + "Pending Orders<br>"
                        + "<div style='font-size:16px; text-align:left; padding-left:10px;'>" + count + "</div></div></html>";
                jButton11.setText(buttonText);
            } else {
                jButton11.setText("<html><div style='text-align:left; padding-left:10px;'>"
                        + "Pending Orders<br>"
                        + "<div style='font-size:16px; text-align:left; padding-left:10px;'>0</div></div></html>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching pending orders count: " + e.getMessage());
        }
    }

    private void setTodaySales() {
        try {
            String query = "SELECT SUM(amount) AS total_sales FROM outlet_sales WHERE DATE(date) = CURDATE()";

            ResultSet resultSet = MySQL.executeSearch(query);

            if (resultSet.next()) {
                double totalSales = resultSet.getDouble("total_sales");

                String buttonText = "<html><div style='text-align:left; padding-left:10px;'>"
                        + "Today Sales<br>"
                        + "<div style='font-size:16px; text-align:left; padding-left:10px;'>"
                        + totalSales + "</div></div></html>";

                jButton12.setText(buttonText);
            } else {
                jButton12.setText("<html><div style='text-align:left; padding-left:10px;'>"
                        + "Today Sales<br>"
                        + "<div style='font-size:16px; text-align:left; padding-left:10px;'>0</div></div></html>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching today's sales: " + e.getMessage());
        }
    }

    private void setOutofStock() {
        try {

            String query = "SELECT COUNT(*) AS outofstock_count FROM `stock` "
                    + "WHERE `stock_status_id` = (SELECT id FROM `stock_status` WHERE type = 'Out of stock')";

            ResultSet resultSet = MySQL.executeSearch(query);

            if (resultSet.next()) {
                int count = resultSet.getInt("outofstock_count");
                String buttonText = "<html><div style='text-align:left; padding-left:10px;'>"
                        + "Restocking Alerts<br>"
                        + "<div style='font-size:16px; text-align:left; padding-left:10px;'>" + count + "</div></div></html>";
                jButton13.setText(buttonText);
            } else {
                jButton13.setText("<html><div style='text-align:left; padding-left:10px;'>"
                        + "Restocking Alerts<br>"
                        + "<div style='font-size:16px; text-align:left; padding-left:10px;'>0</div></div></html>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching Restocking Alerts count: " + e.getMessage());
        }
    }

    private void setMostPopularProduct() {
        try {
            String query = "SELECT p.name AS product_name, SUM(os.qty) AS total_qty_sold "
                    + "FROM outlet_sales os "
                    + "JOIN product p ON os.product_id = p.id "
                    + "WHERE DATE(os.date) = CURDATE() "
                    + "GROUP BY p.name "
                    + "ORDER BY total_qty_sold DESC LIMIT 1";

            ResultSet resultSet = MySQL.executeSearch(query);

            if (resultSet.next()) {
                String productName = resultSet.getString("product_name");
                int totalQuantitySold = resultSet.getInt("total_qty_sold");

                String buttonText = "<html><div style='text-align:left; padding-left:10px;'>"
                        + "Most Popular Product<br>"
                        + "<div style='font-size:12px; text-align:left; padding-left:10px;'>"
                        + productName + "<br>Qty Sold: " + totalQuantitySold + "</div></div></html>";

                jButton15.setText(buttonText);
            } else {
                jButton15.setText("<html><div style='text-align:left; padding-left:10px;'>"
                        + "Most Popular Product<br>"
                        + "<div style='font-size:12px; text-align:left; padding-left:10px;'>None</div></div></html>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching most popular product: " + e.getMessage());
        }
    }

    private void setBest() {
        try {

            String query = "SELECT best.note, best.date, outlet.name, outlet.address, outlet.mobile "
                    + "FROM best "
                    + "INNER JOIN outlet ON best.outlet_id = outlet.id "
                    + "WHERE best.date = (SELECT MAX(date) FROM best)";

            ResultSet resultSet = MySQL.executeSearch(query);

            if (resultSet.next()) {

                String outletName = resultSet.getString("name");
                String outletAddress = resultSet.getString("address");
                String outletMobile = resultSet.getString("mobile");
                String outletNote = resultSet.getString("note");

                String buttonText = "<html><div style='text-align:left; padding-left:10px;'>"
                        + "Best Outlet<br>"
                        + "<div style='text-align:left; padding-left:10px;'>Outlet: " + outletName + "</div>"
                        + "<div style='text-align:left; padding-left:10px; font-size: smaller;'>Address: " + outletAddress + "</div>"
                        + "<div style='text-align:left; padding-left:10px; font-size: smaller;'>Mobile: " + outletMobile + "</div>"
                        + "<div style='text-align:left; padding-left:10px; font-size: smaller;'>Note: " + outletNote + "</div>"
                        + "</div></html>";

                jButton16.setText(buttonText);
            } else {

                jButton16.setText("<html><div style='text-align:left; padding-left:10px;'>"
                        + "Best Outlet<br>"
                        + "<div style='text-align:left; padding-left:10px;'>No Data Available</div>"
                        + "</div></html>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching Best Outlet data: " + e.getMessage());
        }
    }

//    public void showPieChart() {
//        try {
//            // Create dataset
//            DefaultPieDataset pieDataset = new DefaultPieDataset();
//
//           
//           String query = "SELECT outlet_sales.id AS invoice_id, "
//                    + "outlet_sales.date AS invoice_date, "
//                    + "outlet_sales.amount AS total_amount, "
//                   + "product.name AS product_name, "
//                    + "outlet_sales.qty AS product_qty "
//                    + "FROM outlet_sales "
//                   + "INNER JOIN product ON outlet_sales.product_id = product.id "
//                   + "WHERE outlet_sales.outlet_id = '" + outletID + "' "
//                   + "AND outlet_sales.date >= DATE_SUB(CURDATE(), INTERVAL " + intervalDays + " DAY)";
//
//            // Execute the query
//            ResultSet rs = MySQL.executeSearch(query);
//
//            // Populate the dataset with data from the database
//            while (rs.next()) {
//               String outletName = rs.getString("outlet_name");
//                double totalSales = rs.getDouble("total_sales");
//
//                // Add the outlet name and its total sales to the pie chart dataset
//               pieDataset.setValue(outletName, totalSales);
//            }
//
//            // Create the pie chart
//            JFreeChart pieChart = ChartFactory.createPieChart(
//                    "Outlet Sales Distribution", // Chart title
//                    pieDataset, // Dataset
//                    true, // Include legend
//                    true, // Tooltips
//                    false // URLs
//            );
//
//            // Customize the pie chart
//            PiePlot piePlot = (PiePlot) pieChart.getPlot();
//
//            // Set background color
//            piePlot.setBackgroundPaint(Color.WHITE);
//
//            // Optional: Set section colors if you know the outlet names in advance
//            // (Otherwise, JFreeChart assigns random colors)
//            piePlot.setSectionPaint("Outlet A", new Color(221, 160, 221)); // Light Purple
//            piePlot.setSectionPaint("Outlet B", new Color(255, 255, 153)); // Light Yellow
//            piePlot.setSectionPaint("Outlet C", new Color(144, 238, 144)); // Light Green
//
//            // Create a ChartPanel to display the chart
//            ChartPanel chartPanel = new ChartPanel(pieChart);
//            chartPanel.setPreferredSize(new Dimension(700, 400));
//            chartPanel.setMouseWheelEnabled(true);
//
//            // Add to jPanel5
////        jPanel5.removeAll();
////        jPanel5.setLayout(new BorderLayout());
////        jPanel5.add(chartPanel, BorderLayout.CENTER);
////        jPanel5.validate();
//            // Close the result set
//           // rs.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setAutoscrolls(true);
        setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        setPreferredSize(new java.awt.Dimension(930, 720));

        jButton10.setBackground(new java.awt.Color(245, 221, 204));
        jButton10.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-total-sales-40 (1).png"))); // NOI18N
        jButton10.setText("Today Orders");
        jButton10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton10.setIconTextGap(200);
        jButton10.setMargin(new java.awt.Insets(2, 14, 35, 14));
        jButton10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(245, 221, 204));
        jButton11.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-data-pending-35.png"))); // NOI18N
        jButton11.setText("Pending Orders");
        jButton11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton11.setIconTextGap(200);
        jButton11.setMargin(new java.awt.Insets(2, 14, 35, 14));
        jButton11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(245, 221, 204));
        jButton13.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-add-reminder-28.png"))); // NOI18N
        jButton13.setText("Restocking Alerts");
        jButton13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton13.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButton13.setIconTextGap(185);
        jButton13.setMargin(new java.awt.Insets(2, 14, 50, 14));
        jButton13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(245, 221, 204));
        jButton12.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-total-sales-40.png"))); // NOI18N
        jButton12.setText("Today Sales");
        jButton12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton12.setIconTextGap(130);
        jButton12.setMargin(new java.awt.Insets(2, 14, 110, 14));

        jButton15.setBackground(new java.awt.Color(245, 221, 204));
        jButton15.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-best-36.png"))); // NOI18N
        jButton15.setText("Most Popular Product");
        jButton15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton15.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton15.setIconTextGap(150);
        jButton15.setMargin(new java.awt.Insets(2, 14, 60, 14));
        jButton15.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(245, 221, 204));
        jButton16.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-store-31.png"))); // NOI18N
        jButton16.setText("Best Outlet");
        jButton16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton16.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton16.setIconTextGap(100);
        jButton16.setMargin(new java.awt.Insets(2, 14, 50, 14));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-close-24.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton13)
                    .addComponent(jButton11)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton12)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.home.removeDashboardPanel();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        TodayOrders today = new TodayOrders(home, true, this);
        today.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        PendingOrders pending = new PendingOrders(home, true, this);
        pending.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

        LowOfStocks outofstock = new LowOfStocks(home, true, this);
        outofstock.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
