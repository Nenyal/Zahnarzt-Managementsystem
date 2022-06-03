package com.company;

import java.util.Date;

public class Behandlung {
    private int id;
    private String notizen;
    private int opID;
    private int patID;
    private int arztID;
    private Date datum;

    public Behandlung(int id, String n, int opID, int patID, int arztID, Date datum) {
        this.id = id;
        this.notizen = n;
        this.opID = opID;
        this.patID = patID;
        this.arztID = arztID;
        this.datum = datum;
    }

    public void setArztID(int arztID) {
        this.arztID = arztID;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNotizen(String notizen) {
        this.notizen = notizen;
    }

    public void setOpID(int opID) {
        this.opID = opID;
    }

    public void setPatID(int patID) {
        this.patID = patID;
    }

    public int getId() {
        return id;
    }

    public int getArztID() {
        return arztID;
    }

    public Date getDatum() {
        return datum;
    }

    public int getOpID() {
        return opID;
    }

    public int getPatID() {
        return patID;
    }

    public String getNotizen() {
        return notizen;
    }

    public void editBehandlung(String n) {
        this.notizen = n;
    }

    public void addNotiz(String n) {
        this.notizen += " " + n;
    }

}
