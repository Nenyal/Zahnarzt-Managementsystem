package com.gui;

import com.company.User;
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

public class BenutzerOpsController implements Initializable {
    public Button absagenbtn;
    @FXML
    public Button adduserbtn;
    @FXML
    public ImageView imageView;
    @FXML
    public ChoiceBox<String> choicePerm;
    @FXML
    public String[] choices = {"Rezeptionist", "Arzt"};
    @FXML
    public TextField idusertxt;
    @FXML
    public TextField usernametxt;
    @FXML
    public PasswordField passworduser;
    @FXML
    public Button deleteuserbtn;
    @FXML
    public Button updateuserbtn;
    @FXML
    public PasswordField neuPass;
    @FXML
    public Button changepasswordbtn;

    UserDAO udao = new UserDAO();

    @FXML
    void addUser() {
        int id;
        try{
            id = Integer.parseInt(idusertxt.getText());
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        String un = usernametxt.getText();
        String pass = passworduser.getText();
        String perm = "";
        if (choicePerm.getValue().equals("Rezeptionist")) perm = "admin";
        else if (choicePerm.getValue().equals("Arzt")) perm = "user";

        if (id == 0 || un.equals("") || pass.equals("") || perm.equals("")) {
            JOptionPane.showMessageDialog(null, "Einige Felder sind leer!");
        } else {
            int result = udao.saveUser(id, un, pass, perm);
            if (result == -1) JOptionPane.showMessageDialog(null, "User existiert!");
            if (result == 0) {
                JOptionPane.showMessageDialog(null, "User hinzugefuegt!");
                idusertxt.setText("");
                usernametxt.setText("");
                passworduser.setText("");
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

    @FXML
    void deleteUser(){
        int id;
        try {
            id = Integer.parseInt(idusertxt.getText());
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        int result = udao.deleteUser(id);
        if (result == -1) JOptionPane.showMessageDialog(null, "Benutzer existiert nicht!");
        if (result == 0) {
            JOptionPane.showMessageDialog(null, "Benutzer geloescht!");
            idusertxt.setText("");
            usernametxt.setText("");
            passworduser.setText("");
        }
    }
    @FXML
    void updateUser(){
        int id;
        try {
            id = Integer.parseInt(idusertxt.getText());
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        String un = usernametxt.getText();
        String pass = passworduser.getText();
        if (id == 0 || usernametxt.equals("") || passworduser.equals("")) JOptionPane.showMessageDialog(null, "Einige Felder sind leer!");
        else {
            int result = udao.updateUser(id,un,pass);
            if (result == -1) JOptionPane.showMessageDialog(null, "Benutzer existiert nicht!");
            if (result == 0) {
                JOptionPane.showMessageDialog(null, "Benutzer aktualisiert!");
                idusertxt.setText("");
                usernametxt.setText("");
                passworduser.setText("");
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choicePerm.getItems().addAll(choices);
        choicePerm.setValue(choices[0]);
    }
}
