package com.example.mapgame;

public class Area {
    private boolean town;
    private Item items[];

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

    public Area(boolean town, Item[] items) {
        this.town = town;
        this.items = items;
    }

    public Area() {
    }
}
