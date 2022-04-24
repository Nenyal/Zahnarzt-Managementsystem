package com.company;

public class Operation {
    private final int id;
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

    public void editOperation(float kost) {
        this.kost = kost;
    }

    public void editOperation(String name) {
        this.name = name;
    }
}
