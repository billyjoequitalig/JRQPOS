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
public class Category {

    public static ResultSet rs;
    DBConnection DBCon = new DBConnection("localhost", "3306", "jrqdb", "root", "001995234");

    //Adding Category
    public boolean AddCategory(String table, String ColumnID, String ColName, String IDvalue, String ValueName) throws SQLException {
        String sql = "INSERT INTO " + table + " (" + ColumnID + "," + ColName + ") Values ('" + IDvalue + "','" + ValueName + "')";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    // Adding for SubCategory and Variant
    public boolean AddSubVar(String table, String ColumnID, String ColName, String ColFkCatID, String ValueID, String ValueName, String ValueFK) throws SQLException {
        String sql = "INSERT INTO " + table + " (" + ColumnID + "," + ColName + "," + ColFkCatID + ") Values ('" + ValueID + "','" + ValueName + "','" + ValueFK + "')";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    //Getting CategoryID
    public boolean SelectCategory(String table, String Name) throws SQLException {
        String sql = "SELECT * From " + table + " ORDER BY " + Name + " ASC";
        Statement st = DBCon.gettter().createStatement();
        rs = st.executeQuery(sql);
        return false;
    }

    public boolean SelectSubVar(String table, String WhereColID, String ValueWhereID, String ColumnName) throws SQLException {
        String sql = "SELECT * From " + table + " WHERE " + WhereColID + " = '" + ValueWhereID + "' ORDER BY " + ColumnName + " ASC";
        Statement st = DBCon.gettter().createStatement();
        rs = st.executeQuery(sql);
        return false;
    }

    //Getting Id of seleted item
    public boolean SelectIDs(String table, String where, String WhereValue) throws SQLException {
        String sql = "SELECT * FROM " + table + " WHERE " + where + " = '" + WhereValue + "'";
        Statement st = DBCon.gettter().createStatement();
        rs = st.executeQuery(sql);
        return false;
    }

    //Delete Category
    public boolean DeleteCategory(String Table, String ColumnID, String CatID) throws SQLException {
        String sql = "Delete From " + Table + " where " + ColumnID + " = '" + CatID + "'";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    // Update category
    public boolean UpdateCategory(String table, String ColumnName, String CloumnID, String ValueName, String ValueCategID) throws SQLException {
        String sql = "UPDATE " + table + " SET " + ColumnName + " = '" + ValueName + "' WHERE " + CloumnID + " = '" + ValueCategID + "'";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }
}
