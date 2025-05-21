/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import model.MySQL;
import java.sql.ResultSet;
import java.util.Vector;
import javafx.scene.chart.CategoryAxis;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author User
 */
public class dashboard extends javax.swing.JPanel {

    private Home HO;
    private int ordercount;
    private int outletcount;
    private int employeecount;
    private int deliverdItems;
    private int SpecialOrders;
    private int totalSales;

//private profile profile;
    public dashboard(Home HO) {

        initComponents();
        this.HO = HO;
        employeecount = calculateemployeeCount();
        outletcount = calculateoutletcount();
        ordercount = calculateorderCount();
        deliverdItems = calculatedeliveredItems();
        SpecialOrders = calculateSpecialOrders();
        totalSales = calculateSales();
        updateCountsDisplay(ordercount, outletcount, employeecount, deliverdItems, SpecialOrders,totalSales);

        loadProducts();
        showBarChart();
        showPieChart();
        //updateCountsDisplay(outletcount);

    }

    private int calculatedeliveredItems() {
        int count = 0;
        ResultSet rs = null;

        try {
            String query = "SELECT COUNT(*) FROM `delivery` WHERE `delivery_status_id` = 1";
            rs = MySQL.executeSearch(query);

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    private int calculateSpecialOrders() {
        int count = 0;
        ResultSet rs = null;

        try {
            String query = "SELECT COUNT(*) FROM `order` WHERE `order_type_id` = 1";
            rs = MySQL.executeSearch(query);

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    private int calculateemployeeCount() {
        int count = 0;
        ResultSet rs = null;

        try {
            String query = "SELECT COUNT(*) FROM `employee`";
            rs = MySQL.executeSearch(query);

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    private int calculateorderCount() {
        int count = 0;
        ResultSet rs = null;

        try {
            String query = "SELECT COUNT(*) FROM `order`";
            rs = MySQL.executeSearch(query);

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    private int calculateoutletcount() {
        int count = 0;
        ResultSet rs = null;

        try {
            String query = "SELECT COUNT(*) FROM `outlet`";
            rs = MySQL.executeSearch(query);

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }
    private int calculateSales() {
    int totalAmount = 0;
    ResultSet rs = null;

    try {
        
        String query = "SELECT SUM(amount) FROM `outlet_sales`";
        rs = MySQL.executeSearch(query);

        if (rs.next()) {
            totalAmount = rs.getInt(1); 
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return totalAmount;
}

    private void updateCountsDisplay(int ordercount, int outletcount, int employeecount, int deliverdItems, int SpecialOrders,int totalSales) {

        jButton3.setText("<html><div style='text-align: center;'><span style='font-size:14px;'><b>Total Orders </span>"
                + "<span style='font-size:16px;'>" + ordercount + "</span><br></br></div></html>");

        jButton2.setText("<html><div style='text-align: center;'><span style='font-size:14px;'><b>Total Outlets </span>"
                + "<span style='font-size:16px;'>" + outletcount + "</span><br></br></div></html>");
        jButton11.setText("<html><div style='text-align: center;'><span style='font-size:13px;'><b>Total Employees </span>"
                + "<span style='font-size:14px;'>" + employeecount + "</span><br></br></div></html>");
        jButton12.setText("<html><div style='text-align: center;'><span style='font-size:13px;'><b>Total Sales </span>"
                + "<span style='font-size:14px;'>" + totalSales + "</span><br></br></div></html>");

        jButton4.setText("<html><span style='font-size:16px;'>" + deliverdItems + "</span><br></br></div></html>");
        jButton6.setText("<html><span style='font-size:16px;'>" + SpecialOrders + "</span><br></br></div></html>");

    }

    private void loadProducts() {

        try {
        String query = "SELECT " +
                       "p.id AS product_id, " +
                       "p.name AS product_name, " +
                       "s.qty AS amount, " +
                       "c.name AS category_name " +
                       "FROM stock s " +
                       "INNER JOIN product p ON s.product_id = p.id " +
                       "INNER JOIN category c ON p.category_id = c.id";

        ResultSet rs = MySQL.executeSearch(query);  // Replace with your DB method

        // Define column names using a Vector
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Product ID");
        columnNames.add("Product Name");
        columnNames.add("Amount");
        columnNames.add("Category");
        
        

        // Vector to hold all data rows
        Vector<Vector<Object>> data = new Vector<>();

        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            row.add(rs.getInt("product_id"));
            row.add(rs.getString("product_name"));
            row.add(rs.getDouble("amount"));
            row.add(rs.getString("category_name"));
            data.add(row);
        }

        // Set data into JTable using DefaultTableModel
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        jTable1.setModel(model);

        rs.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    //bar chat code
    public void showBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            // Queries to get counts from each table
            String distributorQuery = "SELECT COUNT(*) AS count FROM distributor";
            String supplierQuery = "SELECT COUNT(*) AS count FROM supplier";
            String outletQuery = "SELECT COUNT(*) AS count FROM outlet";

            // Get Distributor count
            ResultSet rsDistributor = MySQL.executeSearch(distributorQuery);
            int distributorCount = 0;
            if (rsDistributor.next()) {
                distributorCount = rsDistributor.getInt("count");
            }

            // Get Supplier count
            ResultSet rsSupplier = MySQL.executeSearch(supplierQuery);
            int supplierCount = 0;
            if (rsSupplier.next()) {
                supplierCount = rsSupplier.getInt("count");
            }

            // Get Outlet count
            ResultSet rsOutlet = MySQL.executeSearch(outletQuery);
            int outletCount = 0;
            if (rsOutlet.next()) {
                outletCount = rsOutlet.getInt("count");
            }

            // Populate dataset
            dataset.addValue(distributorCount, "Count", "Distributor Count");
            dataset.addValue(supplierCount, "Count", "Supplier Count");
            dataset.addValue(outletCount, "Count", "Outlet Count");

            // Create the bar chart
            JFreeChart chart = ChartFactory.createBarChart(
                    "Entity Count Overview", // Chart title
                    "Entity", // X-axis Label
                    "Count", // Y-axis Label
                    dataset, // Dataset
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            // Customize chart
            CategoryPlot plot = chart.getCategoryPlot();
            plot.setBackgroundPaint(new Color(230, 230, 250));
            plot.setDomainGridlinePaint(Color.GRAY);
            plot.setRangeGridlinePaint(Color.GRAY);
            plot.setOutlineVisible(false);

            BarRenderer renderer = new BarRenderer() {
                @Override
                public Paint getItemPaint(int row, int column) {
                    return new GradientPaint(0, 0, new Color(72, 209, 204), 0, 0, new Color(135, 206, 250));
                }
            };
            renderer.setBarPainter(new StandardBarPainter());
            renderer.setItemMargin(0.18);
            renderer.setShadowVisible(true);
            renderer.setShadowPaint(new Color(200, 200, 200));
            plot.setRenderer(renderer);

            // Customize X-axis
            org.jfree.chart.axis.CategoryAxis domainAxis = plot.getDomainAxis();
            domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
            domainAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 12));
            domainAxis.setLabelFont(new Font("Arial", Font.BOLD, 14));

            // Customize Y-axis with maximum = 10
            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setRange(0, 10); // Set Y-axis max value to 10
            rangeAxis.setTickUnit(new NumberTickUnit(1)); // Tick every 1
            rangeAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 12));
            rangeAxis.setLabelFont(new Font("Arial", Font.BOLD, 14));

            // Customize title and legend
            chart.getTitle().setFont(new Font("Arial", Font.BOLD, 16));
            chart.getLegend().setItemFont(new Font("Arial", Font.PLAIN, 12));

            // Show chart on jPanel4
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(700, 400));
            chartPanel.setMouseWheelEnabled(true);

            jPanel3.removeAll();
            jPanel3.setLayout(new BorderLayout());
            jPanel3.add(chartPanel, BorderLayout.CENTER);
            jPanel3.validate();

            rsDistributor.close();
            rsSupplier.close();
            rsOutlet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showPieChart() {
    try {
        // Create dataset
        DefaultPieDataset pieDataset = new DefaultPieDataset();

        // Query to get outlet names and total sales
        String query = "SELECT o.name AS outlet_name, SUM(s.amount) AS total_sales " +
                       "FROM outlet_sales s " +
                       "INNER JOIN outlet o ON s.outlet_id = o.id " +
                       "GROUP BY o.name";

        // Execute the query
        ResultSet rs = MySQL.executeSearch(query);

        // Populate the dataset with data from the database
        while (rs.next()) {
            String outletName = rs.getString("outlet_name");
            double totalSales = rs.getDouble("total_sales");

            // Add the outlet name and its total sales to the pie chart dataset
            pieDataset.setValue(outletName, totalSales);
        }

        // Create the pie chart
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Outlet Sales Distribution", // Chart title
                pieDataset,                 // Dataset
                true,                       // Include legend
                true,                       // Tooltips
                false                       // URLs
        );

        // Customize the pie chart
        PiePlot piePlot = (PiePlot) pieChart.getPlot();

        // Set background color
        piePlot.setBackgroundPaint(Color.WHITE);

        // Optional: Set section colors if you know the outlet names in advance
        // (Otherwise, JFreeChart assigns random colors)
        
        piePlot.setSectionPaint("Outlet A", new Color(221, 160, 221)); // Light Purple
        piePlot.setSectionPaint("Outlet B", new Color(255, 255, 153)); // Light Yellow
        piePlot.setSectionPaint("Outlet C", new Color(144, 238, 144)); // Light Green
        

        // Create a ChartPanel to display the chart
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(700, 400));
        chartPanel.setMouseWheelEnabled(true);

        // Add to jPanel5
        jPanel5.removeAll();
        jPanel5.setLayout(new BorderLayout());
        jPanel5.add(chartPanel, BorderLayout.CENTER);
        jPanel5.validate();

        // Close the result set
        rs.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(233, 255, 245));
        jButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton2.setText("Total Outlet");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 180, 60));

        jButton11.setBackground(new java.awt.Color(229, 238, 255));
        jButton11.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton11.setText("Total Employees");
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 180, 60));

        jButton12.setBackground(new java.awt.Color(234, 253, 255));
        jButton12.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton12.setText("Total Revenue");
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 40, 180, 60));

        jButton3.setBackground(new java.awt.Color(248, 241, 255));
        jButton3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton3.setText("Total Orders");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setPreferredSize(new java.awt.Dimension(139, 37));
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 180, 60));

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Order Summary");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        jButton4.setBackground(new java.awt.Color(255, 204, 153));
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 105, 94));

        jButton5.setBackground(new java.awt.Color(255, 189, 124));
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 105, 94));

        jButton6.setBackground(new java.awt.Color(255, 226, 197));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 105, 94));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Return Orders");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 90, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Deliverd");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Special Orders");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, 90, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 440, 190));

        jPanel4.setBackground(new java.awt.Color(245, 219, 200));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Products");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Product ID", "Product Name", "Amount", "Category"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 358, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 440, 200));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-close-24.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 10, -1, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 460, 310));

        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, 440, 320));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed


    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //this.HO.removedashboardpanel();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        this.HO.removedashboardpanel();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
      //  showPieChart();
    }//GEN-LAST:event_jPanel5MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
