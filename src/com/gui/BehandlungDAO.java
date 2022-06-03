package com.gui;

import com.company.Arzt;
import com.company.Behandlung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.company.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BehandlungDAO {
    Connection con;

    PreparedStatement pst;

    ResultSet rs;

    Database db = new Database();
    ArrayList<String> arrayList = new ArrayList<>();
    public String[] getBehandlungenArray(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM operation");
            rs = pst.executeQuery();
            while (rs.next()) {
                arrayList.add(rs.getString(2));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return arrayList.toArray(new String[0]);
    }

    public boolean behandlungExists(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM behandlung WHERE BehandlungID=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            return rs.next();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Behandlung> getBehandlungen() {
        ObservableList<Behandlung> behandlungen = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM behandlung");
            rs = pst.executeQuery();
            while (rs.next()) {
                Behandlung behandlung = new Behandlung(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),rs.getDate(6));
                behandlungen.add(behandlung);
            }
            return behandlungen;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
