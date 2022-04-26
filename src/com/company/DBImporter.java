package com.company;

import java.sql.*;
import java.util.ArrayList;

public class DBImporter {
    static String jdbcURL = "jdbc:mysql://localhost:3306/zahnarztappdb";
    static String username = "root";
    static String password = "7394";
    Connection con;

    PreparedStatement pst;

    ResultSet rs;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, username, password);
    }

    public void ShowError(SQLException exception) {
        System.out.printf("Error: " + exception.getMessage());
        System.out.printf("Error Code: " + exception.getErrorCode());
    }

    public User getUserObjekt(String uname, String pass) {
        if (uname.equals("") || pass.equals("")) {
            return null;
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = this.getConnection();
                pst = con.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
                pst.setString(1, uname);
                pst.setString(2, pass);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return new User(uname, pass);
                } else {
                    return null;
                }
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Patient getPatientObject(int id) {
        if (id == 0) {
            return null;
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = this.getConnection();
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
    public ArrayList<Patient> getPatienten(){
        ArrayList<Patient> patienten = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = this.getConnection();
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
            con = this.getConnection();
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
