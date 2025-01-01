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
public class UserManagement {

    public static ResultSet rs;

    DBConnection DBCon = new DBConnection("localhost", "3306", "jrqdb", "root", "001995234");

    //Create account
    public boolean CreateAccount(String IDNum, String FName, String LName, String Username, String Password, String AccessType, String Status) throws SQLException {
        String sql = "INSERT INTO users (UserID,FName,LName,Username,Password,AccessType,Status) Values ('" + IDNum + "','" + FName + "','" + LName + "','" + Username + "','" + Password + "','" + AccessType + "','" + Status + "')";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }
    //updateAccount

    public boolean UpdateAccount(String Username, String Password, String AccessType, String Status, String FName, String LName, String UserID) throws SQLException {
        String sql = "UPDATE users SET Username = '" + Username + "', Password = '" + Password + "' ,AccessType = '" + AccessType + "',Status = '" + Status + "',LName = '" + LName + "',FName = '" + FName + "' WHERE UserID = '" + UserID + "'";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    public boolean Activate(String Status, String UserID) throws SQLException {
        String sql = "UPDATE users SET Status = '" + Status + "' WHERE UserID = '" + UserID + "'";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    public boolean SelectRecentlyAdded() throws SQLException {
        String sql = "SELECT * FROM users ORDER BY UserID DESC LIMIT 1";
        Statement st = DBCon.gettter().createStatement();
        rs = st.executeQuery(sql);
        return false;
    }

    public boolean ReadAll() throws SQLException {
        String sql = "SELECT * FROM users";
        Statement st = DBCon.gettter().createStatement();
        rs = st.executeQuery(sql);
        return false;
    }

    public boolean DeleteUser(String IDnumber) throws SQLException {
        String sql = "Delete from users where UserID = '" + IDnumber + "'";
        Statement st = DBCon.gettter().createStatement();
        st.executeUpdate(sql);
        return false;
    }

    public boolean UserExist(String Exist) throws SQLException {
        String sql = "Select UserName from Users where UserName = '" + Exist + "'";
        Statement st = DBCon.gettter().createStatement();
        rs = st.executeQuery(sql);
        return false;
    }

}
