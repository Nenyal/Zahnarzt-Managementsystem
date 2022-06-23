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
import java.time.LocalDate;
import java.time.LocalTime;
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
                Behandlung behandlung = new Behandlung(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),rs.getDate(6).toLocalDate());
                behandlungen.add(behandlung);
            }
            return behandlungen;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Behandlung> getMatchedPatientID(int id){
        ObservableList<Behandlung> behandlungen = FXCollections.observableArrayList();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM behandlung WHERE Patient_ID=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()){
                Behandlung bh = new Behandlung(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getDate(6).toLocalDate());
                behandlungen.add(bh);
            }
            return behandlungen;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Behandlung> getMatchedBehandlungName(String n){
        ObservableList<Behandlung> behandlungen = FXCollections.observableArrayList();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM behandlung WHERE Patient_ID IN (SELECT PatientID FROM patient WHERE Nachname=?)");
            pst.setString(1, n);
            rs = pst.executeQuery();
            while (rs.next()){
                Behandlung bh = new Behandlung(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getDate(6).toLocalDate());
                behandlungen.add(bh);
            }
            return behandlungen;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean behandlungExist(int opid, int patid, int arztid, LocalDate datum){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM behandlung WHERE Operation_ID=? AND Patient_ID=? AND Arzt_ID=? AND Datum=?");
            pst.setInt(1,opid);
            pst.setInt(2,patid);
            pst.setInt(3,arztid);
            pst.setDate(4,java.sql.Date.valueOf(datum));
            rs = pst.executeQuery();
            return rs.next();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int behandlungErstellen(String n, int opid, int patid, int arztid, LocalDate datum){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            if (behandlungExist(opid,patid,arztid,datum)){
                return -1;
            } else {
                pst = con.prepareStatement("INSERT INTO behandlung(Notizen, Operation_ID, Patient_ID, Arzt_ID, Datum) VALUES (?,?,?,?,?)");
                pst.setString(1, n);
                pst.setInt(2, opid);
                pst.setInt(3, patid);
                pst.setInt(4, arztid);
                pst.setDate(5,java.sql.Date.valueOf(datum));
                pst.executeUpdate();
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int deleteBehandlung(int opid, int patid, int arztid, LocalDate datum){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            if (!behandlungExist(opid,patid,arztid,datum)){
                return -1;
            } else {
                pst = con.prepareStatement("DELETE FROM behandlung WHERE Operation_ID=? AND Patient_ID=? AND Arzt_ID=? AND Datum=?");
                pst.setInt(1, opid);
                pst.setInt(2, patid);
                pst.setInt(3, arztid);
                pst.setDate(4,java.sql.Date.valueOf(datum));
                pst.executeUpdate();
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int updateBehandlungen(String n, int opid, int patid, int arztid, LocalDate datum) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            if (!behandlungExist(opid,patid,arztid,datum)) {
                return -1;
            } else {
                pst = con.prepareStatement("UPDATE behandlung SET  Notizen=? WHERE Operation_ID=? AND Patient_ID=? AND Arzt_ID=? AND Datum=?");
                pst.setString(1, n);
                pst.setInt(2, opid);
                pst.setInt(3, patid);
                pst.setInt(4, arztid);
                pst.setDate(5,java.sql.Date.valueOf(datum));
                pst.executeUpdate();
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
