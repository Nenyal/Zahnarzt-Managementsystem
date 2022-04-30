package com.gui;

import com.company.Patient;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewPatientenController implements Initializable {
    public ImageView imageView;
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
    private void redDashboard(ActionEvent event){
        try {
            App.changeStage(event,"Dashboard.fxml","Dashboard");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
