package com.gui;

import com.company.Patient;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewPatientenController implements Initializable {
    @FXML
    public ImageView imageView;
    @FXML
    public TextField getPatientID;
    @FXML
    public TextField getPatientName;
    @FXML
    public Button getPatientenIDbtn;
    @FXML
    public Button refreshbtn;
    @FXML
    public Button getPatientenNamebtn;
    public TextField patientidtxt;
    public TextField nametxt;
    public TextField nachnametxt;
    public TextField telnotxt;
    public Button updatebtn;
    public Button deletebtn;
    public Button addbtn;
    PatientDAO pdao = new PatientDAO();
    ObservableList<Patient> list;

    @FXML
    private TableView<Patient> patientView;
    @FXML
    private TableColumn<Patient, String> ids;
    @FXML
    private TableColumn<Patient, String> namen;
    @FXML
    private TableColumn<Patient, String> nachnamen;
    @FXML
    private TableColumn<Patient, String> telefonnummern;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initiateColumns();
        loadData();
    }

    private void initiateColumns() {
        ids.setCellValueFactory(new PropertyValueFactory<>("id"));
        namen.setCellValueFactory(new PropertyValueFactory<>("name"));
        nachnamen.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        telefonnummern.setCellValueFactory(new PropertyValueFactory<>("telefonNummer"));
    }

    private void loadData() {
        list = pdao.getPatienten();
        patientView.getItems().addAll(list);
    }

    @FXML
    private void redDashboard(ActionEvent event) {
        try {
            App.changeStage(event, "Dashboard.fxml", "Dashboard");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void getPatientenID() {
        int id;
        try {
            id = Integer.parseInt(getPatientID.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        list = pdao.getMatchedPatientenID(id);
        patientView.setItems(list);
        getPatientID.setText("");
    }

    @FXML
    private void refreshTabelle() {
        list = pdao.getPatienten();
        loadData();
        patientView.setItems(list);
    }

    @FXML
    private void getPatientenName() {
        String name = getPatientName.getText();
        list = pdao.getMatchedPatientenName(name);
        list.addAll(pdao.getMatchedPatientenNName(name));
        patientView.setItems(list);
        getPatientName.setText("");
    }

    @FXML
    private void addPatient() {
        int id;
        try {
            id = Integer.parseInt(patientidtxt.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        String name = nametxt.getText();
        String nname = nachnametxt.getText();
        String tn = telnotxt.getText();
        if (id == 0 || name.equals("") || nname.equals("") || tn.equals("")) {
            JOptionPane.showMessageDialog(null, "Einige Felder sind leer!");
        } else {
            int result = pdao.addPatient(id, name, nname, tn);
            if (result == -1) JOptionPane.showMessageDialog(null, "Patient existiert!");
            if (result == 0) {
                JOptionPane.showMessageDialog(null, "Patient hinzugefuegt!");
                nametxt.setText("");
                patientidtxt.setText("");
                telnotxt.setText("");
                nachnametxt.setText("");
                refreshTabelle();
            }
        }
    }

    @FXML
    private void deletePatient() {
        int id;
        try {
            id = Integer.parseInt(patientidtxt.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        int result = pdao.deletePatient(id);
        if (result == -1) JOptionPane.showMessageDialog(null, "Patient existiert nicht!");
        if (result == 0) {
            JOptionPane.showMessageDialog(null, "Patient geloescht!");
            nametxt.setText("");
            patientidtxt.setText("");
            telnotxt.setText("");
            nachnametxt.setText("");
            refreshTabelle();
        }
    }

    @FXML
    private void updatePatient() {
        int id;
        try {
            id = Integer.parseInt(patientidtxt.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        String name = nametxt.getText();
        String nname = nachnametxt.getText();
        String tn = telnotxt.getText();
        if (id == 0 || name.equals("") || nname.equals("") || tn.equals("")) {
            JOptionPane.showMessageDialog(null, "Einige Felder sind leer!");
        } else {
            int result = pdao.updatePatient(id, name, nname, tn);
            if (result == -1) JOptionPane.showMessageDialog(null, "Patient existiert nicht!");
            if (result == 0) {
                JOptionPane.showMessageDialog(null, "Patient aktualisiert!");
                nametxt.setText("");
                patientidtxt.setText("");
                telnotxt.setText("");
                nachnametxt.setText("");
                refreshTabelle();
            }
        }

    }
}
