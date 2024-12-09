/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import model.MySQL;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.mysql.cj.xdevapi.Statement;

import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author User
 */
public class distributorpayment extends javax.swing.JFrame {

    private static HashMap<String, String> paymentstatusMap = new HashMap<>();
    private static HashMap<String, String> paymentmethodMap = new HashMap<>();

    public distributorpayment() {
        initComponents();
        loadpaymentstatus();
        loadpaymetmethod();
        reset();
        //payment();
        loadDistributorPayment();
        //deleteDistributorpayment();
    }
    
    public void deleteDistributorpayment() {
    int selectedRow = jTable1.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a Payment to delete.", "Selection Required", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String DistributorpayID = jTable1.getValueAt(selectedRow, 1).toString();
    
    int confirmDelete = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
    if(confirmDelete==JOptionPane.YES_OPTION)
    {
    try {

        int rowsAffected = MySQL.executeIUD
            ("DELETE FROM distributor_payment WHERE id = '" + DistributorpayID + "'"
        );

        if (rowsAffected > 0) {

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Payment deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No Payment found with the given ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        reset();
        loadDistributorPayment();

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "An error occurred while deleting the employee: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    }
    
    

    private void loadDistributorPayment() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `distributor_payment`"
                    + " INNER JOIN `distributor` ON `distributor_payment`.`distributor_id` = `distributor`.`id`"
                    + " INNER JOIN `payment_status` ON `distributor_payment`.`payment_status_id`= `payment_status`.`id`"
                    + " INNER JOIN `payment_method` ON `distributor_payment`.`payment_method_id`=`payment_method`.`id` ORDER BY `distributor_payment`.`id` ASC  ");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (resultSet.next()) {
                Vector vector = new Vector();
                vector.add(resultSet.getString("distributor.id"));
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("amount"));
                vector.add(resultSet.getString("payment_date"));
                vector.add(resultSet.getString("description"));
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
            jComboBox1.setModel(model);

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
            jComboBox2.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void payment(int distributor_id) {

        try {

            String query = "SELECT name , vehical_no , address FROM distributor WHERE id = distributor_id ";

            ResultSet resultSet = MySQL.executeSearch(query);

            if (resultSet.next()) {

                jTextField1.setText(resultSet.getString("distibutor.id"));
                jTextField2.setText(resultSet.getString("distibutor.name"));
                jTextField4.setText(resultSet.getString("distibutor.vehicle_no"));
                jTextField5.setText(resultSet.getString("distributor.address"));

                JOptionPane.showMessageDialog(this, "Data loaded Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {

                JOptionPane.showMessageDialog(this, "Distributor Not Found", "Warning", JOptionPane.WARNING_MESSAGE);

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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField7 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Distributor Payment");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 246, 237));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel1.setText("Distributor Payments");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, -1, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/file (4).png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 142, 89));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 110));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 170, 40));

        jTextField1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 278, 40));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel3.setText("Name");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 150, 40));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel4.setText("Payment Method");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 500, 180, 40));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel5.setText("Vehicle No");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 150, 40));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel6.setText("Descrption");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 160, 40));

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel7.setText("Address");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 140, 40));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel8.setText("Salaray");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 150, 40));

        jTextField2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 240, 40));
        jPanel4.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, 240, 40));

        jTextField4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jPanel4.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 240, 40));

        jTextField5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jPanel4.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 240, 40));

        jTextField6.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jPanel4.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, 240, 40));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 440, 240, 40));

        jButton2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-clear-26.png"))); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 570, 130, 40));

        jButton3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-add-26.png"))); // NOI18N
        jButton3.setText("Add ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 570, 130, 40));

        jButton4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-update-26.png"))); // NOI18N
        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 570, 130, 40));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-print-25.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 620, 50, 40));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel9.setText("Payment Status");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, 160, 40));

        jTextField3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 240, 40));

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel10.setText("Payment Date");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 150, 40));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 500, 240, 40));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 108, 560, 690));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Distributor ID", "Payment ID", "Salary", "Date", "Description", "Payment Status", "Payment Method"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 700, 600));

        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });
        jPanel2.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 150, 40));

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel11.setText("Search");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, -1, -1));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-delete-38.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 60, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 710, 690));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jButton1.grabFocus();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String DistributorID = jTextField1.getText();
            String Name = jTextField2.getText();
            String vehicleno = jTextField4.getText();
            String address = jTextField5.getText();
            String salary = jTextField6.getText();
            Date paymentDate = jDateChooser1.getDate();
            String description = jTextField3.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String paymentStatus = String.valueOf(jComboBox1.getSelectedItem());
            String paymentMethod = String.valueOf(jComboBox2.getSelectedItem());

            if (DistributorID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter The Distributor ID", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (Name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Click the Search Button", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (vehicleno.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Wrong Vehicle Number", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (address.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Address is empty", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (salary.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter the Salary Amount", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (!salary.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Please Enter Valid Amount", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (paymentDate == null) {
                JOptionPane.showMessageDialog(this, "Please Add Payment Date", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter the desciption", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (paymentStatus.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select The Payment Status", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (paymentMethod.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select The Payment method", "Error", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `distributor_payment` WHERE `distributor_id` = '" + DistributorID + "'");

                if (!resultSet.next()) {
                   String query = "INSERT INTO `distributor_payment` (`amount`,`payment_date`,`description`,`distributor_id`,`payment_status_id`,`payment_method_id`)"
                            + " VALUES('" + salary + "','" + sdf.format(paymentDate) + "','" + description + "','" + DistributorID + "','" + paymentstatusMap.get(paymentStatus) + "','" + paymentmethodMap.get(paymentMethod) + "' )";
                
                   
                   MySQL.executeIUD(query);
                 JOptionPane.showMessageDialog(this, "Payment details added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                }else{
                String query = "INSERT INTO `distributor_payment` (`amount`,`payment_date`,`description`,`distributor_id`,`payment_status_id`,`payment_method_id`)"
                            + " VALUES('" + salary + "','" + sdf.format(paymentDate) + "','" + description + "','" + DistributorID + "','" + paymentstatusMap.get(paymentStatus) + "','" + paymentmethodMap.get(paymentMethod) + "' )";
                
                   
                   MySQL.executeIUD(query);
                 JOptionPane.showMessageDialog(this, "Payment details added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                
                }
                
                }
                loadDistributorPayment();
                reset();
                    

                 

            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            String DistributorId = jTextField1.getText();

            if (DistributorId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Distributor Id is Empty", "Error", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `distributor` WHERE `id` = '" + DistributorId + "'");

                if (resultSet.next()) {
                    String distributorname = resultSet.getString("name");
                    jTextField2.setText(distributorname);

                    String vehicleNo = resultSet.getString("vehicle_no");
                    jTextField4.setText(vehicleNo);

                    String address = resultSet.getString("address");
                    jTextField5.setText(address);

                    jTextField2.setEditable(false);
                    jTextField5.setEditable(false);
                    jTextField4.setEditable(false);

//
//            String deliveryDateStr = resultSet.getString("delivery_date");
//            Date deliveryDate = new SimpleDateFormat("yyyy-MM-dd").parse(deliveryDateStr);
//            jDateChooser1.setDate(deliveryDate);
//
//            String distributorName = resultSet.getString("name");
//            jTextField1.setText(distributorName);
//
//            String note = resultSet.getString("note");
//            jTextField2.setText(note);
                    //String deliveryStatusId = resultSet.getString("delivery_status_id");
//            for (String status : DeliveryStatusMap.keySet()) {
//                if (DeliveryStatusMap.get(status).equals(deliveryStatusId)) {
//                    jComboBox1.setSelectedItem(status);
//                    break;
//                }
//            }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Distributor ID", "Warning", JOptionPane.ERROR_MESSAGE);
                    jTextField1.setText(null);
                    jTextField2.setText(null);
                    jTextField4.setText(null);
                    jTextField5.setText(null);

                }

            }

        } catch (Exception e) {
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        String Name = jTextField2.getText();
        String Vehicle_no = jTextField4.getText();
        String address = jTextField5.getText();
        String salary = jTextField6.getText();
        Date payment_date = jDateChooser1.getDate();
        String description = jTextField3.getText();
        String amount = jTextField4.getText();
        String paystatus = (String) jComboBox1.getSelectedItem();
        String paymethod = (String) jComboBox2.getSelectedItem();

        JFileChooser dialog = new JFileChooser();
        dialog.setSelectedFile(new File(Vehicle_no));
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

                myDocument.add(new Paragraph("Distributor Payment Slip", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                myDocument.add(new Paragraph(new Date().toString()));
                myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));
                myDocument.add((new Paragraph("EMPLOYEE DETAILS", FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD))));
                myDocument.add((new Paragraph("Name of Distributor: " + Name, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));

                myDocument.add((new Paragraph("Vehicle Number: " + Vehicle_no, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                myDocument.add((new Paragraph("Address: " + address, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));

                myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));
                myDocument.add(new Paragraph("Payment Details", FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD)));
                //myDocument.add((new Paragraph("NEW EMPLOYEE",FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN))));
                myDocument.add((new Paragraph("Salary: " + salary, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));
                myDocument.add((new Paragraph("Payment Date: " + payment_date, FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.PLAIN))));

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


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try {
            int selectedRow = jTable1.getSelectedRow();
            String DistributorpayID = jTable1.getValueAt(selectedRow, 1).toString();
            String DistributorID = jTextField1.getText();
            String Name = jTextField2.getText();
            String vehicleno = jTextField4.getText();
            String address = jTextField5.getText();
            String salary = jTextField6.getText();
            Date paymentDate = jDateChooser1.getDate();
            String description = jTextField3.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String paymentStatus = String.valueOf(jComboBox1.getSelectedItem());
            String paymentMethod = String.valueOf(jComboBox2.getSelectedItem());

            if (DistributorID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter The Distributor ID", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (Name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Click the Search Button", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (vehicleno.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Wrong Vehicle Number", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (address.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Address is empty", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (salary.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter the Salary Amount", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (paymentDate == null) {
                JOptionPane.showMessageDialog(this, "Please Add Payment Date", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter the desciption", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (paymentStatus.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select The Payment Status", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (paymentMethod.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select The Payment method", "Error", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `distributor_payment`"
                    + " INNER JOIN `distributor` ON `distributor_payment`.`distributor_id` = `distributor`.`id`"
                    + " INNER JOIN `payment_status` ON `distributor_payment`.`payment_status_id`= `payment_status`.`id`"
                    + " INNER JOIN `payment_method` ON `distributor_payment`.`payment_method_id`=`payment_method`.`id` ORDER BY `distributor_payment`.`id` ASC  ");
                  boolean canUpdate = false;
                if (resultSet.next()) {
                    
                    if(!resultSet.getString("id").equals(DistributorpayID)){
                        
                        MySQL.executeIUD("UPDATE distributor_payment  SET  amount = '" + salary + "' , payment_date = '" + sdf.format(paymentDate) + "' , `description` = '" + description + "', `payment_status_id` = '" + paymentstatusMap.get(paymentStatus) + "' "
                                + " , `payment_method_id` = '" + paymentmethodMap.get(paymentMethod) + "' WHERE id = '" + DistributorpayID + "' ");
                        
                    }
                    canUpdate = true;

                    //JOptionPane.showMessageDialog(this, "", "Error", JOptionPane.WARNING_MESSAGE);
                }

                JOptionPane.showMessageDialog(this, "Payment details Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadDistributorPayment();
                reset();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        reset();
        jButton3.setEnabled(true);
        jTextField1.setEditable(true);
        jTable1.setEnabled(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() == 2) {

            jTable1.setEnabled(false);
            jButton3.setEnabled(false);
            int selectedRow = jTable1.getSelectedRow();
            //System.out.println(selectedRow);

            String DistributorID = String.valueOf(jTable1.getValueAt(selectedRow, 0));
            jTextField1.setText(DistributorID);
            jTextField1.setEditable(false);

            String salary = String.valueOf(jTable1.getValueAt(selectedRow, 2));
            jTextField6.setText(salary);

            String description = String.valueOf(jTable1.getValueAt(selectedRow, 4));
            jTextField3.setText(description);

            //load date to jdatechooser1
            try {
                Date date = new SimpleDateFormat("yyyy-mm-dd").parse((String) jTable1.getValueAt(selectedRow, 3));
                jDateChooser1.setDate(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //load date to jdatechooser1
            

            String paystatus = String.valueOf(jTable1.getValueAt(selectedRow, 5));
            jComboBox1.setSelectedItem(paystatus);

            String paymethod = String.valueOf(jTable1.getValueAt(selectedRow, 6));
            jComboBox2.setSelectedItem(paymethod);

        }
        

    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        DefaultTableModel ob = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        jTable1.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(jTextField7.getText()));


    }//GEN-LAST:event_jTextField7KeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        deleteDistributorpayment();
    

    }//GEN-LAST:event_jButton6ActionPerformed

public static void main(String args[]) {
        FlatMacLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new distributorpayment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables

    private void reset(){
    jTextField1.setText(null);
    jTextField2.setText(null);
    jTextField3.setText(null);
    jTextField4.setText(null);
   jTextField5.setText(null);
    jTextField6.setText(null);
    jDateChooser1.setDate(null);
    jComboBox1.setSelectedItem(null);
    jComboBox2.setSelectedItem(null);
    
    jTable1.setEnabled(true);
    
    
    
    
    }
    
    
    


}
