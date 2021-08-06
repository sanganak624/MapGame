package com.example.mapgame;

public class Food extends Item {
    private int health;

    public void sethealth(int health) {
        this.health = health;
    }

    public int gethealth()
    {
        return health;
    }

    @Override
    public String toString() {
        return "Food{" +
                "health=" + health +
                '}';
    }

    public Food(int mass, String description, int value)
    {
        sethealth(mass);
        setDescription(description);
        setValue(value);
    }
}
