/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import model.MySQL;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.MediaTracker;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author rid
 */
public class Delivery extends javax.swing.JPanel {
private HomeDB ODB;
private int totalOrdersCount;
    /**
     * Creates new form Order
     */
    public Delivery(HomeDB ODB) {
        initComponents();
          ShowData();
        this.ODB = ODB;
        
        totalOrdersCount = calculateTotalOrdersCount();
       

        // Update display with both counts
        updateCountsDisplay(totalOrdersCount);
       
        //  calendar
        jCalendar1.getDayChooser().addPropertyChangeListener("day", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Date selectedDate = jCalendar1.getDate();
                showSpecialOrderNotification(selectedDate);
            }
        });
    }

  private void showSpecialOrderNotification(Date selectedDate) {
    Calendar targetCalendar = Calendar.getInstance();
    targetCalendar.set(2024, Calendar.OCTOBER, 10);  
    Date targetDate = targetCalendar.getTime();

    if (isSameDay(selectedDate, targetDate)) {
        String message = "\nSpecial Delivery -: Chocolate Cakes(5)\n"
                + selectedDate + ": \nLocation: Kiribathgoda \nTime: 11:30-12:00";

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/icons8-add-reminder-28.png"));

        if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.out.println("Icon not found!");
        }

       
        JOptionPane.showMessageDialog(this, message, "Special Order Notification", JOptionPane.INFORMATION_MESSAGE, icon);
    }
}

  

private boolean isSameDay(Date date1, Date date2) {
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(date1);
    Calendar cal2 = Calendar.getInstance();
    cal2.setTime(date2);
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
           cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
           cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
}

    private void ShowData(){
        try{
              ResultSet resultSet = MySQL.executeSearch(
    "SELECT * FROM `delivery` "
  + "INNER JOIN `delivery_status` ON `delivery`.`delivery_status_id` = `delivery_status`.`id` "
  
  + "INNER JOIN `distributor` ON `delivery`.`distributor_id` = `distributor`.`id`"
 
);

             DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
             model.setRowCount(0);
             
             while(resultSet.next()){
                 
                 
            Vector<String> vector = new Vector();
            
             vector.add(resultSet.getString("id"));
            vector.add(resultSet.getString("delivery_date"));
            vector.add(resultSet.getString("note"));
             vector.add(resultSet.getString("delivery_status.status"));
            vector.add(resultSet.getString("distributor.name"));
             vector.add(resultSet.getString("distributor.vehicle_no"));
               
            
            
            model.addRow(vector);
                 
             }
             
                    
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    
     private int calculateTotalOrdersCount() {
        int count = 0;
        ResultSet rs = null;

        try {
            String query = "SELECT COUNT(*) FROM `delivery`";
            rs = MySQL.executeSearch(query);

            if (rs.next()) {
                count = rs.getInt(1); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }
     private void updateCountsDisplay(int orderCount) {
        
        jButton4.setText("<html><div style='text-align: center;'><span style='font-size:14px;'><b>Total Dispatches </span>"
                          + "<span style='font-size:14px;'>" + orderCount + "</span><br></br></div></html>");
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jButton4 = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(811, 601));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-close-24.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 248, 244));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("InaiMathi", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(136, 92, 5));
        jLabel3.setText("Delivery History");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jButton1.setBackground(new java.awt.Color(255, 248, 244));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-print-28.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 40, 40));

        jButton2.setBackground(new java.awt.Color(249, 233, 222));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-invoice-38.png"))); // NOI18N
        jButton2.setText("Order History    ");
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 170, 40));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Delivery ID", "Delivery Date", "Note", "Distributor Name", "Vehicle No", "Delivery Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 700, 230));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 760, 300));

        jPanel4.setBackground(new java.awt.Color(255, 244, 229));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setBackground(new java.awt.Color(249, 233, 222));
        jButton3.setFont(new java.awt.Font("Hiragino Maru Gothic ProN", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-delivery-31 copy.png"))); // NOI18N
        jButton3.setText("Delivery Status   ");
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 220, 100));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(126, 89, 14));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-add-reminder-28.png"))); // NOI18N
        jLabel4.setText("Special Order Notification");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, -1, -1));
        jPanel4.add(jCalendar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 190, 140));

        jButton4.setBackground(new java.awt.Color(249, 233, 222));
        jButton4.setFont(new java.awt.Font("Lava Telugu", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(153, 102, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-deliver-31.png"))); // NOI18N
        jButton4.setText("   Total Deliveries                 ");
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 240, 100));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 770, 190));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked

    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        this.ODB.removeOrderPanel();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
          ShowData();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                                            
    JFileChooser dialog = new JFileChooser();
    dialog.setSelectedFile(new File("xyz" + ".pdf"));
    int dialogResult = dialog.showSaveDialog(null);
    
    if (dialogResult == JFileChooser.APPROVE_OPTION) {
        String filePath = dialog.getSelectedFile().getPath();
        
        Document doc = new Document();
        
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(filePath));
            doc.open();
            
            PdfPTable tb1 = new PdfPTable(5); 
            
            float[] columnWidths = {3f, 3f, 3f, 3f, 3f,};
            tb1.setWidths(columnWidths);
            
           
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL); 
            Font cellFont = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL); 
            
           
            PdfPCell header;
            header = new PdfPCell(new Phrase("Outlet Name", headerFont));
            tb1.addCell(header);
            header = new PdfPCell(new Phrase("Delivery Date", headerFont));
            tb1.addCell(header);
            header = new PdfPCell(new Phrase("Order Status", headerFont));
            tb1.addCell(header);
            header = new PdfPCell(new Phrase("Order Date", headerFont));
            tb1.addCell(header);
            header = new PdfPCell(new Phrase("Amount", headerFont));
            tb1.addCell(header);
           
            
           
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                PdfPCell cell;
                
                
                cell = new PdfPCell(new Phrase(jTable1.getValueAt(i, 0).toString(), cellFont));
                cell.setFixedHeight(20f);  
                tb1.addCell(cell);
                
                cell = new PdfPCell(new Phrase(jTable1.getValueAt(i, 1).toString(), cellFont));
                cell.setFixedHeight(20f);
                tb1.addCell(cell);
                
                cell = new PdfPCell(new Phrase(jTable1.getValueAt(i, 2).toString(), cellFont));
                cell.setFixedHeight(20f);
                tb1.addCell(cell);
                
                cell = new PdfPCell(new Phrase(jTable1.getValueAt(i, 3).toString(), cellFont));
                cell.setFixedHeight(20f);
                tb1.addCell(cell);

                cell = new PdfPCell(new Phrase(jTable1.getValueAt(i, 4).toString(), cellFont));
                cell.setFixedHeight(20f);
                tb1.addCell(cell);
                
                
            }

            doc.add(tb1);  
            doc.close();
            JOptionPane.showMessageDialog(null, "PDF Generated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error generating PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setVisible(true);
    OrderHistoryDB object = new OrderHistoryDB();
        object.setVisible(true);   
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setVisible(true);
        DeliveryStatus object = new DeliveryStatus(ODB, false);
        object.setVisible(true);       
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
