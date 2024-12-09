/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.Frame;
import model.MySQL;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author barth
 */
public class WReturns extends javax.swing.JPanel {
    private WHome home;

    /**
     * Creates new form Overview
     */
    public WReturns(WHome home) {
        initComponents();
        this.home = home;
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        returnInvoiceTable.setDefaultRenderer(Object.class, renderer);
        returnInvoiceItemTable.setDefaultRenderer(Object.class, renderer);
        //wSupervisorIDField.setText(SignIn.getWSupervisorID());
        loadReturnInvoices();
        loadReturnSlips();
    }
    
    public JTextField getslipIDField() {
        return slipIDField;
    }
    
    public JTextField getreturnInvoiceIDField() {
        return returnInvoiceIDField;
    }
    
    public JTextField getoutletManagerIDField() {
        return outletManagerIDField;
    }
    
    public JLabel getoutletNameLabel() {
        return outletNameLabel;
    }
    
    public JLabel getoutletAddressLabel() {
        return outletAddressLabel;
    }
    
    public JTextField getwSupervisorIDField() {
        return wSupervisorIDField;
    }
    
    public JLabel getdeliveryDateLabel() {
        return deliveryDateLabel;
    }
    
    public JLabel gettimeLabel() {
        return timeLabel;
    }
    
    public JLabel getvehicleNumberLabel() {
        return vehicleNumberLabel;
    }
    
    private void loadReturnInvoices() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `return_invoice` INNER JOIN `outlet` "
                    + "ON `return_invoice`.`outlet_id` = `outlet`.`id` INNER JOIN `distributor` "
                    + "ON `return_invoice`.`distributor_id` = `distributor`.`id` INNER JOIN `delivery` "
                    + "ON `return_invoice`.`delivery_id` = `delivery`.`id` INNER JOIN `reason` "
                    + "ON `return_invoice`.`reason_id` = `reason`.`id` INNER JOIN `return_invoice_status` "
                    + "ON `return_invoice`.`return_invoice_status_id` = `return_invoice_status`.`id` "
                    + "WHERE `return_invoice_status`.`name` = 'Pending'");
            DefaultTableModel model = (DefaultTableModel) returnInvoiceTable.getModel();
            model.setRowCount(0);
            
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("return_invoice.id"));
                vector.add(resultSet.getString("outlet.name"));
                vector.add(resultSet.getString("distributor.name"));
                vector.add(resultSet.getString("distributor.vehicle_no"));
                vector.add(resultSet.getString("delivery.delivery_date"));
                vector.add(resultSet.getString("return_invoice.time"));
                vector.add(resultSet.getString("reason.reason"));
                vector.add(resultSet.getString("outlet_manager.id"));
                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadReturnSlips() {
        try {
            String query = "SELECT * FROM `return_slip` INNER JOIN `return_invoice` "
                    + "ON `return_slip`.`return_invoice_id` = `return_invoice`.`id` INNER JOIN `outlet` "
                    + "ON `return_invoice`.`outlet_id` = `outlet`.`id` INNER JOIN `delivery` "
                    + "ON `return_invoice`.`delivery_id` = `delivery`.`id` INNER JOIN `return_slip_status` "
                    + "ON `return_slip`.`return_slip_status_id` = `return_slip_status`.`id` "
                    + "WHERE `return_slip_status`.`name` = 'Approved'";
            
            if (sortByComboBox.getSelectedItem().equals("Delivery Date DESC")) {
                query += "ORDER BY `delivery`.`delivery_date` DESC";
            }else if (sortByComboBox.getSelectedItem().equals("Delivery Date ASC")) {
                query += "ORDER BY `delivery`.`delivery_date` ASC";
            }else if (sortByComboBox.getSelectedItem().equals("Outlet Name ASC")) {
                query += "ORDER BY `outlet`.`name` ASC";
            }else if (sortByComboBox.getSelectedItem().equals("Outlet Name DESC")) {
                query += "ORDER BY `outlet`.`name` DESC";
            }
            
            ResultSet resultSet = MySQL.executeSearch(query);
            DefaultTableModel model = (DefaultTableModel) returnInvoiceTable.getModel();
            model.setRowCount(0);
            
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("return_invoice.id"));
                vector.add(resultSet.getString("outlet.name"));
                vector.add(resultSet.getString("distributor.name"));
                vector.add(resultSet.getString("distributor.vehicle_no"));
                vector.add(resultSet.getString("delivery.delivery_date"));
                vector.add(resultSet.getString("return_invoice.time"));
                vector.add(resultSet.getString("reason.reason"));
                vector.add(resultSet.getString("outlet_manager.id"));
                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void resetReturnInvoices() {
        returnInvoiceTable.clearSelection();
        DefaultTableModel model = (DefaultTableModel) returnInvoiceItemTable.getModel();
        model.setRowCount(0);
    }
    
    private void resetReturnSlips() {
        slipIDField.setText("");
        returnInvoiceIDField.setText("");
        outletManagerIDField.setText("");
        outletNameLabel.setText("OUTLET NAME");
        outletAddressLabel.setText("OUTLET ADDRESS");
        wSupervisorIDField.setText("");
        deliveryDateLabel.setText("DELIVERY DATE");
        timeLabel.setText("TIME");
        vehicleNumberLabel.setText("VEHICLE NUMBER");
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        returnInvoiceTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        returnInvoiceItemTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        resetButton1 = new javax.swing.JButton();
        viewInvoiceButton = new javax.swing.JButton();
        closeLabel1 = new javax.swing.JLabel();
        printInvoiceButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        slipIDField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        returnInvoiceIDField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        outletManagerIDField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        outletNameLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        outletAddressLabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        wSupervisorIDField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        deliveryDateLabel = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        vehicleNumberLabel = new javax.swing.JLabel();
        selectInvoiceButton = new javax.swing.JButton();
        resetButton2 = new javax.swing.JButton();
        approveNPrintButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        returnSlipTable = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        sortByComboBox = new javax.swing.JComboBox<>();
        viewSlipButton = new javax.swing.JButton();
        uploadImageButton = new javax.swing.JButton();
        closeLabel2 = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(66, 45, 22));
        jLabel2.setText("Return Invoice");

        returnInvoiceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Outlet", "Distributor", "Vehicle No.", "Delivery Date", "Time", "Reason", "Out. Man. ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        returnInvoiceTable.getTableHeader().setReorderingAllowed(false);
        returnInvoiceTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                returnInvoiceTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(returnInvoiceTable);

        returnInvoiceItemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Weight", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(returnInvoiceItemTable);

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(66, 45, 22));
        jLabel3.setText("Return Invoice Items");

        resetButton1.setBackground(new java.awt.Color(245, 219, 200));
        resetButton1.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        resetButton1.setForeground(new java.awt.Color(0, 0, 0));
        resetButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset-icon.png"))); // NOI18N
        resetButton1.setText("Reset");
        resetButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButton1ActionPerformed(evt);
            }
        });

        viewInvoiceButton.setBackground(new java.awt.Color(245, 219, 200));
        viewInvoiceButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        viewInvoiceButton.setForeground(new java.awt.Color(0, 0, 0));
        viewInvoiceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/view-icon.png"))); // NOI18N
        viewInvoiceButton.setText("View");

        closeLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/close-icon.png"))); // NOI18N
        closeLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeLabel1MouseClicked(evt);
            }
        });

        printInvoiceButton.setBackground(new java.awt.Color(245, 219, 200));
        printInvoiceButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        printInvoiceButton.setForeground(new java.awt.Color(0, 0, 0));
        printInvoiceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/print-icon.png"))); // NOI18N
        printInvoiceButton.setText("Print");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(viewInvoiceButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printInvoiceButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeLabel1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(closeLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetButton1)
                    .addComponent(viewInvoiceButton)
                    .addComponent(printInvoiceButton))
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Return Invoices", jPanel2);

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(66, 45, 22));
        jLabel1.setText("Return Slip");

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel5.setText("Slip ID :");

        slipIDField.setEditable(false);
        slipIDField.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel6.setText("Ret. Inv. ID :");

        returnInvoiceIDField.setEditable(false);
        returnInvoiceIDField.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel7.setText("Outlet Manager ID :");

        outletManagerIDField.setEditable(false);
        outletManagerIDField.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel8.setText("Outlet :");

        outletNameLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        outletNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outletNameLabel.setText("OUTLET NAME");
        outletNameLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel10.setText("Address :");

        outletAddressLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        outletAddressLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outletAddressLabel.setText("OUTLET ADDRESS");
        outletAddressLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        jLabel12.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel12.setText("W. Supervisor ID :");

        wSupervisorIDField.setEditable(false);
        wSupervisorIDField.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel13.setText("Date :");

        deliveryDateLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        deliveryDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deliveryDateLabel.setText("DELIVERY DATE");
        deliveryDateLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        jLabel15.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel15.setText("Time :");

        timeLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLabel.setText("TIME");
        timeLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        jLabel17.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel17.setText("Vehicle Number :");

        vehicleNumberLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        vehicleNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vehicleNumberLabel.setText("VEHICLE NUMBER");
        vehicleNumberLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        selectInvoiceButton.setBackground(new java.awt.Color(245, 219, 200));
        selectInvoiceButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        selectInvoiceButton.setForeground(new java.awt.Color(0, 0, 0));
        selectInvoiceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/select-icon.png"))); // NOI18N
        selectInvoiceButton.setText("Select Ret. Slip");
        selectInvoiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectInvoiceButtonActionPerformed(evt);
            }
        });

        resetButton2.setBackground(new java.awt.Color(245, 219, 200));
        resetButton2.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        resetButton2.setForeground(new java.awt.Color(0, 0, 0));
        resetButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset-icon.png"))); // NOI18N
        resetButton2.setText("Reset");
        resetButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButton2ActionPerformed(evt);
            }
        });

        approveNPrintButton.setBackground(new java.awt.Color(245, 219, 200));
        approveNPrintButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        approveNPrintButton.setForeground(new java.awt.Color(0, 0, 0));
        approveNPrintButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/confirm-icon.png"))); // NOI18N
        approveNPrintButton.setText("Approve & Print");

        returnSlipTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Slip ID", "Ret. Inv. ID", "Outlet Name", "Delivery Date", "Slip Image"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        returnSlipTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(returnSlipTable);

        jLabel19.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel19.setText("Sort By :");

        sortByComboBox.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        sortByComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Delivery Date DESC", "Delivery Date ASC", "Outlet Name ASC", "Outlet Name DESC", "" }));
        sortByComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sortByComboBoxItemStateChanged(evt);
            }
        });

        viewSlipButton.setBackground(new java.awt.Color(245, 219, 200));
        viewSlipButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        viewSlipButton.setForeground(new java.awt.Color(0, 0, 0));
        viewSlipButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/view-icon.png"))); // NOI18N
        viewSlipButton.setText("View");

        uploadImageButton.setBackground(new java.awt.Color(245, 219, 200));
        uploadImageButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        uploadImageButton.setForeground(new java.awt.Color(0, 0, 0));
        uploadImageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/upload-icon.png"))); // NOI18N
        uploadImageButton.setText("Upload Image");

        closeLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/close-icon.png"))); // NOI18N
        closeLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(selectInvoiceButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(approveNPrintButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetButton2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(slipIDField)
                            .addComponent(outletNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deliveryDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(returnInvoiceIDField)
                            .addComponent(outletAddressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel12)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(outletManagerIDField)
                            .addComponent(wSupervisorIDField)
                            .addComponent(vehicleNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                        .addGap(0, 26, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sortByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(uploadImageButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewSlipButton))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeLabel2)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(closeLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(slipIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(returnInvoiceIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(outletManagerIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(outletNameLabel)
                    .addComponent(jLabel10)
                    .addComponent(outletAddressLabel)
                    .addComponent(jLabel12)
                    .addComponent(wSupervisorIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(deliveryDateLabel)
                    .addComponent(jLabel15)
                    .addComponent(timeLabel)
                    .addComponent(jLabel17)
                    .addComponent(vehicleNumberLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectInvoiceButton)
                    .addComponent(resetButton2)
                    .addComponent(approveNPrintButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(sortByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewSlipButton)
                    .addComponent(uploadImageButton))
                .addGap(31, 31, 31))
        );

        jTabbedPane1.addTab("Return Slips", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void closeLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabel1MouseClicked
        this.home.removeReturns();
    }//GEN-LAST:event_closeLabel1MouseClicked

    private void closeLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabel2MouseClicked
        this.home.removeReturns();
    }//GEN-LAST:event_closeLabel2MouseClicked

    private void returnInvoiceTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnInvoiceTableMouseClicked
        int row = returnInvoiceTable.getSelectedRow();
        
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `return_invoice_items` INNER JOIN `return_invoice` "
                    + "ON `return_invoice_items`.`return_invoice_id` = `return_invoice`.`id` INNER JOIN `w_product` "
                    + "ON `return_invoice_items`.`w_product_id` = `w_product`.`id` "
                    + "WHERE `return_invoice_items`.`return_invoice_id` = '"+String.valueOf(returnInvoiceTable.getValueAt(row, 0))+"'");
            
            DefaultTableModel model = (DefaultTableModel) returnInvoiceItemTable.getModel();
            model.setRowCount(0);
            
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("w_product.id"));
                vector.add(resultSet.getString("w_product.name"));
                vector.add(resultSet.getString("w_product.weight"));
                vector.add(resultSet.getString("w_product.price"));
                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_returnInvoiceTableMouseClicked

    private void resetButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButton1ActionPerformed
        resetReturnInvoices();
    }//GEN-LAST:event_resetButton1ActionPerformed

    private void selectInvoiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectInvoiceButtonActionPerformed
        WSelectReturnSlip selectReturnSlip = new WSelectReturnSlip((Frame) SwingUtilities.getWindowAncestor(this), true, this);
        selectReturnSlip.setVisible(true);
    }//GEN-LAST:event_selectInvoiceButtonActionPerformed

    private void resetButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButton2ActionPerformed
        resetReturnSlips();
    }//GEN-LAST:event_resetButton2ActionPerformed

    private void sortByComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sortByComboBoxItemStateChanged
        loadReturnSlips();
    }//GEN-LAST:event_sortByComboBoxItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton approveNPrintButton;
    private javax.swing.JLabel closeLabel1;
    private javax.swing.JLabel closeLabel2;
    private javax.swing.JLabel deliveryDateLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel outletAddressLabel;
    private javax.swing.JTextField outletManagerIDField;
    private javax.swing.JLabel outletNameLabel;
    private javax.swing.JButton printInvoiceButton;
    private javax.swing.JButton resetButton1;
    private javax.swing.JButton resetButton2;
    private javax.swing.JTextField returnInvoiceIDField;
    private javax.swing.JTable returnInvoiceItemTable;
    private javax.swing.JTable returnInvoiceTable;
    private javax.swing.JTable returnSlipTable;
    private javax.swing.JButton selectInvoiceButton;
    private javax.swing.JTextField slipIDField;
    private javax.swing.JComboBox<String> sortByComboBox;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JButton uploadImageButton;
    private javax.swing.JLabel vehicleNumberLabel;
    private javax.swing.JButton viewInvoiceButton;
    private javax.swing.JButton viewSlipButton;
    private javax.swing.JTextField wSupervisorIDField;
    // End of variables declaration//GEN-END:variables
}
