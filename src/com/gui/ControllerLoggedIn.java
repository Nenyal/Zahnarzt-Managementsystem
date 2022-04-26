package com.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

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
