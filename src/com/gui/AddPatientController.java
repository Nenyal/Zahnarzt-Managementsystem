package com.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.JOptionPane;

import com.company.*;

import java.io.IOException;
import java.sql.*;

import javafx.stage.Stage;

public class AddPatientController {
    @FXML
    public TextField idaddpatienttxt;
    @FXML
    public TextField nameaddpatienttxt;
    @FXML
    public TextField nnameaddpatienttxt;
    @FXML
    public TextField telnumaddpatienttxt;
    @FXML
    public Button addPatientbtn;

    Connection con;

    PreparedStatement pst;

    ResultSet rs;

    @FXML
    void addPatient(ActionEvent event) {
        int id = Integer.parseInt(idaddpatienttxt.getText());
        String name = nameaddpatienttxt.getText();
        String nname = nnameaddpatienttxt.getText();
        String tn = telnumaddpatienttxt.getText();
        DBImporter im = new DBImporter();
        if (id == 0 || name.equals("") || nname.equals("") || tn.equals("")) {
            JOptionPane.showMessageDialog(null, "Fehler beim Hinzufuegen");
        } else {
            int result = im.addPatient(id, name, nname, tn);
            if (result == -1) JOptionPane.showMessageDialog(null, "Patient existiert!");
            if (result == 0) {
                JOptionPane.showMessageDialog(null, "Patient hinzugefuegt!");
                nameaddpatienttxt.setText("");
                idaddpatienttxt.setText("");
                nnameaddpatienttxt.setText("");
                telnumaddpatienttxt.setText("");
            }
        }
    }

    @FXML
    void redLoggedIn(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("LoggedIn.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setTitle("ZahnarztklinikAPP Management");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
