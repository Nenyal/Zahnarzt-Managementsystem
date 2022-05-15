package com.gui;

import com.company.*;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
// Patient Data Access Object
public class PatientDAO {
    Connection con;

    PreparedStatement pst;

    ResultSet rs;

    Database db = new Database();

    public boolean patientExists(int id){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM patient WHERE PatientID=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            return rs.next();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Patient getPatientObject(int id) {
        if (id == 0 || !patientExists(id)) {
            return null;
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = db.getConnection();
                pst = con.prepareStatement("SELECT * FROM patient WHERE PatientID=?");
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return new Patient(id, rs.getString(2), rs.getString(3), rs.getString(4));
                } else {
                    return null;
                }
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public ObservableList<Patient> getPatienten(){
        ObservableList<Patient> patienten = FXCollections.observableArrayList();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM patient");
            rs = pst.executeQuery();
            while (rs.next()){
                Patient patient = new Patient(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
                patienten.add(patient);
            }
            return patienten;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Patient> getMatchedPatientenID(int id){
        ObservableList<Patient> patienten = FXCollections.observableArrayList();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM patient WHERE PatientID=?");
            pst.setInt(1,id);
            rs = pst.executeQuery();
            while (rs.next()){
                Patient patient = new Patient(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
                patienten.add(patient);
            }
            return patienten;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Patient> getMatchedPatientenName(String name){
        ObservableList<Patient> patienten = FXCollections.observableArrayList();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM patient WHERE Name=?");
            return getPatients(name, patienten);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Patient> getMatchedPatientenNName(String nname){
        ObservableList<Patient> patienten = FXCollections.observableArrayList();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM patient WHERE Nachname=?");
            return getPatients(nname, patienten);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<Patient> getPatients(String nname, ObservableList<Patient> patienten) throws SQLException {
        pst.setString(1,nname);
        rs = pst.executeQuery();
        while (rs.next()){
            Patient patient = new Patient(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
            patienten.add(patient);
        }
        return patienten;
    }

    public int addPatient(int id, String name,String nname,String tn){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            if (patientExists(id)){
                return -1;
            } else {
                pst = con.prepareStatement("INSERT INTO patient VALUES (?,?,?,?)");
                pst.setInt(1,id);
                pst.setString(2,name);
                pst.setString(3,nname);
                pst.setString(4,tn);
                pst.executeUpdate();
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }
    public int deletePatient(int id){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            if (!patientExists(id)){
                return -1;
            } else {
                pst = con.prepareStatement("DELETE FROM patient WHERE PatientID=?");
                pst.setInt(1,id);
                pst.executeUpdate();
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int updatePatient(int id, String name, String nname, String tel){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            if (!patientExists(id)){
                return -1;
            } else {
                pst = con.prepareStatement("UPDATE patient SET Name=?, Nachname=?,Telefonnummer=? WHERE (PatientID=?)");
                pst.setString(1,name);
                pst.setString(2,nname);
                pst.setString(3,tel);
                pst.setInt(4,id);
                pst.executeUpdate();
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }
}
