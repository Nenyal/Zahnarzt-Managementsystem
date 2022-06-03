package com.company;

public class User {
    private int id;
    private String username;
    private String password;

    private String permission;  // "admin" (Rezeptionist) oder "user" (Arzt)

    public User(int id, String username, String password, String permission) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.permission = permission;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPermission() {
        return permission;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
