package com.example.mapgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;

public class MarketActivity extends AppCompatActivity {

    private GameData gameData = GameData.getInstance();

    private Button leaveButton;

    private List<Button> itemBuyButtons = new LinkedList<Button>();;
    private List<Button> itemSellButtons = new LinkedList<Button>();;
    private List<TextView> itemText = new LinkedList<TextView>();;

    private TextView locationText;


    private TextView cash;
    private TextView health;
    private TextView equiptmentMass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        cash = (TextView) findViewById(R.id.cashText);
        health = (TextView) findViewById(R.id.healthText);
        equiptmentMass = (TextView) findViewById(R.id.equiptmentMassText);

        leaveButton = (Button) findViewById(R.id.leaveButton);

        itemBuyButtons.add((Button) findViewById(R.id.item1BuyButton));
        itemBuyButtons.add((Button) findViewById(R.id.item2BuyButton));
        itemBuyButtons.add((Button) findViewById(R.id.item3BuyButton));
        itemBuyButtons.add((Button) findViewById(R.id.item4BuyButton));
        itemBuyButtons.add((Button) findViewById(R.id.item5BuyButton));
        itemBuyButtons.add((Button) findViewById(R.id.item6BuyButton));
        itemBuyButtons.add((Button) findViewById(R.id.item7BuyButton));
        itemBuyButtons.add((Button) findViewById(R.id.item8BuyButton));
        itemBuyButtons.add((Button) findViewById(R.id.item9BuyButton));

        itemSellButtons.add((Button) findViewById(R.id.item1SellButton));
        itemSellButtons.add((Button) findViewById(R.id.item2SellButton));
        itemSellButtons.add((Button) findViewById(R.id.item3SellButton));
        itemSellButtons.add((Button) findViewById(R.id.item4SellButton));
        itemSellButtons.add((Button) findViewById(R.id.item5SellButton));
        itemSellButtons.add((Button) findViewById(R.id.item6SellButton));
        itemSellButtons.add((Button) findViewById(R.id.item7SellButton));
        itemSellButtons.add((Button) findViewById(R.id.item8SellButton));
        itemSellButtons.add((Button) findViewById(R.id.item9SellButton));

        itemText.add((TextView) findViewById(R.id.item1Text));
        itemText.add((TextView) findViewById(R.id.item2Text));
        itemText.add((TextView) findViewById(R.id.item3Text));
        itemText.add((TextView) findViewById(R.id.item4Text));
        itemText.add((TextView) findViewById(R.id.item5Text));
        itemText.add((TextView) findViewById(R.id.item6Text));
        itemText.add((TextView) findViewById(R.id.item7Text));
        itemText.add((TextView) findViewById(R.id.item8Text));
        itemText.add((TextView) findViewById(R.id.item9Text));

        updateStatusBar();
        updateList();

        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MarketActivity.this, NavigationActivity.class));
            }
        });

    }

    private void updateList()
    {
        Player player = gameData.getPlayer();
        int row = player.getRowLocation();
        int col = player.getColLocation();
        Area area = gameData.getMap().getArea(row,col);

        List<Item> items = area.getItems();
        fillAreaItems(items);

    }

    private void fillAreaItems(List<Item> items)
    {

        int i = 0;
        while(!items.isEmpty())
        {
            Item currentItem = items.remove(0);
            if(currentItem.getClass().getSimpleName().equals("Equipment"))
            {
                Equipment currentEqu = (Equipment) currentItem;
                Button buyButton = itemBuyButtons.get(i);
                Button sellButton = itemSellButtons.get(i);
                TextView textBox = itemText.get(i);

                buyButton.setText(Integer.toString(currentEqu.getValue()));
                sellButton.setText(Double.toString(currentEqu.getValue()*0.75));
                textBox.setText(currentEqu.getDescription()+" mass:"+Integer.toString(currentEqu.getMass()));

            }
            else
            {
                Food currentEqu = (Food) currentItem;
                Button buyButton = itemBuyButtons.get(i);
                Button sellButton = itemSellButtons.get(i);
                TextView textBox = itemText.get(i);

                buyButton.setText(Integer.toString(currentEqu.getValue()));
                sellButton.setText(Double.toString(currentEqu.getValue()*0.75));
                textBox.setText(currentEqu.getDescription());
            }
            i++;
        }

    }

    private void updateStatusBar()
    {
        int player1EquipmentMass = gameData.getPlayer().getEquipmentMass();
        int player1Health = gameData.getPlayer().getHealth();
        int player1Cash = gameData.getPlayer().getCash();

        equiptmentMass.setText(Integer.toString(player1EquipmentMass));
        health.setText(Integer.toString(player1Health));
        cash.setText(Integer.toString(player1Cash));

    }
}