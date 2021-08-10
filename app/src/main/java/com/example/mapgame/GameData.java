package com.example.mapgame;

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
