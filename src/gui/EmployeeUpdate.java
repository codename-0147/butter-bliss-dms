
package gui;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.MySQL;

/**
 *
 * @author Oshadha
 */
public class EmployeeUpdate extends javax.swing.JFrame {
    
    private static HashMap<String, String> departmentMap = new HashMap<>();
    private static HashMap<String, String> genderMap = new HashMap<>();
    private int selectedRow;

    /**
     * Creates new form EmployeeUpdate
     */
    public EmployeeUpdate() {
        initComponents();
        loadEmployee();
        loadDepartment();
        loadGender();
        loadEmployeeT();
    }
      private void loadEmployeeT() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `employee`"
                    + "INNER JOIN `department` ON `employee`.`department_id`= `department`.`id`"
                    + "INNER JOIN `gender` ON `employee`.`gender_id`=`gender`.`id`");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("f_name"));
                vector.add(resultSet.getString("l_name"));
                vector.add(resultSet.getString("dob"));
                vector.add(resultSet.getString("mobile"));
                vector.add(resultSet.getString("email"));
                vector.add(resultSet.getString("address"));
                vector.add(resultSet.getString("nic"));
                vector.add(resultSet.getString("gender.name"));
                vector.add(resultSet.getString("department.dep_name"));
                vector.add(resultSet.getString("username"));
                vector.add(resultSet.getString("password"));
                
                model.addRow(vector);
                
               }
            
            } catch (Exception e) {
            e.printStackTrace();
        }
   } 

     private void loadEmployee() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `employee`"
                    + "INNER JOIN `department` ON `employee`.`department_id`= `department`.`id`");
            
            } catch (Exception e) {
            e.printStackTrace();
        }
        
        
   }
     
     private void loadGender(){
         
         try {
             ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `gender`");
             
             Vector<String> vector = new Vector<>();
             vector.add("Select");
             
             while (resultSet.next()) {
             vector.add(resultSet.getString("name"));
             genderMap.put(resultSet.getString("name"), resultSet.getString("id"));
             
             DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
             jComboBox2.setModel(model);
                 
             }
         } catch (Exception e) {
         }
     }
     private void  loadDepartment(){
         
         try {
             ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `department`");
             
             Vector<String> vector = new Vector<>();
             vector.add("Select");
             
             while (resultSet.next()) {
                  vector.add(resultSet.getString("dep_name"));
                  departmentMap.put(resultSet.getString("dep_name"), resultSet.getString("id"));
                 
             }
             
             DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
             jComboBox1.setModel(model);
             
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

        jTextField8 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jTextField8.setText("jTextField8");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 246, 209));

        jPanel3.setBackground(new java.awt.Color(255, 248, 222));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 220, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel3.setText("Last Name");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 136, 220, -1));
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 102, 220, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 230, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel2.setText("First Name");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 80, 220, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel7.setText("Department");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 230, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 23, 110, 30));
        jPanel3.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 220, -1));
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 112, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel6.setText("Address");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 210, -1));

        jTextField1.setText("Enter NIC Number");
        jPanel3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 22, 360, 30));
        jPanel3.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 220, -1));

        jButton2.setBackground(new java.awt.Color(255, 204, 179));
        jButton2.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(100, 77, 31));
        jButton2.setText("Update");
        jButton2.setHideActionText(true);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 250, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel5.setText("Email");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 190, -1));

        jButton3.setBackground(new java.awt.Color(253, 221, 205));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, 240, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel8.setText("Mobile");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, 220, -1));
        jPanel3.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, 230, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel4.setText("Gender");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 200, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel9.setText("User Name");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 230, -1));
        jPanel3.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 220, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel11.setText("Password");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 220, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 220, -1));
        jPanel3.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 220, -1));

        jPanel1.setBackground(new java.awt.Color(252, 179, 143));
        jPanel1.setForeground(new java.awt.Color(255, 246, 237));

        jLabel10.setBackground(new java.awt.Color(255, 246, 209));
        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 51, 0));
        jLabel10.setText("Employee Search & Update");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-employee-31 (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jPanel4.setBackground(new java.awt.Color(255, 248, 222));

        jTextField4.setBackground(new java.awt.Color(255, 246, 209));
        jTextField4.setText("Enter Nic");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jTable1.setBackground(new java.awt.Color(255, 246, 209));
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "DOB", "Mobile", "Email", "Address", "NIC", "Gender", "Department", "Username", "Password"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
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

        jButton4.setBackground(new java.awt.Color(255, 246, 237));
        jButton4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Trash.png"))); // NOI18N
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 246, 237));
        jButton5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Print.png"))); // NOI18N
        jButton5.setText("Print");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
           try {
            String firstName = jTextField2.getText();
            String lastName  = jTextField3.getText();
           
            String email     = jTextField5.getText();
            String address   = jTextField6.getText();
            String department= String.valueOf(jComboBox1.getSelectedItem());
            String mobile    = jTextField7.getText();
            String nic       = jTextField1.getText();
            String gender   = String.valueOf(jComboBox2.getSelectedItem());
            String username = jTextField9.getText();
            String password = String.valueOf(jPasswordField1.getPassword());
            
               if (firstName.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "Please Enter Your First Name", "Warning", JOptionPane.WARNING_MESSAGE);
                                
               } else if (lastName.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "Please Enter Your Last Name","Warning",JOptionPane.WARNING_MESSAGE);
                   
               }else if (gender.equals("Select")) {
                   
                   JOptionPane.showMessageDialog(this, "Please Select Your Gender","Warning",JOptionPane.WARNING_MESSAGE);
                                     
               } else if (email.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "Please ENter Your Email","Warning",JOptionPane.WARNING_MESSAGE);
                   
                   
               } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+"
                + "(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")) {
                   JOptionPane.showMessageDialog(this, "Please Enter valid Email","Warning",JOptionPane.WARNING_MESSAGE);
                   
               } else if (address.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "Please Enter Your Address");
                   
               }else if (username.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "Please Enter Your User Name","Warning",JOptionPane.WARNING_MESSAGE);
                   
              } else if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$")) {
                   JOptionPane.showMessageDialog(this, "Password Must Contain at Least One Digit, One Uppercase Letter, One Lowercase Letter, and One Special Character.", "Warning", JOptionPane.WARNING_MESSAGE);
         
               } else if (department.equals("Select")) {
                   JOptionPane.showMessageDialog(this, "Please Select Your Department");
                   
               } else if (mobile.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "Please Enter Your mobile Number");
                   
               
                   
               } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
                   JOptionPane.showMessageDialog(this, "Please Enter Valid Mobile");
                   
               
        
               } else {
                   ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `employee` WHERE `nic`='"+nic+"'");
                   
                   boolean canUpadte = false;
                   
                   if (resultSet.next()) {
                       if (!resultSet.getString("email").equals(email)) {
                           JOptionPane.showMessageDialog(this, "This Email is already in use","Warning",JOptionPane.WARNING_MESSAGE);
                           
                       }else {
                           canUpadte = true;
                       }
                       
                       
                   } else {
                       canUpadte = true;
                   }
                   
                   if(canUpadte) {
                       String updateQuery = "UPDATE employee SET "
                               + "f_name = '" +firstName+ "', "
                               + "l_name  = '"+lastName+"', "
                              
                               + "mobile  = '"+mobile+"', "
                               + "email   = '"+email+"', "
                               + "address = '"+address+"', "
                               + "department_id = '"+departmentMap.get(department)+"' "
                               + "WHERE nic = '"+nic+"'";
                               MySQL.executeIUD(updateQuery);
                               
                                       
                   }
                   JOptionPane.showMessageDialog(this, "Employee Updated","Warning",JOptionPane.INFORMATION_MESSAGE);
                   loadEmployeeT();
                   reset();
               }
        } catch (Exception e) {
            e.printStackTrace();
        }
  
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nic =jTextField1.getText();

try {
    if (selectedRow != -1) {
        ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `employee` WHERE `nic` = '" + nic + "'");

        if (resultSet.next()) {
            String firstName = resultSet.getString("f_name");
            jTextField2.setText(firstName);

            String lastName = resultSet.getString("l_name");
            jTextField3.setText(lastName);

            String email = resultSet.getString("email");
            jTextField5.setText(email);

            String address = resultSet.getString("address");
            jTextField6.setText(address);

            String department = resultSet.getString("department_id");
            jComboBox1.setSelectedItem(department);

            String mobile = resultSet.getString("mobile");
            jTextField7.setText(mobile);
            
            String username = resultSet.getString("username");
            jTextField9.setText(username);
            
            String password = resultSet.getString("password");
            jPasswordField1.setText(password);
            
            String gender = resultSet.getString("gender_id");
            jComboBox2.setSelectedItem(gender);
        } else {
            JOptionPane.showMessageDialog(this, "No employee found with the given NIC.", "Warning", JOptionPane.WARNING_MESSAGE);
        }

        resultSet.close();
    } else {
        JOptionPane.showMessageDialog(this, "Please select a row first.", "Warning", JOptionPane.WARNING_MESSAGE);
    }
} catch (Exception e) {
    e.printStackTrace();
}

        
        /*  String nic = jTextField1.getText();
        
        try {
            if (selectedRow != -1) {
                
            
            
           ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `employee` WHERE `nic` = '"+nic+"' ");
           
            if (resultSet.next()) {
                
                String firstName = resultSet.getString("f_name");
                jTextField2.setText(firstName);
                
                String lastName = resultSet.getString("l_name");
                jTextField3.setText(lastName);
                
             
                
                String email = resultSet.getString("email");
                jTextField5.setText(email);
                
                String address = resultSet.getString("address");
                jTextField6.setText(address);
                
                String department = resultSet.getString("department_id");
                jComboBox1.setSelectedItem(department);
                
                String mobile = resultSet.getString("mobile");
                jTextField7.setText(mobile);
                
            }
            
            }
        } catch (Exception e) {
            e.printStackTrace();
                
        }*/
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed

    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        try {
            String nic = jTextField1.getText();

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `employee`"
                + "INNER JOIN `department` ON `employee`.`department_id`= `department`.`id`"
                + "WHERE `nic` = '" + nic + "'");

            DefaultTableModel tableModel = (DefaultTableModel)jTable1.getModel();
            tableModel.setRowCount(0);

            while (resultSet.next()) {

                Vector vector = new Vector();

                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("f_name"));
                vector.add(resultSet.getString("l_name"));
                vector.add(resultSet.getString("dob"));
                vector.add(resultSet.getString("mobile"));
                vector.add(resultSet.getString("email"));
                vector.add(resultSet.getString("address"));
                vector.add(resultSet.getString("nic"));
                vector.add(resultSet.getString("department.dep_name"));
                tableModel.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            EmployeeUpdate eu = new EmployeeUpdate();
            eu.setVisible(true);

        }
        //   this.dispose();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try {
            String nic = jTextField1.getText();

            MySQL.executeIUD("DELETE FROM `employee` " + "WHERE `nic` = '"+nic+"'");
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadEmployee();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        MessageFormat header = new MessageFormat("I am Header of the Print Page");
        MessageFormat footer = new MessageFormat("Page{0, number, integer}");

        try {
            jTable1.print(JTable.PrintMode.NORMAL, header, footer);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatMacLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeUpdate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
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
    jTextField1.setText("");
    jTextField2.setText("");
    jTextField3.setText("");
    jTextField4.setText("");
    jTextField5.setText("");
    jTextField6.setText("");
    jTextField7.setText("");
    jComboBox1.setSelectedItem("Select");
    jComboBox2.setSelectedItem("Select");
    jPasswordField1.setText("");
   
    }
}
