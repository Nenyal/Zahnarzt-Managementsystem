package com.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.io.IOException;

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
    public ImageView imageView;
    public Button absagenbtn;

    @FXML
    void addPatient() {
        int id = 0;
        try{
            id = Integer.parseInt(idaddpatienttxt.getText());
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        String name = nameaddpatienttxt.getText();
        String nname = nnameaddpatienttxt.getText();
        String tn = telnumaddpatienttxt.getText();
        if (id == 0 || name.equals("") || nname.equals("") || tn.equals("")) {
            JOptionPane.showMessageDialog(null, "Einige Felder sind leer!");
        } else {
            PatientDAO pdao = new PatientDAO();
            int result = pdao.addPatient(id, name, nname, tn);
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
    void redDashboard(ActionEvent event) {
        try {
            App.changeStage(event, "Dashboard.fxml", "Dashboard");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
