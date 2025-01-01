/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jrq.jrqpos;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author billy
 */
public class DBConnection {
    private final String Url;
    private final String Host;
    private final String Port;
    private final String Database;
    private final String Username;
    private final String Password;
    public Connection connection;

    public DBConnection(String host, String port, String database, String username, String password) {
        this.Port = port;
        this.Host = host;
        this.Database = database;
        this.Username = username;
        this.Password = password;
        this.Url = "jdbc:mysql://" + this.Host + ":" + this.Port + "/";
    }

    // to Open Connection
    public void Open() throws SQLException {
        this.connection = DriverManager.getConnection(this.Url + this.Database, this.Username, this.Password);
    }

    // To Close Connection
    public void Close() throws SQLException {
        connection.close();
    }

    public Connection gettter() {
        try {
            this.Open();
        } catch (SQLException var2) {
            JOptionPane.showMessageDialog((Component) null, "Error connecting to the database: " + var2.getMessage());
        }
        return this.connection;
    }
}
