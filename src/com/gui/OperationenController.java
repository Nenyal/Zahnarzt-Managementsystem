package com.gui;

import com.company.Operation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OperationenController implements Initializable {
    @FXML
    public ImageView imageView;
    @FXML
    public TableView<Operation> operationenView;
    @FXML
    public TableColumn<Operation, String> ids;
    @FXML
    public TableColumn<Operation, String> namen;
    @FXML
    public TableColumn<Operation, String> kosten;
    @FXML
    public TextField getopidtxt;
    @FXML
    public Button getOpIDbtn;
    @FXML
    public TextField opidtxt;
    @FXML
    public TextField nametxt;
    @FXML
    public TextField kosttxt;
    @FXML
    public Button refreshbtn;
    @FXML
    public Button updatebtn;
    @FXML
    public Button deletebtn;
    @FXML
    public Button addbtn;
    @FXML
    public TextField getopnametxt;
    @FXML
    public Button getOpNamebtn1;

    OperationDAO odao = new OperationDAO();

    ObservableList<Operation> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initiateColumns();
        loadData();
    }

    private void initiateColumns() {
        ids.setCellValueFactory(new PropertyValueFactory<>("id"));
        namen.setCellValueFactory(new PropertyValueFactory<>("name"));
        kosten.setCellValueFactory(new PropertyValueFactory<>("kost"));
    }

    private void loadData() {
        list = odao.getOperationen();
        operationenView.getItems().addAll(list);
    }

    @FXML
    private void redDashboard(ActionEvent event) {
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
    private void getOperationenID() {
        int id;
        try {
            id = Integer.parseInt(getopidtxt.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        list = odao.getMatchedOps(id);
        operationenView.setItems(list);
        getopidtxt.setText("");
    }

    @FXML
    private void refreshTabelle() {
        list = odao.getOperationen();
        loadData();
        operationenView.setItems(list);
    }

    @FXML
    private void getOperationenName() {
        String name = getopnametxt.getText();
        list = odao.getMatchedOpsName(name);
        operationenView.setItems(list);
        getopnametxt.setText("");
    }

    @FXML
    private void addOperation() {
        int id;
        try {
            id = Integer.parseInt(opidtxt.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        String name = nametxt.getText();
        float kost = Float.parseFloat(kosttxt.getText());
        if (id == 0 || name.equals("")) {
            JOptionPane.showMessageDialog(null, "Einige Felder sind leer!");
        } else {
            int result = odao.addOperation(id, name, kost);
            if (result == -1) JOptionPane.showMessageDialog(null, "Operation existiert!");
            if (result == 0) {
                JOptionPane.showMessageDialog(null, "Operation hinzugefuegt!");
                nametxt.setText("");
                opidtxt.setText("");
                kosttxt.setText("");
                refreshTabelle();
            }
        }
    }

    @FXML
    private void deleteOperation() {
        int id;
        try {
            id = Integer.parseInt(opidtxt.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        int result = odao.deleteOperation(id);
        if (result == -1) JOptionPane.showMessageDialog(null, "Operation existiert nicht!");
        if (result == 0) {
            JOptionPane.showMessageDialog(null, "Operation geloescht!");
            nametxt.setText("");
            opidtxt.setText("");
            kosttxt.setText("");
            refreshTabelle();
        }
    }

    @FXML
    private void updateOperation() {
        int id;
        try {
            id = Integer.parseInt(opidtxt.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID nicht gueltig!");
            return;
        }
        float kost = Float.parseFloat(kosttxt.getText());
        int result = odao.updateOperation(id, kost);
        if (result == -1) JOptionPane.showMessageDialog(null, "Operation existiert nicht!");
        if (result == 0) {
            JOptionPane.showMessageDialog(null, "Operation aktualisiert!");
            nametxt.setText("");
            opidtxt.setText("");
            kosttxt.setText("");
            refreshTabelle();
        }


    }

}
