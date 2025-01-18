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
import java.util.List;
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
    String CatName;

    public jdCategory() {
        setUndecorated(true);
        initComponents();
        this.pack();
        setLocationRelativeTo(null);
    }

    public void setCategoryItems(List<String> categories) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (String category : categories) {
            model.addElement(category); // Add each category to the model
        }
        cbCategory.setModel(model); // Set the updated model to the combo box
    }

    private String SelectCategID() {

        try {
            DBCon.Open();
            db.SelectCategory();
            CatName = cbCategory.getSelectedItem().toString();
            while (rs.next()) {
                if (CatName.equals(rs.getString("CategoryName"))) {
                    CatID = rs.getString("CategoryID");
                }
            }
            DBCon.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return CatID;
    }

    private String SelectSubCategID() {

        try {
            DBCon.Open();
            db.SelectSubCategory();
            CatName = cbSubCategory.getSelectedItem().toString();
            while (rs.next()) {
                if (CatName.equals(rs.getString("SubCategoryName"))) {
                    CatID = rs.getString("SubCategoryID");
                }
            }
            DBCon.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return CatID;
    }

    private String SelectVarID() {

        try {
            DBCon.Open();
            db.SelectVariant();
            CatName = cbVariant.getSelectedItem().toString();
            while (rs.next()) {
                if (CatName.equals(rs.getString("VariantName"))) {
                    CatID = rs.getString("VariantID");
                }
            }
            DBCon.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return CatID;
    }

    public void ReadCategory() {
        try {
            DBCon.Open();
            db.SelectCategory();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            while (rs.next()) {
                String category = rs.getString("categoryName");
                model.addElement(category); // Add each category to the model
            }
            this.cbCategory.setModel(model); // Set the model to the combo box
            DBCon.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void ReadSubCategory() {
        try {
            DBCon.Open();
            db.SelectSubCategory();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            while (rs.next()) {
                String category = rs.getString("SubcategoryName");
                model.addElement(category); // Add each category to the model
            }
            this.cbSubCategory.setModel(model); // Set the model to the combo box
            DBCon.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void ReadVariants() {
        try {
            DBCon.Open();
            db.SelectVariant();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            while (rs.next()) {
                String category = rs.getString("VariantName");
                model.addElement(category); // Add each category to the model
            }
            this.cbVariant.setModel(model); // Set the model to the combo box
            DBCon.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void UpdateCategory() {
        SelectCategID();
        try {
            DBCon.Open();
            db.UpdateCategory(txtCategory.getText(), CatID);
            DBCon.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void UpdateSubCategory() {
        SelectSubCategID();
        try {
            DBCon.Open();
            db.UpdateSubCategory(txtSubCategory.getText(), CatID);
            DBCon.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void UpdateVariant() {
        SelectVarID();
        try {
            DBCon.Open();
            db.UpdateVariant(txtVariant.getText(), CatID);
            DBCon.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void DeleteCategory() {
        SelectCategID();
        try {
            DBCon.Open();
            db.DeleteCategory(CatID);
            DBCon.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void DeleteSubCategory() {
        SelectSubCategID();
        try {
            DBCon.Open();
            db.DeleteSubCategory(CatID);
            DBCon.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void DeleteVariant() {
        SelectVarID();
        try {
            DBCon.Open();
            db.DeleteVarient(CatID);
            DBCon.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public String CatID() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        CatID = "CAT-" + dtf.format(now);
        return CatID;
    }

    private void AddCat() {
        CatID();
        try {
            DBCon.Open();
            db.AddCategory(CatID, txtCategory.getText());
            DBCon.Close();
        } catch (SQLException e) {
        }
    }

    private void AddSubCat() {
        CatID();
        try {
            DBCon.Open();
            db.AddSubCategory(CatID, txtSubCategory.getText());
            DBCon.Close();
        } catch (SQLException e) {
        }
    }

    private void AddVariants() {
        CatID();
        try {
            DBCon.Open();
            db.AddVariant(CatID, txtVariant.getText());
            DBCon.Close();
        } catch (SQLException e) {
        }
    }
// Helper method to add a category or subcategory

    private void addCategory(JTextField textField, JComboBox<String> comboBox, String itemType) {
        // Get and trim the text from the input field
        String itemName = textField.getText().trim();
        if (itemName.isEmpty()) {
            // Show a warning if the text field is empty
            JOptionPane.showMessageDialog(null, "Please enter a " + itemType + " name.", "Warning", JOptionPane.WARNING_MESSAGE);
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
            JOptionPane.showMessageDialog(null, itemType + " Name already exists.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            // Add the item to the combo box
            comboBox.addItem(itemName);
            // Call the appropriate method to handle further actions
            switch (itemType) {
                case "Category" ->
                    AddCat();
                case "Sub-Category" ->
                    AddSubCat();
                case "Product Variant" ->
                    AddVariants();
                default -> {
                }
            }
            // Clear the text field for new input
            textField.setText("");
        }
    }

    private void DeleteCategory(JTextField textField, JComboBox<String> comboBox, String itemType) {
        // Get and trim the text from the input field
        int selectedIndex = comboBox.getSelectedIndex();
        // Check if an item is selected
        if (selectedIndex != -1) {
            if (comboBox.getSelectedItem().equals("Unknown")) {
            } else {
                int confirmation = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to remove " + comboBox.getSelectedItem() + " from category?",
                        "Confirm Removal",
                        JOptionPane.YES_NO_OPTION
                );
                // If the user confirms, remove the item
                if (confirmation == JOptionPane.YES_OPTION) {
                    // Call the appropriate method to handle further actions
                    switch (itemType) {
                        case "Category" ->
                            // Confirm the removal
                            DeleteCategory();
                        case "Sub-Category" ->
                            DeleteSubCategory();
                        case "Product Variant" ->
                            DeleteVariant();
                        default -> {
                        }
                    }
                }
            }
        } else {
            // Show a message if no item is selected
            JOptionPane.showMessageDialog(null, "Please select a category to remove.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        comboBox.removeItemAt(selectedIndex);
        // Clear the text field for new input
        textField.setText("");

    }

    private void UpdateCategory(JTextField textField, JComboBox<String> comboBox, String itemType) {
        // Get and trim the text from the input field
        int selectedIndex = comboBox.getSelectedIndex();
        // Check if an item is selected
        if (selectedIndex != -1) {
            if (comboBox.getSelectedItem().equals("Unknown")) {
            } else {
                // Call the appropriate method to handle further actions
                switch (itemType) {
                    case "Category" ->
                        // Confirm the removal
                        UpdateCategory();
                    case "Sub-Category" ->
                        UpdateSubCategory();
                    case "Product Variant" ->
                        UpdateVariant();
                    default -> {
                    }
                }
                // Update the combo box with the new value
                comboBox.removeItemAt(selectedIndex);
                comboBox.insertItemAt(textField.getText(), selectedIndex);
                comboBox.setSelectedIndex(selectedIndex);
            }
        } else {
            // Show a message if no item is selected
            JOptionPane.showMessageDialog(null, "Please select a category to remove.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        // Clear the text field for new input
        textField.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        center = new javax.swing.JPanel();
        tabCateg = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jpCateg = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbCategory = new javax.swing.JComboBox<>();
        txtCategory = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jpSub = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbSubCategory = new javax.swing.JComboBox<>();
        txtSubCategory = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jpSub1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbVariant = new javax.swing.JComboBox<>();
        txtVariant = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        top = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bot = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        right = new javax.swing.JPanel();
        left = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        jpCateg.setBorder(javax.swing.BorderFactory.createTitledBorder("Category"));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("List Category:");
        jLabel2.setPreferredSize(new java.awt.Dimension(110, 40));

        cbCategory.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbCategory.setPreferredSize(new java.awt.Dimension(150, 40));

        txtCategory.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCategory.setMinimumSize(new java.awt.Dimension(150, 40));
        txtCategory.setPreferredSize(new java.awt.Dimension(150, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Enter Category Name:");

        javax.swing.GroupLayout jpCategLayout = new javax.swing.GroupLayout(jpCateg);
        jpCateg.setLayout(jpCategLayout);
        jpCategLayout.setHorizontalGroup(
            jpCategLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCategLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCategLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpCategLayout.setVerticalGroup(
            jpCategLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCategLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpCateg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpCateg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabCateg.addTab("Category", jPanel1);

        jpSub.setBorder(javax.swing.BorderFactory.createTitledBorder("Sub-Category"));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText(" List Sub-Category:");
        jLabel6.setPreferredSize(new java.awt.Dimension(185, 40));

        cbSubCategory.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbSubCategory.setPreferredSize(new java.awt.Dimension(150, 40));

        txtSubCategory.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSubCategory.setMinimumSize(new java.awt.Dimension(150, 40));
        txtSubCategory.setPreferredSize(new java.awt.Dimension(150, 40));
        txtSubCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubCategoryActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Sub-Category Name:");
        jLabel7.setPreferredSize(new java.awt.Dimension(185, 40));

        javax.swing.GroupLayout jpSubLayout = new javax.swing.GroupLayout(jpSub);
        jpSub.setLayout(jpSubLayout);
        jpSubLayout.setHorizontalGroup(
            jpSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSubLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSubCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpSubLayout.setVerticalGroup(
            jpSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSubLayout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSubCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbSubCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpSubLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel6, jLabel7});

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpSub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabCateg.addTab("Sub-Category", jPanel3);

        jpSub1.setBorder(javax.swing.BorderFactory.createTitledBorder("Sub-Category Product"));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("List Product variant:");
        jLabel8.setPreferredSize(new java.awt.Dimension(185, 40));

        cbVariant.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbVariant.setPreferredSize(new java.awt.Dimension(150, 40));

        txtVariant.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtVariant.setMinimumSize(new java.awt.Dimension(150, 40));
        txtVariant.setPreferredSize(new java.awt.Dimension(150, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Product Variant Name:");
        jLabel9.setPreferredSize(new java.awt.Dimension(185, 40));

        javax.swing.GroupLayout jpSub1Layout = new javax.swing.GroupLayout(jpSub1);
        jpSub1.setLayout(jpSub1Layout);
        jpSub1Layout.setHorizontalGroup(
            jpSub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSub1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtVariant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbVariant, 0, 339, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpSub1Layout.setVerticalGroup(
            jpSub1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSub1Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtVariant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbVariant, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpSub1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpSub1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabCateg.addTab("Product Variants", jPanel2);

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

        javax.swing.GroupLayout centerLayout = new javax.swing.GroupLayout(center);
        center.setLayout(centerLayout);
        centerLayout.setHorizontalGroup(
            centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, centerLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(tabCateg, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        centerLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdd, btnDelete, btnUpdate});

        centerLayout.setVerticalGroup(
            centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabCateg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        jLabel1.setText("Product Categories");

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
            .addGap(0, 404, Short.MAX_VALUE)
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
            .addGap(0, 404, Short.MAX_VALUE)
        );

        getContentPane().add(left, java.awt.BorderLayout.LINE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (tabCateg.getSelectedIndex() == 0) {
            addCategory(txtCategory, cbCategory, "Category");
        } else if (tabCateg.getSelectedIndex() == 1) {
            addCategory(txtSubCategory, cbSubCategory, "Sub-Category");
        } else if (tabCateg.getSelectedIndex() == 2) {
            addCategory(txtVariant, cbVariant, "Product Variant");
        }
//        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (tabCateg.getSelectedIndex() == 0) {
            UpdateCategory(txtCategory, cbCategory, "Category");
            //addCategory(txtCategory, cbCategory, "Category");
        } else if (tabCateg.getSelectedIndex() == 1) {
            //addCategory(txtSubCategory, cbSubCategory, "Sub-Category");
            UpdateCategory(txtSubCategory, cbSubCategory, "Sub-Category");
        } else if (tabCateg.getSelectedIndex() == 2) {
            //addCategory(txtVariant, cbVariant, "Product Variant");
            UpdateCategory(txtVariant, cbVariant, "Product Variant");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (tabCateg.getSelectedIndex() == 0) {
            DeleteCategory(txtCategory, cbCategory, "Category");
        } else if (tabCateg.getSelectedIndex() == 1) {
            DeleteCategory(txtSubCategory, cbSubCategory, "Sub-Category");
        } else if (tabCateg.getSelectedIndex() == 2) {
            DeleteCategory(txtVariant, cbVariant, "Product Variant");
        }
//        if (tabCateg.getSelectedIndex() == 0) {
//            // Get the selected index in the combo box
//            int selectedIndex = cbCategory.getSelectedIndex();
//            // Check if an item is selected
//            if (selectedIndex != -1) {
//                if (cbCategory.getSelectedItem().equals("Unknown")) {
//                } else {
//                    // Confirm the removal
//                    int confirmation = JOptionPane.showConfirmDialog(
//                            null,
//                            "Are you sure you want to remove " + cbCategory.getSelectedItem() + " from category?",
//                            "Confirm Removal",
//                            JOptionPane.YES_NO_OPTION
//                    );
//                    // If the user confirms, remove the item
//                    if (confirmation == JOptionPane.YES_OPTION) {
//                        SelectCategID();
//                        DeleteCategory();
//                        cbCategory.removeItemAt(selectedIndex);
//                        txtCategory.setText(""); // Clear the text field
//                        //ToggleButton(true,false,false);
//                    }
//                }
//            } else {
//                // Show a message if no item is selected
//                JOptionPane.showMessageDialog(null, "Please select a category to remove.", "Warning", JOptionPane.WARNING_MESSAGE);
//            }
//        } else if (tabCateg.getSelectedIndex() == 1) {
//            // Get the selected index in the combo box
//            int selectedIndex = cbSubCategory.getSelectedIndex();
//            // Check if an item is selected
//            if (selectedIndex != -1) {
//                if (cbSubCategory.getSelectedItem().equals("Unknown")) {
//                } else {
//                    // Confirm the removal
//                    int confirmation = JOptionPane.showConfirmDialog(
//                            null,
//                            "Are you sure you want to remove " + cbSubCategory.getSelectedItem() + " from category?",
//                            "Confirm Removal",
//                            JOptionPane.YES_NO_OPTION
//                    );
//                    // If the user confirms, remove the item
//                    if (confirmation == JOptionPane.YES_OPTION) {
//                        SelectSubCategID();
//                        DeleteSubCategory();
//                        cbSubCategory.removeItemAt(selectedIndex);
//                        txtSubCategory.setText(""); // Clear the text field
//                        //ToggleButton(true,false,false);
//                    }
//                }
//            } else {
//                // Show a message if no item is selected
//                JOptionPane.showMessageDialog(null, "Please select a category to remove.", "Warning", JOptionPane.WARNING_MESSAGE);
//            }
//        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtSubCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubCategoryActionPerformed


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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JPanel jpCateg;
    private javax.swing.JPanel jpSub;
    private javax.swing.JPanel jpSub1;
    private javax.swing.JPanel left;
    private javax.swing.JPanel right;
    private javax.swing.JTabbedPane tabCateg;
    private javax.swing.JPanel top;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextField txtSubCategory;
    private javax.swing.JTextField txtVariant;
    // End of variables declaration//GEN-END:variables
}
