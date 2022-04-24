package com.company;

import java.util.ArrayList;

public class Klinik {
    private ArrayList<Operation> operationen = new ArrayList<>();
    private ArrayList<Patient> patienten = new ArrayList<>();
    private ArrayList<Arzt> arzte = new ArrayList<>();

    public void addOperation(int id, String name, float kost) {
        operationen.add(new Operation(id, name, kost));

    }

    public void deleteOperation(int id) {
        for (Operation op : operationen) {
            if (op.getId() == id) {
                operationen.remove(op);
                return;
            }
        }
    }

    public void addPatient(Patient p) {
        patienten.add(p);
    }

    public void deletePatient(int id) {
        patienten.remove(this.searchPatient(id));
    }

    public void addArzt(Arzt a) {
        arzte.add(a);
    }

    public void deleteArzt(int id) {
        arzte.remove(this.searchArzt(id));
    }

    public Patient searchPatient(int id) {
        for (Patient patient : patienten) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    public Arzt searchArzt(int id) {
        for (Arzt arzt : arzte) {
            if (arzt.getId() == id) {
                return arzt;
            }
        }
        return null;
    }
}
