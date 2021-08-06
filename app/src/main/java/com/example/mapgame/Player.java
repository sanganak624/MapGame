package com.example.mapgame;

import android.media.audiofx.DynamicsProcessing;

import java.util.List;

public class Player {
    private int rowLocation;
    private int colLocation;
    private int cash;
    private int health;
    private int equipmentMass;
    private List<Equipment> equipment;

    public int getRowLocation() {
        return rowLocation;
    }

    public void setRowLocation(int rowLocation) {
        this.rowLocation = rowLocation;
    }

    public int getColLocation() {
        return colLocation;
    }

    public void setColLocation(int colLocation) {
        if(colLocation>=0) {
            this.colLocation = colLocation;
        }
        else {
            this.colLocation = 0;
        }
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        if(cash>=0) {
            this.cash = cash;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(health>=0 || health<=100) {
            this.health = health;
        }
    }

    public int getEquipmentMass() {
        return equipmentMass;
    }

    public void setEquipmentMass(int equipmentMass) {
        this.equipmentMass = equipmentMass;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public void addEquipment(Equipment equipment) {
        this.equipment.add(equipment);
    }

    public Player(int rowLocation, int colLocation, int cash, int health, int equipmentMass, List<Equipment> equipment) {
        setEquipment(equipment);
        setCash(cash);
        setHealth(health);
        setEquipmentMass(equipmentMass);
        setRowLocation(rowLocation);
        setColLocation(colLocation);
    }
}
