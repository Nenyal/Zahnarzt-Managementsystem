package com.company;

import java.time.LocalTime;
import java.time.LocalDate;

public class Termin {
    private LocalDate datum;
    private LocalTime zeit;
    private int ArztID;
    private int PatientID;

    public Termin(LocalDate datum, LocalTime zeit, int aid, int pid) {
        this.datum = datum;
        this.zeit = zeit;
        this.ArztID = aid;
        this.PatientID = pid;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public LocalTime getZeit() {
        return zeit;
    }

    public int getArztID() {
        return ArztID;
    }

    public int getPatientID() {
        return PatientID;
    }

    public void editTermin(LocalDate datum) {
        this.datum = datum;
    }

    public void editTermin(LocalTime zeit) {
        this.zeit = zeit;
    }

    @Override
    public boolean equals(Object o) {
        Termin t = (Termin) o;
        if (o == this) {
            return true;
        }
        return this.PatientID == t.PatientID && this.ArztID == t.ArztID && this.datum.equals(t.datum) && this.zeit.equals(t.zeit);
    }
}
