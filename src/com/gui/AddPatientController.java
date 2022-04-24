package com.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import com.company.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
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
    void addPatient(ActionEvent event){
        int id = Integer.parseInt(idaddpatienttxt.getText());
        String nametxt = nameaddpatienttxt.getText();
        String nname = nnameaddpatienttxt.getText();
        String tn = telnumaddpatienttxt.getText();
        Importer im = new Importer();
        if (nametxt.equals("") || nname.equals("")) {
            JOptionPane.showMessageDialog(null,"Fehler beim Hinzufuegen");
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = im.getConnection();
                pst = con.prepareStatement("SELECT COUNT(*) FROM patient WHERE PatientID=?");
                pst.setInt(1,id);
                rs = pst.executeQuery();
                rs.next();
                if (rs.getInt(1) != 0){
                    JOptionPane.showMessageDialog(null,"Patient existiert!");
                } else {
                    pst = con.prepareStatement("INSERT INTO patient VALUES (?,?,?,?)");
                    pst.setInt(1,id);
                    pst.setString(2,nametxt);
                    pst.setString(3,nname);
                    pst.setString(4,tn);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Patient hinzugefuegt!");
                }
            } catch (ClassNotFoundException | SQLException e){
                throw new RuntimeException(e);
            }

        }

    }
    @FXML
    void redLoggedIn(ActionEvent event){
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
