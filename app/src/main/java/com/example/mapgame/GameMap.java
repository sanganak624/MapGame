package com.example.mapgame;

public class GameMap {
    private Area grid[][];

    public GameMap(int size)
    {
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                grid[i][j] = new Area();
            }
        }
    }

    public Area[][] getGrid() {
        return grid;
    }

    public void setGrid(int row, int col, Area area)
    {
        this.grid[row][col] = area;
    }

    public void setGrid(Area[][] areas)
    {
        grid = areas;
    }
}
