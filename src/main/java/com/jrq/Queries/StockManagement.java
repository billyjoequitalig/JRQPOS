/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jrq.Queries;

import static com.jrq.Queries.Category.rs;
import com.jrq.jrqpos.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author billy
 */
public class StockManagement {

    public static ResultSet rs;
    DBConnection DBCon = new DBConnection("localhost", "3306", "jrqdb", "root", "001995234");

    //Add Product
    public boolean AddProduct(String ProductID, String ProductName, String UOM, String QTY, String UnitPrice, String SRP, String Markup, String Supplier, String UnitProfit,String Category,String SubCateg,String Var,String ExpDate) throws SQLException {
        String sql = "INSERT INTO products (ProductID,ProductName,uom,Qty,UnitPrice,SRP,Markup,Supplier,UnitProfit,Category,SubCategory,Variant,ExpiryDate) Values ('" + ProductID + "','" + ProductName + "','" + UOM + "','" + QTY + "','" + UnitPrice + "','" + SRP + "','" + Markup + "','" + Supplier + "','" + UnitProfit + "','" + Category + "','" + SubCateg + "','" + Var + "','" + ExpDate + "')";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    //Read Products
    public boolean ReadAllProducts() throws SQLException {
        String sql = "SELECT ProductID, ProductName,uom,Qty,UnitPrice,SRP,Markup,Supplier,UnitProfit FROM Products";
        Statement st = DBCon.gettter().createStatement();
        rs = st.executeQuery(sql);
        return false;
    }

    //Delete Product
    public boolean DeleteProduct(String ProductID) throws SQLException {
        String sql = "Delete From products where ProductID = '" + ProductID + "'";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    // Update Product
    public boolean UpdateProduct(String NewProductID, String ProductName, String uom, String QTY, String UnitPrice, String SRP, String Markup, String Supplier, String UnitProfit, String OldProductID, String Categ, String SubCateg, String Variant, String Expiry) throws SQLException {
        String sql = "UPDATE Products SET ProductID = '" + NewProductID + "', ProductName = '" + ProductName + "' ,uom = '" + uom + "',Qty = '" + QTY + "',UnitPrice = '" + UnitPrice + "',SRP = '" + SRP + "',Markup = '" + Markup + "',Supplier = '" + Supplier + "',UnitProfit = '" + UnitProfit + "',Category = '" + Categ + "',SubCategory = '" + SubCateg + "',Variant = '" + Variant + "',ExpiryDate = '" + Expiry + "' WHERE ProductID = '" + OldProductID + "'";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    // Search Product
    public boolean SearchProduct(String Search) throws SQLException {
        String sql = "SELECT ProductID, ProductName,uom,Qty,UnitPrice,SRP,Markup,Supplier,UnitProfit FROM products WHERE ProductName LIKE '%" + Search + "%' OR ProductID LIKE '%" + Search + "%' ORDER BY ProductName";
        Statement st = DBCon.gettter().createStatement();
        rs = st.executeQuery(sql);
        return false;
    }

    public boolean SearchProductID(String ProductID) throws SQLException {
        String sql = "SELECT ProductID, ProductName,uom,Qty,UnitPrice,SRP,Markup,Supplier,UnitProfit,Category,SubCategory,Variant,ExpiryDate FROM products WHERE ProductID = '" + ProductID + "'";
        Statement st = DBCon.gettter().createStatement();
        rs = st.executeQuery(sql);
        return false;
    }

}
