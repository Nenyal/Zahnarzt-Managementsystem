package com.company;

import java.util.ArrayList;

public class Arzt extends Person {
    ArrayList<Termin> termine = new ArrayList<>();

    public Arzt(int id, String name, String nachname, int tel) {
        super(id, name, nachname, tel);
    }

    public void editArzt(String name, String nachname) {
        this.setName(name);
        this.setNachname(nachname);
    }

    public void editArzt(int tel) {
        this.setTelefonNummer(tel);
    }

    public void addTermin(Termin t) {
        termine.add(t);
    }

    public ArrayList<Termin> getTermine() {
        return termine;
    }
}
