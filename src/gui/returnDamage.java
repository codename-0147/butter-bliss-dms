/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import model.MySQL2;

/**
 *
 * @author Piyumi
 */
public class returnDamage extends javax.swing.JPanel {

    private static HashMap<String, String> loadTypes = new HashMap<>();
    private static HashMap<String, String> loadDis = new HashMap<>();
    private static HashMap<String, String> loadReason = new HashMap<>();

    private HomeOU home;

    private static HashMap<String,String> loadTypes = new HashMap<>();
    private static HashMap<String,String> loadDis = new HashMap<>();
    private static HashMap<String,String> loadReason = new HashMap<>();
    private static HashMap<String,String> loadStock = new HashMap<>();
    private static HashMap<String,String> loadOutlet = new HashMap<>();
     HashMap<String,ReturnItems> returnItems = new HashMap<>();
    
    private static Connection con;
    
    
    public returnDamage(HomeOU home) {
        Connect();
        initComponents();
        loadProduct();
        loadTable();
        loadDistributor();
        loadReason();
        loadOutlet();
        //loadStatus();
        generateReportID();

        generateReportSlipID();
        loadStock();
        
    }
    
     String path2 = null;
     
    private void generateReportID(){
        String id = ""+ System.currentTimeMillis();
        jTextField3.setText(String.valueOf(id));
    
    }
    
     private void generateReportSlipID(){
        String id = ""+ System.currentTimeMillis();
        jTextField4.setText(String.valueOf(id));
    
    }
    
    private void loadProduct(){
    
          try {
            ResultSet resultset = MySQL.executeSearch("SELECT * FROM `w_product`  ");
           
                      Vector<String> vector = new Vector<>();
                      vector.add("Select");  
            
            while (resultset.next()) {

                vector.add(resultset.getString("name"));

                loadTypes.put(resultset.getString("name"),resultset.getString("id"));
                
                 DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
              //  product.setModel(model);
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadDistributor() {

        try {
            ResultSet resultset = MySQL.executeSearch("SELECT * FROM `distributor`  ");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultset.next()) {

                vector.add(resultset.getString("name"));

                loadDis.put(resultset.getString("name"),resultset.getString("id"));
                
                 DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
                jComboBox12.setModel(model);
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

      private void loadOutlet(){
    
          try {
            ResultSet resultset = MySQL.executeSearch("SELECT * FROM `outlet`  ");
            
           
                      Vector<String> vector = new Vector<>();
                      vector.add("Select");  
            
            while (resultset.next()) {
                
                vector.add(resultset.getString("name"));
                loadOutlet.put(resultset.getString("name"),resultset.getString("id"));
                
                 DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
               jComboBox10.setModel(model);
               
            }
       } catch (Exception e) {
            e.printStackTrace();
        }
    
    }


     
//     public void Connect(){
//     
//         try {
//              Class.forName("com.mysql.cj.jdbc.Driver");
//                 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/butter_bliss_db","root","#C0N47@mysql");
//        
//             
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     
//     
//     }
     
//     PreparedStatement pst;
//     PreparedStatement pst1;
//     ResultSet rs1;
//     ResultSet rs2;
     
      private void loadStatus(){
    
//          try {
//              
//             
//              pst = con.prepareStatement("SELECT COUNT(`status`) FROM `returns` WHERE `status` ='Pending' ");
//              pst1 = con.prepareStatement("SELECT COUNT(`returns`.`status`) FROM `returns` WHERE `status` ='Received' ");
//              
//             ResultSet rs1 = pst.executeQuery();
//             
//              while (rs1.next()) {
//                 int pending = rs1.getInt(1);
//               //   jFormattedTextField2.setText(String.valueOf(pending));
//              }
//              
//              ResultSet rs2 = pst1.executeQuery();
//             
//              while (rs2.next()) {
//                 int pendi = rs2.getInt(1);
//                 // jTextField2.setText(String.valueOf(pendi));
//              }
//              
//               rs1 = pst.exequteQuery();
//               rs2 = pst1.exequteSearch(sql2);
//             String pending = String.valueOf(MySQL2.exequteSearch("SELECT COUNT(`returns`.`status`) FROM `returns` WHERE `status` ='Pending';  ") );
//             String received = String.valueOf( MySQL2.exequteSearch("SELECT COUNT(`returns`.`status`) FROM `returns` WHERE `status` ='Received';  "));
//             
//             
//             jTextField2.setText(received);
//            
//             jTextField2.setText(received);
//            
//       } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void loadReason() {

        try {
            ResultSet resultset = MySQL.executeSearch("SELECT * FROM `reason`  ");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultset.next()) {

                vector.add(resultset.getString("reason"));

                loadReason.put(resultset.getString("reason"),resultset.getString("id"));
                
                 DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
                jComboBox11.setModel(model);
               
            }
       } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
      private void loadStock(){
    
          try {
            ResultSet resultset = MySQL.executeSearch("SELECT * FROM `stock`  ");
           
                      Vector<String> vector = new Vector<>();
                      vector.add("Select");  
            
            while (resultset.next()) {
                
                vector.add(resultset.getString("id"));
                loadStock.put(resultset.getString("id"),resultset.getString("id"));
                
                 DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
               jComboBox17.setModel(model);
               

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

     private void loadTable(){
         
         String product = String.valueOf(this.product.getText());
         String qty = String.valueOf(jFormattedTextField8.getText());
         String reason = String.valueOf(jComboBox11.getSelectedItem());
         String id = String.valueOf(jTextField3.getText());
         
        
         
         
         try {
              ResultSet resultset = MySQL.executeSearch("SELECT * FROM `return_invoice` INNER JOIN `distributor` ON `distributor`.`id` = "
                      + " `return_invoice`.`distributor_id` INNER JOIN `reason` ON `reason`.`id` = `return_invoice`.`reason_id` "
                      + " INNER JOIN `return_invoice_items` ON `return_invoice_items`.`return_invoice_id` = `return_invoice`.`id` "
                      + " INNER JOIN `w_product` ON `w_product`.`id` = `return_invoice_items`.`w_product_id` WHERE "
                      + " `return_invoice_items`.`return_invoice_id` = '"+id+"'");
           
            DefaultTableModel model = (DefaultTableModel)jTable3.getModel();
            model.setRowCount(0);
            
            //
                for (ReturnItems returnItems : returnItems.values()) {
                Vector<String> vector = new Vector<>();
                vector.add(String.valueOf(returnItems.getId()));
                vector.add(String.valueOf(returnItems.getProduct()));
                vector.add(String.valueOf(returnItems.getQty()));
               // vector.add(resultset.getString("reason"));
                
                vector.add(String.valueOf(returnItems.getPrice()));
                
                
                model.addRow(vector);
                loadStatus();
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

        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jComboBox13 = new javax.swing.JComboBox<>();
        jLabel51 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jComboBox15 = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        jFormattedTextField12 = new javax.swing.JFormattedTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jFormattedTextField13 = new javax.swing.JFormattedTextField();
        jLabel56 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jComboBox18 = new javax.swing.JComboBox<>();
        image = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jFormattedTextField8 = new javax.swing.JFormattedTextField();
        jLabel43 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jComboBox17 = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        product = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(73, 31, 10));
        jLabel48.setText("Time");

        jButton8.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jButton8.setText(" Approve & Print");
        jButton8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(73, 31, 10));
        jLabel49.setText("Address");


        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(73, 31, 10));
        jLabel50.setText("Outlet Name");

        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox13ActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(73, 31, 10));
        jLabel51.setText("Vehicle No");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(73, 31, 10));
        jLabel52.setText("Date");

        jPanel15.setBackground(new java.awt.Color(245, 219, 200));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Quantity", "Reason", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel53.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(65, 36, 8));
        jLabel53.setText("Return Slip");

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(73, 31, 10));
        jLabel54.setText("Return Slip ID ");

        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(73, 31, 10));
        jLabel55.setText("Return ID");

        jFormattedTextField12.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        jFormattedTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField12ActionPerformed(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jFormattedTextField13.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance())));
        jFormattedTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField13ActionPerformed(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(73, 31, 10));
        jLabel56.setText("Outlet Manager ID");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(73, 31, 10));
        jLabel57.setText("W.Suppervisor ID");

        jComboBox18.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox18ActionPerformed(evt);
            }
        });

        image.setBackground(new java.awt.Color(204, 204, 204));
        image.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        image.setText("Select Return Slip");
        image.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imageActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()

                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel55))
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel50)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel52)
                                        .addGap(55, 55, 55)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField12, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel48)
                                            .addComponent(jLabel49))
                                        .addGap(33, 33, 33)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jFormattedTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel51))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel57))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel56)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(218, 218, 218)
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(204, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel54)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel55)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel50)
                                .addComponent(jLabel57)
                                .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52)
                            .addComponent(jLabel48)
                            .addComponent(jLabel51)
                            .addComponent(jFormattedTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(153, 153, 153)
                        .addComponent(jLabel4)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane3.addTab("Return Slip", jPanel3);

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(73, 31, 10));
        jLabel38.setText("Stock");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(73, 31, 10));
        jLabel39.setText("Outlet");

        jButton7.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jButton7.setText("Add Return");
        jButton7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(73, 31, 10));
        jLabel40.setText("Reason");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(73, 31, 10));
        jLabel41.setText("Product");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(73, 31, 10));
        jLabel42.setText("Quantity");

        jFormattedTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField8ActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(73, 31, 10));
        jLabel43.setText("Date");

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox10ActionPerformed(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(245, 219, 200));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Product", "Quantity", "Reason", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jButton1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(102, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(65, 36, 8));
        jLabel44.setText("Return Invoice");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(73, 31, 10));
        jLabel45.setText("Return Invoice ID ");

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(73, 31, 10));
        jLabel46.setText("Distributor Name");

        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox12ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox17ActionPerformed(evt);
            }
        });

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setFocusCycleRoot(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addGap(18, 18, 18)
                                .addComponent(product, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel39))
                            .addComponent(jLabel40))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel42)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel38)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel43)
                    .addComponent(jLabel44)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 170, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44)
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46)
                    .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(jComboBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel40)
                    .addComponent(product, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel39)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );

        jTabbedPane3.addTab("Return Invoice", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()

                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1044, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 655, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // Add RETURN
        
        String id = jTextField3.getText();
        String distributor = String.valueOf(jComboBox12.getSelectedItem());
        String stock = String.valueOf(jComboBox17.getSelectedIndex());
        String product = String.valueOf(this.product.getText());
        String reason = String.valueOf(jComboBox11.getSelectedItem());
        String qty = String.valueOf(jFormattedTextField8.getText());
        String date = String.valueOf(jDateChooser1.getDate());
        String outlet = String.valueOf(jComboBox10.getSelectedItem());
        
        if (distributor.equals("Select")) {
           JOptionPane.showMessageDialog(this, "Please Select Distributor.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else  if (stock.equals("Select")) {
           JOptionPane.showMessageDialog(this, "Please Select Stock.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else  if (product.equals("Select")) {
           JOptionPane.showMessageDialog(this, "Please Select Product.", "Warning", JOptionPane.WARNING_MESSAGE);   
        } else  if (reason.equals("Select")) {
           JOptionPane.showMessageDialog(this, "Please Select Reason.", "Warning", JOptionPane.WARNING_MESSAGE);  
        } else  if (qty.isEmpty()) {
           JOptionPane.showMessageDialog(this, "Please Enter Quantity.", "Warning", JOptionPane.WARNING_MESSAGE);   
        } else  if (date.isEmpty()) {
           JOptionPane.showMessageDialog(this, "Please Select Date.", "Warning", JOptionPane.WARNING_MESSAGE);   
        } else  if (outlet.equals("Select")) {
           JOptionPane.showMessageDialog(this, "Please Select Outlet.", "Warning", JOptionPane.WARNING_MESSAGE);   
        }else  {
            
            try {
                
                ResultSet resultset = MySQL.executeSearch("SELECT * FROM `return_invoice` WHERE `id` = '"+id+"'");
                
                if (resultset.next()) {
                    JOptionPane.showMessageDialog(this, "This Return ID Already Added.", "Warning", JOptionPane.WARNING_MESSAGE);  
                } else {
                    MySQL.executeIUD(" INSERT INTO `return_invoice_items`(`qty`,`return_invoice_id`,`w_product_id`)"
                            + " VALUES('"+qty+"','"+id+"','"+product+"')");
                    
                    loadTable();
                    
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jFormattedTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField8ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jComboBox12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox12ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // Approve
        
        String slipid = jTextField4.getText();
        String returnid = jTextField5.getText();
        
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jComboBox13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox13ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable4MouseClicked

    private void jFormattedTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField12ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jFormattedTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField13ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jComboBox18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox18ActionPerformed

    private void imageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imageActionPerformed
        // SELECT RETURN SLIP
        
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        
        String path = f.getAbsolutePath();
        //String path1 = "C:\\Users\\Piyumi\\Documents\\NetBeansProjects\\lotuskitchen\\src\\image-face\\face1.jpg");
        try {
            BufferedImage bi = ImageIO.read(new File(path));
            Image img = bi.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img);
            path2 = path;
            image.setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        
    }//GEN-LAST:event_imageActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jComboBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox10ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // ADD 
         String id = jTextField3.getText();
        String distributor = String.valueOf(jComboBox12.getSelectedItem());
        String stock = String.valueOf(jComboBox17.getSelectedItem());
        String product = String.valueOf(this.product.getText());
        String reason = String.valueOf(jComboBox11.getSelectedItem());
        String qty = String.valueOf(jFormattedTextField8.getText());
        String date = String.valueOf(jDateChooser1.getDate());
        String outlet = String.valueOf(jComboBox10.getSelectedItem());
        
       //Date date = new Date();
    String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        
     ReturnItems returnItems = new ReturnItems();
         returnItems.setId(id);
         returnItems.setQty(qty);
         returnItems.setProduct(product);
         returnItems.setReturnID(id);
         //returnItems.setPrice(time);
        
        if (distributor.equals("Select")) {
           JOptionPane.showMessageDialog(this, "Please Select Distributor.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else  if (stock.equals("Select")) {
           JOptionPane.showMessageDialog(this, "Please Select Stock.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else  if (product.equals("Select")) {
           JOptionPane.showMessageDialog(this, "Please Select Product.", "Warning", JOptionPane.WARNING_MESSAGE);   
        } else  if (reason.equals("Select")) {
           JOptionPane.showMessageDialog(this, "Please Select Reason.", "Warning", JOptionPane.WARNING_MESSAGE);  
        } else  if (qty.isEmpty()) {
           JOptionPane.showMessageDialog(this, "Please Enter Quantity.", "Warning", JOptionPane.WARNING_MESSAGE);   
        } else  if (date.isEmpty()) {
           JOptionPane.showMessageDialog(this, "Please Select Date.", "Warning", JOptionPane.WARNING_MESSAGE);   
        } else  if (outlet.equals("Select")) {
           JOptionPane.showMessageDialog(this, "Please Select Outlet.", "Warning", JOptionPane.WARNING_MESSAGE);   
        }else  {
            
            try {
                
                ResultSet resultset = MySQL.executeSearch("SELECT * FROM `return_invoice` WHERE `id` = '"+id+"'");
                
                if (resultset.next()) {
                    JOptionPane.showMessageDialog(this, "This Return ID Already Added.", "Warning", JOptionPane.WARNING_MESSAGE);  
                } else {
                    MySQL.executeIUD(" INSERT INTO `return_invoice`(`id`,`time`,`outlet_id`,"
                            + " `distributor_id`,`delivery_id`,`reason_id`,`return_invoice_status_id`)"
                            + " VALUES('"+id+"','"+time+"','"+outlet+"','"+distributor+"','1','"+reason+"','1')");
                    
                    //loadTable();
                    
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
                
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox17ActionPerformed
       
         String brand = String.valueOf(jComboBox17.getSelectedItem());
        
        if (brand.isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "Please Enter Stock ","Warning",JOptionPane.WARNING_MESSAGE);
            
        } else {
            
            try {
                
                ResultSet resultset = MySQL2.exequteSearch("SELECT `product`.`name` FROM `product` INNER JOIN"
                        + " `stock` ON `stock`.`product_id` = `product`.`id`  WHERE `stock`.`id` = '"+brand+"' ");
                
                
                if (resultset.next()) {
                  //  JOptionPane.showMessageDialog(this, "This Brand Name Already Added.","Warning",JOptionPane.WARNING_MESSAGE);
                //    product.setSelectedItem("SELECT `product`.`name` FROM `product` INNER JOIN"
                     //   + " `stock` ON `stock`.`product_id` = `product`.`id`  WHERE `stock`.`id` = '"+brand+"'");
                } else {
                    if (jComboBox17.getSelectedIndex() == 0) {
                        
                       MySQL2.exequteIUD("INSERT INTO `brand`(`brand`) VALUES('"+brand+"')");
                       JOptionPane.showMessageDialog(this, "Brand Added Successfully","Success",JOptionPane.WARNING_MESSAGE);
                    } else {
                        int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Do you Want To Update Brand?","Update",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                        
                        if (showConfirmDialog == JOptionPane.YES_OPTION) {
                            
                           // MySQL2.exequteIUD("UPDATE `brand` SET `brand` = '"+brand+"' WHERE `brand`= '"+String.valueOf(jComboBox1.getSelectedItem())+"' ");
                            JOptionPane.showMessageDialog(this, "Brand Updated.","Success",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                   // loadBrad();
                    jTextField3.setText("");
                    reset();
                   // loadProducts();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        

        
        // String mobile = this.mobile.getText();
        
        try {
              ResultSet resultset = MySQL2.exequteSearch("SELECT `product`.`name` FROM `product` INNER JOIN"
                        + " `stock` ON `stock`.`product_id` = `product`.`id`  WHERE `stock`.`id` = '"+brand+"' ");
              
              if (resultset.next()) {
                 product.setText(resultset.getString("name"));
                 
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jComboBox17ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton image;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox15;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox18;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JFormattedTextField jFormattedTextField12;
    private javax.swing.JFormattedTextField jFormattedTextField13;
    private javax.swing.JFormattedTextField jFormattedTextField8;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField product;
    // End of variables declaration//GEN-END:variables

    private void reset() {

        //jComboBox1.setSelectedIndex(0);
     //   jComboBox2.setSelectedIndex(0);
        //jComboBox3.setSelectedIndex(0);
        //jFormattedTextField1.setText("");

    }
}
