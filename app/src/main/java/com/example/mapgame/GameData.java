package com.example.mapgame;

import java.util.LinkedList;
import java.util.List;

public class GameData {
    private static GameData instance = null;

    public static GameData getInstance()
    {
        if(instance == null)
        {
            instance = new GameData();
        }
        return instance;
    }

    private GameMap map;
    private Player player;

    private GameData()
    {
        player = new Player(1,1,100,100,0);
        map = new GameMap(3);
        map.setGrid(setAreas());
    }

    private Area[][] setAreas()
    {
        Area[][] areas = new Area[3][3];
        List<Item> defaultItems =  new LinkedList<Item>();;
        List<Item> GoalSet1 = new LinkedList<Item>();;
        List<Item> GoalSet2 = new LinkedList<Item>();;
        List<Item> GoalSet3 = new LinkedList<Item>();;

        defaultItems.add(new Equipment(1,"phone",1));
        defaultItems.add(new Food(10,"apple",1));
        defaultItems.add(new Food(-10,"mashroom",1));
        defaultItems.add(new Equipment(1,"rocks",1));
        defaultItems.add(new Equipment(1,"gold",1));

        GoalSet1.add(new Equipment(1,"jade monkey",1));
        GoalSet1.add(new Food(10,"apple",1));
        GoalSet1.add(new Food(-10,"mashroom",1));
        GoalSet1.add(new Equipment(1,"rocks",1));
        GoalSet1.add(new Equipment(1,"gold",1));

        GoalSet2.add(new Equipment(1,"the roadmap",1));
        GoalSet2.add(new Food(10,"apple",1));
        GoalSet2.add(new Food(-10,"mashroom",1));
        GoalSet2.add(new Equipment(1,"rocks",1));
        GoalSet2.add(new Equipment(1,"gold",1));

        GoalSet3.add(new Equipment(1,"ice scraper",1));
        GoalSet3.add(new Food(10,"apple",1));
        GoalSet3.add(new Food(-10,"mashroom",1));
        GoalSet3.add(new Equipment(1,"rocks",1));
        GoalSet3.add(new Equipment(1,"gold",1));

        areas[0][0] = new Area(true,GoalSet2,"Nancledra");
        areas[0][1] = new Area(true,defaultItems,"Erast");
        areas[0][2] = new Area(false,defaultItems,"");

        areas[1][0] = new Area(false,defaultItems,"");
        areas[1][1] = new Area(true,GoalSet1,"Whitebridge");
        areas[1][2] = new Area(false,defaultItems,"");

        areas[2][0] = new Area(true,defaultItems,"Spalding");
        areas[2][1] = new Area(false,defaultItems,"");
        areas[2][2] = new Area(false,GoalSet3,"");

        return areas;
    }

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


}
