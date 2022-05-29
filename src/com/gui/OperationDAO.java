package com.gui;

import com.company.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperationDAO {
    Connection con;

    PreparedStatement pst;

    ResultSet rs;

    Database db = new Database();

    public boolean opExists(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM operation WHERE OperationID=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            return rs.next();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Operation> getOperationen() {
        ObservableList<Operation> ops = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM operation");
            rs = pst.executeQuery();
            while (rs.next()) {
                Operation op = new Operation(rs.getInt(1), rs.getString(2), rs.getFloat(3));
                ops.add(op);
            }
            return ops;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Operation> getMatchedOps(int id) {
        ObservableList<Operation> ops = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM operation WHERE OperationID=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Operation op = new Operation(rs.getInt(1), rs.getString(2), rs.getFloat(3));
                ops.add(op);
            }
            return ops;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Operation> getMatchedOpsName(String name) {
        ObservableList<Operation> ops = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM operation WHERE Name=?");
            pst.setString(1,name);
            rs = pst.executeQuery();
            while(rs.next()){
                Operation op = new Operation(rs.getInt(1), rs.getString(2), rs.getFloat(3));
                ops.add(op);
            }
            return ops;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int addOperation(int id, String name, float kost){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            if (opExists(id)){
                return -1;
            } else {
                pst = con.prepareStatement("INSERT INTO operation VALUES (?,?,?)");
                pst.setInt(1,id);
                pst.setString(2,name);
                pst.setFloat(3, kost);
                pst.executeUpdate();
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int deleteOperation(int id){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            if (!opExists(id)){
                return -1;
            } else {
                pst = con.prepareStatement("DELETE FROM operation WHERE OperationID=?");
                pst.setInt(1,id);
                pst.executeUpdate();
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int updateOperation(int id, float kost){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            if (!opExists(id)){
                return -1;
            } else {
                pst = con.prepareStatement("UPDATE operation SET Kost=? WHERE (OperationID=?)");
                pst.setFloat(1,kost);
                pst.setInt(2,id);
                pst.executeUpdate();
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }
}
