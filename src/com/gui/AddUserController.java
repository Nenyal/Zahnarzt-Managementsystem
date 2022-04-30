package com.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javax.swing.*;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {
    public Button absagenbtn;
    @FXML
    public Button adduserbtn;
    @FXML
    public TextField idaddusertxt;
    @FXML
    public TextField usernameaddusertxt;
    @FXML
    public PasswordField passwordadduser;
    @FXML
    public ImageView imageView;
    @FXML
    public ChoiceBox<String> choicePerm;
    public String[] choices = {"Rezeptionist", "Arzt"};


    @FXML
    void addUser() {
        int id = 0;
        try{
            id = Integer.parseInt(idaddusertxt.getText());
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        String un = usernameaddusertxt.getText();
        String pass = passwordadduser.getText();
        String perm = "";
        if (choicePerm.getValue().equals("Rezeptionist")) perm = "admin";
        else if (choicePerm.getValue().equals("Arzt")) perm = "user";

        if (id == 0 || un.equals("") || pass.equals("") || perm.equals("")) {
            JOptionPane.showMessageDialog(null, "Einige Felder sind leer!");
        } else {
            UserDAO udao = new UserDAO();
            int result = udao.saveUser(id, un, pass, perm);
            if (result == -1) JOptionPane.showMessageDialog(null, "Patient existiert!");
            if (result == 0) {
                JOptionPane.showMessageDialog(null, "Patient hinzugefuegt!");
                idaddusertxt.setText("");
                usernameaddusertxt.setText("");
                passwordadduser.setText("");
            }
        }
    }

    @FXML
    void redDashboard(ActionEvent event) {
        try {
            App.changeStage(event, "Dashboard.fxml", "Dashboard");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choicePerm.getItems().addAll(choices);
        choicePerm.setValue(choices[0]);
    }
}
