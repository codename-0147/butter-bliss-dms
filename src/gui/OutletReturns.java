/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.Frame;
import java.io.File;
import java.io.InputStream;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import model.MySQL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.WarehouseReturnItem;
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
public class OutletReturns extends javax.swing.JPanel {
    private HomeOU home;
    HashMap<String, String> distributorsMap = new HashMap<>();
    HashMap<String, String> returnReasonsMap = new HashMap<>();
    HashMap<String, WarehouseReturnItem> warehouseReturnItemMap = new HashMap<>();

    /**
     * Creates new form OutletReturns
     */
    public OutletReturns(HomeOU home) {
        initComponents();
        this.home = home;
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        returnInvoiceItemTable.setDefaultRenderer(Object.class, renderer);
        generateReturnInvoiceID();
        generateReturnSlipID();
        loadDistributors();
        loadReturnReasons();
    }
    
    private void generateReturnInvoiceID() {
        long id = System.currentTimeMillis();
        returnInvoiceIDField1.setText(String.valueOf(id));
    }
    
    private void generateReturnSlipID() {
        long id = System.currentTimeMillis();
        returnSlipIDField.setText(String.valueOf(id));
    }
    
    public JLabel getstockIDLabel() {
        return stockIDLabel;
    }

    public JLabel getproductWeightLabel() {
        return productWeightLabel;
    }
    
    public JComboBox getdistributorComboBox() {
        return distributorComboBox;
    }
    
    public JLabel getproductIDLabel() {
        return productIDLabel;
    }
    
    public JLabel getbuyingPriceLabel() {
        return buyingPriceLabel;
    }
    
    public JLabel getproductNameLabel() {
        return productNameLabel;
    }
    
    public JTextField getquantityField() {
        return quantityField;
    }
    
    public JTextField getreturnInvoiceIDField2() {
        return returnInvoiceIDField2;
    }
    
    public JLabel getoutletNameLabel() {
        return outletNameLabel;
    }
    
    public JLabel getdateLabel() {
        return dateLabel;
    }
    
    public JTextField getoutletManagerIDField2() {
        return outletManagerIDField2;
    }
    
    public JLabel getoutletAddressLabel() {
        return outletAddressLabel;
    }
    
    public JLabel getvehicleNumberLabel2() {
        return vehicleNumberLabel2;
    }
    
    private double total = 0;
    
    private void loadDistributors() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `distributor`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            
            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                distributorsMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }
            
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            distributorComboBox.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadReturnReasons() {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `reason`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            
            while (resultSet.next()) {
                vector.add(resultSet.getString("reason"));
                returnReasonsMap.put(resultSet.getString("reason"), resultSet.getString("id"));
            }
            
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            reasonComboBox.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadWarehouseReturnItems() {
        DefaultTableModel model = (DefaultTableModel) returnInvoiceItemTable.getModel();
        model.setRowCount(0);
        total = 0;
        
        for (WarehouseReturnItem wReturnItem : warehouseReturnItemMap.values()) {
            Vector<String> vector = new Vector<>();
            vector.add(wReturnItem.getProductID());
            vector.add(wReturnItem.getProductName());
            vector.add(wReturnItem.getBuyingPrice());
            vector.add(wReturnItem.getQty());
            double itemTotal = Double.parseDouble(wReturnItem.getQty()) * Double.parseDouble(wReturnItem.getBuyingPrice());
            total += itemTotal;
            vector.add(String.valueOf(itemTotal));
            model.addRow(vector);
        }
        
        totalAmountLabel.setText(String.valueOf(total));
    }
    
    private void resetReturnInvoice() {
        generateReturnInvoiceID();
        returnInvoiceIDField1.setText("");
        stockIDLabel.setText("STOCK ID");
        productWeightLabel.setText("PRODUCT WEIGHT");
        distributorComboBox.setSelectedIndex(0);
        productIDLabel.setText("PRODUCT ID");
        buyingPriceLabel.setText("BUYING PRICE");
        reasonComboBox.setSelectedIndex(0);
        productNameLabel.setText("PRODUCT NAME");
        quantityField.setText("");
        DefaultTableModel model = (DefaultTableModel) returnInvoiceItemTable.getModel();
        model.setRowCount(0);
        totalAmountLabel.setText("TOTAL AMOUNT");
    }
    
    private void resetReturnSlip() {
        generateReturnSlipID();
        returnSlipIDField.setText("");
        outletNameLabel.setText("OUTLET NAME");
        dateLabel.setText("DATE TIME");
        outletManagerIDField2.setText("");
        outletAddressLabel.setText("OUTLET ADDRESS");
        vehicleNumberLabel2.setText("VEHICLE NUMBER");
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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        closeLabel1 = new javax.swing.JLabel();
        outletManagerIDField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        returnInvoiceIDField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        vehicleNumberLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        reasonComboBox = new javax.swing.JComboBox<>();
        selectStockButton = new javax.swing.JButton();
        stockIDLabel = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        productIDLabel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        productNameLabel = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        productWeightLabel = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        buyingPriceLabel = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        quantityField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        saveNPrintButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        returnInvoiceItemTable = new javax.swing.JTable();
        resetButton1 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        totalAmountLabel = new javax.swing.JLabel();
        distributorComboBox = new javax.swing.JComboBox<>();
        outletIDLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        closeLabel2 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        returnSlipIDField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        returnInvoiceIDField2 = new javax.swing.JTextField();
        selectReturnInvoiceButton = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        outletNameLabel = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        outletAddressLabel = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        outletManagerIDField2 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        vehicleNumberLabel2 = new javax.swing.JLabel();
        saveNPrintButton2 = new javax.swing.JButton();
        resetButton2 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        returnInvoiceItemTable1 = new javax.swing.JTable();
        uploadImageButton = new javax.swing.JButton();
        viewSlipButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(66, 45, 22));
        jLabel2.setText("Return Invoice");

        closeLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/close-icon.png"))); // NOI18N
        closeLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeLabel1MouseClicked(evt);
            }
        });

        outletManagerIDField1.setEditable(false);
        outletManagerIDField1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel7.setText("Outlet Manager ID :");

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel8.setText("Return Invoice ID :");

        returnInvoiceIDField1.setEditable(false);
        returnInvoiceIDField1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel9.setText("Outlet :");

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel10.setText("Distributor :");

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel11.setText("Vehicle No :");

        vehicleNumberLabel1.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        vehicleNumberLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vehicleNumberLabel1.setText("VEHICLE NUMBER");
        vehicleNumberLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        jLabel12.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel12.setText("Reason :");

        reasonComboBox.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        reasonComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        selectStockButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        selectStockButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/select-icon.png"))); // NOI18N
        selectStockButton.setText("Select Stock");
        selectStockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectStockButtonActionPerformed(evt);
            }
        });

        stockIDLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        stockIDLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stockIDLabel.setText("STOCK ID");
        stockIDLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel13.setText("Prod. ID :");

        productIDLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        productIDLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productIDLabel.setText("PRODUCT ID");
        productIDLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        jLabel14.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel14.setText("Prod. Name :");

        productNameLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        productNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productNameLabel.setText("PRODUCT NAME");
        productNameLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        jLabel15.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel15.setText("Prod. Weight :");

        productWeightLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        productWeightLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productWeightLabel.setText("PRODUCT WEIGHT");
        productWeightLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        jLabel16.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel16.setText("Buying Price :");

        buyingPriceLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        buyingPriceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buyingPriceLabel.setText("BUYING PRICE");
        buyingPriceLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        jLabel17.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel17.setText("Quantity :");

        quantityField.setEditable(false);
        quantityField.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        addButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add-icon.png"))); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        saveNPrintButton1.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        saveNPrintButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/print-icon.png"))); // NOI18N
        saveNPrintButton1.setText("Save & Print");
        saveNPrintButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveNPrintButton1ActionPerformed(evt);
            }
        });

        returnInvoiceItemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Product Weight", "Quantity", "Buying Price", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        returnInvoiceItemTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(returnInvoiceItemTable);

        resetButton1.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        resetButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset-icon.png"))); // NOI18N
        resetButton1.setText("Reset");
        resetButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButton1ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel25.setText("Total Amount :");

        totalAmountLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        totalAmountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalAmountLabel.setText("TOTAL AMOUNT");
        totalAmountLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        distributorComboBox.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        distributorComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        distributorComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                distributorComboBoxItemStateChanged(evt);
            }
        });

        outletIDLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        outletIDLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outletIDLabel.setText("OUTLET ID");
        outletIDLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(productWeightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(selectStockButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stockIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(outletManagerIDField1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(returnInvoiceIDField1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buyingPriceLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(productIDLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(distributorComboBox, 0, 140, Short.MAX_VALUE)
                            .addComponent(outletIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(productNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(reasonComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(vehicleNumberLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(saveNPrintButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(addButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resetButton1)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(outletManagerIDField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(vehicleNumberLabel1)
                    .addComponent(distributorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(returnInvoiceIDField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(reasonComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(outletIDLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectStockButton)
                    .addComponent(stockIDLabel)
                    .addComponent(jLabel13)
                    .addComponent(productIDLabel)
                    .addComponent(jLabel14)
                    .addComponent(productNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(productWeightLabel)
                    .addComponent(jLabel16)
                    .addComponent(buyingPriceLabel)
                    .addComponent(jLabel17)
                    .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(resetButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(totalAmountLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveNPrintButton1)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Return Invoice", jPanel1);

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(66, 45, 22));
        jLabel3.setText("Return Slip");

        closeLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/close-icon.png"))); // NOI18N
        closeLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeLabel2MouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel18.setText("Slip ID :");

        returnSlipIDField.setEditable(false);
        returnSlipIDField.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel19.setText("Ret. Inv. ID :");

        returnInvoiceIDField2.setEditable(false);
        returnInvoiceIDField2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        selectReturnInvoiceButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        selectReturnInvoiceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/select-icon.png"))); // NOI18N
        selectReturnInvoiceButton.setText("Select Ret. Invoice");
        selectReturnInvoiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectReturnInvoiceButtonActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel20.setText("Outlet :");

        outletNameLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        outletNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outletNameLabel.setText("OUTLET NAME");
        outletNameLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        jLabel21.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel21.setText("Address :");

        outletAddressLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        outletAddressLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outletAddressLabel.setText("OUTLET ADDRESS");
        outletAddressLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        jLabel22.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel22.setText("Date :");

        dateLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateLabel.setText("DATE TIME");
        dateLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        outletManagerIDField2.setEditable(false);
        outletManagerIDField2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel23.setText("Outlet Manager ID :");

        jLabel24.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel24.setText("Vehicle Number :");

        vehicleNumberLabel2.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        vehicleNumberLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vehicleNumberLabel2.setText("VEHICLE NUMBER");
        vehicleNumberLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 171, 77)));

        saveNPrintButton2.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        saveNPrintButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/print-icon.png"))); // NOI18N
        saveNPrintButton2.setText("Save & Print");
        saveNPrintButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveNPrintButton2ActionPerformed(evt);
            }
        });

        resetButton2.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        resetButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reset-icon.png"))); // NOI18N
        resetButton2.setText("Reset");
        resetButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButton2ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(66, 45, 22));
        jLabel26.setText("Return Slips");

        returnInvoiceItemTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Slip ID", "Ret. Inv. ID", "Outlet Name", "Date", "Slip Image"
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

        uploadImageButton.setBackground(new java.awt.Color(245, 219, 200));
        uploadImageButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        uploadImageButton.setForeground(new java.awt.Color(0, 0, 0));
        uploadImageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/upload-icon.png"))); // NOI18N
        uploadImageButton.setText("Upload Image");

        viewSlipButton.setBackground(new java.awt.Color(245, 219, 200));
        viewSlipButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        viewSlipButton.setForeground(new java.awt.Color(0, 0, 0));
        viewSlipButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/view-icon.png"))); // NOI18N
        viewSlipButton.setText("View");

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel1.setText("Sort By :");

        jComboBox4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Date DESC", "Date ASC", "Outlet Name DESC", "Outlet Name ASC" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel18)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(returnSlipIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel19)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(returnInvoiceIDField2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(selectReturnInvoiceButton))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel22))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(outletNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(outletAddressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(vehicleNumberLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(outletManagerIDField2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(saveNPrintButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resetButton2))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(uploadImageButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewSlipButton)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(returnSlipIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20)
                    .addComponent(outletNameLabel)
                    .addComponent(jLabel21)
                    .addComponent(outletAddressLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(returnInvoiceIDField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(dateLabel)
                    .addComponent(jLabel24)
                    .addComponent(vehicleNumberLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectReturnInvoiceButton)
                    .addComponent(jLabel23)
                    .addComponent(outletManagerIDField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveNPrintButton2)
                    .addComponent(resetButton2))
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewSlipButton)
                    .addComponent(uploadImageButton)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Return Slip", jPanel2);

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

    private void selectStockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectStockButtonActionPerformed
        if (distributorComboBox.getSelectedItem().equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a distributor", "Warning", JOptionPane.WARNING_MESSAGE);
        }else {
            SelectReturnStock selectStock = new SelectReturnStock((Frame) SwingUtilities.getWindowAncestor(this), true, this);
            selectStock.setVisible(true);
        }
    }//GEN-LAST:event_selectStockButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        try {
            String stockID = stockIDLabel.getText();
            String productID = productIDLabel.getText();
            String productName = productNameLabel.getText();
            String productWeight = productWeightLabel.getText();
            String buyingPrice = buyingPriceLabel.getText();
            String qty = quantityField.getText();
            
            if (stockID.equals("STOCK ID")) {
                JOptionPane.showMessageDialog(this, "Please select a stock", "Warning", JOptionPane.WARNING_MESSAGE);
            }else {
                WarehouseReturnItem wReturnItem = new WarehouseReturnItem();
                wReturnItem.setStockID(stockID);
                wReturnItem.setProductID(productID);
                wReturnItem.setProductName(productName);
                wReturnItem.setProductWeight(productWeight);
                wReturnItem.setBuyingPrice(buyingPrice);
                wReturnItem.setQty(qty);
                
                if (warehouseReturnItemMap.get(stockID) == null) {
                    warehouseReturnItemMap.put(stockID, wReturnItem);
                }
                loadWarehouseReturnItems();
            }
            
            stockIDLabel.setText("STOCK ID");
            productWeightLabel.setText("PRODUCT WEIGHT");
            productIDLabel.setText("PRODUCT ID");
            buyingPriceLabel.setText("BUYING PRICE");
            productNameLabel.setText("PRODUCT NAME");
            quantityField.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void saveNPrintButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveNPrintButton1ActionPerformed
        try {
            int rowCount = returnInvoiceItemTable.getRowCount();
            
            if (reasonComboBox.getSelectedItem().equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select a return reason", "Warning", JOptionPane.WARNING_MESSAGE);
            }else if (rowCount == 0) {
                JOptionPane.showMessageDialog(this, "Please add item(s) to return invoice", 
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }else {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `return_invoice` "
                        + "WHERE `id` = '"+returnInvoiceIDField1.getText()+"'");
                
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This return invoice already exists", 
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }else {
                    String returnInvoiceID = returnInvoiceIDField1.getText();
                    String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    String outletID = outletIDLabel.getText();
                    String distributorName = String.valueOf(distributorComboBox.getSelectedItem());
                    String returnReason = String.valueOf(reasonComboBox.getSelectedItem());
                    
                    MySQL.executeIUD("INSERT INTO `return_invoice` VALUES('"+returnInvoiceID+"', '"+dateTime+"', "
                            + "'"+outletID+"', '"+distributorsMap.get(distributorName)+"', "
                                    + "'"+returnReasonsMap.get(returnReason)+"', 1,'"+totalAmountLabel.getText()+"')");
                    
                    for (WarehouseReturnItem wReturnItem : warehouseReturnItemMap.values()) {
                        MySQL.executeIUD("INSERT INTO `return_invoice_items`(`qty`, `return_invoice_id`, `stock_id`) "
                                + "VALUES('"+wReturnItem.getQty()+"', '"+returnInvoiceID+"', "
                                        + "'"+wReturnItem.getStockID()+"')");
                        
                        MySQL.executeIUD("UPDATE `stock` SET `qty` = `qty`-'"+wReturnItem.getQty()+"' "
                                + "WHERE `id` = '"+wReturnItem.getStockID()+"'");
                    }
                    
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
                    // Create subfolder for "Return Invoice Reports" if it doesn't exist
                    String retInvoiceReportsFolder = reportsFolder + File.separator + "Return Invoice Reports";
                    File retInvoiceDirectory = new File(retInvoiceReportsFolder);
                    if (!retInvoiceDirectory.exists()) {
                        retInvoiceDirectory.mkdirs();
                    }
                    // Path to export the PDF file (inside "Return Invoice Reports" subfolder)
                    String outputPath = retInvoiceReportsFolder + File.separator + "Return_invoice_report_" + returnInvoiceID + ".pdf";

                    JRTableModelDataSource dataSource = new JRTableModelDataSource(returnInvoiceItemTable.getModel());
                    JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);
                    JasperViewer.viewReport(report, false);
                    JasperPrintManager.printReport(report, false);
                    JasperExportManager.exportReportToPdfFile(report, outputPath);
                    resetReturnInvoice();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_saveNPrintButton1ActionPerformed

    private void distributorComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_distributorComboBoxItemStateChanged
        try {
            if (distributorComboBox.getSelectedItem().equals("Select")) {
                vehicleNumberLabel1.setText("VEHICLE NUMBER");
            }else {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `distributor` "
                        + "WHERE `name` = '"+distributorComboBox.getSelectedItem()+"'");
                
                if (resultSet.next()) {
                    vehicleNumberLabel1.setText(resultSet.getString("vehicle_no"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_distributorComboBoxItemStateChanged

    private void resetButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButton1ActionPerformed
        resetReturnInvoice();
    }//GEN-LAST:event_resetButton1ActionPerformed

    private void selectReturnInvoiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectReturnInvoiceButtonActionPerformed
        SelectReturnInvoice selectReturnInvoice = new SelectReturnInvoice((Frame) SwingUtilities.getWindowAncestor(this), true, this);
        selectReturnInvoice.setVisible(true);
    }//GEN-LAST:event_selectReturnInvoiceButtonActionPerformed

    private void saveNPrintButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveNPrintButton2ActionPerformed
        try {
            if (returnInvoiceIDField2.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please select a return invoice", "Warning", JOptionPane.WARNING_MESSAGE);
            }else {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `return_slip` "
                        + "WHERE `id` = '"+returnSlipIDField.getText()+"'");
                
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This return_slip already exists", 
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }else {
                    String returnSlipID = returnSlipIDField.getText();
                    String returnInvoiceID = returnInvoiceIDField2.getText();
                    String outletManagerID = outletManagerIDField2.getText();
                    
                    MySQL.executeIUD("INSERT INTO `return_slip`(`id`, `return_invoice_id`, `outlet_manager_id`, "
                            + "`return_slip_status_id`) VALUES('"+returnSlipID+"', '"+returnInvoiceID+"', "
                                    + "'"+outletManagerID+"', 1)");
                    
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
                    // Create subfolder for "Pending Return Slip Reports" if it doesn't exist
                    String retInvoiceReportsFolder = reportsFolder + File.separator + "Pending Return Slip Reports";
                    File retInvoiceDirectory = new File(retInvoiceReportsFolder);
                    if (!retInvoiceDirectory.exists()) {
                        retInvoiceDirectory.mkdirs();
                    }
                    // Path to export the PDF file (inside "Pending Return Slip Reports" subfolder)
                    String outputPath = retInvoiceReportsFolder + File.separator + "Pending_Return_Slip_report_" + returnSlipID + ".pdf";

                    JRTableModelDataSource dataSource = new JRTableModelDataSource(returnInvoiceItemTable.getModel());
                    JasperPrint report = JasperFillManager.fillReport(path, parameters, dataSource);
                    JasperViewer.viewReport(report, false);
                    JasperPrintManager.printReport(report, false);
                    JasperExportManager.exportReportToPdfFile(report, outputPath);
                    resetReturnSlip();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_saveNPrintButton2ActionPerformed

    private void resetButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButton2ActionPerformed
        resetReturnSlip();
    }//GEN-LAST:event_resetButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JLabel buyingPriceLabel;
    private javax.swing.JLabel closeLabel1;
    private javax.swing.JLabel closeLabel2;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JComboBox<String> distributorComboBox;
    private javax.swing.JComboBox<String> jComboBox4;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel outletAddressLabel;
    private javax.swing.JLabel outletIDLabel;
    private javax.swing.JTextField outletManagerIDField1;
    private javax.swing.JTextField outletManagerIDField2;
    private javax.swing.JLabel outletNameLabel;
    private javax.swing.JLabel productIDLabel;
    private javax.swing.JLabel productNameLabel;
    private javax.swing.JLabel productWeightLabel;
    private javax.swing.JTextField quantityField;
    private javax.swing.JComboBox<String> reasonComboBox;
    private javax.swing.JButton resetButton1;
    private javax.swing.JButton resetButton2;
    private javax.swing.JTextField returnInvoiceIDField1;
    private javax.swing.JTextField returnInvoiceIDField2;
    private javax.swing.JTable returnInvoiceItemTable;
    private javax.swing.JTable returnInvoiceItemTable1;
    private javax.swing.JTextField returnSlipIDField;
    private javax.swing.JButton saveNPrintButton1;
    private javax.swing.JButton saveNPrintButton2;
    private javax.swing.JButton selectReturnInvoiceButton;
    private javax.swing.JButton selectStockButton;
    private javax.swing.JLabel stockIDLabel;
    private javax.swing.JLabel totalAmountLabel;
    private javax.swing.JButton uploadImageButton;
    private javax.swing.JLabel vehicleNumberLabel1;
    private javax.swing.JLabel vehicleNumberLabel2;
    private javax.swing.JButton viewSlipButton;
    // End of variables declaration//GEN-END:variables
}
