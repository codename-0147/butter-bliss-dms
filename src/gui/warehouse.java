
package gui;

import com.formdev.flatlaf.ui.FlatListCellBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Oshadha
 */
public class warehouse extends javax.swing.JPanel {
    
    private static HashMap<String, String> categoryMap = new HashMap<>();
    private static HashMap<String,String> statusMap  = new HashMap<>();
    private static HashMap<String,String> productMap = new HashMap<>();



    private Home home;

    /**
     * Creates new form warehouse
     */
    public warehouse(Home home) {
        initComponents();
        this.home = home;
        loadCategory();
        loadCategory2();
        loadWProducts();
        loadOrders();
        loadSupplier();
        showPieChart();
      
    }
    private void loadOrders (){
        
        try {
             ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `order` "
                     + "INNER JOIN `outlet` ON `order`.`outlet_id`=`outlet`.`id`"
                     + "INNER JOIN `order_status` ON `order`.`order_status_id`=`order_status`.`id`"
                     + "INNER JOIN `order_type` ON `order`.`order_type_id`=`order_type`.`id`");
             
             DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
             model.setRowCount(0);
             
             while (resultSet.next()) {
                 
                 Vector<String> vector = new Vector<>();
                 vector.add(resultSet.getString("id"));
                 vector.add(resultSet.getString("date"));
                 vector.add(resultSet.getString("outlet.name"));
                 vector.add(resultSet.getString("order_type.type"));
                 vector.add(resultSet.getString("order_status.name"));
                 model.addRow(vector);
                 
                 
                 
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    private void loadWProducts(){
        
        
        try {
            
            ResultSet resultset = MySQL.executeSearch("SELECT * FROM `w_product`"
                    + "INNER JOIN `category` ON `w_product`.`category_id` = `category`.`id`");
            
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            
            while (resultset.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultset.getString("id"));
                vector.add(resultset.getString("name"));
                vector.add(resultset.getString("weight"));
                vector.add(resultset.getString("category.name"));
                vector.add(resultset.getString("price"));
                model.addRow(vector);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
   
   private void loadCategory(){
       
       try {
           ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `category`");
           
           
           
           Vector<String> vector = new Vector<>();
           vector.add("Select");
           
           while (resultSet.next()) {
           vector.add(resultSet.getString("name"));
           categoryMap.put(resultSet.getString("name"), resultSet.getString("id"));
           
               
           }
           DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
           jComboBox1.setModel(model);
           
           
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   private void loadCategory2(){

         try {
        ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `category`");

        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0); 

        while (resultSet.next()) {
            Vector<String> vector = new Vector<>();
            vector.add(resultSet.getString("id"));
            vector.add(resultSet.getString("name"));
            model.addRow(vector);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
   
    private void loadSupplier(){
        
        
        try {
            
            ResultSet resultset = MySQL.executeSearch("SELECT * FROM `supplier`");
            
            DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
            model.setRowCount(0);
            
            while (resultset.next()) {

                Vector<String> vector = new Vector<>();
                
                vector.add(resultset.getString("fname"));
                vector.add(resultset.getString("lname"));
                vector.add(resultset.getString("email"));
                vector.add(resultset.getString("mobile"));
                vector.add(resultset.getString("company"));
                model.addRow(vector);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    //pie chart code start here
    
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
        jPanel8.removeAll();
        jPanel8.setLayout(new BorderLayout());
        jPanel8.add(chartPanel, BorderLayout.CENTER);
        jPanel8.validate();

        // Close the result set
        rs.close();

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

        jButton4 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 239, 228));
        setPreferredSize(new java.awt.Dimension(888, 650));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setBackground(new java.awt.Color(255, 246, 209));
        jButton4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jButton4.setText("Returns");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 78, 230, 40));

        jButton20.setBackground(new java.awt.Color(255, 246, 209));
        jButton20.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jButton20.setText("Stock Details");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 138, 230, 40));

        jButton21.setBackground(new java.awt.Color(255, 246, 209));
        jButton21.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jButton21.setText("Warehouse Management System");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(718, 137, -1, 41));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-close-24.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 6, -1, -1));

        jButton1.setBackground(new java.awt.Color(255, 246, 209));
        jButton1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButton1.setText("SupperVisor Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(718, 76, 230, 40));

        jTabbedPane1.setBackground(new java.awt.Color(255, 246, 209));

        jPanel5.setBackground(new java.awt.Color(236, 236, 255));

        jTable2.setBackground(new java.awt.Color(235, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 0, 102));
        jLabel7.setText("Add Category");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel8.setText("Enter Category");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 204, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButton5.setText("Add");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 222, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButton6.setText("Delete");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(282, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Catergory Management", jPanel3);

        jPanel4.setBackground(new java.awt.Color(254, 255, 214));

        jTable3.setBackground(new java.awt.Color(230, 249, 230));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id ", "Date", "Outlet Name", "Order Type", "Order Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Order table", jPanel4);

        jPanel7.setBackground(new java.awt.Color(245, 219, 200));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel5.setText("Mobile");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel9.setText("First Name");

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel10.setText("Last Name");

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel11.setText("Email");

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel12.setText("Company Name");

        jButton9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jButton9.setText("Delete");

        jButton10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jButton10.setText("Register");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jButton11.setText("Update");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "First Name", "Last Name", "Email", "Mobile", "Company"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField5)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField7)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField8)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(195, 195, 195))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(12, 12, 12)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Supplier Management", jPanel6);

        jPanel1.setBackground(new java.awt.Color(245, 245, 255));
        jPanel1.setForeground(new java.awt.Color(245, 219, 200));
        jPanel1.setToolTipText("");
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(245, 219, 200));

        jTable1.setBackground(new java.awt.Color(255, 241, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Weight", "Category", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 380));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Product Name");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 230, -1));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, 250, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Category");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 130, 210, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, 250, 30));

        jButton7.setBackground(new java.awt.Color(204, 204, 255));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setText("Add");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, 140, 30));

        jButton2.setBackground(new java.awt.Color(204, 204, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 270, 130, 30));

        jButton3.setBackground(new java.awt.Color(204, 204, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 350, 290, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Weight");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, 200, -1));

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 250, 30));

        jButton8.setBackground(new java.awt.Color(204, 204, 255));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setText("Print");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 310, 290, 30));

        jLabel3.setBackground(new java.awt.Color(245, 219, 200));
        jLabel3.setForeground(new java.awt.Color(245, 219, 200));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 120, 70, 20));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Price");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 190, 250, -1));
        jPanel1.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 250, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 973, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Product Management", jPanel2);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 205, 951, 417));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );

        add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 380, 190));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        returnTable rt = new returnTable(home, true);
        rt.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        stockDetails sd = new stockDetails(home, true);
        sd.setVisible(true);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
      WHome.getObj().setVisible(true);
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.home.removeWarehouse();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       suppervisorLogin sl = new suppervisorLogin(home, true);
       sl.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        MessageFormat header = new MessageFormat("I am Header of the Print Page");
        MessageFormat footer = new MessageFormat("Page{0, number, integer}");

        try {
            jTable1.print(JTable.PrintMode.NORMAL, header, footer);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String pname = jTextField1.getText();

            MySQL.executeIUD("DELETE FROM `w_product` "
                + "WHERE `name` = '"+pname+"'");
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadWProducts();
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed


 try {
        String pname = jTextField1.getText();
        String weight = jTextField3.getText();
        String category = String.valueOf(jComboBox1.getSelectedItem());
        String productId = jLabel3.getText(); // You must get the product ID from somewhere
        String price = jTextField9.getText();

        if (pname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter product name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (weight.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter weight", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (category.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select category", "Warning", JOptionPane.WARNING_MESSAGE);
            
        }else if (price.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter the Price","Warning",JOptionPane.WARNING_MESSAGE);

        } else {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `w_product` WHERE `name`='" + pname + "' AND id != '" + productId + "'");

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "This product name is already used", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                String updateQuery = "UPDATE w_product SET "
                    + "name = '" + pname + "',"
                    + "weight = '" + weight + "',"
                    + "category_id = '" + categoryMap.get(category) + "' "
                    + "WHERE id = '" + productId + "'";

                MySQL.executeIUD(updateQuery);
                JOptionPane.showMessageDialog(this, "Product updated successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
                loadWProducts();
                reset();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
      try {
            
            String pname = jTextField1.getText();
            String price = jTextField9.getText();
            String weight = jTextField3.getText();
            String category = String.valueOf(jComboBox1.getSelectedItem());
            
            if (pname.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Product Name","Warning",JOptionPane.WARNING_MESSAGE);
            }else if (price.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the Price","Warning", JOptionPane.WARNING_MESSAGE);
            }else if (weight.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Weight","Warning",JOptionPane.WARNING_MESSAGE);

            }else if (category.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select Category","Warning",JOptionPane.WARNING_MESSAGE);
               
            }else {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `w_product` WHERE `name`='"+pname+"'");
                
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This product already Registeres","Warning", JOptionPane.WARNING_MESSAGE);
                    
                } else {
                    MySQL.executeIUD("INSERT INTO `w_product`(`name`,`price`,`weight`,`category_id`)"
                            + "VALUES ('"+pname+"','"+price+"','"+weight+"',"
                                    + "'"+categoryMap.get(category)+"')");
                    JOptionPane.showMessageDialog(this, "New Product added","Warning",JOptionPane.INFORMATION_MESSAGE);
                    loadWProducts();
                    reset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {

            int selectedRow = jTable1.getSelectedRow();
            if (selectedRow != -1) {
                
                jLabel3.setText(String.valueOf(jTable1.getValueAt(selectedRow,0)));
                jTextField1.setText(String.valueOf(jTable1.getValueAt(selectedRow, 1)));
                jTextField3.setText(String.valueOf(jTable1.getValueAt(selectedRow, 2)));
                jComboBox1.setSelectedItem(String.valueOf(jTable1.getValueAt(selectedRow, 3)));
                jTextField9.setText(String.valueOf(jTable1.getValueAt(selectedRow, 4)));

            }

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            String category = jTextField2.getText();

            // Correct DELETE statement
            int rowsDeleted = MySQL.executeIUD("DELETE FROM `category` WHERE `name`='" + category + "'");

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Category Deleted", "Information", JOptionPane.INFORMATION_MESSAGE);
                loadCategory2();
                reset();
            } else {
                JOptionPane.showMessageDialog(this, "Category not found", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        /*try {
            String category = jTextField1.getText();

            MySQL.executeSearch("DELETE * FROM `category` WHERE `name`='"+category+"'");
            JOptionPane.showMessageDialog(this, "Category Deleted","Warning",JOptionPane.WARNING_MESSAGE);
            loadCategory();
            reset();

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            String category = jTextField2.getText();

            if (category.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the new category name","Warning",JOptionPane.WARNING_MESSAGE);
            } else {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `category` WHERE `name`='"+category+"'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This Category Already Added","Warning",JOptionPane.WARNING_MESSAGE);

                } else {
                    MySQL.executeIUD("INSERT INTO `category`(`name`)"
                        + "VALUES('"+category+"')");
                    JOptionPane.showMessageDialog(this, "New Category added","Warning",JOptionPane.INFORMATION_MESSAGE);
                    loadCategory2();
                    reset();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        if (evt.getClickCount() == 2) {

            int SelectedRow = jTable2.getSelectedRow();
            if (SelectedRow !=-1) {

                jTextField2.setText(String.valueOf(jTable2.getValueAt(SelectedRow, 1)));

            }
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
       try {
    String mobile = jTextField4.getText();
    String fname = jTextField5.getText();
    String lname = jTextField6.getText();
    String email = jTextField7.getText();
    String cname = jTextField8.getText();

    if (mobile.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please Enter mobile", "Warning", JOptionPane.WARNING_MESSAGE);
    } else if (!mobile.matches("^07[012345678]{1}[0-9]{7}$")) {
        JOptionPane.showMessageDialog(this, "Please Enter a Valid Mobile Number", "Warning", JOptionPane.WARNING_MESSAGE);
    } else if (fname.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please Enter Your First Name", "Warning", JOptionPane.WARNING_MESSAGE);
    } else if (lname.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please Enter Your Last Name", "Warning", JOptionPane.WARNING_MESSAGE);
    } else if (email.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please Enter an Email", "Warning", JOptionPane.WARNING_MESSAGE);
    } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
        JOptionPane.showMessageDialog(this, "Please Enter a Valid Email", "Warning", JOptionPane.WARNING_MESSAGE);
    } else if (cname.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please Enter Your Company Name", "Warning", JOptionPane.WARNING_MESSAGE);
    } else {
        
        ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `supplier` WHERE `mobile` = '"+mobile+"'");
        
        if (resultSet.next()) {
            // Supplier exists, so update the record
            MySQL.executeIUD("UPDATE `supplier` SET "
                    + "`fname` = '"+fname+"', "
                    + "`lname` = '"+lname+"', "
                    + "`email` = '"+email+"', "
                    + "`company` = '"+cname+"' "
                    + "WHERE `mobile` = '"+mobile+"'");
            
            JOptionPane.showMessageDialog(this, "Supplier Details Updated", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Supplier Not Found. Please Register First.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        
        reset();
        loadSupplier();
    }
} catch (Exception e) {
    e.printStackTrace();
}

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
     try {
    String mobile = jTextField4.getText();
    String fname = jTextField5.getText();
    String lname = jTextField6.getText();
    String email = jTextField7.getText();
    String cname = jTextField8.getText();

    if (mobile.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please Enter mobile", "Warning", JOptionPane.WARNING_MESSAGE);
    } else if (!mobile.matches("^07[012345678]{1}[0-9]{7}$")) {
        JOptionPane.showMessageDialog(this, "Please Enter a Valid Mobile Number", "Warning", JOptionPane.WARNING_MESSAGE);
    } else if (fname.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please Enter Your First Name", "Warning", JOptionPane.WARNING_MESSAGE);
    } else if (lname.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please Enter Your Last Name", "Warning", JOptionPane.WARNING_MESSAGE);
    } else if (email.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please Enter an Email", "Warning", JOptionPane.WARNING_MESSAGE);
    } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
        JOptionPane.showMessageDialog(this, "Please Enter a Valid Email", "Warning", JOptionPane.WARNING_MESSAGE);
    } else if (cname.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please Enter Your Company Name", "Warning", JOptionPane.WARNING_MESSAGE);
    } else {
        
        ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `supplier` WHERE `mobile` ='"+mobile+"'");
        
        if (resultSet.next()) {
             JOptionPane.showMessageDialog(this, "This Supplier is Already Registered", "Warning", JOptionPane.WARNING_MESSAGE);
        }else {
            
        
         MySQL.executeIUD("INSERT INTO `supplier` (`mobile`,`fname`,`lname`,`email`,`company`)"
           + "VALUES('"+mobile+"','"+fname+"','"+lname+"','"+email+"','"+cname+"')");
          JOptionPane.showMessageDialog(this, "Suplier Registered", "Information", JOptionPane.INFORMATION_MESSAGE);
          reset();
          loadSupplier();
        }
    }
} catch (Exception e) {
    e.printStackTrace();
}
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        
    }//GEN-LAST:event_jTable5MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

     private void reset() {
        
       jTextField1.setText("");
       jTextField2.setText("");
       jTextField3.setText("");
       jTextField2.setText("");
       jTextField9.setText("");

       jComboBox1.setSelectedItem("Select");
     
       
    }
}
