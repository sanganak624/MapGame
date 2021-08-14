package com.example.mapgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class NavigationActivity extends AppCompatActivity {

    private GameData gameData = GameData.getInstance();

    private GameMap map = gameData.getMap();
    private Player player1 = gameData.getPlayer();


    //Buttons
    private Button northButton;
    private Button southButton;
    private Button eastButton;
    private Button westButton;
    private Button restartButton;
    private Button optionsButton;


    //Text fields
    private TextView cash;
    private TextView health;
    private TextView equiptmentMass;
    private TextView currentArea;
    private TextView currentAreaText2;
    private TextView status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);


        northButton = (Button) findViewById(R.id.northButton);
        southButton = (Button) findViewById(R.id.southButton);
        eastButton = (Button) findViewById(R.id.eastButton);
        westButton = (Button) findViewById(R.id.westButton);
        restartButton = (Button) findViewById(R.id.restartButton);
        optionsButton = (Button) findViewById(R.id.optionsButton);

        cash = (TextView) findViewById(R.id.cashText);
        health = (TextView) findViewById(R.id.healthText);
        equiptmentMass = (TextView) findViewById(R.id.equiptmentMassText);
        currentArea = (TextView) findViewById(R.id.currentAreaText);
        status = (TextView) findViewById(R.id.statusText);
        currentAreaText2 = (TextView) findViewById(R.id.currentAreaText2);

        //gameSetup();
        gameUpdate();

        northButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameData.getPlayer().setHealth((int) Math.max(0.0,player1.getHealth()-5.0-(player1.getEquipmentMass()/2.0)));
                gameData.getPlayer().incrementRow();
                gameUpdate();
            }
        });

        southButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameData.getPlayer().setHealth((int) Math.max(0.0,player1.getHealth()-5.0-(player1.getEquipmentMass()/2.0)));
                gameData.getPlayer().decrementRow();
                gameUpdate();
            }
        });

        eastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameData.getPlayer().setHealth((int) Math.max(0.0,player1.getHealth()-5.0-(player1.getEquipmentMass()/2.0)));
                gameData.getPlayer().incrementCol();
                gameUpdate();
            }
        });

        westButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameData.getPlayer().setHealth((int) Math.max(0.0,player1.getHealth()-5.0-(player1.getEquipmentMass()/2.0)));
                gameData.getPlayer().decrementCol();
                gameUpdate();
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameSetup();
                gameUpdate();
            }
        });

        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigationActivity.this, MarketActivity.class));
            }
        });

    }

    private void updateStatusBar()
    {
        int player1EquipmentMass = gameData.getPlayer().getEquipmentMass();
        int player1Health = gameData.getPlayer().getHealth();
        int player1Cash = gameData.getPlayer().getCash();

        equiptmentMass.setText(Integer.toString(player1EquipmentMass));
        if(player1Health<=0)
        {
            health.setText("0");
            status.setText("You Lost The Game :(");
            status.setVisibility(View.VISIBLE);
        }
        else
        {
            health.setText(Integer.toString(player1Health));
            status.setVisibility(View.INVISIBLE);
        }
        cash.setText(Integer.toString(player1Cash));

    }

    private void updateTextView()
    {
        int row = gameData.getPlayer().getRowLocation();
        int col = gameData.getPlayer().getColLocation();
        Area area = gameData.getMap().getArea(row,col);

        Boolean town = area.isTown();

        if(town)
        {
            currentArea.setText("Town");
            currentAreaText2.setVisibility(View.VISIBLE);
            currentAreaText2.setText(area.getName());
        }
        else
        {
            currentArea.setText("Wilderness");
            currentAreaText2.setVisibility(View.INVISIBLE);
        }
    }

    private void gameUpdate()
    {
        updateStatusBar();
        updateTextView();
        hideNavButtons();
    }
    private void gameSetup()
    {
        gameData.getMap().setGrid(setAreas());
        gameData.getPlayer().setPlayer(1,1,100,100,0);
    }

    private Area[][] setAreas()
    {
        Area[][] areas = new Area[3][3];
        List<Item> defaultItems = null;
        List<Item> GoalSet1 = null;
        List<Item> GoalSet2 = null;
        List<Item> GoalSet3 = null;

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

    private void hideNavButtons()
    {
        int row = gameData.getPlayer().getRowLocation();
        int col = gameData.getPlayer().getColLocation();

        int health = gameData.getPlayer().getHealth();
        if(health>0)
        {
            if(row==0)
            {
                southButton.setVisibility(View.INVISIBLE);
                northButton.setVisibility(View.VISIBLE);

            }
            else if(row == 2)
            {
                southButton.setVisibility(View.VISIBLE);
                northButton.setVisibility(View.INVISIBLE);
            }
            else
            {
                southButton.setVisibility(View.VISIBLE);
                northButton.setVisibility(View.VISIBLE);
            }

            if(col==0)
            {
                westButton.setVisibility(View.INVISIBLE);
                eastButton.setVisibility(View.VISIBLE);

            }
            else if(col == 2)
            {
                westButton.setVisibility(View.VISIBLE);
                eastButton.setVisibility(View.INVISIBLE);
            }
            else
            {
                westButton.setVisibility(View.VISIBLE);
                eastButton.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            southButton.setVisibility(View.INVISIBLE);
            northButton.setVisibility(View.INVISIBLE);
            westButton.setVisibility(View.INVISIBLE);
            eastButton.setVisibility(View.INVISIBLE);
        }

    }
}