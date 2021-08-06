package com.example.mapgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private GameMap map = new GameMap(3);
    private Player player1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void gameSetup()
    {
        map.setGrid(setAreas());
        player1 = new Player(1,1,100,100,0);
    }

    private Area[][] setAreas()
    {
        Area[][] areas = new Area[3][3];
        Item[] defaultItems = new Item[5];

        defaultItems[0] = new Equipment(1,"phone",1);
        defaultItems[1] = new Food(10,"apple",1);
        defaultItems[2] = new Food(-10,"mashroom",1);
        defaultItems[3] = new Equipment(1,"rocks",1);
        defaultItems[4] = new Equipment(1,"gold",1);


        areas[0][0] = new Area(true,defaultItems);
        areas[0][1] = new Area(true,defaultItems);
        areas[0][2] = new Area(false,defaultItems);

        areas[1][0] = new Area(false,defaultItems);
        areas[1][1] = new Area(true,defaultItems);
        areas[1][2] = new Area(false,defaultItems);

        areas[2][0] = new Area(true,defaultItems);
        areas[2][1] = new Area(false,defaultItems);
        areas[2][2] = new Area(false,defaultItems);

        return areas;
    }
}