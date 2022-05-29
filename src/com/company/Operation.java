package com.company;

import javafx.beans.value.ObservableValue;

public class Operation {
    private int id;
    private String name;
    private float kost;

    public Operation(int id, String name, float kost) {
        this.id = id;
        this.name = name;
        this.kost = kost;
    }

    public int getId() {
        return id;
    }

    public float getKost() {
        return kost;
    }

    public String getName() {
        return name;
    }

    public void editOperation(float kost) {
        this.kost = kost;
    }

    public void editOperation(String name) {
        this.name = name;
    }
}
