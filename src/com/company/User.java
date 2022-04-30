package com.company;

public class User {
    private String username;
    private String password;

    private String permission;  // "admin" (Rezeptionist) oder "user" (Arzt)

    public User(String username, String password, String permission) {
        this.username = username;
        this.password = password;
        this.permission = permission;
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
