
package gui;

import model.MySQL;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class finance extends javax.swing.JPanel {
    
    private int totaldistributors;
    private int totaloutlets;
    private Home HO;

    private static HashMap<String, String> paymentstatusMap = new HashMap<>();
    private static HashMap<String, String> paymentmethodMap = new HashMap<>();
    
    

   
    public finance(Home HO) {
        initComponents();
        this.HO = HO;
        loadpaymentstatus();
        loadpaymetmethod();
        loadoutletpayment();
        totaldistributors = calculatedistributor();
       totaloutlets = calculateoutlet();

        updateCountsDisplay(totaldistributors, totaloutlets);
       
    }
    
    
    
    private int calculatedistributor() {
    int totalAmount = 0;
    ResultSet rs = null;

    try {

        String query = "SELECT SUM(amount) FROM distributor_payment";
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
private int calculateoutlet() {
    int totalAmount = 0;
    ResultSet rs = null;

    try {

        String query = "SELECT SUM(amount) FROM transaction";
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

private void updateCountsDisplay(int DistributorTotalAmount, int OutletTotalAmount) {
    jButton12.setText("<html><div style='text-align: center;'><span style='font-size:12px;'><b>Distributor Total Amount </b></span>"
                      + "<span style='font-size:14px;'>Rs." + DistributorTotalAmount + "</span><br></br></div></html>");
    jButton16.setText("<html><div style='text-align: center;'><span style='font-size:12px;'><b>Outlet Returns Total </b></span>"
                      + "<span style='font-size:14px;'><br>Rs." + OutletTotalAmount + "</span><br></br></div></html>");

}

    
    
    
    private void loadoutletpayment(){
    
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `transaction` "
                    + "INNER JOIN `return_invoice` ON `transaction`.`return_invoice_id`=`return_invoice`.`id`"
                    + "INNER JOIN `outlet` ON `transaction`.`outlet_id`=`outlet`.`id`"
                    + "INNER JOIN `payment_status` ON `transaction`.`payment_status_id`"
                    + "INNER JOIN `payment_method` ON `transaction`.`payment_status_id` ORDER BY `transaction`.`id` ASC  ");
            
            
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            
            dtm.setRowCount(0);

            while (resultSet.next()) {
                Vector vector = new Vector();
                vector.add(resultSet.getString("outlet.name"));
                vector.add(resultSet.getString("return_invoice.date"));
                vector.add(resultSet.getString("amount"));
                vector.add(resultSet.getString("date"));
                
                vector.add(resultSet.getString("payment_status.status"));
                vector.add(resultSet.getString("payment_method.type"));

                dtm.addRow(vector);

            }
            
        
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    
    }
    
    
    
    
    private void loadpaymentstatus() {
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `payment_status`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("status"));
                paymentstatusMap.put(resultSet.getString("status"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);
            jComboBox4.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private void loadpaymetmethod() {
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `payment_method`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("type"));
                paymentmethodMap.put(resultSet.getString("type"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);
            jComboBox3.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jTextField4 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jTextField9 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jTextField10 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jFormattedTextField1.setText("jFormattedTextField1");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 186, 86));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 242, 230));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel1.setText("Distributor Name");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel2.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel2.setText("Payment Date");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jLabel3.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel3.setText("Vehicle No");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel4.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel4.setText("Amount");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 70, -1));

        jLabel8.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel8.setText("Payment Method");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 135, -1));
        jPanel4.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 190, 30));

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 190, 30));
        jPanel4.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 190, 30));
        jPanel4.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 190, 30));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, 190, 30));

        jButton1.setText("Payment ID");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 150, 30));
        jPanel4.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 180, 30));

        jLabel10.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel10.setText("Payment Status");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 135, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 190, 30));

        jButton2.setText("Pay");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 310, 110, 30));

        jButton3.setText("Clear");
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 310, 110, 30));

        jLabel11.setBackground(new java.awt.Color(204, 255, 0));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/picpro.png"))); // NOI18N
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 290, 270));

        jButton10.setText("Print Slip");
        jPanel4.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 350, 290, 30));

        jButton11.setText("Distributor Payment");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, 190, 40));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Distributor Payment", jPanel2);

        jPanel5.setBackground(new java.awt.Color(255, 255, 222));

        jLabel12.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel12.setText("Outlet Name");

        jLabel13.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel13.setText("Return Date");

        jLabel14.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel14.setText("Return Amount");

        jLabel15.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel15.setText("Payment Date");

        jLabel16.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel16.setText("Status");

        jLabel17.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel17.setText("Payment Method");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton4.setText("Outlet address");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-clear-25.png"))); // NOI18N
        jButton5.setText("Clear");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-print-25.png"))); // NOI18N
        jButton7.setText("Print");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-usd-25.png"))); // NOI18N
        jButton8.setText("Pay");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Outlet Name", "Return Date", "Amount", "Payment Date", "Status", "Payment Method"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton9.setText("Outlet Payment History");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14))
                                        .addGap(32, 32, 32))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(153, 153, 153)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(409, 409, 409)
                        .addComponent(jButton9)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton5))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Outlet Payment", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Invoices", jPanel6);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 960, 490));

        jButton12.setBackground(new java.awt.Color(204, 255, 204));
        jButton12.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-amount-40.png"))); // NOI18N
        jButton12.setText("Distributor Total Amount");
        jPanel1.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 290, 70));

        jButton15.setBackground(new java.awt.Color(227, 255, 255));
        jButton15.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-count-40.png"))); // NOI18N
        jButton15.setText("Return Status Count");
        jPanel1.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, 290, 70));

        jButton16.setBackground(new java.awt.Color(229, 253, 204));
        jButton16.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-return-40.png"))); // NOI18N
        jButton16.setText("Outlet Returns Total");
        jPanel1.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 310, 70));

        jButton6.setBackground(new java.awt.Color(255, 186, 86));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-close-24.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 40, 36));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1016, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        
        try {
            String PaymentID = jTextField5.getText();

            if (PaymentID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Distributor Id is Empty", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

               ResultSet resultSet = MySQL.executeSearch(
    "SELECT * FROM distributor_payment " +
    "INNER JOIN distributor ON distributor_payment.distributor_id = distributor.id " +
    "INNER JOIN payment_status ON distributor_payment.payment_status_id = payment_status.id " +
    "INNER JOIN payment_method ON distributor_payment.payment_method_id = payment_method.id " +
    "WHERE distributor_payment.id = '" + PaymentID + "' " +
    "ORDER BY distributor_payment.id ASC"
);

                if (resultSet.next()) {
                    String distributorname = resultSet.getString("distributor.name");
                    jTextField1.setText(distributorname);

                    String vehicleNo = resultSet.getString("vehicle_no");
                    jTextField3.setText(vehicleNo);

                   
                    
                    String amount = resultSet.getString("amount");
                    jTextField4.setText(amount);
                    
                    
                    
                    

                    jTextField1.setEditable(false);
                    jTextField3.setEditable(false);
                    jTextField4.setEditable(false);

//
//      
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Distributor ID", "Warning", JOptionPane.ERROR_MESSAGE);
                    

                }
loadoutletpayment();
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            
            String distributorID = jTextField5.getText();
            String Vehino = jTextField3.getText();
            String amounttext = jTextField4.getText();
            String paymentstatus = String.valueOf(jComboBox2.getSelectedItem());
            
            if (distributorID.isEmpty() || amounttext.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Id or Amount Empty", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            double amount = Double.parseDouble(amounttext);
            StripePayment.initStripe();
            
            String clientSecret = StripePayment.createPaymentIntent(amount);
            
            MySQL.executeIUD("UPDATE `distributor_payment` SET payment_status_id = '"+paymentstatusMap.get(paymentstatus)+"' WHERE id = '"+distributorID+"'");
            
            JOptionPane.showMessageDialog(this,
                    "Payment initiated successfully for distributor ID" +distributorID+
                      "\n Vehicle No" +Vehino+  
                            "Amount"+amount, 
                    "Success", JOptionPane.WARNING_MESSAGE);
            
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Amount Format", "Error", JOptionPane.WARNING_MESSAGE);
        }catch(Exception e){
            e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Id or Amount Empty"+e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        
        }
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        try {
            String outletaddress = jTextField6.getText();

            if (outletaddress.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Outlet is Empty", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

                ResultSet resultSet = MySQL.executeSearch(
    "SELECT * FROM transaction " +
    "INNER JOIN outlet ON transaction.outlet_id = outlet.id " +
    "INNER JOIN payment_status ON transaction.payment_status_id = payment_status.id " +
    "INNER JOIN return_invoice ON transaction.return_invoice_id = return_invoice.id " +
    "INNER JOIN payment_method ON transaction.payment_method_id = payment_method.id " +
    "WHERE outlet.address = '" + outletaddress+ "' " +
    "ORDER BY transaction.id ASC"
);

                if (resultSet.next()) {
                    String outletname = resultSet.getString("outlet.name");
                   jTextField9.setText(outletname);
                   
                   
                   Date returndate = resultSet.getDate("date");
                   jDateChooser3.setDate(returndate);

                    
//                    String amount = resultSet.getString("amount");
//                    jTextField10.setText(amount);



                    jTextField9.setEditable(false);
//                    jTextField10.setEditable(false);
                    jDateChooser1.setEnabled(false);

//
//
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Outlet ID", "Warning", JOptionPane.ERROR_MESSAGE);


                }



        } catch (Exception e) {
            e.printStackTrace();
        }
       
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       reset();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        
        setVisible(true);
        distributorpayment object = new distributorpayment();
        object.setVisible(true);
        
        
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String address = jTextField6.getText();
        String name = jTextField4.getText();
        Date returndate = jDateChooser3.getDate();
        String amount = jTextField10.getText();
        Date paymentdate = jDateChooser2.getDate();
        
        String paystatus = (String) jComboBox1.getSelectedItem();
        String paymethod = (String) jComboBox2.getSelectedItem();

        JFileChooser dialog = new JFileChooser();
        dialog.setSelectedFile(new File(address));
        int dialogResult = dialog.showSaveDialog(null);
        if (dialogResult == JFileChooser.APPROVE_OPTION) {

            String filePath = dialog.getSelectedFile().getPath();

//    String path ="";
//    JFileChooser j= new JFileChooser();
//    j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//    int x=j.showSaveDialog(this);
            Document myDocument = new Document();

            try {
                PdfWriter.getInstance(myDocument, new FileOutputStream(filePath + "payment.pdf"));

                myDocument.open();

                myDocument.open();

                myDocument.add(new Paragraph("Outlet Payment Slip", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                myDocument.add(new Paragraph(new Date().toString()));
                myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));
                myDocument.add((new Paragraph("Outlet DETAILS", FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD))));
                myDocument.add((new Paragraph("Name of Outlet: " + name, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));

                myDocument.add((new Paragraph("City: " + address, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                myDocument.add((new Paragraph("Address: " + address, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));

                myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));
                myDocument.add(new Paragraph("Payment Details", FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD)));
                //myDocument.add((new Paragraph("NEW EMPLOYEE",FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN))));
                myDocument.add((new Paragraph("Salary: " + amount, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                myDocument.add((new Paragraph("Status: " + paystatus, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                myDocument.add((new Paragraph("Salary: " + paymethod, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                
                myDocument.add((new Paragraph("Return Date: " + returndate, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                myDocument.add((new Paragraph("Payment Date: " + paymentdate, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));

                //myDocument.add((new Paragraph("Deduction Amount: "+amount,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN))));
                myDocument.newPage();
                myDocument.close();
                JOptionPane.showMessageDialog(null, "Report was successfully generated");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);

            } finally {

                try {

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);

                }
            }

        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        
        
        
        
        
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.HO.removefinance();
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

private void reset(){
    
 jTextField6.setText(null);
 jTextField9.setText(null);
 jTextField10.setText(null);
 jDateChooser2.setDate(null);
 jDateChooser3.setDate(null);
 jComboBox1.setSelectedItem("Select");
 jComboBox2.setSelectedItem("Select");
 jComboBox3.setSelectedItem("Select");
 jComboBox4.setSelectedItem("Select");
    
    
    
}






}
