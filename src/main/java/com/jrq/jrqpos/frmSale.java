/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.jrq.jrqpos;

import com.jrq.Queries.Sale;
import static com.jrq.Queries.Sale.rs;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author billy
 */
public class frmSale extends javax.swing.JPanel {

    Sale db = new Sale();
    DBConnection DBCon = new DBConnection("localhost", "3306", "jrqdb", "root", "001995234");
    private double total;
    String SaleID;
    String getUserID;
    frmPay pay;

    public frmSale(String UserID) {
        getUserID = frmMain.UserID = UserID;
        initComponents();
    }

    public String SaleID() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        SaleID = "SALE-" + dtf.format(now);
        return SaleID;
    }

    public DefaultTableModel getJtable() {
        DefaultTableModel model = (DefaultTableModel) tblSale.getModel();
        model.setRowCount(0);
        return model; //tblSales is your JTable instance
    }

    public double GetTotal() {
        total = Double.parseDouble(lblPrice.getText());
        return total; // Return the total value
    }

    public void clear() {
        lblPrice.setText("0.00");
        DefaultTableModel model = (DefaultTableModel) tblSale.getModel();
        model.setRowCount(0); // Removes all rows from the model
        btnPay.setEnabled(false);
        btnNewTransac.setEnabled(false);
        btnVoid.setEnabled(false);
        lblTItem.setText("0");
    }

    public String Date() {
        DateTimeFormatter Date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return Date.format(now);
    }

    public String Time() {
        DateTimeFormatter Time = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return Time.format(now);
    }

    public boolean ValidateStock(double AvailableQty, double GetQty) {
        if (AvailableQty < 0) {
            JOptionPane.showMessageDialog(null, "Insufficient stock! You only have " + GetQty);
            return false;
        }
        return true;
    }

    private void updateRow(DefaultTableModel model, int rowIndex, double newQty, double SRP) {
        double newTotal = newQty * SRP;
        model.setValueAt(newQty, rowIndex, 3); // Update quantity
        model.setValueAt(String.format("%.2f", newTotal), rowIndex, 5); // Update total price
    }

    private void addNewRow(DefaultTableModel model, String productId, String productName, String uom, double SRP) {
        model.addRow(new Object[]{productId, productName, uom, 1, String.format("%.2f", SRP), String.format("%.2f", SRP)});
    }

    private void AddSale() {
        String getCashAmount = pay.getInputValue();
        String getTotal = pay.getTotal();
        String getChange = String.valueOf(pay.GetChange());
        try {
            DBCon.Open();
            db.AddSale(SaleID(), getUserID, getTotal, getCashAmount, getChange, Date(), Time(), lblTItem.getText());
            DBCon.Close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void AddProductSold() {
        try {
            DBCon.Open();
            DefaultTableModel model = (DefaultTableModel) tblSale.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                String productId = model.getValueAt(i, 0).toString();
                double orderedQty = Double.parseDouble(model.getValueAt(i, 3).toString());
                db.SelectProduct(productId);
                double currentStock = 0;
                if (rs.next()) {
                    currentStock = rs.getDouble("QTY");
                }
                db.AddProductSold(SaleID(), "ProductID", "PricepU", "QTY", "UoM", "SRP", "ProfitpU", "DateSold");
            }
            DBCon.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void UpdateStocks() {
        try {
            // Open database connection
            DBCon.Open();
            // Loop through the table rows
            DefaultTableModel model = (DefaultTableModel) tblSale.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                // Get ProductID and QTY from the table
                String productId = model.getValueAt(i, 0).toString();
                double orderedQty = Double.parseDouble(model.getValueAt(i, 3).toString());
                // Fetch current stock from the database
                db.SelectProduct(productId); // Implement this method
                double currentStock = 0;
                if (rs.next()) {
                    currentStock = rs.getDouble("QTY");
                    if (currentStock < orderedQty) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Insufficient stock for Product ID: " + productId,
                                "Stock Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return; // Exit if stock is insufficient
                    }
                }
                // Calculate the updated stock
                double updatedStock = currentStock - orderedQty;
                // Update the stock in the database
                db.UpdateQty(String.valueOf(updatedStock), productId); // Implement this method
            }
            // Close database connection
            DBCon.Close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error updating stocks: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
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

        jpUP = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtProductID = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lblPrice = new javax.swing.JLabel();
        lblPrice1 = new javax.swing.JLabel();
        jpRight = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtQTY = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jpLeft = new javax.swing.JPanel();
        jpDown = new javax.swing.JPanel();
        btnPay = new javax.swing.JButton();
        btnNewTransac = new javax.swing.JButton();
        btnVoid = new javax.swing.JButton();
        jpCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSale = new javax.swing.JTable();
        lbl1 = new javax.swing.JLabel();
        lblTItem = new javax.swing.JLabel();

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
                .addContainerGap(259, Short.MAX_VALUE))
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
        lblPrice.setForeground(new java.awt.Color(0, 0, 204));
        lblPrice.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPrice.setText("0.00");
        lblPrice.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lblPrice1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblPrice1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPrice1.setText("Total :");
        lblPrice1.setToolTipText("");
        lblPrice1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblPrice1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPrice)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblPrice, lblPrice1});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPrice1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

        btnPay.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnPay.setText("Pay");
        btnPay.setEnabled(false);
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });

        btnNewTransac.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnNewTransac.setText("New");
        btnNewTransac.setEnabled(false);
        btnNewTransac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewTransacActionPerformed(evt);
            }
        });

        btnVoid.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnVoid.setText("Void");
        btnVoid.setEnabled(false);
        btnVoid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpDownLayout = new javax.swing.GroupLayout(jpDown);
        jpDown.setLayout(jpDownLayout);
        jpDownLayout.setHorizontalGroup(
            jpDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDownLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewTransac, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVoid, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(744, Short.MAX_VALUE))
        );
        jpDownLayout.setVerticalGroup(
            jpDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDownLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jpDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoid, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNewTransac, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
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

        lbl1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl1.setText("# of Items:");
        lbl1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblTItem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTItem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTItem.setText("0");
        lblTItem.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jpCenterLayout = new javax.swing.GroupLayout(jpCenter);
        jpCenter.setLayout(jpCenterLayout);
        jpCenterLayout.setHorizontalGroup(
            jpCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCenterLayout.createSequentialGroup()
                .addContainerGap(900, Short.MAX_VALUE)
                .addComponent(lbl1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTItem)
                .addContainerGap())
            .addGroup(jpCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpCenterLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jpCenterLayout.setVerticalGroup(
            jpCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCenterLayout.createSequentialGroup()
                .addContainerGap(301, Short.MAX_VALUE)
                .addGroup(jpCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTItem)
                    .addComponent(lbl1))
                .addContainerGap())
            .addGroup(jpCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpCenterLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                    .addGap(33, 33, 33)))
        );

        add(jpCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtProductIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductIDActionPerformed
        // TODO add your handling code here:
        // Initialize the table model
        DefaultTableModel model = (DefaultTableModel) tblSale.getModel();
        model.setColumnIdentifiers(new Object[]{"ProductID", "Product Name", "UoM", "QTY", "SRP", "Total"}); // Ensure the "Total" column is added
        boolean productExists = false;
        String GetID = txtProductID.getText();
        double totalPrice; // Overall total amount
        try {
            // Open the database connection
            DBCon.Open();
            db.SelectProduct(GetID); // Assuming this fetches product details
            if (rs.next()) {
                // Fetch product details
                String ProductName = rs.getString("ProductName");
                String uom = rs.getString("uom");
                double SRP = rs.getDouble("SRP");
                double GetQty = rs.getDouble("QTY");
                // Check if the product already exists in the table
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).toString().equals(GetID)) {
                        double CurrentQty = Double.parseDouble(model.getValueAt(i, 3).toString());
                        double newQty = CurrentQty + 1;
                        if (!ValidateStock(GetQty - newQty, GetQty)) {
                            return;
                        }
                        updateRow(model, i, newQty, SRP);
                        productExists = true;
                        break;
                    }
                }
                // If the product does not exist, add a new row
                if (!productExists) {
                    addNewRow(model, GetID, ProductName, uom, SRP);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Product not found in stock!");
            }
            DBCon.Close();
            // Calculate the overall total price
            totalPrice = 0.0;
            double totalQty = 0.0;
            for (int i = 0; i < model.getRowCount(); i++) {
                double rowTotal = Double.parseDouble(model.getValueAt(i, 5).toString()); // Total price per row
                totalPrice += rowTotal; // Accumulate total price
                double rowQty = Double.parseDouble(model.getValueAt(i, 3).toString()); // QTY column
                totalQty += rowQty;
            }
            // Update the lblPrice with the overall total amount
            lblPrice.setText(String.format("%.2f", totalPrice));
            lblTItem.setText(String.valueOf(totalQty));
            // Disable table editing
            tblSale.setDefaultEditor(Object.class, null);
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Clear the input field
            txtProductID.setText("");
            btnPay.setEnabled(true);
            btnNewTransac.setEnabled(true);
        }
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
                clear();
            }
            case JOptionPane.NO_OPTION ->
                System.out.println("You selected: No");
            default ->
                System.out.println("Dialog closed without a selection.");
        }
    }//GEN-LAST:event_btnNewTransacActionPerformed

    private void btnVoidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVoidActionPerformed

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed
        // TODO add your handling code here:
        total = Double.parseDouble(lblPrice.getText());
        pay = new frmPay(this, getUserID);
        pay.setVisible(true);
        AddSale();
        UpdateStocks();
        clear();
    }//GEN-LAST:event_btnPayActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNewTransac;
    private javax.swing.JButton btnPay;
    private javax.swing.JButton btnVoid;
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
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblPrice1;
    private javax.swing.JLabel lblTItem;
    private javax.swing.JTable tblSale;
    public javax.swing.JTextField txtProductID;
    private javax.swing.JTextField txtQTY;
    // End of variables declaration//GEN-END:variables
}
