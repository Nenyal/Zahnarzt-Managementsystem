package com.company;

public class Person {
    private int id;
    private String name;
    private String nachname;
    private String telefonNummer;

    public Person(int id, String name, String nachname, String tel){
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

    public void setTelefonNummer(String telefonNummer) {
        this.telefonNummer = telefonNummer;
    }

    public int getId() {
        return id;
    }

    public String getTelefonNummer() {
        return telefonNummer;
    }

    public String getNachname() {
        return nachname;
    }

    public String getName() {
        return name;
    }
}
