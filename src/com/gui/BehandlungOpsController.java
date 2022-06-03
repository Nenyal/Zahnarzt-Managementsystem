package com.gui;

import com.company.Behandlung;
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
import java.time.LocalTime;
import java.util.ResourceBundle;

public class BehandlungOpsController implements Initializable {
    public String[] choices;
    public TextField getBehandlungID;
    public TextField getBehandlungenName;
    public TextField patientidtxt;
    public TextField nametxt;
    public ChoiceBox<String> choiceOp;
    public TextField arztidtxt;
    public DatePicker datePicker;
    public TableView<Behandlung> behandlungView;
    //public TableColumn<Behandlung, String> ids;
    public TableColumn<Behandlung, String> notizen;
    public TableColumn<Behandlung, String> oids;
    public TableColumn<Behandlung, String> pids;
    public TableColumn<Behandlung, String> aids;
    public TableColumn<Behandlung, String> datum;

    BehandlungDAO bdao = new BehandlungDAO();

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

    }
    @FXML
    public void deleteBehandlung(){

    }
    @FXML
    public void addBehandlung(){

    }
    @FXML
    public void redDashboard(){

    }
    @FXML
    public void getBehandlungenID(){

    }
    @FXML
    public void getBehandlungenName(){

    }
}

