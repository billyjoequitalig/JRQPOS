/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jrq.jrqpos;

/**
 *
 * @author billy
 */
public class frmMain extends javax.swing.JFrame {

    frmUserManagement Users = new frmUserManagement();
    frmLogin Login = new frmLogin();
    frmStockManagent Stocks = new frmStockManagent();
    jdCategory jdc = new jdCategory();
    public static String UserID;
    frmSale Sale;

    public frmMain(String UserID) {
        frmMain.UserID = UserID;
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        Sale = new frmSale(UserID);
        jpMiddle.add(Sale);
        Sale.txtProductID.requestFocusInWindow();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        panelLeft = new javax.swing.JPanel();
        tbtnManageUsers = new javax.swing.JButton();
        btnSale = new javax.swing.JButton();
        btnMangeStocks = new javax.swing.JButton();
        btnSupplier = new javax.swing.JButton();
        panelUp = new javax.swing.JPanel();
        panelButtom = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jpMiddle = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        panelLeft.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbtnManageUsers.setText("Manage Users");
        tbtnManageUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnManageUsersActionPerformed(evt);
            }
        });
        panelLeft.add(tbtnManageUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 120, 120));

        btnSale.setText("Sale");
        btnSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaleActionPerformed(evt);
            }
        });
        panelLeft.add(btnSale, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 120));

        btnMangeStocks.setText("Manage Stocks");
        btnMangeStocks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMangeStocksActionPerformed(evt);
            }
        });
        panelLeft.add(btnMangeStocks, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 120, 120));

        btnSupplier.setText("Manage Supplier");
        btnSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierActionPerformed(evt);
            }
        });
        panelLeft.add(btnSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 120, 120));

        getContentPane().add(panelLeft, java.awt.BorderLayout.LINE_START);

        javax.swing.GroupLayout panelUpLayout = new javax.swing.GroupLayout(panelUp);
        panelUp.setLayout(panelUpLayout);
        panelUpLayout.setHorizontalGroup(
            panelUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelUpLayout.setVerticalGroup(
            panelUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(panelUp, java.awt.BorderLayout.PAGE_START);

        btnLogout.setText("Logout");
        btnLogout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jButton3.setText("Report");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelButtomLayout = new javax.swing.GroupLayout(panelButtom);
        panelButtom.setLayout(panelButtomLayout);
        panelButtomLayout.setHorizontalGroup(
            panelButtomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtomLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(370, Short.MAX_VALUE))
        );
        panelButtomLayout.setVerticalGroup(
            panelButtomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelButtomLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(panelButtom, java.awt.BorderLayout.PAGE_END);

        jpMiddle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jpMiddle.setLayout(new java.awt.CardLayout());
        getContentPane().add(jpMiddle, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaleActionPerformed
        // TODO add your handling code here:
        jpMiddle.removeAll();
        jpMiddle.add(Sale);
        jpMiddle.revalidate();
        jpMiddle.repaint();
        Sale.txtProductID.requestFocusInWindow();
    }//GEN-LAST:event_btnSaleActionPerformed

    private void btnMangeStocksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMangeStocksActionPerformed
        // TODO add your handling code here:
        jpMiddle.removeAll();
        jpMiddle.add(Stocks);
        jpMiddle.revalidate();
        jpMiddle.repaint();
        Stocks.txtProductID.requestFocusInWindow();
        Stocks.Search();

    }//GEN-LAST:event_btnMangeStocksActionPerformed

    private void tbtnManageUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnManageUsersActionPerformed
        // TODO add your handling code here:
        jpMiddle.removeAll();
        jpMiddle.add(Users);
        jpMiddle.revalidate();
        jpMiddle.repaint();
    }//GEN-LAST:event_tbtnManageUsersActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Login.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnSupplierActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new frmMain(UserID).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMangeStocks;
    private javax.swing.JButton btnSale;
    private javax.swing.JButton btnSupplier;
    private javax.swing.JButton jButton3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jpMiddle;
    private javax.swing.JPanel panelButtom;
    private javax.swing.JPanel panelLeft;
    private javax.swing.JPanel panelUp;
    private javax.swing.JButton tbtnManageUsers;
    // End of variables declaration//GEN-END:variables
}
