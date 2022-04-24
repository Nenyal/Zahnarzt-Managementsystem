package com.company;

import java.time.LocalTime;
import java.time.LocalDate;

public class Termin {
    private LocalDate datum;
    private LocalTime zeit;

    public Termin(LocalDate datum, LocalTime zeit) {
        this.datum = datum;
        this.zeit = zeit;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public LocalTime getZeit() {
        return zeit;
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
        return this.datum.equals(t.datum) && this.zeit.equals(t.zeit);
    }
}
