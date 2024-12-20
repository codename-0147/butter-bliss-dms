/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

/**
 *
 * @author rid
 */
public class PaymentDB extends javax.swing.JPanel {
private HomeDB PB;
    /**
     * Creates new form Payment
     */
    public PaymentDB(HomeDB PB) {
        initComponents();
        this.PB = PB;
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
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton10.setBackground(new java.awt.Color(249, 233, 222));
        jButton10.setFont(new java.awt.Font("Hiragino Maru Gothic ProN", 1, 14)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-store-31.png"))); // NOI18N
        jButton10.setText("Payment Status           ");
        jButton10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton10.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 230, 120));

        jButton11.setBackground(new java.awt.Color(249, 233, 222));
        jButton11.setFont(new java.awt.Font("Hiragino Maru Gothic ProN", 0, 14)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-route-31.png"))); // NOI18N
        jButton11.setText("Payment Method Reports                                         ");
        jButton11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButton11.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel2.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 420, 40));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-close-24.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, -1, -1));

        jTable1.setBackground(new java.awt.Color(249, 233, 222));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Date", "Name", "Mobile", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 400, 220));

        jButton2.setBackground(new java.awt.Color(249, 233, 222));
        jButton2.setFont(new java.awt.Font("Hiragino Maru Gothic ProN", 1, 14)); // NOI18N
        jButton2.setText("Invoice Reports                     ");
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 230, 90));

        jButton3.setBackground(new java.awt.Color(249, 233, 222));
        jButton3.setFont(new java.awt.Font("Hiragino Maru Gothic ProN", 1, 14)); // NOI18N
        jButton3.setText("Payment Method                                                                          ");
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, 430, 90));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        this.PB.removePaymentPanel();
    }//GEN-LAST:event_jLabel10MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
