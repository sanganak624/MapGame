package com.example.mapgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MarketActivity extends AppCompatActivity {

    private GameData gameData = GameData.getInstance();

    private Button leaveButton;

    private Button item1BuyButton;
    private Button item2BuyButton;
    private Button item3BuyButton;
    private Button item4BuyButton;
    private Button item5BuyButton;
    private Button item6BuyButton;
    private Button item7BuyButton;
    private Button item8BuyButton;
    private Button item9BuyButton;
    private Button item10BuyButton;

    private Button item1SellButton;
    private Button item2SellButton;
    private Button item3SellButton;
    private Button item4SellButton;
    private Button item5SellButton;
    private Button item6SellButton;
    private Button item7SellButton;
    private Button item8SellButton;
    private Button item9SellButton;
    private Button item10SellButton;

    private TextView locationText;
    private TextView item1Text;
    private TextView item2Text;
    private TextView item3Text;
    private TextView item4Text;
    private TextView item5Text;
    private TextView item6Text;
    private TextView item7Text;
    private TextView item8Text;
    private TextView item9Text;
    private TextView item10Text;

    private TextView cash;
    private TextView health;
    private TextView equiptmentMass;

    Player player1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        cash = (TextView) findViewById(R.id.cashText);
        health = (TextView) findViewById(R.id.healthText);
        equiptmentMass = (TextView) findViewById(R.id.equiptmentMassText);

        player1 = gameData.getPlayer();

        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MarketActivity.this, NavigationActivity.class));
            }
        });

    }

    private void updateStatusBar()
    {
        int player1EquipmentMass = player1.getEquipmentMass();
        int player1Health = player1.getHealth();
        int player1Cash = player1.getCash();

        equiptmentMass.setText(Integer.toString(player1EquipmentMass));
        health.setText(Integer.toString(player1Health));
        cash.setText(Integer.toString(player1Cash));

    }
}