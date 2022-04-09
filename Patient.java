package com.company;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Patient extends Person {
    ArrayList<Termin> termine = new ArrayList<>();
    ArrayList<Behandlung> vergangeneBehandlungen = new ArrayList<>();

    public Patient(int id, String name, String nachname, int tel) {
        super(id, name, nachname, tel);
    }

    public void addTermin(Termin t) {
        termine.add(t);
    }

    public void deleteTermin(Termin t) {
        termine.remove(searchTermin(t.getDatum(),t.getZeit()));
    }

    public Termin searchTermin(Date datum, LocalTime zeit) {
        Termin ter = new Termin(datum,zeit);
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

    public void addVergangeneBehandlung(Behandlung b) {
        vergangeneBehandlungen.add(b);
    }

    public ArrayList<Behandlung> getVergangeneBehandlungen() {
        return vergangeneBehandlungen;
    }

    public void editPatient(String name, String nachname) {
        this.setName(name);
        this.setNachname(nachname);
    }

    public void editPatient(int tel) {
        this.setTelefonNummer(tel);
    }
}
