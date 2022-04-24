package com.company;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Patient extends Person {
    private ArrayList<Termin> termine = new ArrayList<>();
    private ArrayList<Behandlung> vergangeneBehandlungen = new ArrayList<>();

    public Patient(int id, String name, String nachname, String tel) {
        super(id, name, nachname, tel);
    }

    public void addTermin(Termin t) {
        termine.add(t);
    }

    public void deleteTermin(Termin t) {
        termine.remove(searchTermin(t.getDatum(),t.getZeit()));
    }

    public Termin searchTermin(LocalDate datum, LocalTime zeit) {
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

    public void deleteBehandlung(Behandlung b){
        vergangeneBehandlungen.remove(b);
    }

    public void editPatient(String name, String nachname) {
        this.setName(name);
        this.setNachname(nachname);
    }

    public void editPatient(String tel) {
        this.setTelefonNummer(tel);
    }
}
