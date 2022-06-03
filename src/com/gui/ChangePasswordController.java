package com.gui;

import com.company.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;

public class ChangePasswordController {
    @FXML
    public TextField usernametxt;
    @FXML
    public PasswordField altPass;
    @FXML
    public Button absagenbtn;
    @FXML
    public Button changepasswordbtn;
    @FXML
    public PasswordField neuPass;

    UserDAO udao = new UserDAO();

    @FXML
    void changePassword() {
        User user = ControllerLogIn.getUser();
        if (usernametxt.getText().equals(user.getUsername()) && altPass.getText().equals(user.getPassword())) {
            int res = udao.changePasswordID(user.getId(), neuPass.getText());
            if (res == -1) JOptionPane.showMessageDialog(null, "Fehler beim Aenderung!");
            if (res == 0) JOptionPane.showMessageDialog(null, "Password geaendert!");
        } else {
            JOptionPane.showMessageDialog(null, "Fehler beim Aenderung!");
        }
        usernametxt.setText("");
        altPass.setText("");
        neuPass.setText("");
    }

    @FXML
    void redDashboard(ActionEvent event) {
        try {
            App.changeStage(event, "DashboardArzt.fxml", "Dashboard");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
