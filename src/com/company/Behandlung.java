package com.company;

public class Behandlung {
    private Operation operation;
    private String notizen;

    public Behandlung(Operation o, String n) {
        this.operation = o;
        this.notizen = n;
    }

    public void editBehandlung(Operation o) {
        this.operation = o;
    }

    public void editBehandlung(String n) {
        this.notizen = n;
    }

    public void addNotiz(String n) {
        this.notizen += " " + n;
    }

}
