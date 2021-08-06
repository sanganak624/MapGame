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

    public Food(int health, String description, int value)
    {
        sethealth(health);
        setDescription(description);
        setValue(value);
    }
}
