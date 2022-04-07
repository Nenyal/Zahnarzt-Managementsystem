package com.company;

public class Person {
    private int id;
    private String name;
    private String nachname;
    private int telefonNummer;

    public Person(int id, String name, String nachname, int tel){
        this.id = id;
        this.name = name;
        this.nachname = nachname;
        this.telefonNummer = tel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelefonNummer(int telefonNummer) {
        this.telefonNummer = telefonNummer;
    }

    public int getId() {
        return id;
    }

    public int getTelefonNummer() {
        return telefonNummer;
    }

    public String getNachname() {
        return nachname;
    }

    public String getName() {
        return name;
    }
}
