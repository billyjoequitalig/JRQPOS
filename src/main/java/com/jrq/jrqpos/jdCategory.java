/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.jrq.jrqpos;

import com.jrq.Queries.Category;
import static com.jrq.Queries.Category.rs;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author billy
 */
public class jdCategory extends javax.swing.JDialog {

    Category db = new Category();
    DBConnection DBCon = new DBConnection("localhost", "3306", "jrqdb", "root", "001995234");
    String CatID;
    String SubID;
    String VarID;
    frmStockManagent stock;
    String GetID;
    String GetCatID;
    String GetSubCatID;

    public jdCategory(frmStockManagent Stocks) {
        this.stock = Stocks;
        setUndecorated(true);
        initComponents();
        this.pack();
        setLocationRelativeTo(null);
        rbbtnGroup.add(rbCategory);
        rbbtnGroup.add(rbSubCategory);
        rbbtnGroup.add(rbVariant);
        rbCategory.setSelected(true);
        togglerb(true, false, false);
        //toggleBtn(true, false, false);
        if (cbCategory.getSelectedIndex() == -1) {
            toggleCombobox(true, false, false);
            
        }
    }

    private String SelectCatID(String table, JComboBox<String> comboBox, String ColumnID, String ColumnName) {
        try {
            DBCon.Open();
            db.SelectCategory(table, ColumnName);
            String getCBItem = comboBox.getSelectedItem().toString();
            while (rs.next()) {
                if (getCBItem.equals(rs.getString(ColumnName))) {
                    CatID = rs.getString(ColumnID);
                }
            }

            DBCon.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return CatID;
    }

    public JComboBox cbCategGetter() {
        return cbCategory;
    }

    public JComboBox cbSubCategGetter() {
        return cbSubCategory;
    }

    public JComboBox cbVariantGetter() {
        return cbVariant;
    }

    public void loadCategories(String table, String Name, JComboBox<String> comboBox) {
        try {
            DBCon.Open();
            db.SelectCategory(table, Name); // Pass the dynamic table nam
//            if (rbCategory.isSelected()) {
//                db.SelectSubVar(table, "CategoryID", GetID, "SubCategoryName");
//                //db.SelectSubVar("SubCategory", "CategoryID", GetID, "SubCategoryName");
//            } else if (rbSubCategory.isSelected()) {
//                db.SelectSubVar("Variant", "SubCategoryID", GetID, "VariantName");
//            }
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            while (rs.next()) {
                String value = rs.getString(Name); // Use the dynamic column name
                model.addElement(value); // Add each value to the model
            }
            DBCon.Close();
            //model.addElement("");
            comboBox.setModel(model); // Set the model to the combo box
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String CatID() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        CatID = "CAT-" + dtf.format(now);
        return CatID;
    }

    public String SubID() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        SubID = "Sub-" + dtf.format(now);
        return SubID;
    }

    public String VarID() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        VarID = "Var-" + dtf.format(now);
        return VarID;
    }

    public void ReadCateg() {
        try {
            DBCon.Open();
            db.SelectCategory("Category", "CategoryName");
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            while (rs.next()) {
                String value = rs.getString("CategoryName"); // Use the dynamic column name
                model.addElement(value); // Add each value to the model
            }
            DBCon.Close();
            //model.addElement("");
            cbCategory.setModel(model);
            DBCon.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

// Helper method to add a category or subcategory
    private void addCategory(JTextField textField, JComboBox<String> comboBox, String table, String ColumnID, String ColumnName) {
        // Get and trim the text from the input field
        String itemName = textField.getText().trim();
        if (itemName.isEmpty()) {
            // Show a warning if the text field is empty
            JOptionPane.showMessageDialog(null, "Please enter a " + table + " name.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Check if the item already exists in the combo box
        boolean exists = false;
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            if (itemName.equals(comboBox.getItemAt(i))) {
                exists = true;
                break;
            }
        }
        if (exists) {
            // Show a message if the item already exists
            JOptionPane.showMessageDialog(null, table + " Name already exists.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            // Add the item to the combo box
            comboBox.addItem(itemName);
            try {
                DBCon.Open();
                if (rbCategory.isSelected()) {
                    db.AddCategory(table, ColumnID, ColumnName, CatID, textField.getText());
                } else if (rbSubCategory.isSelected()) {
                    db.AddSubVar(table, ColumnID, ColumnName, "CategoryID", SubID, textField.getText(), GetCatID);
                } else if (rbVariant.isSelected()) {
                    db.AddSubVar(table, ColumnID, ColumnName, "SubCategoryID", VarID, textField.getText(), GetSubCatID);
                }
                DBCon.Close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
            // Clear the text field for new input
            textField.setText("");
        }
    }

    private void DeleteCategory(JTextField textField, JComboBox<String> comboBox, String ItemType, String table, String ColumnID, String ColumnName) {
        // Get and trim the text from the input field
        SelectCatID(table, comboBox, ColumnID, ColumnName);
        int selectedIndex = comboBox.getSelectedIndex();
        // Check if an item is selected
        if (selectedIndex != -1) {
            int confirmation = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to remove " + comboBox.getSelectedItem() + " from category?",
                    "Confirm Removal",
                    JOptionPane.YES_NO_OPTION
            );
            // If the user confirms, remove the item
            if (confirmation == JOptionPane.YES_OPTION) {
                // Call the appropriate method to handle further actions
                try {
                    DBCon.Open();
                    db.DeleteCategory(table, ColumnID, CatID);
                    DBCon.Close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                comboBox.removeItemAt(selectedIndex);
                textField.setText("");
            }
        } else {
            // Show a message if no item is selected
            JOptionPane.showMessageDialog(null, "Please select a category to remove.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void UpdateCategory(JTextField textField, JComboBox<String> comboBox, String table, String ColumnName, String ColumnID) {
        // Get and trim the text from the input field
        SelectCatID(table, comboBox, ColumnID, ColumnName);
        int selectedIndex = comboBox.getSelectedIndex();
        // Check if an item is selected
        if (selectedIndex != -1) {
            try {
                DBCon.Open();
                db.UpdateCategory(table, ColumnName, ColumnID, textField.getText(), CatID);
                DBCon.Close();
                // Update the combo box with the new value
                comboBox.removeItemAt(selectedIndex);
                comboBox.insertItemAt(textField.getText(), selectedIndex);
                comboBox.setSelectedIndex(selectedIndex);
                textField.setText("");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else {
            // Show a message if no item is selected
            JOptionPane.showMessageDialog(null, "Please select a category to remove.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public final void toggleCombobox(boolean Cat, boolean Sub, boolean Var) {
        txtCategory.setEnabled(Cat);
        cbCategory.setEnabled(Cat);

        txtSubCategory.setEnabled(Sub);
        cbSubCategory.setEnabled(Sub);

        txtVariant.setEnabled(Var);
        cbVariant.setEnabled(Var);

    }

    private void togglerb(boolean Cat, boolean Sub, boolean Var) {
        rbCategory.setEnabled(Cat);
        rbSubCategory.setEnabled(Sub);
        rbVariant.setEnabled(Var);
    }

    public void toggleBtn(boolean Add, boolean Update, boolean Del) {
        btnAdd.setEnabled(Add);
        btnUpdate.setEnabled(Update);
        btnDelete.setEnabled(Del);
    }

    public void getID(String table, String Colwhere, JComboBox<String> comboBox, String ID) {
        try {
            DBCon.Open();
            String getselected = comboBox.getSelectedItem() != null ? comboBox.getSelectedItem().toString() : "No item selected";
            db.SelectIDs(table, Colwhere, getselected);
            if (rs.next()) {
                GetID = rs.getString(ID);
                if (rbCategory.isSelected()) {
                    GetCatID = GetID;
                } else if (rbSubCategory.isSelected()) {
                    GetSubCatID = GetID;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void FirstLetterCaps(JTextField txtField) {
        String text = txtField.getText();
        if (!text.isEmpty()) {
            txtField.setText(text.substring(0, 1).toUpperCase() + text.substring(1));
        }
    }

    public void loadinCB(JComboBox<String> comboBox, String table, String WhereColID, String ColName) {
        try {
            DBCon.Open();
            db.SelectSubVar(table, WhereColID, GetID, ColName);
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            while (rs.next()) {
                String value = rs.getString(ColName); // Use the dynamic column name
                model.addElement(value); // Add each value to the model
            }
            comboBox.setModel(model);
            comboBox.setSelectedIndex(-1);
            DBCon.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
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

        rbbtnGroup = new javax.swing.ButtonGroup();
        center = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtCategory = new javax.swing.JTextField();
        cbCategory = new javax.swing.JComboBox<>();
        rbCategory = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        txtSubCategory = new javax.swing.JTextField();
        cbSubCategory = new javax.swing.JComboBox<>();
        rbSubCategory = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        txtVariant = new javax.swing.JTextField();
        cbVariant = new javax.swing.JComboBox<>();
        rbVariant = new javax.swing.JRadioButton();
        top = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bot = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        right = new javax.swing.JPanel();
        left = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setPreferredSize(new java.awt.Dimension(120, 120));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUpdate.setPreferredSize(new java.awt.Dimension(120, 120));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setPreferredSize(new java.awt.Dimension(120, 120));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Category"));

        txtCategory.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCategory.setMinimumSize(new java.awt.Dimension(150, 40));
        txtCategory.setPreferredSize(new java.awt.Dimension(150, 40));
        txtCategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCategoryKeyReleased(evt);
            }
        });

        cbCategory.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbCategory.setPreferredSize(new java.awt.Dimension(150, 40));
        cbCategory.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCategoryItemStateChanged(evt);
            }
        });
        cbCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoryActionPerformed(evt);
            }
        });

        rbCategory.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rbCategory.setText("Edit Category");
        rbCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCategoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rbCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbCategory, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCategory, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbCategory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Sub Category"));

        txtSubCategory.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSubCategory.setMinimumSize(new java.awt.Dimension(150, 40));
        txtSubCategory.setPreferredSize(new java.awt.Dimension(150, 40));
        txtSubCategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSubCategoryKeyReleased(evt);
            }
        });

        cbSubCategory.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbSubCategory.setPreferredSize(new java.awt.Dimension(150, 40));
        cbSubCategory.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbSubCategoryItemStateChanged(evt);
            }
        });
        cbSubCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubCategoryActionPerformed(evt);
            }
        });

        rbSubCategory.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rbSubCategory.setText("Edit Sub Category");
        rbSubCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSubCategoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbSubCategory, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSubCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbSubCategory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbSubCategory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSubCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbSubCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Variant/Brand"));

        txtVariant.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtVariant.setMinimumSize(new java.awt.Dimension(150, 40));
        txtVariant.setPreferredSize(new java.awt.Dimension(150, 40));
        txtVariant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVariantKeyReleased(evt);
            }
        });

        cbVariant.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbVariant.setPreferredSize(new java.awt.Dimension(150, 40));
        cbVariant.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbVariantItemStateChanged(evt);
            }
        });

        rbVariant.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rbVariant.setText("Edit Variant/Brand");
        rbVariant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbVariantActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbVariant, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtVariant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbVariant, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbVariant)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtVariant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbVariant, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout centerLayout = new javax.swing.GroupLayout(center);
        center.setLayout(centerLayout);
        centerLayout.setHorizontalGroup(
            centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(centerLayout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        centerLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdd, btnDelete, btnUpdate});

        centerLayout.setVerticalGroup(
            centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        centerLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdd, btnDelete, btnUpdate});

        getContentPane().add(center, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Manage Categories");

        javax.swing.GroupLayout topLayout = new javax.swing.GroupLayout(top);
        top.setLayout(topLayout);
        topLayout.setHorizontalGroup(
            topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addContainerGap())
        );
        topLayout.setVerticalGroup(
            topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(top, java.awt.BorderLayout.PAGE_START);

        btnExit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnExit.setText("Exit");
        btnExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExit.setPreferredSize(new java.awt.Dimension(120, 40));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout botLayout = new javax.swing.GroupLayout(bot);
        bot.setLayout(botLayout);
        botLayout.setHorizontalGroup(
            botLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(259, Short.MAX_VALUE))
        );
        botLayout.setVerticalGroup(
            botLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(bot, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout rightLayout = new javax.swing.GroupLayout(right);
        right.setLayout(rightLayout);
        rightLayout.setHorizontalGroup(
            rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        rightLayout.setVerticalGroup(
            rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 661, Short.MAX_VALUE)
        );

        getContentPane().add(right, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout leftLayout = new javax.swing.GroupLayout(left);
        left.setLayout(leftLayout);
        leftLayout.setHorizontalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        leftLayout.setVerticalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 661, Short.MAX_VALUE)
        );

        getContentPane().add(left, java.awt.BorderLayout.LINE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        togglerb(true, false, false);
        rbCategory.setSelected(true);
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        CatID();
        SubID();
        VarID();
        if (rbCategory.isSelected()) {
            addCategory(txtCategory, cbCategory, "Category", "CategoryID", "CategoryName");
        } else if (rbSubCategory.isSelected()) {
            addCategory(txtSubCategory, cbSubCategory, "SubCategory", "SubCategoryID", "SubCategoryName");
        } else if (rbVariant.isSelected()) {
            addCategory(txtVariant, cbVariant, "Variant", "VariantID", "VariantName");
        }
        this.getID("Category", "CategoryName", stock.cbCategGetter(), "CategoryID");
        this.loadinCB(stock.cbSubGetter(), "SubCategory", "CategoryID", "SubCategoryName");
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (rbCategory.isSelected()) {
            UpdateCategory(txtCategory, cbCategory, "Category", "CategoryName", "CategoryID");
        } else if (rbSubCategory.isSelected()) {
            UpdateCategory(txtSubCategory, cbSubCategory, "SubCategory", "SubCategoryName", "SubCategoryID");
        } else if (rbVariant.isSelected()) {
            UpdateCategory(txtVariant, cbVariant, "Variant", "VariantName", "VariantID");
        }
        
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (rbCategory.isSelected()) {
            DeleteCategory(txtCategory, cbCategory, "Category", "Category", "CategoryID", "CategoryName");
        } else if (rbSubCategory.isSelected()) {
            DeleteCategory(txtCategory, cbSubCategory, "Sub-Category", "SubCategory", "SubCategoryID", "SubCategoryName");
        } else if (rbVariant.isSelected()) {
            DeleteCategory(txtCategory, cbVariant, "Product Variant", "Variant", "VariantID", "VariantName");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void cbCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCategoryActionPerformed

    private void cbCategoryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoryItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            getID("Category", "CategoryName", cbCategory, "CategoryID");
            loadinCB(cbSubCategory, "SubCategory", "CategoryID", "SubCategoryName");
            if (cbCategory.getSelectedIndex() != -1) {
                toggleCombobox(true, false, false);
                togglerb(true, true, false);
                toggleBtn(true, true, true);
            }
        }
    }//GEN-LAST:event_cbCategoryItemStateChanged

    private void cbSubCategoryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbSubCategoryItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            if (cbSubCategory.getSelectedIndex() != -1) {
                toggleCombobox(false, true, false);
                togglerb(true, true, true);
            }
            // get SElected ID
            getID("SubCategory", "SubCategoryName", cbSubCategory, "SubCategoryID");
            loadinCB(cbVariant, "Variant", "SubCategoryID", "VariantName");
            cbVariant.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_cbSubCategoryItemStateChanged

    private void rbSubCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSubCategoryActionPerformed
        // TODO add your handling code here:
        toggleCombobox(false, true, false);
    }//GEN-LAST:event_rbSubCategoryActionPerformed

    private void rbCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCategoryActionPerformed
        // TODO add your handling code here:
        toggleCombobox(true, false, false);
    }//GEN-LAST:event_rbCategoryActionPerformed

    private void rbVariantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbVariantActionPerformed
        // TODO add your handling code here:
        toggleCombobox(false, false, true);
        togglerb(false, false, true);
    }//GEN-LAST:event_rbVariantActionPerformed

    private void cbSubCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSubCategoryActionPerformed

    private void cbVariantItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbVariantItemStateChanged
        // TODO add your handling code here:
        getID("Variant", "VariantID", cbSubCategory, "VariantID");
        //JOptionPane.showMessageDialog(null, GetID);
    }//GEN-LAST:event_cbVariantItemStateChanged

    private void txtCategoryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCategoryKeyReleased
        // TODO add your handling code here:
        FirstLetterCaps(txtCategory);
    }//GEN-LAST:event_txtCategoryKeyReleased

    private void txtSubCategoryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubCategoryKeyReleased
        // TODO add your handling code here:
        FirstLetterCaps(txtSubCategory);
    }//GEN-LAST:event_txtSubCategoryKeyReleased

    private void txtVariantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVariantKeyReleased
        // TODO add your handling code here:
        FirstLetterCaps(txtVariant);
    }//GEN-LAST:event_txtVariantKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bot;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbCategory;
    private javax.swing.JComboBox<String> cbSubCategory;
    private javax.swing.JComboBox<String> cbVariant;
    private javax.swing.JPanel center;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel left;
    private javax.swing.JRadioButton rbCategory;
    private javax.swing.JRadioButton rbSubCategory;
    private javax.swing.JRadioButton rbVariant;
    private javax.swing.ButtonGroup rbbtnGroup;
    private javax.swing.JPanel right;
    private javax.swing.JPanel top;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextField txtSubCategory;
    private javax.swing.JTextField txtVariant;
    // End of variables declaration//GEN-END:variables
}
