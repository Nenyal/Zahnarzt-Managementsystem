package com.gui;

import com.company.Termin;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class TerminOperationenController implements Initializable {
    @FXML
    public DatePicker filterDate;
    @FXML
    private TextField patientidtxt;
    @FXML
    private TextField arztidtxt;
    @FXML
    private TextField zeittxt;
    @FXML
    private DatePicker terminDatePicker;
    @FXML
    private TextField getPatientID;
    @FXML
    private TextField getArztID;
    @FXML
    private TableColumn<Termin, String> patient;
    @FXML
    private TableColumn<Termin, String> arzt;
    @FXML
    private TableColumn<Termin, String> zeit;
    @FXML
    private TableColumn<Termin, String> datum;
    @FXML
    private TableView<Termin> terminView;

    TerminDAO tdao = new TerminDAO();

    ObservableList<Termin> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initiateColumns();
        loadData();
    }

    private void initiateColumns() {
        datum.setCellValueFactory(new PropertyValueFactory<>("datum"));
        zeit.setCellValueFactory(new PropertyValueFactory<>("zeit"));
        arzt.setCellValueFactory(new PropertyValueFactory<>("ArztID"));
        patient.setCellValueFactory(new PropertyValueFactory<>("PatientID"));
    }

    private void loadData() {
        list = tdao.getTermine();
        terminView.getItems().addAll(list);
    }

    @FXML
    public void refreshTabelle() {
        list = tdao.getTermine();
        loadData();
        terminView.setItems(list);
    }

    @FXML
    public void redDashboard(ActionEvent event) {
        try {
            if (ControllerLogIn.isAdmin()) App.changeStage(event, "Dashboard.fxml", "Dashboard");
            else App.changeStage(event, "DashboardArzt.fxml", "Dashboard");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void getTermineArztID() {
        int id;
        try {
            id = Integer.parseInt(getArztID.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        if (terminDatePicker.getValue() == null) list = tdao.getMatchedTermineArztID(id);
        else list = tdao.getMatchedTermineArztID(id, terminDatePicker.getValue());

        terminView.setItems(list);
        getArztID.setText("");
    }

    @FXML
    public void getTerminePatientID() {
        int id;
        try {
            id = Integer.parseInt(getPatientID.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }

        if (terminDatePicker.getValue() == null) list = tdao.getMatchedTerminePatientID(id);
        else list = tdao.getMatchedTerminePatientID(id, terminDatePicker.getValue());
        terminView.setItems(list);
        getPatientID.setText("");

    }

    @FXML
    public void getTermineDatum() {
        LocalDate date = filterDate.getValue();
        list = tdao.getMatchedTermineDatum(date);
        terminView.setItems(list);
    }

    @FXML
    public void terminAktualisieren() {
        if (terminDatePicker.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Waehlen Sie ein Datum!");
            return;
        }
        if (zeittxt.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Geben Sie die Zeit ein!");
            return;
        }
        LocalDate date = terminDatePicker.getValue();
        LocalTime zeit = LocalTime.parse(zeittxt.getText());
        Termin t = terminView.getSelectionModel().getSelectedItem();
        int result = tdao.updateTermin(t.getDatum(), t.getZeit(), t.getArztID(), t.getPatientID(), date, zeit);
        if (result == -1) JOptionPane.showMessageDialog(null, "Fehler beim Aktualisieren");
        if (result == 0) {
            JOptionPane.showMessageDialog(null, "Termin aktualisiert!");
            zeittxt.setText("");
            arztidtxt.setText("");
            patientidtxt.setText("");
            refreshTabelle();
        }
    }

    @FXML
    public void deleteTermin() {
        if (terminDatePicker.getValue() == null) JOptionPane.showMessageDialog(null, "Waehlen Sie ein Datum!");
        if (zeittxt.getText().equals("")) JOptionPane.showMessageDialog(null, "Geben Sie die Zeit ein!");
        LocalDate date = terminDatePicker.getValue();
        LocalTime zeit = LocalTime.parse(zeittxt.getText());

        int aid, pid;
        try {
            aid = Integer.parseInt(arztidtxt.getText());
            pid = Integer.parseInt(patientidtxt.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        int result = tdao.deleteTermin(date,zeit,aid,pid);
        if (result == -1) JOptionPane.showMessageDialog(null, "Termin existiert nicht!");
        if (result == 0) {
            JOptionPane.showMessageDialog(null, "Termin geloescht!");
            zeittxt.setText("");
            arztidtxt.setText("");
            patientidtxt.setText("");
            refreshTabelle();
        }
    }

    @FXML
    public void terminErstellen() {
        if (terminDatePicker.getValue() == null) JOptionPane.showMessageDialog(null, "Waehlen Sie ein Datum!");
        if (zeittxt.getText().equals("")) JOptionPane.showMessageDialog(null, "Geben Sie die Zeit ein!");
        LocalDate date = terminDatePicker.getValue();
        LocalTime zeit = LocalTime.parse(zeittxt.getText());

        int aid, pid;
        try {
            aid = Integer.parseInt(arztidtxt.getText());
            pid = Integer.parseInt(patientidtxt.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        int result = tdao.terminErstellen(date,zeit,aid,pid);
        if (result == -1) JOptionPane.showMessageDialog(null, "Termin existiert!");
        if (result == 0) {
            JOptionPane.showMessageDialog(null, "Termin erstellt!");
            zeittxt.setText("");
            arztidtxt.setText("");
            patientidtxt.setText("");
            refreshTabelle();
        }
    }

}
