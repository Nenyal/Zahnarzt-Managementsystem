package com.company;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Importer {
        static String jdbcURL = "jdbc:mysql://localhost:3306/zahnarztappdb";
        static String username = "root";
        static String password = "7394";

        public Connection getConnection() throws SQLException{
            return (Connection) DriverManager.getConnection(jdbcURL, username, password);
        }

        public void ShowError(SQLException exception){
            System.out.printf("Error: "+exception.getMessage());
            System.out.printf("Error Code: "+exception.getErrorCode());
    }

}
