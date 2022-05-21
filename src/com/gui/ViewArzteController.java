package com.gui;

import com.company.Arzt;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewArzteController implements Initializable {
    @FXML
    public ImageView imageView;
    @FXML
    public TextField getArztID;
    @FXML
    public TextField getArztName;
    @FXML
    public Button getArzteIDbtn;
    @FXML
    public Button getArzteNamebtn;
    @FXML
    public TextField arztidtxt;
    @FXML
    public TextField nametxt;
    @FXML
    public TextField nachnametxt;
    @FXML
    public TextField telnotxt;
    @FXML
    public Button refreshbtn;
    @FXML
    public Button updatebtn;
    @FXML
    public Button deletebtn;
    @FXML
    public Button addbtn;

    ArztDAO adao = new ArztDAO();
    ObservableList<Arzt> list;

    @FXML
    private TableView<Arzt> arztView;
    @FXML
    private TableColumn<Arzt, String> ids;
    @FXML
    private TableColumn<Arzt, String> namen;
    @FXML
    private TableColumn<Arzt, String> nachnamen;
    @FXML
    private TableColumn<Arzt, String> telefonnummern;

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
        list = adao.getArzte();
        arztView.getItems().addAll(list);
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
    private void getArzteID() {
        int id;
        try {
            id = Integer.parseInt(getArztID.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        list = adao.getMatchedArzt(id);
        arztView.setItems(list);
        getArztID.setText("");
    }

    @FXML
    private void refreshTabelle() {
        list = adao.getArzte();
        loadData();
        arztView.setItems(list);
    }

    @FXML
    private void getArzteName() {
        String name = getArztName.getText();
        list = adao.getMatchedArzteName(name);
        list.addAll(adao.getMatchedArzteNName(name));
        arztView.setItems(list);
        getArztName.setText("");
    }

    @FXML
    private void addArzt() {
        int id;
        try {
            id = Integer.parseInt(arztidtxt.getText());
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
            int result = adao.addArzt(id, name, nname, tn);
            if (result == -1) JOptionPane.showMessageDialog(null, "Arzt existiert!");
            if (result == 0) {
                JOptionPane.showMessageDialog(null, "Arzt hinzugefuegt!");
                nametxt.setText("");
                arztidtxt.setText("");
                telnotxt.setText("");
                nachnametxt.setText("");
                refreshTabelle();
            }
        }
    }

    @FXML
    private void deleteArzt() {
        int id;
        try {
            id = Integer.parseInt(arztidtxt.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        int result = adao.deleteArzt(id);
        if (result == -1) JOptionPane.showMessageDialog(null, "Arzt existiert nicht!");
        if (result == 0) {
            JOptionPane.showMessageDialog(null, "Arzt geloescht!");
            nametxt.setText("");
            arztidtxt.setText("");
            telnotxt.setText("");
            nachnametxt.setText("");
            refreshTabelle();
        }
    }

    @FXML
    private void updateArzt() {
        int id;
        try {
            id = Integer.parseInt(arztidtxt.getText());
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
            int result = adao.updateArzt(id, name, nname, tn);
            if (result == -1) JOptionPane.showMessageDialog(null, "Arzt existiert nicht!");
            if (result == 0) {
                JOptionPane.showMessageDialog(null, "Arzt aktualisiert!");
                nametxt.setText("");
                arztidtxt.setText("");
                telnotxt.setText("");
                nachnametxt.setText("");
                refreshTabelle();
            }
        }

    }
}
