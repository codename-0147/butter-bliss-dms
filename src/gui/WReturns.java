/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.Frame;
import java.io.File;
import java.io.InputStream;
import model.MySQL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.GmailOAuthService;
import model.SupplierReturnItem;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author barth
 */
public class WReturns extends javax.swing.JPanel {
    private WHome home;
    HashMap<String, String> suppliersMap = new HashMap<>();
    HashMap<String, String> supplierReturnReasonMap = new HashMap<>();
    HashMap<String, SupplierReturnItem> supplierReturnItemMap = new HashMap<>();

    /**
     * Creates new form Overview
     */
    public WReturns(WHome home) {
        initComponents();
        this.home = home;
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        returnInvoiceTable.setDefaultRenderer(Object.class, renderer);
        returnInvoiceItemTable1.setDefaultRenderer(Object.class, renderer);
        //wSupervisorIDField.setText(SignIn.getWSupervisorID());
        loadReturnInvoices();
        loadReturnSlips();
        generateSupplierReturnInvoiceID();
        loadSuppliers();
        loadSupplierReturnReasons();
    }
    
    private void generateSupplierReturnInvoiceID() {
        long id = System.currentTimeMillis();
        supRetInvIDField.setText(String.valueOf(id));
    }
    
    public JTextField getslipIDField() {
        return slipIDField;
    }
    
    public JTextField getreturnInvoiceIDField() {
        return returnInvoiceIDField1;
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
        return wSupervisorIDField1;
    }
    
    public JLabel getdateLabel() {
        return dateLabel;
    }
    
    public JLabel getvehicleNumberLabel() {
        return vehicleNumberLabel;
    }
    
    public JTextField getreturnInvoiceIDField2() {
        return returnInvoiceIDField2;
    }
    
    public JTable getreturnInvoiceItemTable2() {
        return returnInvoiceItemTable2;
    }
    
    private void loadReturnInvoices() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `return_invoice` INNER JOIN `outlet` "
                    + "ON `return_invoice`.`outlet_id` = `outlet`.`id` INNER JOIN `outlet_manager` "
                    + "ON `outlet`.`outlet_manager_id` = `outlet_manager`.`id` INNER JOIN `distributor` "
                    + "ON `return_invoice`.`distributor_id` = `distributor`.`id` INNER JOIN `reason` "
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
                vector.add(resultSet.getString("return_invoice.date"));
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
            
            if (sortByComboBox1.getSelectedItem().equals("Delivery Date DESC")) {
                query += "ORDER BY `delivery`.`delivery_date` DESC";
            }else if (sortByComboBox1.getSelectedItem().equals("Delivery Date ASC")) {
                query += "ORDER BY `delivery`.`delivery_date` ASC";
            }else if (sortByComboBox1.getSelectedItem().equals("Outlet Name ASC")) {
                query += "ORDER BY `outlet`.`name` ASC";
            }else if (sortByComboBox1.getSelectedItem().equals("Outlet Name DESC")) {
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
    
    private void loadSuppliers() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `supplier`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            
            while (resultSet.next()) {
                vector.add(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
                suppliersMap.put(resultSet.getString("email"), resultSet.getString("mobile"));
            }
            
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            supplierComboBox.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadSupplierReturnReasons() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `supplier_return_reason`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            
            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                supplierReturnReasonMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }
            
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            reasonComboBox.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadSupplierReturnInvoices(){
        try {
            String query = "SELECT * FROM `supplier_return_invoice` INNER JOIN `supplier` "
                    + "ON `supplier_return_invoice`.`supplier_mobile` = `supplier`.`mobile` "
                    + "INNER JOIN `supplier_return_reason` "
                    + "ON `supplier_return_invoice`.`supplier_return_reason_id` = `supplier_return_reason`.`id` "
                    + "INNER JOIN `supplier_return_invoice_status` "
                    + "ON `return_invoice`.`return_invoice_status_id` = `return_invoice_status`.`id` WHERE ";
            
            Date from = null;
            Date to = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            
            if (fromDateChooser.getDate() != null) {
                from = fromDateChooser.getDate();
                query += "`supplier_return_invoice`.`date` > '"+format.format(from)+"' AND ";
            }
            
            if (toDateChooser.getDate() != null) {
                to = toDateChooser.getDate();
                query += "`supplier_return_invoice`.`date` < '"+format.format(to)+"' AND ";
            }
            
            String sort = String.valueOf(sortByComboBox2.getSelectedItem());
            
            if (sort.equals("Date ASC")) {
                query += "ORDER BY ";
                query = query.replace("WHERE ORDER BY ", "ORDER BY `supplier_return_invoice`.`date` ASC");
                query = query.replace("AND ORDER BY ", "ORDER BY `supplier_return_invoice`.`date` ASC");
            }else if (sort.equals("Date DESC")) {
                query += "ORDER BY ";
                query = query.replace("WHERE ORDER BY ", "ORDER BY `supplier_return_invoice`.`date` DESC");
                query = query.replace("AND ORDER BY ", "ORDER BY `supplier_return_invoice`.`date` DESC");
            }else if (sort.equals("Reason ASC")) {
                query += "ORDER BY ";
                query = query.replace("WHERE ORDER BY ", 
                        "ORDER BY `supplier_return_reason`.`name` ASC, `supplier_return_invoice`.`date` DESC");
                query = query.replace("AND ORDER BY ", 
                        "ORDER BY `supplier_return_reason`.`name` ASC, `supplier_return_invoice`.`date` DESC");
            }else if (sort.equals("Reason DESC")) {
                query += "ORDER BY ";
                query = query.replace("WHERE ORDER BY ", 
                        "ORDER BY `supplier_return_reason`.`name` DESC, `supplier_return_invoice`.`date` DESC");
                query = query.replace("AND ORDER BY ", 
                        "ORDER BY `supplier_return_reason`.`name` DESC, `supplier_return_invoice`.`date` DESC");
            }else if (sort.equals("Supplier ASC")) {
                query += "ORDER BY ";
                query = query.replace("WHERE ORDER BY ", 
                        "ORDER BY `supplier`.`first_name` ASC, `supplier_return_invoice`.`date` DESC");
                query = query.replace("AND ORDER BY ", 
                        "ORDER BY `supplier`.`first_name` ASC, `supplier_return_invoice`.`date` DESC");
            }else if (sort.equals("Supplier DESC")) {
                query += "ORDER BY ";
                query = query.replace("WHERE ORDER BY ", 
                        "ORDER BY `supplier`.`first_name` DESC, `supplier_return_invoice`.`date` DESC");
                query = query.replace("AND ORDER BY ", 
                        "ORDER BY `supplier`.`first_name` DESC, `supplier_return_invoice`.`date` DESC");
            }else if (sort.equals("Pending")) {
                query += "`supplier_return_invoice_status`.`name` = 'Pending' ORDER BY `supplier_return_invoice`.`date` DESC";
            }else if (sort.equals("Approved")){
                query += "`supplier_return_invoice_status`.`name` = 'Approved' ORDER BY `supplier_return_invoice`.`date` DESC";
            }
            
            ResultSet resultSet = MySQL.executeSearch(query);
            DefaultTableModel model = (DefaultTableModel)supRetInvoicesTable.getModel();
            model.setRowCount(0);
            
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("supplier_return_invoice.id"));
                vector.add(resultSet.getString("supplier_return_reason.name"));
                vector.add(resultSet.getString("supplier.first_name") + " " + resultSet.getString("supplier.last_name"));
                vector.add(resultSet.getString("supplier_return_invoice.date"));
                vector.add(resultSet.getString("supplier_return_invoice_status.name"));
                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void resetReturnInvoices() {
        returnInvoiceTable.clearSelection();
        DefaultTableModel model = (DefaultTableModel) returnInvoiceItemTable1.getModel();
        model.setRowCount(0);
    }
    
    private void resetReturnSlip() {
        slipIDField.setText("");
        returnInvoiceIDField1.setText("");
        outletManagerIDField.setText("");
        outletNameLabel.setText("OUTLET NAME");
        dateLabel.setText("DATE TIME");
        outletAddressLabel.setText("OUTLET ADDRESS");
        vehicleNumberLabel.setText("VEHICLE NUMBER");
        sortByComboBox1.setSelectedIndex(0);
    }
    
    private void resetSupplierReturns() {
        generateSupplierReturnInvoiceID();
        returnInvoiceIDField2.setText("");
        reasonComboBox.setSelectedIndex(0);
        DefaultTableModel model = (DefaultTableModel) returnInvoiceItemTable2.getModel();
        model.setRowCount(0);
        fromDateChooser.setDate(null);
        toDateChooser.setDate(null);
        sortByComboBox2.setSelectedIndex(0);
        supRetInvoicesTable.clearSelection();
        loadSupplierReturnInvoices();
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
        returnInvoiceItemTable1 = new javax.swing.JTable();
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
        returnInvoiceIDField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        outletManagerIDField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        outletNameLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        wSupervisorIDField1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        outletAddressLabel = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        vehicleNumberLabel = new javax.swing.JLabel();
        selectInvoiceButton = new javax.swing.JButton();
        resetButton2 = new javax.swing.JButton();
        approveNPrintButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        returnSlipTable = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        sortByComboBox1 = new javax.swing.JComboBox<>();
        viewSlipButton = new javax.swing.JButton();
        uploadImageButton = new javax.swing.JButton();
        closeLabel2 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        closeLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        wSupervisorIDField2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        supRetInvIDField = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        returnInvoiceIDField2 = new javax.swing.JTextField();
        selectRetInvButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        submitButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        returnInvoiceItemTable2 = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        supRetInvoicesTable = new javax.swing.JTable();
        updateButton = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        fromDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel27 = new javax.swing.JLabel();
        toDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel37 = new javax.swing.JLabel();
        sortByComboBox2 = new javax.swing.JComboBox<>();
        findButton = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        reasonComboBox = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        supplierComboBox = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        supplierEmailField = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        returnInvoiceIDField3 = new javax.swing.JTextField();

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(66, 45, 22));
        jLabel2.setText("Return Invoice");

        returnInvoiceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Outlet", "Distributor", "Vehicle No.", "Date", "Reason", "Out. Man. ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        returnInvoiceItemTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Weight", "Quantity", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        returnInvoiceItemTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(returnInvoiceItemTable1);

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
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(viewInvoiceButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(printInvoiceButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resetButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
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

        returnInvoiceIDField1.setEditable(false);
        returnInvoiceIDField1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

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
        jLabel10.setText("Date :");

        dateLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateLabel.setText("DATE TIME");
        dateLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        jLabel12.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel12.setText("W. Supervisor ID :");

        wSupervisorIDField1.setEditable(false);
        wSupervisorIDField1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel13.setText("Address :");

        outletAddressLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        outletAddressLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outletAddressLabel.setText("OUTLET ADDRESS");
        outletAddressLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

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
        approveNPrintButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveNPrintButtonActionPerformed(evt);
            }
        });

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

        sortByComboBox1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        sortByComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Delivery Date DESC", "Delivery Date ASC", "Outlet Name ASC", "Outlet Name DESC", "" }));
        sortByComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sortByComboBox11ItemStateChanged(evt);
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

        jLabel21.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(66, 45, 22));
        jLabel21.setText("Return Slips");

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
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sortByComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 252, Short.MAX_VALUE)
                        .addComponent(uploadImageButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewSlipButton))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(slipIDField)
                                            .addComponent(outletNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel10))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(returnInvoiceIDField1)
                                            .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel7)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(outletAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                        .addComponent(jLabel17)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(outletManagerIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(wSupervisorIDField1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(vehicleNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(outletManagerIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel12)
                            .addComponent(wSupervisorIDField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(slipIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(outletNameLabel))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(returnInvoiceIDField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(dateLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(outletAddressLabel)
                    .addComponent(jLabel17)
                    .addComponent(vehicleNumberLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectInvoiceButton)
                    .addComponent(resetButton2)
                    .addComponent(approveNPrintButton))
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(sortByComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewSlipButton)
                    .addComponent(uploadImageButton))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Return Slips", jPanel3);

        closeLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/close-icon.png"))); // NOI18N
        closeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeLabelMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(66, 45, 22));
        jLabel4.setText("Supplier Returns");

        wSupervisorIDField2.setEditable(false);
        wSupervisorIDField2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel14.setText("W. Supervisor ID :");

        supRetInvIDField.setEditable(false);
        supRetInvIDField.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel16.setText("Sup. Ret. Inv. ID :");

        jLabel18.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel18.setText("Ret. Invoice ID :");

        returnInvoiceIDField2.setEditable(false);
        returnInvoiceIDField2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        selectRetInvButton.setBackground(new java.awt.Color(245, 219, 200));
        selectRetInvButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        selectRetInvButton.setForeground(new java.awt.Color(0, 0, 0));
        selectRetInvButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/select-icon.png"))); // NOI18N
        selectRetInvButton.setText("Select Return Invoice");
        selectRetInvButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectRetInvButtonActionPerformed(evt);
            }
        });

        resetButton.setBackground(new java.awt.Color(245, 219, 200));
        resetButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        resetButton.setForeground(new java.awt.Color(0, 0, 0));
        resetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset-icon.png"))); // NOI18N
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        submitButton.setBackground(new java.awt.Color(245, 219, 200));
        submitButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        submitButton.setForeground(new java.awt.Color(0, 0, 0));
        submitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/submit-icon.png"))); // NOI18N
        submitButton.setText("Submit Request");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        returnInvoiceItemTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Weight", "Qty", "Price", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        returnInvoiceItemTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(returnInvoiceItemTable2);

        jLabel20.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(66, 45, 22));
        jLabel20.setText("Supplier Return Invoices");

        supRetInvoicesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Reason", "Supplier", "Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        supRetInvoicesTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(supRetInvoicesTable);

        updateButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/update-icon.png"))); // NOI18N
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel23.setText("From");

        fromDateChooser.setDateFormatString("yyyy-MM-dd");

        jLabel27.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel27.setText("To");

        toDateChooser.setDateFormatString("yyyy-MM-dd");

        jLabel37.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel37.setText("Sort By :");

        sortByComboBox2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        sortByComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Date DESC", "Date ASC", "Reason ASC", "Reason DESC", "Supplier ASC", "Supplier DESC", "Pending", "Approved" }));

        findButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        findButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/search-icon.png"))); // NOI18N
        findButton.setText("Find");
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findButtonActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel22.setText("Reason :");

        reasonComboBox.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        reasonComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel24.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel24.setText("Supplier :");

        supplierComboBox.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        supplierComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel25.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel25.setText("Sup. Email :");

        supplierEmailField.setEditable(false);
        supplierEmailField.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel26.setText("Total Amount :");

        returnInvoiceIDField3.setEditable(false);
        returnInvoiceIDField3.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        returnInvoiceIDField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(supRetInvIDField, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(wSupervisorIDField2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(returnInvoiceIDField2)
                            .addComponent(reasonComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(selectRetInvButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(submitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(updateButton)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fromDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sortByComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(findButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(supplierComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(supplierEmailField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(returnInvoiceIDField3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeLabel)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(wSupervisorIDField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(returnInvoiceIDField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectRetInvButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(supRetInvIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(reasonComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submitButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(supplierComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(supplierEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(returnInvoiceIDField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateButton)
                            .addComponent(jLabel23)
                            .addComponent(jLabel27))
                        .addComponent(fromDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(toDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(findButton)
                        .addComponent(jLabel37)
                        .addComponent(sortByComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Supplier Returns", jPanel1);

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
                    + "ON `return_invoice_items`.`return_invoice_id` = `return_invoice`.`id` INNER JOIN `stock` "
                    + "ON `return_invoice_items`.`stock_id` = `stock`.`id` INNER JOIN `product` "
                    + "ON `stock`.`product_id` = `product`.`id` INNER JOIN `grn_items` "
                    + "ON `stock`.`id` = `grn_items`.`stock_id` "
                    + "WHERE `return_invoice_items`.`return_invoice_id` = '"+String.valueOf(returnInvoiceTable.getValueAt(row, 0))+"'");
            
            DefaultTableModel model = (DefaultTableModel) returnInvoiceItemTable1.getModel();
            model.setRowCount(0);
            
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("product.id"));
                vector.add(resultSet.getString("product.name"));
                vector.add(resultSet.getString("product.weight"));
                vector.add(resultSet.getString("return_invoice_items.qty"));
                vector.add(resultSet.getString("grn_items.price"));
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
        resetReturnSlip();
    }//GEN-LAST:event_resetButton2ActionPerformed

    private void sortByComboBox11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sortByComboBox11ItemStateChanged
        loadReturnSlips();
    }//GEN-LAST:event_sortByComboBox11ItemStateChanged

    private void closeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseClicked
        this.home.removeInventory();
    }//GEN-LAST:event_closeLabelMouseClicked

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        int row = supRetInvoicesTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select an invoice to update", "Warning",
                JOptionPane.WARNING_MESSAGE);
        }else {
            try {
                int option = JOptionPane.showConfirmDialog(this, "Confirm return request status update?", "Message",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (option == JOptionPane.YES_OPTION) {
                    if (supRetInvoicesTable.getValueAt(row, 4).equals("Approved")) {
                        JOptionPane.showMessageDialog(this, "Status is already updated to approved", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                        supRetInvoicesTable.clearSelection();
                    }else {
                        MySQL.executeIUD("UPDATE `return_invoice` SET `return_invoice_status_id` = 2 "
                            + "WHERE `id` = '"+String.valueOf(supRetInvoicesTable.getValueAt(row, 0))+"'");
                        loadSupplierReturnInvoices();
                        supRetInvoicesTable.clearSelection();
                        JOptionPane.showMessageDialog(this, "Status updated successfully", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void findButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findButtonActionPerformed
        loadSupplierReturnInvoices();
    }//GEN-LAST:event_findButtonActionPerformed

    private void selectRetInvButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectRetInvButtonActionPerformed
        WSelectReturnInvoice selectReturnInvoice = new WSelectReturnInvoice((Frame) SwingUtilities.getWindowAncestor(this), true, this);
        selectReturnInvoice.setVisible(true);
    }//GEN-LAST:event_selectRetInvButtonActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        try {
            int rowCount = returnInvoiceItemTable2.getRowCount();
            
            if (reasonComboBox.getSelectedItem().equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select a return reason", "Warning", JOptionPane.WARNING_MESSAGE);
            }else if (rowCount == 0) {
                JOptionPane.showMessageDialog(this, "Please select a return invoice", "Warning", JOptionPane.WARNING_MESSAGE);
            }else {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `supplier_return_invoice` "
                    + "WHERE `id` = '"+supRetInvIDField.getText()+"'");
            
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This return invoice already exists", "Warning", 
                            JOptionPane.WARNING_MESSAGE);
                }else {
                    String warehouseSupervisorID = wSupervisorIDField2.getText();
                    String supplierReturnInvoiceID = supRetInvIDField.getText();
                    String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    String supplierEmail = supplierEmailField.getText();
                    String reason = String.valueOf(reasonComboBox.getSelectedItem());
                    
                    MySQL.executeIUD("INSERT INTO `supplier_return_invoice` "
                            + "VALUES('"+supplierReturnInvoiceID+"', '"+dateTime+"', '"+suppliersMap.get(supplierEmail)+"', "
                                    + "'"+supplierReturnReasonMap.get(reason)+"', 1, '"+warehouseSupervisorID+"')");
                    
                    for (SupplierReturnItem supReturnItem : supplierReturnItemMap.values()) {
                        MySQL.executeIUD("INSERT INTO `supplier_return_invoice_item`(`qty`, `supplier_return_invoice_id`, "
                                + "`w_stock_id`) VALUES('"+supReturnItem.getQty()+"', '"+supplierReturnInvoiceID+"', "
                                        + "'"+supReturnItem.getStockID()+"')");
                        
                        MySQL.executeIUD("INSERT INTO `returning_stock`(`w_stock_id`, `qty`, `returning_stock_status_id`) "
                                + "VALUES('"+supReturnItem.getStockID()+"', '"+supReturnItem.getQty()+"', 1)");
                    }
                    
                    InputStream path = this.getClass().getResourceAsStream("/reports/gh_return_invoice.jasper");
                    HashMap<String, Object> parameters = new HashMap<>();
                    parameters.put("Parameter1", wSupervisorIDField2.getText());
                    parameters.put("Parameter2", supRetInvIDField.getText());
                    parameters.put("Parameter3", supplierComboBox.getSelectedItem());
                    parameters.put("Parameter4", dateTime);
                    parameters.put("Parameter5", reasonComboBox.getSelectedItem());
                    
                    String appDir = new File("").getAbsolutePath(); // Get the application's directory
                    String reportsFolder = appDir + File.separator + "ExportedReports"; // Main folder path
                    // Create the main "ExportedReports" folder if it doesn't exist
                    File mainDirectory = new File(reportsFolder);
                    if (!mainDirectory.exists()) {
                        mainDirectory.mkdirs();
                    }
                    // Create subfolder for "Supplier Return Invoice Reports" if it doesn't exist
                    String retInvoiceReportsFolder = reportsFolder + File.separator + "Supplier Return Invoice Reports";
                    File retInvoiceDirectory = new File(retInvoiceReportsFolder);
                    if (!retInvoiceDirectory.exists()) {
                        retInvoiceDirectory.mkdirs();
                    }
                    // Path to export the PDF file (inside "Supplier Return Invoice Reports" subfolder)
                    String outputPath = retInvoiceReportsFolder + File.separator + "Supplier_Return_invoice_report_" + supplierReturnInvoiceID + ".pdf";

                    JRTableModelDataSource dataSource = new JRTableModelDataSource(returnInvoiceItemTable2.getModel());
                    JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);
                    JasperViewer.viewReport(report, false);
                    JasperPrintManager.printReport(report, false);
                    JasperExportManager.exportReportToPdfFile(report, outputPath);
                    resetSupplierReturns();
                    
                    GmailOAuthService service = new GmailOAuthService();
                    //String recipient = supplierEmailLabel.getText();
                    String recipient = "gamergangster866@gmail.com";
                    String subject = "Report Attached";
                    String bodyText = "Please find the attached PDF report.";
                    String pdfFileName = "Supplier_Return_invoice_report_" + supplierReturnInvoiceID + ".pdf";
                    service.sendEmailWithAttachment(recipient, subject, bodyText, pdfFileName);
                    
                    JOptionPane.showMessageDialog(this, "Return request submitted successfully", "Success", 
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        resetSupplierReturns();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void approveNPrintButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveNPrintButtonActionPerformed
        try {
            if (slipIDField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please select a return slip", "Warning", JOptionPane.WARNING_MESSAGE);
            }else {
                String returnSlipID = slipIDField.getText();
                
                MySQL.executeIUD("UPDATE `return_invoice` SET `return_invoice_status_id` = 2 "
                        + "WHERE `id` = '"+returnInvoiceIDField1.getText()+"'");
                
                MySQL.executeIUD("UPDATE `return_slip` SET `w_supervisor_id` = '"+wSupervisorIDField1.getText()+"', "
                        + "`return_slip_status_id` = 2 WHERE `id` = '"+returnSlipID+"'");
                
                InputStream path = this.getClass().getResourceAsStream("/reports/gh_return_invoice.jasper");
                HashMap<String, Object> parameters = new HashMap<>();
                //parameters.put("Parameter1", wSupervisorIDField2.getText());
                //parameters.put("Parameter2", supRetInvIDField.getText());
                //parameters.put("Parameter3", supplierComboBox.getSelectedItem());
                //parameters.put("Parameter4", dateTime);
                //parameters.put("Parameter5", reasonComboBox.getSelectedItem());

                String appDir = new File("").getAbsolutePath(); // Get the application's directory
                String reportsFolder = appDir + File.separator + "ExportedReports"; // Main folder path
                // Create the main "ExportedReports" folder if it doesn't exist
                File mainDirectory = new File(reportsFolder);
                if (!mainDirectory.exists()) {
                    mainDirectory.mkdirs();
                }
                // Create subfolder for "Approved Return Slip Reports" if it doesn't exist
                String retInvoiceReportsFolder = reportsFolder + File.separator + "Approved Return Slip Reports";
                File retInvoiceDirectory = new File(retInvoiceReportsFolder);
                if (!retInvoiceDirectory.exists()) {
                    retInvoiceDirectory.mkdirs();
                }
                // Path to export the PDF file (inside "Approved Return Slip Reports" subfolder)
                String outputPath = retInvoiceReportsFolder + File.separator + "Approved_Return_Slip_report_" + returnSlipID + ".pdf";

                //JRTableModelDataSource dataSource = new JRTableModelDataSource(returnInvoiceItemTable.getModel());
                //JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);
                //JasperViewer.viewReport(report, false);
                //JasperPrintManager.printReport(report, false);
                //JasperExportManager.exportReportToPdfFile(report, outputPath);
                resetReturnSlip();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_approveNPrintButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton approveNPrintButton;
    private javax.swing.JLabel closeLabel;
    private javax.swing.JLabel closeLabel1;
    private javax.swing.JLabel closeLabel2;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JButton findButton;
    private com.toedter.calendar.JDateChooser fromDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel outletAddressLabel;
    private javax.swing.JTextField outletManagerIDField;
    private javax.swing.JLabel outletNameLabel;
    private javax.swing.JButton printInvoiceButton;
    private javax.swing.JComboBox<String> reasonComboBox;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton resetButton1;
    private javax.swing.JButton resetButton2;
    private javax.swing.JTextField returnInvoiceIDField1;
    private javax.swing.JTextField returnInvoiceIDField2;
    private javax.swing.JTextField returnInvoiceIDField3;
    private javax.swing.JTable returnInvoiceItemTable1;
    private javax.swing.JTable returnInvoiceItemTable2;
    private javax.swing.JTable returnInvoiceTable;
    private javax.swing.JTable returnSlipTable;
    private javax.swing.JButton selectInvoiceButton;
    private javax.swing.JButton selectRetInvButton;
    private javax.swing.JTextField slipIDField;
    private javax.swing.JComboBox<String> sortByComboBox1;
    private javax.swing.JComboBox<String> sortByComboBox2;
    private javax.swing.JButton submitButton;
    private javax.swing.JTextField supRetInvIDField;
    private javax.swing.JTable supRetInvoicesTable;
    private javax.swing.JComboBox<String> supplierComboBox;
    private javax.swing.JTextField supplierEmailField;
    private com.toedter.calendar.JDateChooser toDateChooser;
    private javax.swing.JButton updateButton;
    private javax.swing.JButton uploadImageButton;
    private javax.swing.JLabel vehicleNumberLabel;
    private javax.swing.JButton viewInvoiceButton;
    private javax.swing.JButton viewSlipButton;
    private javax.swing.JTextField wSupervisorIDField1;
    private javax.swing.JTextField wSupervisorIDField2;
    // End of variables declaration//GEN-END:variables
}
