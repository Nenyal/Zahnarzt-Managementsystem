package com.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.JOptionPane;

import com.company.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.image.ImageView;

public class ControllerLogIn implements Initializable {
    @FXML
    public Button btnlogin;

    @FXML
    public TextField txtuname;

    @FXML
    public PasswordField txtpass;

    @FXML
    public ImageView imageView;

    @FXML
    void login(ActionEvent event) {
        String uname = txtuname.getText();
        String pass = txtpass.getText();
        UserDAO udao = new UserDAO();
        User user = udao.getUserObjekt(uname, pass);
        if (user == null) {
            JOptionPane.showMessageDialog(null, "Fehler beim Einloggen!");
            txtuname.setText("");
            txtpass.setText("");
            txtuname.requestFocus();
        } else {
            try {
                if (user.getPermission().equals("admin")) {
                    JOptionPane.showMessageDialog(null, "Einloggen erfolgreich!");
                    App.changeStage(event, "Dashboard.fxml", "ZahnarztAPP Dashboard");
                } else {
                    JOptionPane.showMessageDialog(null, "Einloggen erfolgreich!"); // FARKLI SAYFAYA YONLENDIRECEK @@@
                    App.changeStage(event, "Dashboard.fxml", "ZahnarztAPP Dashboard");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
