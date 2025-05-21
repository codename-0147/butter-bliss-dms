
package gui;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.MySQL;


public class DistributorPaymentTable1 extends javax.swing.JDialog {

    
    public DistributorPaymentTable1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadDistributorPayment();
    }

   private void loadDistributorPayment() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `distributor_payment` " 
                    + " INNER JOIN `distributor` ON `distributor_payment`.`distributor_id` = `distributor`.`id`"
                    + " INNER JOIN `payment_status` ON `distributor_payment`.`payment_status_id`= `payment_status`.`id`"
                    + " INNER JOIN `payment_method` ON `distributor_payment`.`payment_method_id`=`payment_method`.`id` "
                   + "WHERE `payment_status`.`status` = 'Complete'"
                    + "ORDER BY `distributor_payment`.`id` ASC  ");

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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField7 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 830, 550));

        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 150, 40));

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel11.setText("Search");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, -1, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-print-25.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 50, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        DefaultTableModel ob = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        jTable1.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(jTextField7.getText()));
    }//GEN-LAST:event_jTextField7KeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        try {
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please Select a Row", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String distributorID = jTable1.getValueAt(selectedRow, 0).toString();
    String paymentID = jTable1.getValueAt(selectedRow, 1).toString();
    String salary = jTable1.getValueAt(selectedRow, 2).toString();
    String paymentDate = jTable1.getValueAt(selectedRow, 3).toString();
    String description = jTable1.getValueAt(selectedRow, 4).toString();
    String paymentStatus = jTable1.getValueAt(selectedRow, 5).toString();
    String paymentMethod = jTable1.getValueAt(selectedRow, 6).toString();

    JFileChooser dialog = new JFileChooser();
    dialog.setSelectedFile(new File("DistributorPaymentSlip.pdf"));
    int dialogResult = dialog.showSaveDialog(null);

    if (dialogResult == JFileChooser.APPROVE_OPTION) {
        String filePath = dialog.getSelectedFile().getPath();
        Document myDocument = new Document(PageSize.A4, 50, 50, 50, 50);

        try {
            PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
            myDocument.open();

            // Title
            Paragraph title = new Paragraph("Distributor Payment Slip",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20));
            title.setAlignment(Element.ALIGN_CENTER);
            myDocument.add(title);

            Paragraph date = new Paragraph("Generated on: " + new Date().toString(),
                    FontFactory.getFont(FontFactory.HELVETICA, 10));
            date.setAlignment(Element.ALIGN_RIGHT);
            myDocument.add(date);

            myDocument.add(Chunk.NEWLINE);
            myDocument.add(new LineSeparator());
            myDocument.add(Chunk.NEWLINE);

            // Distributor Details
            myDocument.add(new Paragraph("Distributor Details",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            myDocument.add(Chunk.NEWLINE);

            PdfPTable distTable = new PdfPTable(2);
            distTable.setWidthPercentage(100);
            distTable.setSpacingBefore(5f);
            distTable.setSpacingAfter(10f);

            distTable.addCell("Distributor ID:");
            distTable.addCell(distributorID);
            distTable.addCell("Payment ID:");
            distTable.addCell(paymentID);
            distTable.addCell("Description:");
            distTable.addCell(description);

            myDocument.add(distTable);

            myDocument.add(new LineSeparator());
            myDocument.add(Chunk.NEWLINE);

            // Payment Details
            myDocument.add(new Paragraph("Payment Details",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            myDocument.add(Chunk.NEWLINE);

            PdfPTable payTable = new PdfPTable(2);
            payTable.setWidthPercentage(100);
            payTable.setSpacingBefore(5f);
            payTable.setSpacingAfter(10f);

            payTable.addCell("Salary:");
            payTable.addCell(salary);
            payTable.addCell("Payment Date:");
            payTable.addCell(paymentDate);
            payTable.addCell("Payment Status:");
            payTable.addCell(paymentStatus);
            payTable.addCell("Payment Method:");
            payTable.addCell(paymentMethod);

            myDocument.add(payTable);

            myDocument.add(new LineSeparator());
            myDocument.add(Chunk.NEWLINE);

            Paragraph thankYou = new Paragraph("Thank you for your cooperation!",
                    FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12));
            thankYou.setAlignment(Element.ALIGN_CENTER);
            myDocument.add(thankYou);

            myDocument.close();
            JOptionPane.showMessageDialog(null, "Report was successfully generated");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
} catch (Exception e) {
    e.printStackTrace();
}
       
    }//GEN-LAST:event_jButton5ActionPerformed

    
    public static void main(String args[]) {
         FlatMacLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DistributorPaymentTable1 dialog = new DistributorPaymentTable1(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
