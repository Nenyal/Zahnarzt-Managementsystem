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

    private static User user;

    public static User getUser(){
        return user;
    }
    public static boolean isAdmin() {
        return user.getPermission().equals("admin");
    }
    public static void setAdmin(boolean b){
        if (b) user.setPermission("admin");
        else user.setPermission("user");
    }

    @FXML
    void login(ActionEvent event) {
        String uname = txtuname.getText();
        String pass = txtpass.getText();
        UserDAO udao = new UserDAO();
        user = udao.getUserObjekt(uname, pass);
        if (user == null) {
            JOptionPane.showMessageDialog(null, "Fehler beim Einloggen!");
            txtuname.setText("");
            txtpass.setText("");
            txtuname.requestFocus();
        } else {
            try {
                if (user.getPermission().equals("admin")) {
                    ControllerLogIn.setAdmin(true);
                    App.changeStage(event, "Dashboard.fxml", "ZahnarztAPP Dashboard");
                } else {
                    ControllerLogIn.setAdmin(false);
                    App.changeStage(event, "DashboardArzt.fxml", "ZahnarztAPP Dashboard");
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
