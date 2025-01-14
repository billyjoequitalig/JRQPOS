/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.jrq.jrqpos;

import com.jrq.Queries.StockManagement;
import static com.jrq.Queries.StockManagement.rs;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author billy
 */
public class frmStockManagent extends javax.swing.JPanel {

    StockManagement db = new StockManagement();
    DBConnection DBCon = new DBConnection("localhost", "3306", "jrqdb", "root", "001995234");
    String ProductID;

    /**
     * Creates new form frmManageStocks
     */
    //Column Sizes
    public final void JTableProduct() {
        tblProducts.setDefaultEditor(Object.class, null);
        TableColumnModel tcm = tblProducts.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(15);
        tcm.getColumn(1).setPreferredWidth(250);
        tcm.getColumn(2).setPreferredWidth(15);
        tcm.getColumn(3).setPreferredWidth(15);
        tcm.getColumn(4).setPreferredWidth(15);
        tcm.getColumn(5).setPreferredWidth(15);
        tcm.getColumn(6).setPreferredWidth(15);
        tcm.getColumn(7).setPreferredWidth(15);
        tcm.getColumn(8).setPreferredWidth(15);
        tblProducts.changeSelection(0, 0, false, false);
    }

    private void refreshTable() {

    }

    private void SearchID() {
        if (txtSearch.getText().equals("")) {
            clear();
        } else {
            try {
                DBCon.Open();
                db.SearchProductID(txtSearch.getText());
                tblProducts.setModel(DbUtils.resultSetToTableModel(rs));
                DBCon.Close();
                JTableProduct();
                SelectFromtbl();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void Search() {
        if (txtSearch.getText().equals("")) {
            clear();
        } else {
            try {
                DBCon.Open();
                db.SearchProduct(txtSearch.getText());
                tblProducts.setModel(DbUtils.resultSetToTableModel(rs));
                DBCon.Close();
                JTableProduct();
                SelectFromtbl();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public void ReadAllProduct() {
        try {
            DBCon.Open();
            db.ReadProducts();
            tblProducts.setModel(DbUtils.resultSetToTableModel(rs));
            int totalRows = tblProducts.getRowCount();
            lblTotalProduct.setText("# of Products: " + String.valueOf(totalRows));
            tblProducts.setDefaultEditor(Object.class, null);
        } catch (SQLException e) {
        }
    }

    public frmStockManagent() {
        initComponents();
        ReadAllProduct();
        JTableProduct();
    }

    private void clear() {
        txtProductID.setText("");
        txtProductName.setText("");
        txtQTY.setText("");
        txtSRP.setText("");
        txtUnitPrice.setText("");
        txtMarkup.setText("");
        cbSupplier.setSelectedIndex(-1);
        cbUOM.setSelectedIndex(-1);
        lblProfit.setText("0");
        updateButtonStates(true, false, false, false);
        //txtProductID.requestFocusInWindow();

    }

// Helper method to manage button states
    private void updateButtonStates(boolean addEnabled, boolean clearEnabled, boolean deleteEnabled, boolean updateEnabled) {
        btnAdd.setEnabled(addEnabled);
        btnClear.setEnabled(clearEnabled);
        btnDelete.setEnabled(deleteEnabled);
        btnUpdate.setEnabled(updateEnabled);
    }

    private void SelectFromtbl() {
        int row = tblProducts.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please select a product from the table.");
            return;
        }
        try {
            // Ensure the table has data
            if (tblProducts.getRowCount() == 0) {
                clear();
                return;
            }
            // Safely retrieve and set values
            ProductID = tblProducts.getValueAt(row, 0).toString();
            txtProductID.setText(ProductID);
            txtProductName.setText(tblProducts.getValueAt(row, 1).toString());
            cbUOM.setSelectedItem(tblProducts.getValueAt(row, 2).toString());
            txtQTY.setText(tblProducts.getValueAt(row, 3).toString());

            double up = Double.parseDouble(tblProducts.getValueAt(row, 4).toString());
            txtUnitPrice.setText(String.valueOf(up));

            double srp = Double.parseDouble(tblProducts.getValueAt(row, 5).toString());
            txtSRP.setText(String.valueOf(srp));

            txtMarkup.setText(tblProducts.getValueAt(row, 6).toString());
            cbSupplier.setSelectedItem(tblProducts.getValueAt(row, 7).toString());

            double Profit = srp - up;
            lblProfit.setText(String.valueOf(String.format("%.2f", Profit)));

            updateButtonStates(false, true, true, true);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while selecting the product. Please ensure the table data is complete.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format in table data. Please check the input.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage());
        }
        ProductID = tblProducts.getValueAt(row, 0).toString();
        txtProductID.setText(tblProducts.getValueAt(row, 0).toString());
        txtProductName.setText(tblProducts.getValueAt(row, 1).toString());
        cbUOM.setSelectedItem(tblProducts.getValueAt(row, 2).toString());
        txtQTY.setText(tblProducts.getValueAt(row, 3).toString());
        double up = Double.parseDouble(tblProducts.getValueAt(row, 4).toString());
        txtUnitPrice.setText(String.valueOf(up));
        double srp = Double.parseDouble(tblProducts.getValueAt(row, 5).toString());
        txtSRP.setText(String.valueOf(srp));
        txtMarkup.setText(tblProducts.getValueAt(row, 6).toString());
        cbSupplier.setSelectedItem(tblProducts.getValueAt(row, 7).toString());
        double Profit = srp - up;
        lblProfit.setText(String.valueOf(String.format("%.2f", Profit)));
        updateButtonStates(false, true, true, true);
    }

    private void CalculateUnitProfit() {
        if (txtSRP.getText().equals("")) {
            txtMarkup.setText("0");
        } else {
            double UnitPrice = Double.parseDouble(txtUnitPrice.getText());
            //int markup = Integer.parseInt(txtMarkup.getText());
            double SRP = Double.parseDouble(txtSRP.getText());
            double Markup = ((SRP - UnitPrice) / UnitPrice) * 100;
            int MarkupInt = (int) Markup;
            double Profit = SRP - UnitPrice;
            lblProfit.setText(String.valueOf(String.format("%.2f", Profit)));
            txtMarkup.setText(String.valueOf(MarkupInt));
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

        jpMiddle = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtQTY = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSRP = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtMarkup = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        cbSupplier = new javax.swing.JComboBox<>();
        txtProductID = new javax.swing.JTextField();
        cbUOM = new javax.swing.JComboBox<>();
        txtUnitPrice = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        lblProfit = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTotalProduct = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Search:");

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });
        txtSearch.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                txtSearchCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtSearchInputMethodTextChanged(evt);
            }
        });
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });

        tblProducts.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblProducts.setRowHeight(40);
        tblProducts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblProducts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblProducts.getTableHeader().setResizingAllowed(false);
        tblProducts.getTableHeader().setReorderingAllowed(false);
        tblProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblProductsMousePressed(evt);
            }
        });
        tblProducts.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblProductsKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblProducts);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Product Name:");

        txtProductName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("UoM:");

        txtQTY.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Quantity:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Unit Price:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("SRP:");

        txtSRP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSRP.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSRPCaretUpdate(evt);
            }
        });
        txtSRP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSRPMouseClicked(evt);
            }
        });
        txtSRP.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtSRPInputMethodTextChanged(evt);
            }
        });
        txtSRP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSRPActionPerformed(evt);
            }
        });
        txtSRP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSRPKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSRPKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Markup %:");

        txtMarkup.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMarkup.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMarkupCaretUpdate(evt);
            }
        });
        txtMarkup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarkupActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Product ID:");

        btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setEnabled(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Supplier:");

        cbSupplier.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Unknown", "D&L" }));
        cbSupplier.setSelectedIndex(-1);

        txtProductID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtProductID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductIDActionPerformed(evt);
            }
        });
        txtProductID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductIDKeyReleased(evt);
            }
        });

        cbUOM.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbUOM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pcs", "dz", "kg", "g", "L", "mL", "gal", "Meters", "Centimeters", "Inches", "Cartons", "Bags", "Rolls", "Boxes", "Pallets", "Bundles", "Crates", "Barrels " }));
        cbUOM.setSelectedIndex(-1);

        txtUnitPrice.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnClear.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnClear.setText("Clear");
        btnClear.setEnabled(false);
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Unit Profit:");

        lblProfit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblProfit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfit.setText("0");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Product Image");

        lblTotalProduct.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTotalProduct.setText("Total Product");

        javax.swing.GroupLayout jpMiddleLayout = new javax.swing.GroupLayout(jpMiddle);
        jpMiddle.setLayout(jpMiddleLayout);
        jpMiddleLayout.setHorizontalGroup(
            jpMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMiddleLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jpMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMiddleLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotalProduct))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMiddleLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMiddleLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblProfit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMiddleLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMarkup, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMiddleLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSRP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMiddleLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMiddleLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtQTY, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMiddleLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbUOM, 0, 150, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMiddleLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtProductID, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMiddleLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMiddleLayout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMiddleLayout.createSequentialGroup()
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jpMiddleLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbSupplier, cbUOM, txtMarkup, txtProductID, txtQTY, txtSRP});

        jpMiddleLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdd, btnClear, btnDelete, btnUpdate});

        jpMiddleLayout.setVerticalGroup(
            jpMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMiddleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMiddleLayout.createSequentialGroup()
                        .addGroup(jpMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblTotalProduct, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMiddleLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProductID, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProductName, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbUOM, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQTY, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSRP, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMarkup, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(lblProfit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        jpMiddleLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbSupplier, cbUOM, txtMarkup, txtProductID, txtProductName, txtQTY, txtSRP});

        jpMiddleLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdd, btnClear, btnDelete, btnUpdate});

        jpMiddleLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel11, jLabel13, jLabel14, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9});

        add(jpMiddle, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
        //SearchID();
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (txtProductID.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Insert Product ID");
        } else if (txtProductName.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Insert Product Name");
        } else if (cbUOM.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Please Insert Unit Of Measure ");
        } else if (txtQTY.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Insert Number of QTY ");
        } else if (txtUnitPrice.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Insert Unit Price ");
        } else if (txtSRP.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Insert SRP");
        } else if (cbSupplier.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Please Insert Supplier");
        } else {
            try {
                DBCon.Open();
                CalculateUnitProfit();
                db.AddProduct(txtProductID.getText(), txtProductName.getText(), cbUOM.getSelectedItem().toString(), txtQTY.getText(), txtUnitPrice.getText(), txtSRP.getText(), txtMarkup.getText(), cbSupplier.getSelectedItem().toString(), lblProfit.getText());
                DBCon.Close();
                Search();
                clear();
                JOptionPane.showMessageDialog(null, "Successful");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Product ID Already Exist");
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(
                null,
                "Do you want to proceed?",
                "Confirm Action",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        try {
            switch (response) {
                case JOptionPane.YES_OPTION -> {
                    System.out.println("You selected: Yes");
                    DBCon.Open(); // Open the database connection
                    db.DeleteProduct(txtProductID.getText()); // Delete the product
                    DBCon.Close(); // Close the database connection
                    Search();
                    //ReadAllProduct(); // Refresh the product list
                    clear(); // Clear input fields
                    JOptionPane.showMessageDialog(null, "Deleted successfully.");
                }
                case JOptionPane.NO_OPTION ->
                    System.out.println("You selected: No");
                default ->
                    System.out.println("Dialog closed without a selection.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                DBCon.Close(); // Ensure the connection is closed in case of an error
            } catch (SQLException closeException) {
                System.err.println("Failed to close the database connection: " + closeException.getMessage());
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(
                null,
                "Do you want to proceed?",
                "Confirm Action",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        try {
            switch (response) {
                case JOptionPane.YES_OPTION -> {
                    DBCon.Open();
                    CalculateUnitProfit();
                    db.UpdateProduct(txtProductID.getText(), txtProductName.getText(), cbUOM.getSelectedItem().toString(), txtQTY.getText(), txtUnitPrice.getText(), txtSRP.getText(), txtMarkup.getText(), cbSupplier.getSelectedItem().toString(), lblProfit.getText(), ProductID);
                    DBCon.Close();
                    ReadAllProduct();
                    Search();
                    clear();
                    JOptionPane.showMessageDialog(null, "Updated");
                }
                case JOptionPane.NO_OPTION ->
                    System.out.println("You selected: No");
                default ->
                    System.out.println("Dialog closed without a selection.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

//        try {
//            DBCon.Open();
//            db.UpdateProduct(txtProductID.getText(), txtProductName.getText(), cbUOM.getSelectedItem().toString(), txtQTY.getText(), txtUnitPrice.getText(), txtSRP.getText(), txtMarkup.getText(), cbSupplier.getSelectedItem().toString(), ProductID, lblProfit.getText());
//            DBCon.Close();
//            ReadAllProduct();
//            clear();
//            JOptionPane.showMessageDialog(null, "Updated");
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblProductsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductsMousePressed
        // TODO add your handling code here:
        SelectFromtbl();
    }//GEN-LAST:event_tblProductsMousePressed

    private void txtMarkupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarkupActionPerformed
        // TODO add your handling code here:        
    }//GEN-LAST:event_txtMarkupActionPerformed

    private void txtSRPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSRPMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txtSRPMouseClicked

    private void txtSRPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSRPActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_txtSRPActionPerformed

    private void txtSRPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSRPCaretUpdate
        // TODO add your handling code here:
        CalculateUnitProfit();
    }//GEN-LAST:event_txtSRPCaretUpdate

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        ReadAllProduct();
        Search();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtSRPInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtSRPInputMethodTextChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_txtSRPInputMethodTextChanged

    private void txtSRPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSRPKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtSRPKeyReleased

    private void txtSRPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSRPKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtSRPKeyTyped

    private void txtProductIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductIDKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtProductIDKeyReleased

    private void txtMarkupCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMarkupCaretUpdate
        // TODO add your handling code here:
//        double UnitPrice = Double.parseDouble(txtUnitPrice.getText());
//        double Markup = Double.parseDouble(txtMarkup.getText());
//        double SRP = UnitPrice + (UnitPrice * Markup / 100);
//        // Format SRP to two decimal places
//        txtSRP.setText(String.format("%.2f", SRP));
    }//GEN-LAST:event_txtMarkupCaretUpdate

    private void txtProductIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductIDActionPerformed
        // TODO add your handling code here:
        try {
            DBCon.Open();
            db.SearchProductID(txtProductID.getText());
            if (rs.next() && rs != null) {
                JOptionPane.showMessageDialog(null, "Product Exist");
                txtProductName.setText(rs.getString(2));
                cbUOM.setSelectedItem(rs.getString(3));
                txtQTY.setText(rs.getString(4));
                txtUnitPrice.setText(rs.getString(5));
                txtSRP.setText(rs.getString(6));
                txtMarkup.setText(rs.getString(7));
                cbSupplier.setSelectedItem(rs.getString(8));
                lblProfit.setText(rs.getString(9));
                updateButtonStates(false, true, true, true);
            }
            DBCon.Close();
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txtProductIDActionPerformed

    private void tblProductsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductsKeyPressed
        //TODO add your handling code here:
        int selectedRow = tblProducts.getSelectedRow(); // Get the currently selected row
        // Check which key is pressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
            // Move selection up
            if (selectedRow > 0) {
                //tblProducts.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
                SelectFromtbl();
            }
        } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
            // Move selection down
            if (selectedRow < tblProducts.getRowCount() - 1) {
                //tblProducts.setRowSelectionInterval(selectedRow + 1, selectedRow + 1);
                SelectFromtbl();
            }
        }
    }//GEN-LAST:event_tblProductsKeyPressed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        // TODO add your handling code here:

    }//GEN-LAST:event_txtSearchCaretUpdate

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchKeyPressed

    private void txtSearchCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtSearchCaretPositionChanged
        // TODO add your handling code here:


    }//GEN-LAST:event_txtSearchCaretPositionChanged

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        // TODO add your handling code here:


    }//GEN-LAST:event_txtSearchKeyTyped

    private void txtSearchInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtSearchInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchInputMethodTextChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbSupplier;
    private javax.swing.JComboBox<String> cbUOM;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpMiddle;
    private javax.swing.JLabel lblProfit;
    private javax.swing.JLabel lblTotalProduct;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtMarkup;
    public javax.swing.JTextField txtProductID;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtQTY;
    private javax.swing.JTextField txtSRP;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtUnitPrice;
    // End of variables declaration//GEN-END:variables
}
