package com.example.mapgame;

public class Equipment extends Item {
    private int mass;

    public void setMass(int mass) {
        if(mass>=0) {
            this.mass = mass;
        }
        else
        {
            this.mass=0;
        }
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "mass=" + mass +
                '}';
    }

    public int getMass()
    {
        return mass;
    }

    public Equipment(int mass, String description, int value)
    {
        setMass(mass);
        setDescription(description);
        setValue(value);
    }
}
