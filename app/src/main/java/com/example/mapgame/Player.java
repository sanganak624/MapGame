package com.example.mapgame;

import android.media.audiofx.DynamicsProcessing;
import android.support.annotation.NonNull;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class Player implements Cloneable {
    private int rowLocation;
    private int colLocation;
    private int cash;
    private int health;
    private int equipmentMass;
    private List<Equipment> equipments;

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
        return equipments;
    }

    public void setEquipment(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public void addEquipment(Equipment equipments) {
        this.equipments.add(equipments);
    }

    public Player(int rowLocation, int colLocation, int cash, int health, int equipmentMass, List<Equipment> equipment) {
        setEquipment(equipment);
        setCash(cash);
        setHealth(health);
        setEquipmentMass(equipmentMass);
        setRowLocation(rowLocation);
        setColLocation(colLocation);
    }

    public void setPlayer(int rowLocation, int colLocation, int cash, int health, int equipmentMass)
    {
        setCash(cash);
        setHealth(health);
        setEquipmentMass(equipmentMass);
        setRowLocation(rowLocation);
        setColLocation(colLocation);
    }

    public Player(int rowLocation, int colLocation, int cash, int health, int equipmentMass) {
        setCash(cash);
        setHealth(health);
        setEquipmentMass(equipmentMass);
        setRowLocation(rowLocation);
        setColLocation(colLocation);
        equipments = new LinkedList<Equipment>();
    }

    public void decrementCol()
    {
        colLocation --;
    }

    public void decrementRow()
    {
        rowLocation --;
    }

    public void incrementCol()
    {
        colLocation ++;
    }

    public void incrementRow()
    {
        rowLocation ++;
    }

}
