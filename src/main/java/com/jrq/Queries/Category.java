/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jrq.Queries;

import static com.jrq.Queries.Sale.rs;
import static com.jrq.Queries.StockManagement.rs;
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

    public boolean AddCategory(String catID, String CatName) throws SQLException {
        String sql = "INSERT INTO category (CategoryID,CategoryName) Values ('" + catID + "','" + CatName + "')";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    public boolean AddSubCategory(String catID, String CatName) throws SQLException {
        String sql = "INSERT INTO Subcategory (SubCategoryID,SubCategoryName) Values ('" + catID + "','" + CatName + "')";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    public boolean AddVariant(String VarID, String VarName) throws SQLException {
        String sql = "INSERT INTO Variant (VariantID,VariantName) Values ('" + VarID + "','" + VarName + "')";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    public boolean SelectCategory() throws SQLException {
        String sql = "SELECT * From category";
        Statement st = DBCon.gettter().createStatement();
        rs = st.executeQuery(sql);
        return false;
    }

    public boolean SelectSubCategory() throws SQLException {
        String sql = "SELECT * From Subcategory";
        Statement st = DBCon.gettter().createStatement();
        rs = st.executeQuery(sql);
        return false;
    }

    public boolean SelectVariant() throws SQLException {
        String sql = "SELECT * From Variant";
        Statement st = DBCon.gettter().createStatement();
        rs = st.executeQuery(sql);
        return false;
    }

    //Delete Category
    public boolean DeleteCategory(String CatID) throws SQLException {
        String sql = "Delete From category where CategoryID = '" + CatID + "'";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    //Delete SubCategory
    public boolean DeleteSubCategory(String SubCatID) throws SQLException {
        String sql = "Delete From Subcategory where SubCategoryID = '" + SubCatID + "'";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }
    //Delete SubCategory

    public boolean DeleteVarient(String VarID) throws SQLException {
        String sql = "Delete From Variant where VariantID = '" + VarID + "'";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    // Update category
    public boolean UpdateCategory(String SubCategName, String SubCategID) throws SQLException {
        String sql = "UPDATE category SET CategoryName = '" + SubCategName + "' WHERE CategoryID = '" + SubCategID + "'";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }
    // Update Subcategory
    public boolean UpdateSubCategory(String SubCategName, String SubCategID) throws SQLException {
        String sql = "UPDATE Subcategory SET SubCategoryName = '" + SubCategName + "' WHERE SubCategoryID = '" + SubCategID + "'";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }
    
    public boolean UpdateVariant(String VarName, String VarID) throws SQLException {
        String sql = "UPDATE Variant SET VariantName = '" + VarName + "' WHERE VariantID = '" + VarID + "'";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

}
