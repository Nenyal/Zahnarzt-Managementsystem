package com.company;

import java.time.LocalTime;
import java.util.Date;

public class Termin {
    private Date datum;
    private LocalTime zeit;

    public Termin(Date datum, LocalTime zeit){
        this.datum = datum;
        this.zeit = zeit;
    }

    public void editTermin(Date datum) {
        this.datum = datum;
    }

    public void editTermin(LocalTime zeit){
        this.zeit = zeit;
    }


}
