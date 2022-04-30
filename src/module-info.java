module com {
        requires javafx.controls;
        requires javafx.fxml;
        requires java.sql;
        requires java.desktop;
        requires mysql.connector.java;

        exports com.gui;
        exports com.company;

        opens com.gui to javafx.fxml;
        opens com.company to javafx.fxml;
        }