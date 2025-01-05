/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.jrq.jrqpos;

import com.jrq.Queries.Sale;
import static com.jrq.Queries.Sale.rs;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author billy
 */
public class frmSale extends javax.swing.JPanel {

    Sale db = new Sale();
    DBConnection DBCon = new DBConnection("localhost", "3306", "jrqdb", "root", "001995234");

    /**
     * Creates new form frmSale
     */
    public frmSale() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpUP = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtProductID = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lblPrice = new javax.swing.JLabel();
        jpRight = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtQTY = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jpLeft = new javax.swing.JPanel();
        jpDown = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnNewTransac = new javax.swing.JButton();
        jpCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSale = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jpUP.setLayout(new java.awt.GridLayout(1, 0));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Product ID:");

        txtProductID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtProductID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProductID, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProductID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpUP.add(jPanel1);

        lblPrice.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblPrice.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPrice.setText("Total: 0.00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPrice)
                .addContainerGap(260, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPrice)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpUP.add(jPanel2);

        add(jpUP, java.awt.BorderLayout.PAGE_START);

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setText("+");

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton4.setText("-");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("QTY:");

        txtQTY.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel7.setText("Product Image");

        javax.swing.GroupLayout jpRightLayout = new javax.swing.GroupLayout(jpRight);
        jpRight.setLayout(jpRightLayout);
        jpRightLayout.setHorizontalGroup(
            jpRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpRightLayout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(jpRightLayout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQTY)))
                .addContainerGap())
        );

        jpRightLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton3, jButton4});

        jpRightLayout.setVerticalGroup(
            jpRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(txtQTY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(140, 140, 140))
        );

        jpRightLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton3, jButton4});

        add(jpRight, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout jpLeftLayout = new javax.swing.GroupLayout(jpLeft);
        jpLeft.setLayout(jpLeftLayout);
        jpLeftLayout.setHorizontalGroup(
            jpLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpLeftLayout.setVerticalGroup(
            jpLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        add(jpLeft, java.awt.BorderLayout.LINE_START);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Pay");

        btnNewTransac.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnNewTransac.setText("New");
        btnNewTransac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewTransacActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpDownLayout = new javax.swing.GroupLayout(jpDown);
        jpDown.setLayout(jpDownLayout);
        jpDownLayout.setHorizontalGroup(
            jpDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDownLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewTransac, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpDownLayout.setVerticalGroup(
            jpDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDownLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNewTransac, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jpDown, java.awt.BorderLayout.PAGE_END);

        tblSale.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tblSale.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblSale.setRowHeight(40);
        tblSale.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblSale);

        javax.swing.GroupLayout jpCenterLayout = new javax.swing.GroupLayout(jpCenter);
        jpCenter.setLayout(jpCenterLayout);
        jpCenterLayout.setHorizontalGroup(
            jpCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 895, Short.MAX_VALUE)
            .addGroup(jpCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpCenterLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jpCenterLayout.setVerticalGroup(
            jpCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 369, Short.MAX_VALUE)
            .addGroup(jpCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCenterLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        add(jpCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtProductIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductIDActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblSale.getModel();
        model.setColumnIdentifiers(new Object[]{"ProductID", "roduct Name", "UoM", "QTY", "SRP"}); // Set your column headers
        boolean productExists = false;
        String GetID = txtProductID.getText();
        double totalPrice = 0.0;
        try {
            DBCon.Open();
            db.SelectProduct(GetID);
            if (rs.next()) {
                String ProductName = rs.getString("ProductName");
                String uom = rs.getString("uom");
                String SRP = rs.getString("SRP");
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).toString().equals(GetID)) {
                        int CurrentQty = Integer.parseInt(model.getValueAt(i, 3).toString());
                        model.setValueAt(CurrentQty + 1, i, 3);
                        // Update price (optional, based on quantity logic)
                        double unitPrice = Double.parseDouble(SRP);
                        model.setValueAt(String.format("%.2f", unitPrice * (CurrentQty + 1)), i, 4);
                        productExists = true;
                        break;
                    }
                }
                // If the product does not exist, add a new row
                if (!productExists) {
                    model.addRow(new Object[]{GetID, ProductName, uom, 1, SRP});
                }
            } else {
                JOptionPane.showMessageDialog(null, "Product not found in stock!");
            }
            DBCon.Close();
            for (int i = 0; i < model.getRowCount(); i++) {
                totalPrice += Double.parseDouble(model.getValueAt(i, 4).toString());
            }
            lblPrice.setText("Total: " + String.format("%.2f", totalPrice));
            tblSale.setDefaultEditor(Object.class, null);

        } catch (HeadlessException | NumberFormatException | SQLException e) {
            System.out.println(e);
        }
        txtProductID.setText("");
    }//GEN-LAST:event_txtProductIDActionPerformed

    private void btnNewTransacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewTransacActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(
                null,
                "Do you want to proceed?",
                "Confirm Action",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        switch (response) {
            case JOptionPane.YES_OPTION -> {
                DefaultTableModel model = (DefaultTableModel) tblSale.getModel();
                model.setRowCount(0); // Removes all rows from the model
                lblPrice.setText("Total: 0.00");
            }
            case JOptionPane.NO_OPTION ->
                System.out.println("You selected: No");
            default ->
                System.out.println("Dialog closed without a selection.");
        }
    }//GEN-LAST:event_btnNewTransacActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNewTransac;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpCenter;
    private javax.swing.JPanel jpDown;
    private javax.swing.JPanel jpLeft;
    private javax.swing.JPanel jpRight;
    private javax.swing.JPanel jpUP;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JTable tblSale;
    public javax.swing.JTextField txtProductID;
    private javax.swing.JTextField txtQTY;
    // End of variables declaration//GEN-END:variables
}
