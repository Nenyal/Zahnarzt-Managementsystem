package com.company;

import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDate;

public class Arzt extends Person {
    private ArrayList<Termin> termine = new ArrayList<>();

    public Arzt(int id, String name, String nachname, String tel) {
        super(id, name, nachname, tel);
    }

    public void editArzt(String name, String nachname) {
        this.setName(name);
        this.setNachname(nachname);
    }

    public void editArzt(String tel) {
        this.setTelefonNummer(tel);
    }

    public void addTermin(Termin t) {
        termine.add(t);
    }

    public Termin searchTermin(LocalDate datum, LocalTime zeit, int pid) {
        Termin ter = new Termin(datum,zeit,this.getId(),pid);
        for (Termin t : termine) {
            if (t.equals(ter)){
                return t;
            }
        }
        return null;
    }

    public ArrayList<Termin> getTermine() {
        return termine;
    }
}
