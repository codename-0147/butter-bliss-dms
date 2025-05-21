/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.InvoiceItemOU;
import model.MySQL;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import static org.slf4j.helpers.Util.report;


/**
 *
 * @author chath
 */
public class InvoiceOU extends javax.swing.JPanel {

    private HomeOU home;

    HashMap<String, String> customerMap = new HashMap<>();
    HashMap<String, InvoiceItemOU> invoiceItemMap = new HashMap<>();
    HashMap<String, String> paymentMethodMap = new HashMap<>();

    private void loadPaymentMethods() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `outlet_payment_method`");

            Vector<String> vector = new Vector<>();
            vector.add("Select Payment Method");

            while (resultSet.next()) {
                vector.add(resultSet.getString("type"));
                paymentMethodMap.put(resultSet.getString("type"), resultSet.getString("id"));
            }

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadInvoiceItems() {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        total = 0;

        for (InvoiceItemOU invoiceItem : invoiceItemMap.values()) {
            Vector<String> vector = new Vector<>();
            vector.add(invoiceItem.getStockId());
            vector.add(invoiceItem.getCategory());
            vector.add(invoiceItem.getName());
            vector.add(invoiceItem.getQty());
            vector.add(invoiceItem.getSellingPrice());
            vector.add(invoiceItem.getMfd());
            vector.add(invoiceItem.getExp());

            double itemTotal = Double.parseDouble(invoiceItem.getQty()) * Double.parseDouble(invoiceItem.getSellingPrice());
            total += itemTotal;
            vector.add(String.valueOf(itemTotal));

            model.addRow(vector);
        }

        totalField.setText(String.valueOf(total));

        calculate();
    }

    /**
     * Creates new form Invoice
     */
    //stock id
    public void setStockId(String stockId) {
        jTextField3.setText(stockId);
    }
    
    //barcode
    public void setBarcode(String barcode) {
        stockBarcodeField.setText(barcode);
    }

    // Category Name
    public void setCategory(String category) {
        jLabel23.setText(category);
    }

    // Product Name
    public void setName(String name) {
        jLabel19.setText(name);
    }

    //Selling Price 
    public void setSellingPrice(String sellingPrice) {
        jTextField5.setText(sellingPrice);
    }

    // MFD
    public void setMfd(String mfd) {
        jLabel17.setText(mfd);
    }

    // EXP
    public void setExp(String exp) {
        jLabel22.setText(exp);
    }

    //QTY
    public JFormattedTextField getjFormattedTextField1() {
        return jFormattedTextField1;
    }

    // Available Qty
    public JLabel getjLabel21() {
        return jLabel21;
    }

    public InvoiceOU(HomeOU home) {
        initComponents();
        this.home = home;
        loadCustomer();
        generateInvoiceID();
        loadPaymentMethods();
    }

    private void generateInvoiceID() {
        long id = System.currentTimeMillis();
        invoiceNumberField.setText(String.valueOf(id));
    }

    private void loadCustomer() {

        try {

            Vector<String> vector = new Vector<>();
            vector.add("Select Customer");

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `customer`");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                customerMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            jComboBox2.setModel(new DefaultComboBoxModel<>(vector));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double total = 0;
    private double discount = 0;
    private double payment = 0;
    private double balance = 0;
    private String paymentMethod = "Select";

    private void calculate() {

        if (discountField.getText().isEmpty()) {
            discount = 0;
        } else {
            discount = Double.parseDouble(discountField.getText());
        }

        if (paymentField.getText().isEmpty()) {
            payment = 0;
        } else {
            payment = Double.parseDouble(paymentField.getText());
        }

        total = Double.parseDouble(totalField.getText());

        paymentMethod = String.valueOf(jComboBox1.getSelectedItem());

        total -= discount;

        if (total < 0) {

        }

        if (paymentMethod.equals("Cash")) {
            //cash
            paymentField.setEditable(true);
            balance = payment - total;

            if (balance < 0) {
                printInvoiceButton.setEnabled(false);
            } else {
                printInvoiceButton.setEnabled(true);
            }

        } else {
            //card
            payment = total;
            balance = 0;
            paymentField.setText(String.valueOf(payment));
            paymentField.setEditable(false);
            printInvoiceButton.setEnabled(true);
        }

        balanceFiled.setText(String.valueOf(balance));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        invoiceNumberField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        totalField = new javax.swing.JFormattedTextField();
        balanceFiled = new javax.swing.JFormattedTextField();
        paymentField = new javax.swing.JFormattedTextField();
        discountField = new javax.swing.JFormattedTextField();
        printInvoiceButton = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton18 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        stockBarcodeField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(930, 720));

        jLabel6.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        jLabel6.setText("Invoice");

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel8.setText("Invoice ID");

        invoiceNumberField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 219, 200)));
        invoiceNumberField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceNumberFieldActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-close-24.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(245, 219, 200));
        jButton16.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jButton16.setText("Add to Invoice");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel7.setText("Stock");

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jButton1.setText("Select");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel11.setText("Quantity");

        jFormattedTextField1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(245, 219, 200));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("0");
        jLabel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel18.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel18.setText("Name");

        jLabel19.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel19.setText("PRODUCT NAME ");

        jLabel25.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel25.setText("Category");

        jLabel23.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel23.setText("CATEGORY NAME ");

        jLabel16.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel16.setText("MFD");

        jLabel17.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel17.setText("MFD HERE");

        jLabel20.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel20.setText("EXP");

        jLabel22.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel22.setText("EXP HERE");

        jButton17.setBackground(new java.awt.Color(245, 219, 200));
        jButton17.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jButton17.setText("Reset");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel5.setText("Customer");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock ID", "Category", "Name", "Quantity", "Selling Price", "MFD", "EXP", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel24.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel24.setText("Total");

        jLabel26.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel26.setText("Discount");

        jLabel27.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel27.setText("Payment Method");

        jComboBox1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel28.setText("Payment");

        jLabel29.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel29.setText("Balance");

        totalField.setEditable(false);
        totalField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        totalField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalField.setText("0");
        totalField.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        totalField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalFieldActionPerformed(evt);
            }
        });

        balanceFiled.setEditable(false);
        balanceFiled.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        balanceFiled.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        balanceFiled.setText("0");
        balanceFiled.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N

        paymentField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        paymentField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        paymentField.setText("0");
        paymentField.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        paymentField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentFieldActionPerformed(evt);
            }
        });
        paymentField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paymentFieldKeyReleased(evt);
            }
        });

        discountField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        discountField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        discountField.setText("0");
        discountField.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        discountField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountFieldActionPerformed(evt);
            }
        });
        discountField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                discountFieldKeyReleased(evt);
            }
        });

        printInvoiceButton.setBackground(new java.awt.Color(245, 219, 200));
        printInvoiceButton.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        printInvoiceButton.setText("Print Invoice");
        printInvoiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printInvoiceButtonActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel30.setText("Withdaw Points");

        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel26)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel27)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalField)
                    .addComponent(balanceFiled, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(paymentField)
                    .addComponent(discountField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(printInvoiceButton, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(discountField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel30)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paymentField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(balanceFiled, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printInvoiceButton, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel13.setText("Selling Price");

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(245, 219, 200));
        jButton18.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jButton18.setText("Clear All");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel9.setText("Barcode");

        stockBarcodeField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 219, 200)));
        stockBarcodeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockBarcodeFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel11))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(invoiceNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addGap(26, 26, 26)
                                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel9))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(stockBarcodeField)))))
                                    .addComponent(jButton1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel18))
                                        .addGap(47, 47, 47)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 8, Short.MAX_VALUE)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel25)
                                            .addComponent(jLabel20))
                                        .addGap(14, 14, 14)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jButton16)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton17))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(75, 75, 75))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(invoiceNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(stockBarcodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel22)
                    .addComponent(jLabel7)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.home.removeInvoicePanel();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        GRN grn = null;
        OrderOU order = null;

        Product p = new Product(home, true, grn, this, order);
        p.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        calculate();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void totalFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalFieldActionPerformed

    private void paymentFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paymentFieldKeyReleased
        calculate();
    }//GEN-LAST:event_paymentFieldKeyReleased

    private void discountFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discountFieldActionPerformed

    private void discountFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discountFieldKeyReleased
        calculate();
    }//GEN-LAST:event_discountFieldKeyReleased

    private void printInvoiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printInvoiceButtonActionPerformed

        if (invoiceItemMap.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add at least one product to the cart", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String customer = String.valueOf(jComboBox2.getSelectedItem());
        String stockID = jTextField3.getText();
        String qty = jFormattedTextField1.getText();
        String sellingPrice = jTextField5.getText();

        if (customer.equals("Select Customer")) {
            JOptionPane.showMessageDialog(this, "Please select a customer", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                String invoiceID = invoiceNumberField.getText();
                String customerId = customerMap.get(customer);
                if (customerId == null) {
                    JOptionPane.showMessageDialog(this, "Invalid customer selected", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                String paidAmount = paymentField.getText();
                String paymentMethodID = paymentMethodMap.get(String.valueOf(jComboBox1.getSelectedItem()));
                String discount = String.valueOf(discountField.getText());

                MySQL.executeIUD("INSERT INTO `customer_invoice` VALUES('" + invoiceID + "','" + dateTime + "',"
                        + "'" + paidAmount + "','" + discount + "','" + customerId + "','" + paymentMethodID + "')");

                for (InvoiceItemOU invoiceItem : invoiceItemMap.values()) {

                    MySQL.executeIUD("INSERT INTO `customer_invoice_items`(`qty`,`customer_invoice_id`,`stock_id` )"
                            + "VALUES('" + invoiceItem.getQty() + "','" + invoiceID + "','" + invoiceItem.getStockId() + "')");

                    JOptionPane.showMessageDialog(this, "Invoice Printed", "Success", JOptionPane.INFORMATION_MESSAGE);
                    resetInvoice();
                    generateInvoiceID();
                    totalField.setText("0");
                    discountField.setText("0");
                    paymentField.setText("0");
                    balanceFiled.setText("0");
                    jComboBox2.setSelectedIndex(0);
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    model.setRowCount(0);

                    MySQL.executeIUD("UPDATE `stock` SET `qty`=`qty`-'" + invoiceItem.getQty() + "' WHERE `id`='" + invoiceItem.getStockId() + "'");

                }

//                double points = Double.parseDouble(totalField.getText()) / 100;
                //Withdraw Points
//            if (withdawPoints) {
//                newPoints += points;
//                MySQL2.executeIUD("UPDATE `customer` SET `point` ='" + newPoints + "' WHERE `mobile` = '" + customerMobile + "'");
//            } else {
//                
//                MySQL2.executeIUD("UPDATE `customer` SET `point`=`point`+'" + points + "' WHERE `mobile` = '" + customerMobile + "'");
//            }
                //Withdraw Points
                //view or print report
//                String path = "src//reports//test_invoice.jasper";
//
//                HashMap<String, Object> params = new HashMap<>();
//                params.put("Parameter5", totalField.getText());
//                params.put("Parameter6", discountField.getText());
//                params.put("Parameter7", String.valueOf(jComboBox1.getSelectedItem()));
//                params.put("Parameter8", paymentField.getText());
//                params.put("Parameter9", balanceFiled.getText());
//
//                params.put("Parameter2", invoiceNumberField.getText());
//                params.put("Parameter7", String.valueOf(jComboBox2.getSelectedItem()));
//                //params.put("Parameter1", employeeLable.getText());
//                params.put("Parameter4", dateTime);
//
//                JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
//
//                JasperPrint jasperPrint = JasperFillManager.fillReport(path, params, dataSource);
//
//                JasperViewer.viewReport(jasperPrint, false);
//                JasperPrintManager.printReport(jasperPrint, false);
//                JasperExportManager.exportReportToPdfFile(jasperPrint, path);
//
//            } catch (Exception e) {
//                e.printStackTrace();



                InputStream path = this.getClass().getResourceAsStream("/reports/test_invoice.jasper");
                HashMap<String, Object> params = new HashMap<>();
                params.put("Parameter5", totalField.getText());
                params.put("Parameter6", discountField.getText());
                params.put("Parameter7", String.valueOf(jComboBox1.getSelectedItem()));
                params.put("Parameter8", paymentField.getText());
                params.put("Parameter9", balanceFiled.getText());

                params.put("Parameter2", invoiceNumberField.getText());
                params.put("Parameter3", String.valueOf(jComboBox2.getSelectedItem()));
                //params.put("Parameter1", employeeLable.getText());
                params.put("Parameter4", dateTime);

                JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
                JasperPrint report = JasperFillManager.fillReport(path, params, dataSource);
                JasperViewer.viewReport(report, false);
                JasperPrintManager.printReport(report, false);
                //JasperExportManager.exportReportToPdfFile(report, outputPath);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_printInvoiceButtonActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed

    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed

        String customer = String.valueOf(jComboBox2.getSelectedItem());
        String stockID = jTextField3.getText();
        String category = jLabel23.getText();
        String productName = jLabel19.getText();
        String qty = jFormattedTextField1.getText();
        String mfd = jLabel17.getText();
        String exp = jLabel22.getText();
        String sellingPrice = jTextField5.getText();

        if (customer.equals("Select Customer")) {
            JOptionPane.showMessageDialog(this, "Please select a customer", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (stockID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter stock id", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (qty.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter quantity", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (sellingPrice.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter selling price", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {
                InvoiceItemOU invoiceItem = new InvoiceItemOU();
                invoiceItem.setCategory(category);
                invoiceItem.setCustomer(customer);
                invoiceItem.setExp(exp);
                invoiceItem.setMfd(mfd);
                invoiceItem.setName(productName);
                invoiceItem.setQty(qty);
                invoiceItem.setSellingPrice(sellingPrice);
                invoiceItem.setStockId(stockID);

                if (invoiceItemMap.get(stockID) == null) {
                    invoiceItemMap.put(stockID, invoiceItem);
                } else {

                    InvoiceItemOU found = invoiceItemMap.get(stockID);

                    int option = JOptionPane.showConfirmDialog(this, "Do you want to Update the Quantity of Product :" + productName, "Message",
                            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                    if (option == JOptionPane.YES_OPTION) {

                        found.setQty(String.valueOf(Double.parseDouble(found.getQty()) + Double.parseDouble(qty)));

                    }
                }
                loadInvoiceItems();
                resetInvoice();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jButton16ActionPerformed

    private void invoiceNumberFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceNumberFieldActionPerformed
        jComboBox2.grabFocus();
    }//GEN-LAST:event_invoiceNumberFieldActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        resetInvoice();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        resetInvoice();
        generateInvoiceID();
        totalField.setText("0");
        discountField.setText("0");
        paymentField.setText("0");
        balanceFiled.setText("0");
        jComboBox2.setSelectedIndex(0);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        // 5
        calculate();
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void paymentFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentFieldActionPerformed

    private void stockBarcodeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockBarcodeFieldActionPerformed
        String barcode = stockBarcodeField.getText();
        
        try {
            if (barcode.length() == 12) {
                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `stock` "
                        + "INNER JOIN `product` ON `stock`.`product_id` = `product`.`id` "
                        + "INNER JOIN `category` ON `product`.`category_id` = `category`.`id` "
                        + "WHERE `stock`.`barcode` = '"+barcode+"'");
                
                if (resultSet.next()) {
                    jTextField3.setText(resultSet.getString("stock.id"));
                    jLabel21.setText(resultSet.getString("stock.qty"));
                    jLabel23.setText(resultSet.getString("category.name"));
                    jLabel19.setText(resultSet.getString("product.name"));
                    jLabel17.setText(resultSet.getString("stock.mfd"));
                    jLabel22.setText(resultSet.getString("stock.exp"));
                    jTextField5.setText(resultSet.getString("stock.price"));
                }else {
                    JOptionPane.showMessageDialog(this, "No product found", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(this, "Invalid barcode", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }         
    }//GEN-LAST:event_stockBarcodeFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField balanceFiled;
    private javax.swing.JFormattedTextField discountField;
    private javax.swing.JTextField invoiceNumberField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JFormattedTextField paymentField;
    private javax.swing.JButton printInvoiceButton;
    private javax.swing.JTextField stockBarcodeField;
    private javax.swing.JFormattedTextField totalField;
    // End of variables declaration//GEN-END:variables

    private void resetInvoice() {
        jTextField3.setText("");
        jTextField5.setText("");
        jLabel19.setText("PRODUCT NAME");
        jLabel23.setText("CATEGORY NAME");
        jLabel17.setText("MFD HERE");
        jLabel22.setText("EXP HERE");
        jLabel21.setText("0");
        jFormattedTextField1.setText("");

        jTextField3.grabFocus();
        jTextField3.setEditable(true);

    }
}
