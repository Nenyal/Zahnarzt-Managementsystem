package com.company;

import java.util.ArrayList;

public class Patient extends Person {
    ArrayList<Termin> termine = new ArrayList<>();
    ArrayList<Behandlung> vergangeneBehandlungen = new ArrayList<>();

    public Patient(int id, String name, String nachname, int tel) {
        super(id, name, nachname, tel);
    }

    public void addTermin(Termin t){
        termine.add(t);
    }

    public ArrayList<Termin> getTermine() {
        return termine;
    }

    public void editPatient(String name,String nachname){
        this.setName(name);
        this.setNachname(nachname);
    }
    public void editPatient(int tel){
        this.setTelefonNummer(tel);
    }
}
