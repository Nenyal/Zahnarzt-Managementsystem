package com.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ControllerDashboard {
    @FXML
    public Button redaddpatientbtn;
    @FXML
    public Button showpatientenbtn;
    @FXML
    public Button adduserbtn;

    @FXML
    void redaddpatient(ActionEvent event) {
        try {
            App.changeStage(event, "addPatient.fxml", "Patient hinzufuegen");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void redAddUser(ActionEvent event){
        try {
            App.changeStage(event, "addUser.fxml", "User hinzufuegen");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void redpatienten(ActionEvent event) {
        try {
            App.changeStage(event, "viewPatienten.fxml", "Patienten ansehen");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
