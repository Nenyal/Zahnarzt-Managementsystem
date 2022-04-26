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

import com.company.DBImporter;
import com.company.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControllerLogIn implements Initializable {
    @FXML
    private Label label;

    @FXML
    public Button btnlogin;

    @FXML
    public TextField txtuname;

    @FXML
    public PasswordField txtpass;

    @FXML
    public ImageView imageView;

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    @FXML
    void login(ActionEvent event) {
        String uname = txtuname.getText();
        String pass = txtpass.getText();
        DBImporter im = new DBImporter();
        User user = im.getUserObjekt(uname, pass);
        if (user == null) {
            JOptionPane.showMessageDialog(null, "Fehler beim Einloggen!");
            txtuname.setText("");
            txtpass.setText("");
            txtuname.requestFocus();
        } else {
            try {
                JOptionPane.showMessageDialog(null, "Einloggen erfolgreich!");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
