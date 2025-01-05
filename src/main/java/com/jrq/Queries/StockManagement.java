/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jrq.Queries;

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
    public boolean AddProduct(String ProductID, String ProductName, String UOM, String QTY, String UnitPrice, String SRP, String Markup, String Supplier, String UnitProfit) throws SQLException {
        String sql = "INSERT INTO products (ProductID,ProductName,uom,Qty,UnitPrice,SRP,Markup,Supplier,UnitProfit) Values ('" + ProductID + "','" + ProductName + "','" + UOM + "','" + QTY + "','" + UnitPrice + "','" + SRP + "','" + Markup + "','" + Supplier + "','" + UnitProfit + "')";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    //Read Products
    public boolean ReadProducts() throws SQLException {
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
    public boolean UpdateProduct(String NewProductID, String ProductName, String uom, String QTY, String UnitPrice, String SRP, String Markup, String Supplier, String OldProductID, String UnitProfit) throws SQLException {
        String sql = "UPDATE Products SET ProductID = '" + NewProductID + "', ProductName = '" + ProductName + "' ,uom = '" + uom + "',Qty = '" + QTY + "',UnitPrice = '" + UnitPrice + "',SRP = '" + SRP + "',Markup = '" + Markup + "',Supplier = '" + Supplier + "',UnitProfit = '" + UnitProfit + "' WHERE ProductID = '" + OldProductID + "'";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    // Search Product
    public boolean SearchProduct(String ProductName, String ProductID) throws SQLException {
        String sql = "SELECT ProductID, ProductName,uom,Qty,UnitPrice,SRP,Markup,Supplier,UnitProfit FROM products WHERE ProductName LIKE '%" + ProductName + "%' OR ProductID = '" + ProductID + "' order by ProductName ASC";
        Statement st = DBCon.gettter().createStatement();
        rs = st.executeQuery(sql);
        return false;
    }
}
