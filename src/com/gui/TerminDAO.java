package com.gui;

import com.company.Database;
import com.company.Termin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class TerminDAO {
    private Connection con;

    private PreparedStatement pst;

    private ResultSet rs;

    Database db = new Database();

    public ObservableList<Termin> getTermine() {
        ObservableList<Termin> termine = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM termin");
            rs = pst.executeQuery();
            while (rs.next()) {
                Termin termin = new Termin(rs.getDate(2).toLocalDate(), LocalTime.parse(rs.getString(3)), rs.getInt(4), rs.getInt(5));
                termine.add(termin);
            }
            return termine;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Termin> getMatchedTermineArztID(int id) {
        ObservableList<Termin> termine = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM termin WHERE Arzt_ID=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Termin termin = new Termin(rs.getDate(2).toLocalDate(), LocalTime.parse(rs.getString(3)), rs.getInt(4), rs.getInt(5));
                termine.add(termin);
            }
            return termine;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Termin> getMatchedTermineArztID(int id, LocalDate date) {
        ObservableList<Termin> termine = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM termin WHERE Arzt_ID=? AND Datum=?");
            pst.setInt(1, id);
            pst.setDate(2, java.sql.Date.valueOf(date));
            rs = pst.executeQuery();
            while (rs.next()) {
                Termin termin = new Termin(rs.getDate(2).toLocalDate(), LocalTime.parse(rs.getString(3)), rs.getInt(4), rs.getInt(5));
                termine.add(termin);
            }
            return termine;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Termin> getMatchedTerminePatientID(int id) {
        ObservableList<Termin> termine = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM termin WHERE Patient_ID=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Termin termin = new Termin(rs.getDate(2).toLocalDate(), LocalTime.parse(rs.getString(3)), rs.getInt(4), rs.getInt(5));
                termine.add(termin);
            }
            return termine;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Termin> getMatchedTerminePatientID(int id, LocalDate date) {
        ObservableList<Termin> termine = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM termin WHERE Patient_ID=? AND Datum=?");
            pst.setInt(1, id);
            pst.setDate(2, java.sql.Date.valueOf(date));
            rs = pst.executeQuery();
            while (rs.next()) {
                Termin termin = new Termin(rs.getDate(2).toLocalDate(), LocalTime.parse(rs.getString(3)), rs.getInt(4), rs.getInt(5));
                termine.add(termin);
            }
            return termine;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Termin> getMatchedTermineDatum(LocalDate date) {
        ObservableList<Termin> termine = FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM termin WHERE Datum=?");
            pst.setDate(1, java.sql.Date.valueOf(date));
            rs = pst.executeQuery();
            while (rs.next()) {
                Termin termin = new Termin(rs.getDate(2).toLocalDate(), LocalTime.parse(rs.getString(3)), rs.getInt(4), rs.getInt(5));
                termine.add(termin);
            }
            return termine;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean terminExists(LocalDate date, LocalTime time, int aid, int pid) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM termin WHERE Datum=? AND Zeit=? AND Arzt_ID=? AND Patient_ID=?");
            pst.setDate(1, java.sql.Date.valueOf(date));
            pst.setTime(2, java.sql.Time.valueOf(time));
            pst.setInt(3, aid);
            pst.setInt(4, pid);
            rs = pst.executeQuery();
            return rs.next();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean terminExistsDay(LocalDate date, int aid, int pid) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM termin WHERE Datum=? AND Arzt_ID=? AND Patient_ID=?");
            pst.setDate(1, java.sql.Date.valueOf(date));
            pst.setInt(2, aid);
            pst.setInt(3, pid);
            rs = pst.executeQuery();
            return rs.next();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*public int updateTermin(LocalDate date, LocalTime time, int aid, int pid) {       @@@@@@@@@@
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            if (!terminExistsDay(date, aid, pid)) {
                return -1;
            } else {
                pst = con.prepareStatement("UPDATE termin SET  Zeit=? WHERE (Datum=? AND Arzt_ID=? AND Patient_ID=?)");
                pst.setTime(1, java.sql.Time.valueOf(time));
                pst.setDate(2, java.sql.Date.valueOf(date));
                pst.setInt(3, aid);
                pst.setInt(4, pid);
                pst.executeUpdate();
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    public int updateTermin(LocalDate date, LocalTime time, int aid, int pid, LocalDate newDate, LocalTime newTime) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            if (!terminExistsDay(date, aid, pid)) {
                return -1;
            } else {
                pst = con.prepareStatement("UPDATE termin SET  Datum=?, Zeit=? WHERE (Datum=? AND Zeit=? AND Arzt_ID=? AND Patient_ID=?)");
                pst.setDate(1, java.sql.Date.valueOf(newDate));
                pst.setTime(2, java.sql.Time.valueOf(newTime));
                pst.setDate(3, java.sql.Date.valueOf(date));
                pst.setTime(4,java.sql.Time.valueOf(time));
                pst.setInt(5, aid);
                pst.setInt(6, pid);
                pst.executeUpdate();
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteTermin(LocalDate date, LocalTime time, int aid, int pid) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            if (!terminExists(date,time,aid,pid)){
                return -1;
            } else {
                pst = con.prepareStatement("DELETE FROM termin WHERE Datum=? AND Zeit=? AND Arzt_ID=? AND Patient_ID=?");
                pst.setDate(1, java.sql.Date.valueOf(date));
                pst.setTime(2, java.sql.Time.valueOf(time));
                pst.setInt(3, aid);
                pst.setInt(4, pid);
                pst.executeUpdate();
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int terminErstellen(LocalDate date, LocalTime time, int aid, int pid) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            if (terminExists(date,time,aid,pid)){
                return -1;
            } else {
                pst = con.prepareStatement("INSERT INTO termin(Datum,Zeit,Arzt_ID,Patient_ID) VALUES (?,?,?,?)");
                pst.setDate(1, java.sql.Date.valueOf(date));
                pst.setTime(2, java.sql.Time.valueOf(time));
                pst.setInt(3, aid);
                pst.setInt(4, pid);
                pst.executeUpdate();
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }
}
