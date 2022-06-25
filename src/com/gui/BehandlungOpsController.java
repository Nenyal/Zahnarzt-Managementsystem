package com.gui;

import com.company.Behandlung;
import com.company.Operation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BehandlungOpsController implements Initializable {
    @FXML
    public String[] choices;
    @FXML
    public TextField getBehandlungID;
    @FXML
    public TextField notizentxt;
    @FXML
    public TextField getBehandlungenName;
    @FXML
    public TextField patientidtxt;
    @FXML
    public TextField nametxt;
    @FXML
    public ChoiceBox<String> choiceOp;
    @FXML
    public TextField arztidtxt;
    @FXML
    public DatePicker datePicker;
    @FXML
    public TableView<Behandlung> behandlungView;
    @FXML
    public TableColumn<Behandlung, String> notizen;
    @FXML
    public TableColumn<Behandlung, String> oids;
    @FXML
    public TableColumn<Behandlung, String> pids;
    @FXML
    public TableColumn<Behandlung, String> aids;
    @FXML
    public TableColumn<Behandlung, String> datum;

    BehandlungDAO bdao = new BehandlungDAO();

    OperationDAO odao = new OperationDAO();

    ObservableList<Behandlung> list;

    private void initiateColumns() {
        //ids.setCellValueFactory(new PropertyValueFactory<>("id"));
        notizen.setCellValueFactory(new PropertyValueFactory<>("notizen"));
        oids.setCellValueFactory(new PropertyValueFactory<>("opID"));
        pids.setCellValueFactory(new PropertyValueFactory<>("patID"));
        aids.setCellValueFactory(new PropertyValueFactory<>("arztID"));
        datum.setCellValueFactory(new PropertyValueFactory<>("datum"));
    }

    private void loadData() {
        list = bdao.getBehandlungen();
        behandlungView.getItems().addAll(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initiateColumns();
        loadData();
        choices = bdao.getBehandlungenArray();
        choiceOp.getItems().addAll(choices);
        choiceOp.setValue(choices[0]);
    }



    @FXML
    public void refreshTabelle(){
        list = bdao.getBehandlungen();
        loadData();
        behandlungView.setItems(list);
    }
    @FXML
    public void updateBehandlung(){
        if (datePicker.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Waehlen Sie ein Datum!");
            return;
        }
        LocalDate date = datePicker.getValue();
        int pid;
        int aid;
        try {
            pid = Integer.parseInt(patientidtxt.getText());
            aid = Integer.parseInt(arztidtxt.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        String not = notizentxt.getText();
        Operation op = odao.getOpsName(choiceOp.getValue());

        int result = bdao.updateBehandlungen(not, op.getId(),pid,aid,date);
        if (result == -1) JOptionPane.showMessageDialog(null, "Fehler beim Aktualisieren");
        if (result == 0) {
            JOptionPane.showMessageDialog(null, "Behandlung aktualisiert!");
            notizentxt.setText("");
            arztidtxt.setText("");
            patientidtxt.setText("");
            refreshTabelle();
        }
        refreshTabelle();
    }
    @FXML
    public void deleteBehandlung(){
        if (datePicker.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Waehlen Sie ein Datum!");
            return;
        }
        LocalDate date = datePicker.getValue();
        int pid;
        int aid;
        try {
            pid = Integer.parseInt(patientidtxt.getText());
            aid = Integer.parseInt(arztidtxt.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        Operation op = odao.getOpsName(choiceOp.getValue());
        String not = notizentxt.getText();
        int result = bdao.deleteBehandlung(op.getId(),pid,aid,date);
        if (result == -1) JOptionPane.showMessageDialog(null, "Fehler beim Loeschen!");
        if (result == 0) {
            JOptionPane.showMessageDialog(null, "Behandlung geloescht!");
            notizentxt.setText("");
            arztidtxt.setText("");
            patientidtxt.setText("");
            refreshTabelle();
        }
    }
    @FXML
    public void addBehandlung(){
        if (datePicker.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Waehlen Sie ein Datum!");
            return;
        }
        LocalDate date = datePicker.getValue();
        int pid;
        int aid;
        try {
            pid = Integer.parseInt(patientidtxt.getText());
            aid = Integer.parseInt(arztidtxt.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        Operation op = odao.getOpsName(choiceOp.getValue());
        String not = notizentxt.getText();
        int result = bdao.behandlungErstellen(not,op.getId(),pid,aid,date);
        if (result == -1) JOptionPane.showMessageDialog(null, "Fehler beim Hinzufuegen!");
        if (result == 0) {
            JOptionPane.showMessageDialog(null, "Behandlung hinzugefuegt!");
            notizentxt.setText("");
            arztidtxt.setText("");
            patientidtxt.setText("");
            refreshTabelle();
        }
    }
    @FXML
    public void redDashboard(ActionEvent event){
        try {
            if (ControllerLogIn.isAdmin()){
                App.changeStage(event, "Dashboard.fxml", "Dashboard");
            } else {
                App.changeStage(event, "DashboardArzt.fxml", "Dashboard");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void getBehandlungenID(){
        int pid;
        try {
            pid = Integer.parseInt(getBehandlungID.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        list = bdao.getMatchedPatientID(pid);
        behandlungView.setItems(list);
        getBehandlungID.setText("");
    }
    @FXML
    public void getBehandlungenName(){
        String s = getBehandlungenName.getText();
        list = bdao.getMatchedBehandlungName(s);
        behandlungView.setItems(list);
        getBehandlungID.setText("");
    }
}

