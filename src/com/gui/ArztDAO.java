package com.gui;

import com.company.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArztDAO {
    Connection con;

    PreparedStatement pst;

    ResultSet rs;

    Database db = new Database();

    public boolean arztExists(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM arzt WHERE ArztID=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            return rs.next();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Arzt getArztObject(int id) {
        if (id == 0 || !arztExists(id)) {
            return null;
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = db.getConnection();
                pst = con.prepareStatement("SELECT * FROM arzt WHERE ArztID=?");
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return new Arzt(id, rs.getString(2), rs.getString(3), rs.getString(4));
                } else {
                    return null;
                }
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ObservableList<Arzt> getArzte() {
        ObservableList<Arzt> arzte = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM arzt");
            rs = pst.executeQuery();
            while (rs.next()) {
                Arzt arzt = new Arzt(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                arzte.add(arzt);
            }
            return arzte;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Arzt> getMatchedArzt(int id) {
        ObservableList<Arzt> arzte = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM arzt WHERE ArztID=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Arzt arzt = new Arzt(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                arzte.add(arzt);
            }
            return arzte;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Arzt> getMatchedArzteName(String name) {
        ObservableList<Arzt> arzte = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM arzt WHERE Name=?");
            return getArzten(name, arzte);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Arzt> getMatchedArzteNName(String nname) {
        ObservableList<Arzt> patienten = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM arzt WHERE Nachname=?");
            return getArzten(nname, patienten);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<Arzt> getArzten(String nname, ObservableList<Arzt> arzte) throws SQLException {
        pst.setString(1, nname);
        rs = pst.executeQuery();
        while (rs.next()) {
            Arzt arzt = new Arzt(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            arzte.add(arzt);
        }
        return arzte;
    }

    public int addArzt(int id, String name,String nname,String tn){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            if (arztExists(id)){
                return -1;
            } else {
                pst = con.prepareStatement("INSERT INTO arzt VALUES (?,?,?,?)");
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

    public int deleteArzt(int id){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            if (!arztExists(id)){
                return -1;
            } else {
                pst = con.prepareStatement("DELETE FROM arzt WHERE ArztID=?");
                pst.setInt(1,id);
                pst.executeUpdate();
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int updateArzt(int id, String name, String nname, String tel){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            if (!arztExists(id)){
                return -1;
            } else {
                pst = con.prepareStatement("UPDATE arzt SET Name=?, Nachname=?,Telefonnummer=? WHERE (ArztID=?)");
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
