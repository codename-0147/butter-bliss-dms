package gui;

import com.itextpdf.text.Chunk;
import model.MySQL;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

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
        //loadoutletpayment();
        loadTransaction();
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
                if (rs != null) {
                    rs.close();
                }
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
                if (rs != null) {
                    rs.close();
                }
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

//    private void loadoutletpayment() {
//
//        try {
//            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `transaction` "
//                    + "INNER JOIN `return_invoice` ON `transaction`.`return_invoice_id`=`return_invoice`.`id`"
//                    + "INNER JOIN `outlet` ON `transaction`.`outlet_id`=`outlet`.`id`"
//                    + "INNER JOIN `payment_status` ON `transaction`.`payment_status_id`"
//                    + "INNER JOIN `payment_method` ON `transaction`.`payment_status_id` ORDER BY `transaction`.`id` ASC  ");
//
//            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
//
//            dtm.setRowCount(0);
//
//            while (resultSet.next()) {
//                Vector vector = new Vector();
//                vector.add(resultSet.getString("outlet.name"));
//                vector.add(resultSet.getString("return_invoice.date"));
//                vector.add(resultSet.getString("amount"));
//                vector.add(resultSet.getString("date"));
//
//                vector.add(resultSet.getString("payment_status.status"));
//                vector.add(resultSet.getString("payment_method.type"));
//
//                dtm.addRow(vector);
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
    
    
    
    private void loadSupplierPayment(){
    
    
    
    
    
    
    
    
    }
    
private void printreturn(){
String ReturnInvoiceID = jTextField6.getText();
        String name = jTextField9.getText();
        String Id = jTextField2.getText();
        Date returndate = jDateChooser3.getDate();
        String amount = jTextField10.getText();
        Date paymentdate = jDateChooser2.getDate();

        String paystatus = (String) jComboBox1.getSelectedItem();
        String paymethod = (String) jComboBox2.getSelectedItem();

        if (ReturnInvoiceID==null ||name==null|| Id == null || returndate == null||amount==null||paymentdate==null || paystatus=="Select" || paystatus=="Select" ) {
             JOptionPane.showMessageDialog(this, "Can't Print Empty", "Error", JOptionPane.WARNING_MESSAGE);
             return;
        }
        
        JFileChooser dialog = new JFileChooser();
        dialog.setSelectedFile(new File(ReturnInvoiceID+"PaymentSlip.pdf"));
        int dialogResult = dialog.showSaveDialog(null);
        
        
        

        if (dialogResult == JFileChooser.APPROVE_OPTION) {
            String filePath = dialog.getSelectedFile().getPath();
            Document myDocument = new Document(PageSize.A4, 50, 50, 50, 50);

            try {
                PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                myDocument.open();

                // Title
                Paragraph title = new Paragraph("Return Payment Slip(System)", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20));
                title.setAlignment(Element.ALIGN_CENTER);
                myDocument.add(title);

                // Date
                Paragraph date = new Paragraph("Date: " + new Date().toString(), FontFactory.getFont(FontFactory.HELVETICA, 10));
                date.setAlignment(Element.ALIGN_RIGHT);
                myDocument.add(date);

                myDocument.add(Chunk.NEWLINE);
                myDocument.add(new LineSeparator());
                myDocument.add(Chunk.NEWLINE);

                // Outlet Info
                Paragraph outletInfo = new Paragraph("Outlet Details", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
                myDocument.add(outletInfo);
                myDocument.add(Chunk.NEWLINE);

                PdfPTable outletTable = new PdfPTable(2);
                outletTable.setWidthPercentage(100);
                outletTable.setSpacingBefore(10f);
                outletTable.setSpacingAfter(10f);

                outletTable.addCell("Outlet Name:");
                outletTable.addCell(name);
                outletTable.addCell("Outlet ID:");
                outletTable.addCell(Id);
                

                myDocument.add(outletTable);

                myDocument.add(new LineSeparator());
                myDocument.add(Chunk.NEWLINE);

                // Payment Info
                Paragraph paymentInfo = new Paragraph("Payment Details", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
                myDocument.add(paymentInfo);
                myDocument.add(Chunk.NEWLINE);

                PdfPTable paymentTable = new PdfPTable(2);
                paymentTable.setWidthPercentage(100);
                paymentTable.setSpacingBefore(10f);
                paymentTable.setSpacingAfter(10f);

                paymentTable.addCell("Amount:");
                paymentTable.addCell(amount);
                paymentTable.addCell("Status:");
                paymentTable.addCell(paystatus);
                paymentTable.addCell("Method:");
                paymentTable.addCell(paymethod);
                paymentTable.addCell("Return Date:");
                paymentTable.addCell(returndate.toString());
                paymentTable.addCell("Payment Date:");
                paymentTable.addCell(paymentdate.toString());

                myDocument.add(paymentTable);

                myDocument.add(new LineSeparator());
                myDocument.add(Chunk.NEWLINE);

                Paragraph thankYou = new Paragraph("Payment Successfull !", FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12));
                thankYou.setAlignment(Element.ALIGN_CENTER);
                myDocument.add(thankYou);

                myDocument.close();

                JOptionPane.showMessageDialog(null, "Report was successfully generated");
                reset();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
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
            jComboBox6.setModel(model);

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
            jComboBox5.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void loadTransaction() {
    try {
    ResultSet resultSet = MySQL.executeSearch(
        "SELECT " +
        "outlet.name AS outlet_name, " +
        "return_invoice.date AS return_date, " +
        "`transaction`.amount AS transaction_amount, " +
        "`transaction`.date AS payment_date, " +
        "payment_status.status AS payment_status, " +
        "payment_method.type AS payment_type " +
        "FROM `transaction` " +
        "INNER JOIN `return_invoice` ON `transaction`.`return_invoice_id` = `return_invoice`.`id` " +
        "INNER JOIN `outlet` ON `transaction`.`outlet_id` = `outlet`.`id` " +
        "INNER JOIN `payment_status` ON `transaction`.`payment_status_id` = `payment_status`.`id` " +
        "INNER JOIN `payment_method` ON `transaction`.`payment_method_id` = `payment_method`.`id` " +
        "ORDER BY `transaction`.`id` ASC"
    );

    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
    dtm.setRowCount(0); // Clear existing rows

    while (resultSet.next()) {
        Vector<String> vector = new Vector<>();
        vector.add(resultSet.getString("outlet_name"));       // Outlet name
        vector.add(resultSet.getString("return_date"));       // Return invoice date
        vector.add(resultSet.getString("transaction_amount")); // Amount
        vector.add(resultSet.getString("payment_date"));      // Transaction date
        vector.add(resultSet.getString("payment_status"));    // Payment status
        vector.add(resultSet.getString("payment_type"));      // Payment method type
        dtm.addRow(vector);
    }

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, e.getMessage());
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
        jButton18 = new javax.swing.JButton();
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
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jTextField10 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jTextField7 = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton19 = new javax.swing.JButton();
        jTextField8 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jTextField12 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
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

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-usd-25.png"))); // NOI18N
        jButton2.setText("Pay");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 330, 110, 40));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-clear-25.png"))); // NOI18N
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 330, 110, 40));

        jLabel11.setBackground(new java.awt.Color(204, 255, 0));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/picpro.png"))); // NOI18N
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel11.setPreferredSize(new java.awt.Dimension(300, 300));
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 290, 270));

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Print.png"))); // NOI18N
        jButton10.setText("Print Slip");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 390, 290, 40));

        jButton11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton11.setText("Distributor Payment");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 150, 190, 40));

        jButton18.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        jButton18.setText("Completed Payments");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 220, 190, 40));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Distributor Payment", jPanel2);

        jPanel5.setBackground(new java.awt.Color(255, 255, 222));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 20, 169, 30));

        jLabel12.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel12.setText("Outlet ID");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, 30));

        jLabel13.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel13.setText("Return Date");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, 30));

        jLabel14.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel14.setText("Return Amount");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 225, -1, 26));

        jLabel15.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel15.setText("Payment Date");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 280, 121, 30));

        jLabel16.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel16.setText("Status");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 328, 70, 30));

        jLabel17.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel17.setText("Payment Method");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 370, 135, 30));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 371, 190, 30));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 328, 190, 30));
        jPanel5.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 280, 190, 30));

        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 190, 30));

        jButton4.setText("Invoice ID");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 20, 156, 30));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-clear-25.png"))); // NOI18N
        jButton5.setText("Clear");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 420, 96, -1));
        jPanel5.add(jDateChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 176, 190, 30));

        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 224, 190, 30));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-usd-25.png"))); // NOI18N
        jButton8.setText("Pay");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 420, 96, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Outlet ID", "Return Date", "Amount", "Payment Date", "Status", "Method"
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
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 74, 490, 330));

        jButton9.setText("Return Invoice");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, 36));
        jPanel5.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 190, 30));

        jLabel18.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 14)); // NOI18N
        jLabel18.setText("Outlet Name");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, 30));

        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField11KeyReleased(evt);
            }
        });
        jPanel5.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 40, 140, 30));

        jLabel19.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel19.setText("Search");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, 30));

        jTabbedPane1.addTab("Return Payment", jPanel5);

        jPanel7.setBackground(new java.awt.Color(255, 244, 242));

        jButton20.setText("GRN ID");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Amount", "Payment Method", "Status", "Grn ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
        }

        jButton19.setText("GRN Table");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Amount");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Payment Method ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Payment Status");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-usd-25.png"))); // NOI18N
        jButton21.setText("Pay");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-print-25.png"))); // NOI18N
        jButton22.setText("Print");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-clear-25.png"))); // NOI18N
        jButton23.setText("Clear");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Date");

        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField12KeyReleased(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel20.setText("Search");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(48, 48, 48)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Suplier Payments", jPanel7);

        jPanel6.setBackground(new java.awt.Color(202, 255, 237));

        jButton13.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jButton13.setText("Distributor Payment Invoice");

        jButton14.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jButton14.setText("Supplier Payment Invoice");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        jButton17.setText("Outlet Payment Invoice");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(407, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Invoices & Reports", jPanel6);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 960, 530));

        jButton12.setBackground(new java.awt.Color(204, 255, 204));
        jButton12.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-amount-40.png"))); // NOI18N
        jButton12.setText("Distributor Total Amount");
        jPanel1.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 290, 70));

        jButton15.setBackground(new java.awt.Color(227, 255, 255));
        jButton15.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-count-40.png"))); // NOI18N
        jButton15.setText("Supplier Pending Payments");
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
                "SELECT * FROM distributor_payment "
                + "INNER JOIN distributor ON distributor_payment.distributor_id = distributor.id "
                + "INNER JOIN payment_status ON distributor_payment.payment_status_id = payment_status.id "
                + "INNER JOIN payment_method ON distributor_payment.payment_method_id = payment_method.id "
                + "WHERE distributor_payment.id = '" + PaymentID + "' "
                + "ORDER BY distributor_payment.id ASC"
        );

        if (resultSet.next()) {
            String distributorname = resultSet.getString("distributor.name");
            jTextField1.setText(distributorname);

            String vehicleNo = resultSet.getString("vehicle_no");
            jTextField3.setText(vehicleNo);

            String amount = resultSet.getString("amount");
            jTextField4.setText(amount);

            String imageName = resultSet.getString("distributor.imagename");
            if (imageName != null && !imageName.isEmpty()) {
                ImageIcon imageIcon = new ImageIcon("Src/resources/" + imageName);
                Image img = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                jLabel11.setIcon(new ImageIcon(img));
            }

            jTextField1.setEditable(false);
            jTextField3.setEditable(false);
            jTextField4.setEditable(false);

        } else {
            JOptionPane.showMessageDialog(this, "Invalid Distributor ID", "Warning", JOptionPane.ERROR_MESSAGE);
        }

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

    if (paymentstatus.equals("Pending")) {
        JOptionPane.showMessageDialog(this, "Payment Status Must Be Complete", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    double amount = Double.parseDouble(amounttext);
    StripePayment.initStripe();

    String clientSecret = StripePayment.createPaymentIntent(amount);

    MySQL.executeIUD("UPDATE `distributor_payment` SET payment_status_id = '" + paymentstatusMap.get(paymentstatus) + "' WHERE id = '" + distributorID + "'");

    JOptionPane.showMessageDialog(this,
        "Payment initiated successfully.\n\n"
        + "Distributor ID: " + distributorID + "\n"
        + "Vehicle No    : " + Vehino + "\n"
        + "Amount        : " + amount,
        "Success", JOptionPane.INFORMATION_MESSAGE);

} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Invalid Amount Format", "Error", JOptionPane.WARNING_MESSAGE);
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
}



    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

       try {
    String returnInvoiceId = jTextField6.getText();

    if (returnInvoiceId.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a Return Invoice ID", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }
jTextField2.setEditable(false);
    ResultSet resultSet = MySQL.executeSearch(
        "SELECT * FROM `return_invoice` "
        + "INNER JOIN `outlet` ON `return_invoice`.`outlet_id` = `outlet`.`id` "
        + "INNER JOIN `distributor` ON `return_invoice`.`distributor_id` = `distributor`.`id` "
        + "INNER JOIN `reason` ON `return_invoice`.`reason_id` = `reason`.`id` "
        + "INNER JOIN `return_invoice_status` ON `return_invoice`.`return_invoice_status_id` = `return_invoice_status`.`id` "
        + "WHERE `return_invoice`.`id` = '" + returnInvoiceId + "'"
    );

    if (resultSet.next()) {
        String outletName = resultSet.getString("outlet.name"); // Adjust column name if needed
        jTextField9.setText(outletName);
        String outletID = resultSet.getString("outlet.id"); // Adjust column name if needed
        jTextField2.setText(outletID);

        Date returndate = resultSet.getDate("date");
        jDateChooser3.setDate(returndate);
        String amount = resultSet.getString("total_amount"); // Adjust column name if needed
        jTextField10.setText(amount);

        jTextField9.setEditable(false);
        jDateChooser3.setEnabled(false);
    } else {
       
        JOptionPane.showMessageDialog(this, "No Return ID Found", "Warning", JOptionPane.ERROR_MESSAGE);
        reset();
    }

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "An error occurred while searching. Please check the console for details.", "Error", JOptionPane.ERROR_MESSAGE);
    reset();
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

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

    try {
    String InvoiceID = jTextField6.getText();
    String OutletId = jTextField2.getText(); 
    String amounttext = jTextField10.getText();
    Date PaymentDate = jDateChooser3.getDate();
    String paymentstatus = String.valueOf(jComboBox4.getSelectedItem());
    String PaymentMethod = String.valueOf(jComboBox3.getSelectedItem());

    if (InvoiceID.isEmpty() || amounttext.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Id or Amount Empty", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }
if (paymentstatus.equals("Select")) {
        JOptionPane.showMessageDialog(this, "Please Selecct Payment Status", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (paymentstatus.equals("Pending")) {
        JOptionPane.showMessageDialog(this, "Payment Status Must Be Complete", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (PaymentMethod.equals("Select")) {
        JOptionPane.showMessageDialog(this, "Please Selecct Payment Method", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    double amount = Double.parseDouble(amounttext);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    StripePayment.initStripe();
    String clientSecret = StripePayment.createPaymentIntent(amount);

    MySQL.executeIUD("INSERT INTO `transaction` (`date`, `amount`, `payment_method_id`, `outlet_id`, `payment_status_id`, `return_invoice_id`) " +
        "VALUES ('" + sdf.format(PaymentDate) + "', '" + amounttext + "', '" + paymentmethodMap.get(PaymentMethod) + "', '" + OutletId + "', '" + paymentstatusMap.get(paymentstatus) + "', '" + InvoiceID + "')");
printreturn();
    JOptionPane.showMessageDialog(this,
        "Payment initiated successfully.\n\n"
        + "Return Invoice ID: " + InvoiceID + "\n"
        + "Outlet ID        : " + OutletId + "\n"
        + "Amount           : " + amount,
        "Success", JOptionPane.INFORMATION_MESSAGE);
reset();
    MySQL.executeIUD("UPDATE `return_invoice` SET `return_invoice_status_id` = 2 WHERE `id` = '" + InvoiceID + "'");
loadTransaction();
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Invalid Amount Format", "Error", JOptionPane.WARNING_MESSAGE);
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
}

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.HO.removefinance();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        String paymentID = jTextField5.getText();
        String vehicleNo = jTextField3.getText();
        String distributorName = jTextField1.getText();
        Date paymentDate = jDateChooser1.getDate();
        String amount = jTextField4.getText();

        String payStatus = (String) jComboBox1.getSelectedItem();
        String payMethod = (String) jComboBox2.getSelectedItem();

        JFileChooser dialog = new JFileChooser();
        dialog.setSelectedFile(new File(vehicleNo+paymentID+"DistributorPaymentSlip.pdf"));
        int dialogResult = dialog.showSaveDialog(null);

        if (dialogResult == JFileChooser.APPROVE_OPTION) {
            String filePath = dialog.getSelectedFile().getPath();
            Document myDocument = new Document(PageSize.A4, 50, 50, 50, 50);

            try {
                PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                myDocument.open();

                // Title
                Paragraph title = new Paragraph("Distributor Payment Slip", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20));
                title.setAlignment(Element.ALIGN_CENTER);
                myDocument.add(title);

                // Date
                Paragraph date = new Paragraph("Generated On: " + new Date().toString(), FontFactory.getFont(FontFactory.HELVETICA, 10));
                date.setAlignment(Element.ALIGN_RIGHT);
                myDocument.add(date);

                myDocument.add(Chunk.NEWLINE);
                myDocument.add(new LineSeparator());
                myDocument.add(Chunk.NEWLINE);

                // Distributor Info Section
                Paragraph distributorInfo = new Paragraph("Distributor Details", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
                myDocument.add(distributorInfo);
                myDocument.add(Chunk.NEWLINE);

                PdfPTable distributorTable = new PdfPTable(2);
                distributorTable.setWidthPercentage(100);
                distributorTable.setSpacingBefore(10f);
                distributorTable.setSpacingAfter(10f);

                distributorTable.addCell("Distributor Name:");
                distributorTable.addCell(distributorName);
                distributorTable.addCell("Vehicle Number:");
                distributorTable.addCell(vehicleNo);

                myDocument.add(distributorTable);

                myDocument.add(new LineSeparator());
                myDocument.add(Chunk.NEWLINE);

                // Payment Info Section
                Paragraph paymentInfo = new Paragraph("Payment Details", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
                myDocument.add(paymentInfo);
                myDocument.add(Chunk.NEWLINE);

                PdfPTable paymentTable = new PdfPTable(2);
                paymentTable.setWidthPercentage(100);
                paymentTable.setSpacingBefore(10f);
                paymentTable.setSpacingAfter(10f);

                paymentTable.addCell("Payment ID:");
                paymentTable.addCell(paymentID);
                paymentTable.addCell("Amount:");
                paymentTable.addCell(amount);
                paymentTable.addCell("Payment Status:");
                paymentTable.addCell(payStatus);
                paymentTable.addCell("Payment Method:");
                paymentTable.addCell(payMethod);
                paymentTable.addCell("Payment Date:");
                paymentTable.addCell(paymentDate.toString());

                myDocument.add(paymentTable);

                myDocument.add(new LineSeparator());
                myDocument.add(Chunk.NEWLINE);

                Paragraph footer = new Paragraph("Thank you for your business!", FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12));
                footer.setAlignment(Element.ALIGN_CENTER);
                myDocument.add(footer);

                myDocument.close();
                JOptionPane.showMessageDialog(null, "Report was successfully generated");
      reset();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }


    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        setVisible(true);
        ReturnInvoiceHO object = new ReturnInvoiceHO(HO, true);
        object.setVisible(true);


    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        reset();


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        setVisible(true);
        DistributorPaymentTable1 object = new DistributorPaymentTable1(HO,true);
        object.setVisible(true);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed

       try {
    String GrnId = jTextField7.getText();

    if (GrnId.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a Return Invoice ID", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }
jTextField2.setEditable(false);
    ResultSet resultSet = MySQL.executeSearch(
        "SELECT * FROM `w_grn` "
    );

    if (resultSet.next()) {
        String amount = resultSet.getString("paid_amount"); // Adjust column name if needed
        jTextField8.setText(amount);
        

       

    } else {
       
        JOptionPane.showMessageDialog(this, "No ID Found", "Warning", JOptionPane.ERROR_MESSAGE);
        reset();
    }

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "An error occurred while searching. Please check the console for details.", "Error", JOptionPane.ERROR_MESSAGE);
    reset();
}        
        
        
        
        
        
        
        
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
   
           setVisible(true);
        SupplierPayment object = new SupplierPayment();
        object.setVisible(true);        
        
        
        
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
       
         try {

    String GrnID = jTextField7.getText();
    String Amounttxt = jTextField8.getText();
    
    String paymentstatus = String.valueOf(jComboBox6.getSelectedItem());
    String paymentMethod = String.valueOf(jComboBox5.getSelectedItem());
    
    Date paymentDate = jDateChooser4.getDate();
    SimpleDateFormat sdf = new SimpleDateFormat();

    if (GrnID.isEmpty() || Amounttxt.isEmpty() || paymentDate == null) {
        JOptionPane.showMessageDialog(this, "Wrong Or Empty Details Enterd", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }
if (paymentstatus.equals("Select")) {
        JOptionPane.showMessageDialog(this, "Select Payment Status", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (paymentstatus.equals("Pending")) {
        JOptionPane.showMessageDialog(this, "Payment Status Must Be Complete", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (paymentMethod.equals("Select")) {
        JOptionPane.showMessageDialog(this, "Select Payment Status", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    double amount = Double.parseDouble(Amounttxt);
    StripePayment.initStripe();

    String clientSecret = StripePayment.createPaymentIntent(amount);

    MySQL.executeIUD("INSERT INTO `supplier_payment` (`amount`, `payment_method_id`, `payment_status_id`, `w_grn_id`) " +
        "VALUES ('" + Amounttxt + "', '" + paymentmethodMap.get(paymentMethod) + "', '" + paymentstatusMap.get(paymentstatus) + "', '" + GrnID + "')");

    JOptionPane.showMessageDialog(this,
        "Payment initiated successfully.\n\n"
        + "Distributor ID: " + GrnID + "\n"
        + "Amount        : " + amount,
        "Success", JOptionPane.INFORMATION_MESSAGE);
reset();
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Invalid Amount Format", "Error", JOptionPane.WARNING_MESSAGE);
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
}
        
        
        
        
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        
        reset();
        
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jTextField11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyReleased
        DefaultTableModel ob = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        jTable1.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(jTextField7.getText()));
    }//GEN-LAST:event_jTextField11KeyReleased

    private void jTextField12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
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
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
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

        jTextField6.setText(null);
        jTextField9.setText(null);
        jTextField10.setText(null);
        jDateChooser2.setDate(null);
        jDateChooser3.setDate(null);
        jDateChooser4.setDate(null);
        jTextField7.setText(null);
        jTextField9.setText(null);
       
        jComboBox1.setSelectedItem("Select");
        jComboBox2.setSelectedItem("Select");
        jComboBox3.setSelectedItem("Select");
        jComboBox4.setSelectedItem("Select");
        jComboBox5.setSelectedItem("Select");
        jComboBox6.setSelectedItem("Select");
        jTextField1.setText(null);
        jTextField5.setText(null);
        jTextField3.setText(null);
        jDateChooser1.setDate(null);
        jTextField4.setText(null);
        jTextField2.setText(null);
        jLabel11.setIcon(null);

    }

}
