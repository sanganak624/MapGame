package com.example.mapgame;

public class Area {
    private boolean town;
    private String name;
    private Item items[];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTown() {
        return town;
    }

    public void setTown(boolean town) {
        this.town = town;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public Area(boolean town, Item[] items,String name) {
        this.town = town;
        this.items = items;
        this.name = name;
    }

    public Area() {
    }
}
