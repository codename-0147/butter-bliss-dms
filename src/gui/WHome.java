/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import static java.lang.Thread.sleep;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.SwingUtilities;

/**
 *
 * @author barth
 */
public class WHome extends javax.swing.JFrame {
    private static WHome obj = null;
    public static WDashboard dashboard;
    public static WOrders orders;
    public static WInvoice invoice;
    public static WProducts inventory;
    public static WReturns returns;
    public static BackupNRestore backupNRestore;

    /**
     * Creates new form Home
     */
    public WHome() {
        initComponents();
        leftMenuButton.setVisible(false);
        updateFieldDate();
    }
    
    public static WHome getObj() {
        if (obj == null) {
            obj = new WHome();
        }
        return obj;
    }
    
    private void moveLeft() {
        rightMenuButton.setVisible(false);
        Thread t = new Thread(
                    () -> {
                        for (int i = 268; i >= 71; i-=10) {
                            jPanel1.setPreferredSize(new Dimension(i,jPanel1.getHeight()));
                            SwingUtilities.updateComponentTreeUI(jPanel1);
                            
                            try {
                                Thread.sleep(1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        leftMenuButton.setVisible(true);
                        jLabel2.setVisible(false);
                        jLabel4.setVisible(false);
                        dashboardButton.setText("");
                        ordersButton.setText("");
                        invoiceButton.setText("");
                        packingButton.setText("");
                        inventoryButton.setText("");
                        returnsButton.setText("");
                        backupNRestoreButton.setText("");
                        jLabel3.setVisible(false);
                        helpNReportsButton.setText("");
                        logoutButton.setText("");
                    }
            );
            t.start();
    }
    
    private void moveRight() {
        leftMenuButton.setVisible(false);
        Thread t = new Thread(
                    () -> {
                        for (int i = 65; i <= 268; i+=10) {
                            jPanel1.setPreferredSize(new Dimension(i,jPanel1.getHeight()));
                            SwingUtilities.updateComponentTreeUI(jPanel1);
                            
                            try {
                                Thread.sleep(1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        rightMenuButton.setVisible(true);
                        jLabel2.setVisible(true);
                        jLabel4.setVisible(true);
                        dashboardButton.setText("Dashboard");
                        ordersButton.setText("Orders");
                        invoiceButton.setText("Invoice");
                        packingButton.setText("Packing");
                        inventoryButton.setText("Inventory");
                        returnsButton.setText("Returns");
                        backupNRestoreButton.setText("Backup & Restore");
                        jLabel3.setVisible(true);
                        helpNReportsButton.setText("Help & Reports");
                        logoutButton.setText("Logout");
                    }
            );
            t.start();
    }
    
    public void updateFieldDate() {
        Thread Clock = new Thread() {
            public void run() {
                    for(;;) {
                        try {
                            sleep(1000);
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss a dd/MM/yyyy"); 
                            LocalDateTime now = LocalDateTime.now();  
                            dateTimeLabel.setText(dtf.format(now));
                        }
                        catch (Exception e) {
                            System.out.println("Error");
                        }
                    }
            }
        };
        Clock.start();
    }
    
    public void addDashboard() {
        if (dashboard == null) {
            loadPanel.removeAll();
            dashboard = new WDashboard(this);
            loadPanel.add(dashboard, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(loadPanel);
        }else {
            loadPanel.removeAll();
            loadPanel.add(dashboard, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(loadPanel);
        }
    }
    
    public void removeDashboard() {
        loadPanel.remove(dashboard);
        dashboard = null;
        SwingUtilities.updateComponentTreeUI(loadPanel);
    }
    
    public void addOrders() {
        if (orders == null) {
            loadPanel.removeAll();
            orders = new WOrders(this);
            loadPanel.add(orders, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(loadPanel);
        }else {
            loadPanel.removeAll();
            loadPanel.add(orders, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(loadPanel);
        }
    }
    
    public void removeOrders() {
        loadPanel.remove(orders);
        orders = null;
        SwingUtilities.updateComponentTreeUI(loadPanel);
    }
    
    public void addInvoice() {
        if (invoice == null) {
            loadPanel.removeAll();
            invoice = new WInvoice(this);
            loadPanel.add(invoice, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(loadPanel);
        }else {
            loadPanel.removeAll();
            loadPanel.add(invoice, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(loadPanel);
        }
    }
    
    public void removeInvoice() {
        loadPanel.remove(invoice);
        invoice = null;
        SwingUtilities.updateComponentTreeUI(loadPanel);
    }
    
    public void addInventory() {
        if (inventory == null) {
            loadPanel.removeAll();
            inventory = new WProducts(this);
            loadPanel.add(inventory, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(loadPanel);
        }else {
            loadPanel.removeAll();
            loadPanel.add(inventory, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(loadPanel);
        }
    }
    
    public void removeInventory() {
        loadPanel.remove(inventory);
        inventory = null;
        SwingUtilities.updateComponentTreeUI(loadPanel);
    }
    
    public void addReturns() {
        if (returns == null) {
            loadPanel.removeAll();
            returns = new WReturns(this);
            loadPanel.add(returns, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(loadPanel);
        }else {
            loadPanel.removeAll();
            loadPanel.add(returns, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(loadPanel);
        }
    }
    
    public void removeReturns() {
        loadPanel.remove(returns);
        returns = null;
        SwingUtilities.updateComponentTreeUI(loadPanel);
    }
    
    public void addBackupNRestore() {
        if (backupNRestore == null) {
            loadPanel.removeAll();
            backupNRestore = new BackupNRestore(this);
            loadPanel.add(backupNRestore, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(loadPanel);
        }else {
            loadPanel.removeAll();
            loadPanel.add(backupNRestore, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(loadPanel);
        }
    }
    
    public void removeBackupNRestore() {
        loadPanel.remove(backupNRestore);
        backupNRestore = null;
        SwingUtilities.updateComponentTreeUI(loadPanel);
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
        jLabel4 = new javax.swing.JLabel();
        dashboardButton = new javax.swing.JButton();
        ordersButton = new javax.swing.JButton();
        packingButton = new javax.swing.JButton();
        inventoryButton = new javax.swing.JButton();
        returnsButton = new javax.swing.JButton();
        invoiceButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        helpNReportsButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        leftMenuButton = new javax.swing.JButton();
        rightMenuButton = new javax.swing.JButton();
        backupNRestoreButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        userButton = new javax.swing.JButton();
        usernameLabel = new javax.swing.JLabel();
        dateTimeLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        loadPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 246, 237));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/sub-logo.jpeg"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(66, 45, 22));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Butter Bliss");

        jLabel4.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Warehouse Management System");

        dashboardButton.setBackground(new java.awt.Color(245, 219, 200));
        dashboardButton.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        dashboardButton.setForeground(new java.awt.Color(0, 0, 0));
        dashboardButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/overview-icon.png"))); // NOI18N
        dashboardButton.setText("Dashboard");
        dashboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardButtonActionPerformed(evt);
            }
        });

        ordersButton.setBackground(new java.awt.Color(245, 219, 200));
        ordersButton.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        ordersButton.setForeground(new java.awt.Color(0, 0, 0));
        ordersButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/orders-icon.png"))); // NOI18N
        ordersButton.setText("Orders");
        ordersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordersButtonActionPerformed(evt);
            }
        });

        packingButton.setBackground(new java.awt.Color(245, 219, 200));
        packingButton.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        packingButton.setForeground(new java.awt.Color(0, 0, 0));
        packingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/packing-icon.png"))); // NOI18N
        packingButton.setText("Packing");

        inventoryButton.setBackground(new java.awt.Color(245, 219, 200));
        inventoryButton.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        inventoryButton.setForeground(new java.awt.Color(0, 0, 0));
        inventoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/products-icon.png"))); // NOI18N
        inventoryButton.setText("Products");
        inventoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventoryButtonActionPerformed(evt);
            }
        });

        returnsButton.setBackground(new java.awt.Color(245, 219, 200));
        returnsButton.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        returnsButton.setForeground(new java.awt.Color(0, 0, 0));
        returnsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/returns-icon.png"))); // NOI18N
        returnsButton.setText("Returns");
        returnsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnsButtonActionPerformed(evt);
            }
        });

        invoiceButton.setBackground(new java.awt.Color(245, 219, 200));
        invoiceButton.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        invoiceButton.setForeground(new java.awt.Color(0, 0, 0));
        invoiceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/invoice-icon.png"))); // NOI18N
        invoiceButton.setText("Invoice");
        invoiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(66, 45, 22));
        jLabel3.setText("Others");

        helpNReportsButton.setBackground(new java.awt.Color(245, 219, 200));
        helpNReportsButton.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        helpNReportsButton.setForeground(new java.awt.Color(0, 0, 0));
        helpNReportsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/help-icon.png"))); // NOI18N
        helpNReportsButton.setText("Help & Reports");

        logoutButton.setBackground(new java.awt.Color(245, 219, 200));
        logoutButton.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(0, 0, 0));
        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/logout-icon.png"))); // NOI18N
        logoutButton.setText("Logout");

        leftMenuButton.setBackground(new java.awt.Color(245, 219, 200));
        leftMenuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/menu-icon.png"))); // NOI18N
        leftMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftMenuButtonActionPerformed(evt);
            }
        });

        rightMenuButton.setBackground(new java.awt.Color(245, 219, 200));
        rightMenuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/menu-icon.png"))); // NOI18N
        rightMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightMenuButtonActionPerformed(evt);
            }
        });

        backupNRestoreButton.setBackground(new java.awt.Color(245, 219, 200));
        backupNRestoreButton.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        backupNRestoreButton.setForeground(new java.awt.Color(0, 0, 0));
        backupNRestoreButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/database-icon.png"))); // NOI18N
        backupNRestoreButton.setText("Backup & Restore");
        backupNRestoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backupNRestoreButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ordersButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(packingButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inventoryButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(returnsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(invoiceButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(leftMenuButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rightMenuButton))
                    .addComponent(dashboardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backupNRestoreButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(helpNReportsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leftMenuButton)
                    .addComponent(rightMenuButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(dashboardButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ordersButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(invoiceButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(packingButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inventoryButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(returnsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(backupNRestoreButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(helpNReportsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logoutButton)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 246, 237));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        userButton.setBackground(new java.awt.Color(255, 246, 237));
        userButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/user-icon.png"))); // NOI18N
        userButton.setBorder(null);

        usernameLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(0, 0, 0));
        usernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        usernameLabel.setText("Username");

        dateTimeLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        dateTimeLabel.setForeground(new java.awt.Color(0, 0, 0));
        dateTimeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dateTimeLabel.setText("Date/Time");

        jLabel6.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("WELCOME!");

        emailLabel.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(0, 0, 0));
        emailLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        emailLabel.setText("Email");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(usernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 456, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateTimeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(userButton)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usernameLabel)
                            .addComponent(jLabel6)
                            .addComponent(emailLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateTimeLabel))
                    .addComponent(userButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        loadPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loadPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardButtonActionPerformed
        addDashboard();
    }//GEN-LAST:event_dashboardButtonActionPerformed

    private void ordersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordersButtonActionPerformed
        addOrders();
    }//GEN-LAST:event_ordersButtonActionPerformed

    private void invoiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceButtonActionPerformed
        addInvoice();
    }//GEN-LAST:event_invoiceButtonActionPerformed

    private void inventoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventoryButtonActionPerformed
        addInventory();
    }//GEN-LAST:event_inventoryButtonActionPerformed

    private void returnsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnsButtonActionPerformed
        addReturns();
    }//GEN-LAST:event_returnsButtonActionPerformed

    private void leftMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftMenuButtonActionPerformed
        moveRight();
    }//GEN-LAST:event_leftMenuButtonActionPerformed

    private void rightMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rightMenuButtonActionPerformed
        moveLeft();
    }//GEN-LAST:event_rightMenuButtonActionPerformed

    private void backupNRestoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backupNRestoreButtonActionPerformed
        addBackupNRestore();
    }//GEN-LAST:event_backupNRestoreButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WHome().setVisible(true);
            }
        });
    }
    
//    public static void main(String args[]) {
//        FlatLightLaf.setup();
//        
//        // Get the application's current directory
//        String appPath = new File("").getAbsolutePath();
//        File dbInfoFile = new File(appPath + File.separator + "dbinfo.ser");
//        
//        // Check if the dbinfo.ser file exists
//        if (dbInfoFile.exists()) {
//            // If the dbinfo.ser file exists, launch the Home GUI
//            java.awt.EventQueue.invokeLater(() -> new Home().setVisible(true));
//        } else {
//            // If the dbinfo.ser file does not exist, launch the SetupConnection GUI
//            java.awt.EventQueue.invokeLater(() -> new SetupConnection().setVisible(true));
//        }
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backupNRestoreButton;
    private javax.swing.JButton dashboardButton;
    private javax.swing.JLabel dateTimeLabel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JButton helpNReportsButton;
    private javax.swing.JButton inventoryButton;
    private javax.swing.JButton invoiceButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton leftMenuButton;
    private javax.swing.JPanel loadPanel;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton ordersButton;
    private javax.swing.JButton packingButton;
    private javax.swing.JButton returnsButton;
    private javax.swing.JButton rightMenuButton;
    private javax.swing.JButton userButton;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
