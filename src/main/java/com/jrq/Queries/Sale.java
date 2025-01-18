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
public class Sale {

    public static ResultSet rs;
    DBConnection DBCon = new DBConnection("localhost", "3306", "jrqdb", "root", "001995234");

    public boolean SelectAllFieldProduct(String ProductID) throws SQLException {
        String sql = "SELECT * FROM Products where ProductID = '" + ProductID + "'";
        Statement st = DBCon.gettter().createStatement();
        rs = st.executeQuery(sql);
        return false;
    }

    public boolean SelectProduct(String ProductID) throws SQLException {
        String sql = "SELECT ProductID, ProductName, uom, Qty, SRP From Products where ProductID = '" + ProductID + "'";
        Statement st = DBCon.gettter().createStatement();
        rs = st.executeQuery(sql);
        return false;
    }

    public boolean UpdateQty(String Qty, String ProductID) throws SQLException {
        String sql = "UPDATE Products SET Qty = '" + Qty + "' WHERE ProductID = '" + ProductID + "'";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    // Addsale record
    public boolean AddSale(String SaleID, String UserID, String TotalAmount, String Cash, String Change, String Date, String Time, String TQty,String ModePayment,String Ref) throws SQLException {
        String sql = "INSERT INTO Sale (SaleID,UserID,TotalAmount,Cash,CashChange,TransDate,TransTime,TotalQty,ModePayment,Reference) Values ('" + SaleID + "','" + UserID + "','" + TotalAmount + "','" + Cash + "','" + Change + "','" + Date + "','" + Time + "','" + TQty + "','" + ModePayment + "','" + Ref + "')";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    // AddProduct Sold
    public boolean AddProductSold(String SaleID, String ProductID, String PricepU, String QTY, String UoM, String SRP,String Total,String UnitProfit, String DateSold,String Time) throws SQLException {
        String sql = "INSERT INTO SoldProduct (SaleID,ProductID,PricepUnit,Qty,UoM,SRP,Total,ProfitpU,DateSold,Time) Values ('" + SaleID + "','" + ProductID + "','" + PricepU + "','" + QTY + "','" + UoM + "','" + SRP + "','" + Total + "','" + UnitProfit + "','" + DateSold + "','" + Time + "')";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }
}
