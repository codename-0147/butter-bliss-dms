package gui;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.SwingUtilities;
import org.apache.log4j.BasicConfigurator;

public class HomeOU extends javax.swing.JFrame {

    private static HomeOU obj = null;
    public static DashboardOU db;
    public static OrderOU od;
    public static Inventory i;
    public static GRN grn;
    public static InvoiceOU in;
    public static FeedbackOU fb;
    public static Product p;
    private outletReport or;

    private outletSales os;
    private OutletReturns oret;

    public HomeOU() {
        initComponents();
        jToggleButton2.setVisible(false);
        navigator = new PanelNavigator(jPanel2);
    }

    public static HomeOU getObj() {
        if (obj == null) {
            obj = new HomeOU();
        }
        return obj;
    }

    private void moveLeft() {
        jToggleButton1.setVisible(false);
        Thread t = new Thread(
                () -> {
                    for (int i = 248; i >= 71; i -= 10) {
                        jPanel1.setPreferredSize(new Dimension(i, jPanel1.getHeight()));
                        SwingUtilities.updateComponentTreeUI(jPanel1);

                        try {
                            Thread.sleep(1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    jToggleButton2.setVisible(true);
                    jLabel1.setVisible(false);
                    jLabel2.setVisible(false);
                    jLabel7.setVisible(false);
                    jButton1.setText("");
                    jButton2.setText("");
                    jButton6.setText("");
                    jButton10.setText("");
                    jButton12.setText("");
                    jButton7.setText("");
                    jButton4.setText("");
                    jButton3.setText("");
                    jButton5.setText("");
                    jButton9.setText("");

                }
        );
        t.start();
    }

    private void moveRight() {
        jToggleButton2.setVisible(false);
        Thread t = new Thread(
                () -> {
                    for (int i = 65; i <= 248; i += 10) {
                        jPanel1.setPreferredSize(new Dimension(i, jPanel1.getHeight()));
                        SwingUtilities.updateComponentTreeUI(jPanel1);

                        try {
                            Thread.sleep(1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    jToggleButton1.setVisible(true);
                    jLabel1.setVisible(true);
                    jLabel2.setVisible(true);
                    jLabel7.setVisible(true);
                    jButton1.setText("Dashboard");
                    jButton2.setText("Orders");
                    jButton6.setText("Distributor GRN");
                    jButton10.setText("Inventory");
                    jButton12.setText("Invoice & Payments");
                    jButton7.setText("Feedbacks");
                    jButton4.setText("Return & Damage");
                    jButton3.setText("Sales");
                    jButton5.setText("Reports");
                    jButton9.setText("Logout");

                }
        );
        t.start();
    }

    public void addDashboardPanel() {

        if (db == null) {
            jPanel2.removeAll();
            db = new DashboardOU(this);
            jPanel2.add(db, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);

        } else {
            jPanel2.removeAll();
            jPanel2.add(db, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }

    public void removeDashboardPanel() {
        jPanel2.remove(db);
        db = null;
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }

    public void addOrderPanel() {

        if (od == null) {
            jPanel2.removeAll();
            od = new OrderOU(this);
            jPanel2.add(od, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
            navigator.addPanel(od);
              updateNavButtons();


        } else {
            jPanel2.removeAll();
            jPanel2.add(od, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }
    }

    public void removeOrderPanel() {
        jPanel2.remove(od);
        od = null;
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }

    public void addInventoryPanel() {

        if (i == null) {
            jPanel2.removeAll();
            i = new Inventory(this);
            jPanel2.add(i, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        } else {
            jPanel2.removeAll();
            jPanel2.add(i, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }

    }

    public void removeInventoryPanel() {
        jPanel2.remove(i);
        i = null;
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }

    public void addGRNPanel() {

        if (grn == null) {
            jPanel2.removeAll();
            grn = new GRN(this);
            jPanel2.add(grn, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        } else {
            jPanel2.removeAll();
            jPanel2.add(grn, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }

    }

    public void removeGRNPanel() {
        jPanel2.remove(grn);
        grn = null;
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }

    public void addInvoicePanel() {

        if (in == null) {
            jPanel2.removeAll();
            in = new InvoiceOU(this);
            jPanel2.add(in, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        } else {
            jPanel2.removeAll();
            jPanel2.add(in, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }

    }

    public void removeInvoicePanel() {
        jPanel2.remove(in);
        in = null;
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }

    public void addFeedbackPanel() {

        if (fb == null) {
            jPanel2.removeAll();
            fb = new FeedbackOU(this);
            jPanel2.add(fb, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        } else {
            jPanel2.removeAll();
            jPanel2.add(fb, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }

    }

    public void removeFeedbackPanel() {
        jPanel2.remove(fb);
        fb = null;
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }

    public void addoutletSales() {

        if (os == null) {
            os = new outletSales1(this);
            jPanel2.add(os, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);

        } else {
            jPanel2.removeAll();
            jPanel2.add(os, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }

    }

    public void removeoutletSales() {
        jPanel2.remove(os);
        os = null;
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }


    public void addReturns(){
    
        if (oret == null) {
          oret = new OutletReturns(this);
          jPanel2.add(oret,BorderLayout.CENTER);
          SwingUtilities.updateComponentTreeUI(jPanel2);  
        } else {
            jPanel2.removeAll();
            jPanel2.add(oret,BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }

    }

    
    public void removeReturns() {
        jPanel2.remove(oret);
        oret = null;
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }

    public void addoutletReport() {
        if (or == null) {
            or = new outletReport(this);
            jPanel2.add(or, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);

        } else {
            jPanel2.removeAll();
            jPanel2.add(or, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(jPanel2);
        }

    }

    public void removeoutletReport() {
        jPanel2.remove(or);
        or = null;
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        back = new javax.swing.JLabel();
        forward = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setSize(new java.awt.Dimension(1200, 710));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowDeiconified(java.awt.event.WindowEvent evt) {
                formWindowDeiconified(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 246, 237));
        jPanel1.setPreferredSize(new java.awt.Dimension(242, 710));
        jPanel1.setVerifyInputWhenFocusTarget(false);

        jButton9.setBackground(new java.awt.Color(245, 219, 200));
        jButton9.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(66, 45, 22));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/logoutIcon.png"))); // NOI18N
        jButton9.setText("Logout");
        jButton9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton9.setPreferredSize(new java.awt.Dimension(164, 38));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Poppins SemiBold", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(111, 48, 2));
        jLabel7.setText("Others");

        jButton7.setBackground(new java.awt.Color(245, 219, 200));
        jButton7.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(66, 45, 22));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/feedbackIcon.png"))); // NOI18N
        jButton7.setText("Feedbacks");
        jButton7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton7.setPreferredSize(new java.awt.Dimension(164, 38));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(245, 219, 200));
        jButton6.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(66, 45, 22));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/grnIcon .png"))); // NOI18N
        jButton6.setText("Distributor GRN");
        jButton6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton6.setPreferredSize(new java.awt.Dimension(164, 38));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(245, 219, 200));
        jButton5.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(66, 45, 22));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reportsIcon.png"))); // NOI18N
        jButton5.setText("Reports");
        jButton5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton5.setPreferredSize(new java.awt.Dimension(164, 38));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(245, 219, 200));
        jButton4.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(66, 45, 22));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/returnIcon.png"))); // NOI18N
        jButton4.setText("Return & Damage");
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton4.setPreferredSize(new java.awt.Dimension(164, 38));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(245, 219, 200));
        jButton3.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(66, 45, 22));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/salesIcon.png"))); // NOI18N
        jButton3.setText("Sales");
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton3.setPreferredSize(new java.awt.Dimension(164, 38));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(245, 219, 200));
        jButton2.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(66, 45, 22));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ordersIcon.png"))); // NOI18N
        jButton2.setText("Orders");
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.setPreferredSize(new java.awt.Dimension(164, 38));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(245, 219, 200));
        jButton1.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(66, 45, 22));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/dashboardIcon.png"))); // NOI18N
        jButton1.setText("Dashboard");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.setPreferredSize(new java.awt.Dimension(164, 38));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Outlet Dashboard");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(111, 48, 2));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Butter Bliss");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/sub-logo.jpeg"))); // NOI18N

        jToggleButton2.setBackground(new java.awt.Color(255, 246, 237));
        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-filter-mail-32 (1).png"))); // NOI18N
        jToggleButton2.setBorder(null);
        jToggleButton2.setBorderPainted(false);
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(245, 219, 200));
        jButton10.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(66, 45, 22));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/inventoryIcon.png"))); // NOI18N
        jButton10.setText("Inventory");
        jButton10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton10.setPreferredSize(new java.awt.Dimension(164, 38));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(245, 219, 200));
        jButton12.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(66, 45, 22));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/invoiceIcon.png"))); // NOI18N
        jButton12.setText("Invoice & Payments");
        jButton12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton12.setPreferredSize(new java.awt.Dimension(164, 38));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jToggleButton1.setBackground(new java.awt.Color(255, 246, 237));
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-filter-mail-32 (1).png"))); // NOI18N
        jToggleButton1.setBorder(null);
        jToggleButton1.setBorderPainted(false);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jToggleButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jToggleButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToggleButton1)
                    .addComponent(jToggleButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2))
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(930, 631));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 251, 247));
        jPanel3.setPreferredSize(new java.awt.Dimension(930, 80));

        jLabel3.setFont(new java.awt.Font("Poppins Medium", 1, 14)); // NOI18N
        jLabel3.setText("Hi, Admin Name");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-waving-hand-light-skin-tone-32.png"))); // NOI18N
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextField1.setForeground(new java.awt.Color(0, 102, 102));
        jTextField1.setText("Search...");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-search-27.png"))); // NOI18N
        jButton20.setBorder(null);
        jButton20.setBorderPainted(false);
        jButton20.setContentAreaFilled(false);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(255, 251, 247));
        jButton18.setFont(new java.awt.Font("Poppins", 0, 10)); // NOI18N
        jButton18.setForeground(new java.awt.Color(0, 102, 102));
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-notification-32.png"))); // NOI18N
        jButton18.setText("Notification");
        jButton18.setBorder(null);
        jButton18.setBorderPainted(false);
        jButton18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton18.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton17.setBackground(new java.awt.Color(255, 251, 247));
        jButton17.setFont(new java.awt.Font("Poppins", 0, 10)); // NOI18N
        jButton17.setForeground(new java.awt.Color(0, 102, 102));
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-test-account-32.png"))); // NOI18N
        jButton17.setText("Profile");
        jButton17.setAlignmentY(0.0F);
        jButton17.setBorder(null);
        jButton17.setBorderPainted(false);
        jButton17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton17.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton17.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/back.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });

        forward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/forward.png"))); // NOI18N
        forward.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forwardMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(forward)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton20)
                .addGap(74, 74, 74)
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(jButton20)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(back)
                                    .addComponent(forward))
                                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, 79, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        addDashboardPanel();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        addOrderPanel();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        addoutletSales();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        addGRNPanel();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        addFeedbackPanel();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void formWindowDeiconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeiconified
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowDeiconified

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        addInventoryPanel();

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        addInvoicePanel();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        addReturns();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed

        moveRight();
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed

        moveLeft();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        addoutletReport();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
 navigator.goBack();
    }//GEN-LAST:event_backMouseClicked

    private void forwardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forwardMouseClicked
          navigator.goForward();
    }//GEN-LAST:event_forwardMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatMacLightLaf.setup();
        
        //log error fix
        BasicConfigurator.configure();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeOU().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JLabel forward;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    // End of variables declaration//GEN-END:variables
}
