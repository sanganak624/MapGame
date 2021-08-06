package com.example.mapgame;

public class GameMap {
    private Area grid[][];

    public GameMap(Area[][] grid) {
        this.grid = grid;
    }

    public Area[][] getGrid() {
        return grid;
    }

    public void setGrid(Area[][] grid) {
        this.grid = grid;
    }
}
