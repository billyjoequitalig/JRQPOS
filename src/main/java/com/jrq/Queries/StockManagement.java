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
    public boolean AddProduct(String ProductID, String ProductName, String UOM, String QTY, String UnitPrice, String SRP, String Markup, String Supplier) throws SQLException {
        String sql = "INSERT INTO products (ProductID,ProductName,uom,Qty,UnitPrice,SRP,Markup,Supplier) Values ('" + ProductID + "','" + ProductName + "','" + UOM + "','" + QTY + "','" + UnitPrice + "','" + SRP + "','" + Markup + "','" + Supplier + "')";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    //Read Products
    public boolean RealProducts() throws SQLException {
        String sql = "SELECT ProductID, ProductName,uom,Qty,UnitPrice,SRP,Markup,Supplier FROM Products";
        Statement st = DBCon.gettter().createStatement();
        rs = st.executeQuery(sql);
        return false;
    }

    //Add Product
    public boolean DeleteProduct(String ProductID) throws SQLException {
        String sql = "Delete From products where ProductID = '" + ProductID + "'";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }
}
