/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

//import static gui.SignIn.logger;
import model.MySQL;
import java.sql.ResultSet;
import java.util.Vector;
//import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.SupplierReturnItem;

/**
 *
 * @author barth
 */
public class SelectReturnInvoice extends javax.swing.JDialog {
    private OutletReturns wReturns;
    /**
     * Creates new form CompanyRegistration
     */
    public SelectReturnInvoice(java.awt.Frame parent, boolean modal, OutletReturns wReturns) {
        super(parent, modal);
        this.wReturns = wReturns;
        initComponents();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        returnInvoiceTable.setDefaultRenderer(Object.class, renderer);
        loadReturnInvoices(returnInvoiceIDField.getText());
    }
    
    private void loadReturnInvoices(String returnInvoiceID) {
        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `return_invoice` INNER JOIN `outlet` "
                    + "ON `return_invoice`.`outlet_id` = `outlet`.`id` INNER JOIN `outlet_manager` "
                    + "ON `outlet`.`outlet_manager_id` = `outlet_manager`.`id` INNER JOIN `distributor` "
                    + "ON `return_invoice`.`distributor_id` = `distributor`.`id` INNER JOIN `reason` "
                    + "ON `return_invoice`.`reason_id` = `reason`.`id` INNER JOIN `return_invoice_status` "
                    + "ON `return_invoice`.`return_invoice_status_id` = `return_invoice_status`.`id` "
                    + "WHERE `return_invoice_status`.`name` = 'Pending' "
                    + "AND `return_invoice`.`id` LIKE '"+returnInvoiceID+"%'");
            
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
                vector.add(resultSet.getString("outlet.address"));
                vector.add(resultSet.getString("outlet_manager.id"));
                model.addRow(vector);
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        returnInvoiceTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        returnInvoiceIDField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Select Invoice");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(66, 45, 22));
        jLabel1.setText("Select Return Invoice");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        returnInvoiceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Outlet", "Distributor", "Vehicle No.", "Date", "Reason", "Outlet Address", "Out. Man. ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel2.setText("Search Invoice ID");

        returnInvoiceIDField.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        returnInvoiceIDField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                returnInvoiceIDFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(returnInvoiceIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(returnInvoiceIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void returnInvoiceTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnInvoiceTableMouseClicked
        //logger.log(Level.INFO, "MouseEvent");
        int row = returnInvoiceTable.getSelectedRow();
        
        if (evt.getClickCount() == 2) {
            try {
                if (wReturns != null) {
                    wReturns.getreturnInvoiceIDField2().setText(String.valueOf(returnInvoiceTable.getValueAt(row, 0)));
                    wReturns.getoutletNameLabel().setText(String.valueOf(returnInvoiceTable.getValueAt(row, 1)));
                    wReturns.getdateLabel().setText(String.valueOf(returnInvoiceTable.getValueAt(row, 4)));
                    wReturns.getoutletManagerIDField2().setText(String.valueOf(returnInvoiceTable.getValueAt(row, 7)));
                    wReturns.getoutletAddressLabel().setText(String.valueOf(returnInvoiceTable.getValueAt(row, 6)));
                    wReturns.getvehicleNumberLabel2().setText(String.valueOf(returnInvoiceTable.getValueAt(row, 3)));
                    this.dispose();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_returnInvoiceTableMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //logger.log(Level.INFO, "WindowEvent");
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        //logger.log(Level.INFO, "WindowEvent");
    }//GEN-LAST:event_formWindowClosed

    private void returnInvoiceIDFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_returnInvoiceIDFieldKeyReleased
        loadReturnInvoices(returnInvoiceIDField.getText());
    }//GEN-LAST:event_returnInvoiceIDFieldKeyReleased

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField returnInvoiceIDField;
    private javax.swing.JTable returnInvoiceTable;
    // End of variables declaration//GEN-END:variables
}
