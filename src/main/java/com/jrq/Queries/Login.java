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
public class Login {

    public static ResultSet rs;

    DBConnection DBCon = new DBConnection("localhost", "3306", "jrqdb", "root", "001995234");

    public boolean UserExist(String Username, String Password) throws SQLException {
        String sql = "SELECT UserName FROM users Where UserName = '" + Username + "' and Password = '" + Password + "'";
        Statement st = DBCon.gettter().createStatement();
        rs = st.executeQuery(sql);
        return false;
    }
}
