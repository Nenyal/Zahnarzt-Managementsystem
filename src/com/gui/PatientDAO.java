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

    public Patient getPatientObject(int id) {
        if (id == 0) {
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

    public int addPatient(int id, String name,String nname,String tn){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT COUNT(*) FROM patient WHERE PatientID=?");
            pst.setInt(1,id);
            rs = pst.executeQuery();
            rs.next();
            if (rs.getInt(1) != 0){
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
}
