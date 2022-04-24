package com.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import com.company.Importer;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.stage.Stage;
public class ControllerLoggedIn {
    @FXML
    public Button redaddpatientbtn;
    @FXML
    public Button showpatientenbtn;

    @FXML
    void redaddpatient(ActionEvent event){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("addPatient.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setTitle("Patient hinzufuegen");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void redpatienten(){

    }
}
