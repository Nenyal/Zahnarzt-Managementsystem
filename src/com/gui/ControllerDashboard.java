package com.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ControllerDashboard {
    @FXML
    public Button redaddpatientbtn;
    @FXML
    public Button showpatientenbtn;
    @FXML
    public Button adduserbtn;
    @FXML
    public ImageView imageView;
    @FXML
    public Button redLoginbtn;

    @FXML
    void redAddUser(ActionEvent event){
        try {
            App.changeStage(event, "benutzerOperationen.fxml", "Benutzer Operationen");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void redarzte(ActionEvent event) {
        try {
            App.changeStage(event, "viewArzte.fxml", "Arzte ansehen");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void redChangePassword(ActionEvent event){
        try {
            App.changeStage(event, "changePassword.fxml", "Passwort aendern");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void redBehandlungen(ActionEvent event){
        try {
            App.changeStage(event, "behandlungoperationen.fxml", "Passwort aendern");
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
    @FXML
    private void redLogin(ActionEvent event){
        try {
            App.changeStage(event,"Login.fxml","ZahnarztAPP");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void redTerminErstellen(ActionEvent event){
        try {
            App.changeStage(event,"terminOperationen.fxml","ZahnarztAPP");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void redOperationen(ActionEvent event){
        try {
            App.changeStage(event,"operationen.fxml","ZahnarztAPP");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
