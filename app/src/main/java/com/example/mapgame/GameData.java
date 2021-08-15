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
        player = new Player(1,1,100,50,0);
        map = new GameMap(3);
        map.setGrid(setAreas());
    }

    public void GameData()
    {
        player = new Player(1,1,100,100,0);
        map = new GameMap(3);
        map.setGrid(setAreas());
    }

    public Area[][] setAreas()
    {
        Area[][] areas = new Area[3][3];
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                List<Item> itemSet1 =  new LinkedList<Item>();

                itemSet1.add(new Equipment(1,"Phone",5));
                itemSet1.add(new Food(10,"Apple",10));
                itemSet1.add(new Food(-10,"Mashroom",20));
                itemSet1.add(new Equipment(1,"Rocks",6));
                itemSet1.add(new Equipment(1,"Gold",8));

                if(i==0 && j==0)
                {
                    areas[i][j] = new Area(true,itemSet1,"Nancledra");
                }
                else if(i==0 && j==1)
                {
                    areas[i][j] = new Area(true,itemSet1,"Erast");
                }
                else if(i==0 && j==2)
                {
                    itemSet1.add(new Equipment(1,"Jade monkey",9));
                    areas[i][j] = new Area(false,itemSet1,"");
                }

                else if(i==1 && j==0)
                {
                    areas[i][j] = new Area(false,itemSet1,"");
                }
                else if(i==1 && j==1)
                {
                    areas[i][j] = new Area(true,itemSet1,"Whitebridge");
                }
                else if(i==1 && j==2)
                {
                    areas[i][j] = new Area(false,itemSet1,"");
                }

                else if(i==2 && j==0)
                {
                    itemSet1.add(new Equipment(1,"The roadmap",6));
                    areas[i][j] = new Area(true,itemSet1,"Spalding");
                }
                else if(i==2 && j==1)
                {
                    areas[i][j] = new Area(false,itemSet1,"");
                }
                else if(i==2 && j==2)
                {
                    itemSet1.add(new Equipment(1,"Ice scraper",8));
                    areas[i][j] = new Area(false,itemSet1,"");
                }

            }
        }
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
