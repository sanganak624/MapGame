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

        gameUpdate();

        northButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameData.getPlayer().setHealth((int) Math.max(0.0,gameData.getPlayer().getHealth()-5.0-(gameData.getPlayer().getEquipmentMass()/2.0)));
                gameData.getPlayer().incrementRow();
                gameUpdate();
            }
        });

        southButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameData.getPlayer().setHealth((int) Math.max(0.0,gameData.getPlayer().getHealth()-5.0-(gameData.getPlayer().getEquipmentMass()/2.0)));
                gameData.getPlayer().decrementRow();
                gameUpdate();
            }
        });

        eastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameData.getPlayer().setHealth((int) Math.max(0.0,gameData.getPlayer().getHealth()-5.0-(gameData.getPlayer().getEquipmentMass()/2.0)));
                gameData.getPlayer().incrementCol();
                gameUpdate();
            }
        });

        westButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameData.getPlayer().setHealth((int) Math.max(0.0,gameData.getPlayer().getHealth()-5.0-(gameData.getPlayer().getEquipmentMass()/2.0)));
                gameData.getPlayer().decrementCol();
                gameUpdate();
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameData.GameData();
                gameUpdate();
            }
        });

        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentArea.getText().equals("Town")) {
                    startActivity(new Intent(NavigationActivity.this, MarketActivity.class));
                }
                else
                {
                    startActivity(new Intent(NavigationActivity.this, WildernessActivity.class));
                }
            }
        });
        winState();

    }
    private void winState()
    {

        List<Equipment> playerItem = gameData.getPlayer().getEquipment();
        int i=0;
        boolean ck1 = false;
        boolean ck2 = false;
        boolean ck3 = false;
        while(i<playerItem.size())
        {
            if(playerItem.get(i).getDescription().equals("Jade monkey"))
            {
                ck1 = true;
            }
            else if(playerItem.get(i).getDescription().equals("The roadmap"))
            {
                ck2 = true;
            }
            else if(playerItem.get(i).getDescription().equals("Ice scraper"))
            {
                ck3 = true;
            }
            i++;
        }
        if(ck1&&ck2&&ck3)
        {
            status.setText("WINNER");
            currentArea.setText("YOU HAVE");
            currentAreaText2.setVisibility(View.VISIBLE);
            currentAreaText2.setText("WON");
            status.setVisibility(View.VISIBLE);

            optionsButton.setVisibility(View.INVISIBLE);
            southButton.setVisibility(View.INVISIBLE);
            northButton.setVisibility(View.INVISIBLE);
            westButton.setVisibility(View.INVISIBLE);
            eastButton.setVisibility(View.INVISIBLE);
        }
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
            optionsButton.setVisibility(View.INVISIBLE);
        }
        else
        {
            health.setText(Integer.toString(player1Health));
            status.setVisibility(View.INVISIBLE);
            optionsButton.setVisibility(View.VISIBLE);
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
        gameData.getMap().setGrid(gameData.setAreas());
        gameData.getPlayer().setPlayer(1,1,100,100,0);
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