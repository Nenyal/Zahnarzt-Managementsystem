package com.gui;

import com.company.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// User Data Access Object
public class UserDAO {
    Connection con;

    PreparedStatement pst;

    ResultSet rs;

    Database db = new Database();

    public boolean userExists(int id){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT * FROM user WHERE UserID=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            return rs.next();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public User getUserObjekt(String uname, String pass) {
        if (uname.equals("") || pass.equals("")) {
            return null;
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = db.getConnection();
                pst = con.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
                pst.setString(1, uname);
                pst.setString(2, pass);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return new User(uname, pass, rs.getString(3));
                } else {
                    return null;
                }
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int saveUser(int id,String uname,String pass, String permission){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            pst = con.prepareStatement("SELECT COUNT(*) FROM user WHERE UserID=?");
            pst.setInt(1,id);
            rs = pst.executeQuery();
            rs.next();
            if (rs.getInt(1) != 0){
                return -1;
            } else {
                pst = con.prepareStatement("INSERT INTO user VALUES (?,?,?,?)");
                pst.setInt(1,id);
                pst.setString(2,uname);
                pst.setString(3,pass);
                pst.setString(4,permission);
                pst.executeUpdate();
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }

    }

    public int deleteUser(int id){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            if (!userExists(id)) return -1;
            else {
                pst = con.prepareStatement("DELETE FROM user WHERE UserID=?");
                pst.setInt(1,id);
                pst.executeUpdate();
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int updateUser(int id, String un, String pass){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = db.getConnection();
            if (!userExists(id)){
                return -1;
            } else {
                pst = con.prepareStatement("UPDATE user SET username=?, password=? WHERE (UserID=?)");
                pst.setString(1,un);
                pst.setString(2,pass);
                pst.setInt(3,id);
                pst.executeUpdate();
                return 0;
            }
        } catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }


}
