package com.company;

import java.sql.*;

public class Database {
    static String jdbcURL = "jdbc:mysql://localhost:3306/zahnarztappdb";
    static String username = "root";
    static String password = "7394";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, username, password);
    }

    public void ShowError(SQLException exception) {
        System.out.printf("Error: " + exception.getMessage());
        System.out.printf("Error Code: " + exception.getErrorCode());
    }
}
