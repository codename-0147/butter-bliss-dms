
package gui;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import java.util.Date;
import java.text.SimpleDateFormat;



public class outletSales extends javax.swing.JPanel {
    private HomeOU home;
    private static HashMap<String,String> loadTypes = new HashMap<>();
    
    /**
     * Creates new form outletSales
     */
    public outletSales(HomeOU home) {
        initComponents();
        this.home = home;
        loadSales();
        loadProduct();
    }

    private void loadSales(){
    
        try {
            ResultSet resultset = MySQL.executeSearch("SELECT * FROM `outlet_sales` INNER JOIN `product`"
                    + " ON `product`.`id` = `outlet_sales`.`product_id` ");
           
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            model.setRowCount(0);
            
            while (resultset.next()) {
                
                Vector<String> vector = new Vector<>();
                vector.add(resultset.getString("outlet_sales.id"));
                vector.add(resultset.getString("product.name"));
                vector.add(resultset.getString("outlet_sales.qty"));
                vector.add(resultset.getString("outlet_sales.amount"));
                vector.add(resultset.getString("outlet_sales.date"));
                
                model.addRow(vector);
                
                
            }
       } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
    private void loadProduct(){
    
          try {
            ResultSet resultset = MySQL.executeSearch("SELECT * FROM `Product`  ");
           
                      Vector<String> vector = new Vector<>();
                      vector.add("Select");  
            
            while (resultset.next()) {
                
           
                vector.add(resultset.getString("product.Name"));
                loadTypes.put(resultset.getString("Name"),resultset.getString("Id"));
                
                
                 DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
                jComboBox2.setModel(model);
               
            }
       } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
      
     private void Sort(){ //2
     try {
        String query = "SELECT * FROM `outlet_sales` INNER JOIN `product` ON `product`.`id` =`outlet_sales`.`product_id` WHERE ";
         
         Date start = null;
            Date end = null;
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            
            if (jDateChooser1.getDate() != null) {
                start = jDateChooser1.getDate();
                query+= "`outlet_sales`.`date` > '"+format.format(start)+"' AND ";
                  
            }
            
            if (jDateChooser2.getDate() != null) {
                end = jDateChooser2.getDate();
                query+= "`outlet_sales`.`date` < '"+format.format(end)+"' ";
                        
            }
            String sort = String.valueOf(jComboBox1.getSelectedItem());
             query+="ORDER BY ";  
            
            query = query.replace("WHERE ORDER BY ", "ORDER BY ");
            query = query.replace("AND ORDER BY ", "ORDER BY ");
            
            if (sort.equals("Product Name ASC")) {
                query+="`product`.`name` ASC";
            }else if(sort.equals("Product Name DESC")){
                query+="`product`.`name` DESC";

            }else if(sort.equals("Quantity ASC")){
                query+="`outlet_sales`.`qty` ASC";
            }else if(sort.equals("Quantity DESC")){
                query+="`outlet_sales`.`qty` DESC";
            }else if(sort.equals("Amount ASC")){
                query+="`outlet_sales`.`amount` ASC";
            }else if(sort.equals("Amount DESC")){
                query+="`outlet_sales`.`amount` DESC";
            }
             ResultSet resultset = MySQL.executeSearch(query);
            
            DefaultTableModel model =(DefaultTableModel)jTable1.getModel();
            model.setRowCount(0);
            
            while (resultset.next()) {
                
                Vector<String> vector = new Vector<>();
                vector.add(resultset.getString("outlet_sales.id"));
                vector.add(resultset.getString("product.Name"));
                vector.add(resultset.getString("outlet_sales.qty"));
                vector.add(resultset.getString("outlet_sales.amount"));
                vector.add(resultset.getString("outlet_sales.date"));
                
                model.addRow(vector);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDayChooser1 = new com.toedter.calendar.JDayChooser();
        jDayChooser2 = new com.toedter.calendar.JDayChooser();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(930, 655));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(65, 36, 8));
        jLabel20.setText("Sales Overview");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(73, 31, 10));
        jLabel21.setText("From");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(73, 31, 10));
        jLabel22.setText("To");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(73, 31, 10));
        jLabel23.setText("Category");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(73, 31, 10));
        jLabel24.setText("Sort By");

        jTable1.setBackground(new java.awt.Color(245, 219, 200));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name ", "Quantity Sold", "Total Sales Amount", "Date of Sales"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionBackground(new java.awt.Color(243, 223, 179));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setText("Product Category Sales");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setText("Top Products");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setText("Sales Trend");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-close-24.png"))); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product Name ASC", "Product Name DESC", "Quantity ASC", "Quantity DESC", "Amount ASC", "Amount DESC" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("SORT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(894, 894, 894)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(15, 15, 15)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jLabel22)
                                .addGap(3, 3, 3)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jLabel23)
                                .addGap(8, 8, 8)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(3, 3, 3)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(288, 288, 288)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(jLabel29)
                                .addGap(53, 53, 53)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel32)))))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        this.home.removeoutletSales();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // category
         String category = String.valueOf(jComboBox2.getSelectedItem());
        
        try {
            String query = "SELECT * FROM `outlet_sales` INNER JOIN `product` "
                    + " ON `product`.`Id` = `outlet_sales`.`product_Id` ";
            
            if (!category.equals("Select")) {
                query+= "  WHERE `product`.`name` = '"+category+"' ";
                
                 ResultSet resultset = MySQL.executeSearch(query);
            
            DefaultTableModel model =(DefaultTableModel)jTable1.getModel();
            model.setRowCount(0);
            
            while (resultset.next()) {
                
                Vector<String> vector = new Vector<>();
                vector.add(resultset.getString("outlet_sales.id"));
                vector.add(resultset.getString("product.Name"));
                vector.add(resultset.getString("outlet_sales.qty"));
                vector.add(resultset.getString("outlet_sales.amount"));
                vector.add(resultset.getString("outlet_sales.date"));
                
                model.addRow(vector);
            }
            }
            
           
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Sort
       
                   Sort();
         
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        try {
            String sort = String.valueOf(jComboBox1.getSelectedItem());
        
        
        String query = "SELECT * FROM `outlet_sales` INNER JOIN `product` "
                    + " ON `product`.`Id` = `outlet_sales`.`product_Id` ";
            
            
            query+="ORDER BY ";  
            
            query = query.replace("WHERE ORDER BY ", "ORDER BY ");
            query = query.replace("AND ORDER BY ", "ORDER BY ");
            
            if (sort.equals("Product Name ASC")) {
                query+="`product`.`name` ASC";
            }else if(sort.equals("Product Name DESC")){
                query+="`product`.`name` DESC";

            }else if(sort.equals("Quantity ASC")){
                query+="`outlet_sales`.`qty` ASC";
            }else if(sort.equals("Quantity DESC")){
                query+="`outlet_sales`.`qty` DESC";
            }else if(sort.equals("Amount ASC")){
                query+="`outlet_sales`.`amount` ASC";
            }else if(sort.equals("Amount DESC")){
                query+="`outlet_sales`.`amount` DESC";
            }
            
            ResultSet resultset = MySQL.executeSearch(query);
            
            DefaultTableModel model =(DefaultTableModel)jTable1.getModel();
            model.setRowCount(0);
            
            while (resultset.next()) {
                
                Vector<String> vector = new Vector<>();
                vector.add(resultset.getString("outlet_sales.id"));
                vector.add(resultset.getString("product.Name"));
                vector.add(resultset.getString("outlet_sales.qty"));
                vector.add(resultset.getString("outlet_sales.amount"));
                vector.add(resultset.getString("outlet_sales.date"));
                
                model.addRow(vector);
                
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
            
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jDateChooser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MouseClicked
       Sort();
    }//GEN-LAST:event_jDateChooser1MouseClicked

    private void jDateChooser2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser2MouseClicked
       
         Sort();
    }//GEN-LAST:event_jDateChooser2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDayChooser jDayChooser1;
    private com.toedter.calendar.JDayChooser jDayChooser2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
