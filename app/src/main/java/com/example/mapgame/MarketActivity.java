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
    private Button inventory;
    private Button market;

    private List<Button> itemBuyButtons = new LinkedList<Button>();;
    private List<Button> itemSellButtons = new LinkedList<Button>();;
    private List<TextView> itemText = new LinkedList<TextView>();;
    private TextView locationText;

    private TextView cash;
    private TextView health;
    private TextView equiptmentMass;

    boolean toggle = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        cash = (TextView) findViewById(R.id.cashText);
        health = (TextView) findViewById(R.id.healthText);
        equiptmentMass = (TextView) findViewById(R.id.equiptmentMassText);

        leaveButton = (Button) findViewById(R.id.leaveButton);
        inventory = (Button) findViewById(R.id.inventory);
        market = (Button) findViewById(R.id.market);

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
        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle == false) {
                    Player player = gameData.getPlayer();
                    fillPlayerItems(player.getEquipment());
                    toggle = true;
                }
                else if(toggle == true)
                {
                    updateList();
                    toggle = false;
                }

            }
        });
        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateList();
            }
        });

    }

    private void updateList()
    {
        Player player = gameData.getPlayer();
        int row = player.getRowLocation();
        int col = player.getColLocation();
        Area area = new Area();
        try {
            area = (Area) gameData.getMap().getArea(row,col).clone();
        }
        catch (Exception e)
        {

        }

        List<Item> items = area.getItems();

        fillAreaItems(items);

    }

    private void fillAreaItems(List<Item> items)
    {

        int i = 0;
        while(i<items.size())
        {
            Item currentItem = items.get(i);
            if(currentItem.getClass().getSimpleName().equals("Equipment"))
            {
                Equipment currentEqu = (Equipment) currentItem;
                Button buyButton = itemBuyButtons.get(i);
                Button sellButton = itemSellButtons.get(i);
                TextView textBox = itemText.get(i);

                buyButton.setText(Integer.toString(currentEqu.getValue()));
                sellButton.setText(Double.toString(currentEqu.getValue()*0.75));
                textBox.setText(currentEqu.getDescription()+" (Mass:"+Integer.toString(currentEqu.getMass())+")");

                buyButton.setVisibility(View.VISIBLE);
                sellButton.setVisibility(View.VISIBLE);
                textBox.setVisibility(View.VISIBLE);

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
                buyButton.setVisibility(View.VISIBLE);
                sellButton.setVisibility(View.VISIBLE);
                textBox.setVisibility(View.VISIBLE);
            }
            i++;
        }

        while(i<9)
        {
            Button buyButton = itemBuyButtons.get(i);
            Button sellButton = itemSellButtons.get(i);
            TextView textBox = itemText.get(i);
            buyButton.setVisibility(View.INVISIBLE);
            sellButton.setVisibility(View.INVISIBLE);
            textBox.setVisibility(View.INVISIBLE);
            i++;
        }

    }

    private void fillPlayerItems(List<Equipment> items)
    {

        int i = 0;
        if(items!=null)
        {
            while (i<items.size()) {
                Item currentItem = items.get(i);

                Equipment currentEqu = (Equipment) currentItem;
                Button buyButton = itemBuyButtons.get(i);
                Button sellButton = itemSellButtons.get(i);
                TextView textBox = itemText.get(i);

                buyButton.setText(Integer.toString(currentEqu.getValue()));
                sellButton.setText(Double.toString(currentEqu.getValue() * 0.75));
                textBox.setText(currentEqu.getDescription() + " (Mass:" + Integer.toString(currentEqu.getMass()) + ")");

                buyButton.setVisibility(View.VISIBLE);
                sellButton.setVisibility(View.VISIBLE);
                textBox.setVisibility(View.VISIBLE);

                i++;
            }
        }
        while(i<9)
        {
            Button buyButton = itemBuyButtons.get(i);
            Button sellButton = itemSellButtons.get(i);
            TextView textBox = itemText.get(i);
            buyButton.setVisibility(View.INVISIBLE);
            sellButton.setVisibility(View.INVISIBLE);
            textBox.setVisibility(View.INVISIBLE);
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