package com.example.mapgame;

import java.util.List;

public class Area implements Cloneable{
    private boolean town;
    private String name;
    private List<Item> items;

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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Area(boolean town, List<Item> items,String name) {
        this.town = town;
        this.items = items;
        this.name = name;
    }

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }

    public Area() {
    }
}
