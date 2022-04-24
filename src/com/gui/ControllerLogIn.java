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

import com.company.Importer;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
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
        Importer im = new Importer();
        if (uname.equals("") && pass.equals("")) {
            JOptionPane.showMessageDialog(null, "ID und Passwort nicht eingegeben");
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = im.getConnection();
                pst = con.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");

                pst.setString(1, uname);
                pst.setString(2, pass);

                rs = pst.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Einloggen erfolgreich!");
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("LoggedIn.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.setTitle("ZahnarztklinikAPP Management");
                    stage.setScene(scene);
                    stage.show();
                } else {
                    JOptionPane.showMessageDialog(null, "Fehler beim Einloggen");
                    txtuname.setText("");
                    txtpass.setText("");
                    txtuname.requestFocus();
                }
            } catch (ClassNotFoundException | SQLException | IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void showImage() {
        Image image = new Image("..\\..\\png\\dentist.png");
        imageView.setImage(image);
        imageView.setCache(true);
    }
}
