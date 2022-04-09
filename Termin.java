package com.company;

import java.time.LocalTime;
import java.util.Date;

public class Termin {
    private Date datum;
    private LocalTime zeit;

    public Termin(Date datum, LocalTime zeit) {
        this.datum = datum;
        this.zeit = zeit;
    }

    public Date getDatum() {
        return datum;
    }

    public LocalTime getZeit() {
        return zeit;
    }

    public void editTermin(Date datum) {
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
