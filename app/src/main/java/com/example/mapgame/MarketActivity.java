package com.example.mapgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

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

    private TextView cash;
    private TextView health;
    private TextView equiptmentMass;

    private int MAXMASS = 10;

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
                Player player = gameData.getPlayer();
                fillPlayerItems(player.getEquipment());

            }
        });
        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateList();
            }
        });

        buyButtonListeners();
        sellButtonListeners();

    }

    private Area buyAction(int i)
    {
        Player player = gameData.getPlayer();
        Area curArea = (Area) gameData.getMap().getArea(player.getRowLocation(),player.getColLocation());
        List<Item> items = curArea.getItems();
        Item item = items.get(i);
        int curCash = player.getCash();
        int remCash = curCash - item.getValue();
        if(remCash > 0)
        {
            //Equipment check
            if (item.getClass().getSimpleName().equals("Equipment")) {
                Equipment equ = (Equipment) item;
                int curMass = player.getEquipmentMass();
                int newMass = curMass + equ.getMass();
                if(newMass < MAXMASS)
                {
                    player.addEquipment((Equipment) item);
                    player.setEquipmentMass(newMass);
                    player.setCash(remCash);
                    items.remove(i);
                    Toast.makeText(MarketActivity.this, "Purchase successful", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MarketActivity.this, "To heavy to carry. Purchase Failed", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Food food = (Food) item;
                int curHealth = player.getHealth();
                int newHealth = curHealth + food.gethealth();

                if(newHealth<0)
                {
                    newHealth = 0;
                    player.setHealth(newHealth);
                    startActivity(new Intent(MarketActivity.this, NavigationActivity.class));
                    //END game
                }
                else if(newHealth >= 100)
                {
                    newHealth = 100;
                }
                player.setHealth(newHealth);
                items.remove(i);
                Toast.makeText(MarketActivity.this, "Purchase successful", Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            Toast.makeText(MarketActivity.this, "Insufficient cash. Purchase Failed", Toast.LENGTH_SHORT).show();
        }
        return curArea;
    }

    private void sellAction(int i)
    {
        Player player = gameData.getPlayer();
        Area curArea = gameData.getMap().getArea(player.getRowLocation(),player.getColLocation());
        List<Item> areaItems = curArea.getItems();

        if(areaItems.size()<9)
        {

            List<Equipment> items = player.getEquipment();
            Equipment equ = items.get(i);
            int curCash = player.getCash();
            int remCash = curCash + equ.getValue();

            int curMass = player.getEquipmentMass();
            int newMass = curMass - equ.getMass();

            player.setEquipmentMass(newMass);
            player.setCash(remCash);

            areaItems.add(equ);

            items.remove(i);

            Toast.makeText(MarketActivity.this, "Sale successful", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MarketActivity.this, "Market Full, Sale Failed", Toast.LENGTH_SHORT).show();

        }
    }

    private void buyButtonListeners()
    {
        itemBuyButtons.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Area curArea = buyAction(0);
                updateStatusBar();
                fillAreaItems(curArea.getItems());
            }
        });
        itemBuyButtons.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Area curArea = buyAction(1);
                updateStatusBar();
                fillAreaItems(curArea.getItems());
            }
        });
        itemBuyButtons.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Area curArea = buyAction(2);
                updateStatusBar();
                fillAreaItems(curArea.getItems());
            }
        });
        itemBuyButtons.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Area curArea = buyAction(3);
                updateStatusBar();
                fillAreaItems(curArea.getItems());
            }
        });
        itemBuyButtons.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Area curArea = buyAction(4);
                updateStatusBar();
                fillAreaItems(curArea.getItems());
            }
        });
        itemBuyButtons.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Area curArea = buyAction(5);
                updateStatusBar();
                fillAreaItems(curArea.getItems());
            }
        });
        itemBuyButtons.get(6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Area curArea = buyAction(6);
                updateStatusBar();
                fillAreaItems(curArea.getItems());
            }
        });
        itemBuyButtons.get(7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Area curArea = buyAction(7);
                updateStatusBar();
                fillAreaItems(curArea.getItems());
            }
        });
        itemBuyButtons.get(8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Area curArea = buyAction(8);
                updateStatusBar();
                fillAreaItems(curArea.getItems());
            }
        });
    }

    private void sellButtonListeners()
    {
        itemSellButtons.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellAction(0);
                updateStatusBar();
                fillPlayerItems(gameData.getPlayer().getEquipment());
            }
        });
        itemSellButtons.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellAction(1);
                updateStatusBar();
                fillPlayerItems(gameData.getPlayer().getEquipment());
            }
        });
        itemSellButtons.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellAction(2);
                updateStatusBar();
                fillPlayerItems(gameData.getPlayer().getEquipment());
            }
        });
        itemSellButtons.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellAction(3);
                updateStatusBar();
                fillPlayerItems(gameData.getPlayer().getEquipment());
            }
        });
        itemSellButtons.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellAction(4);
                updateStatusBar();
                fillPlayerItems(gameData.getPlayer().getEquipment());
            }
        });
        itemSellButtons.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellAction(5);
                updateStatusBar();
                fillPlayerItems(gameData.getPlayer().getEquipment());
            }
        });
        itemSellButtons.get(6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellAction(6);
                updateStatusBar();
                fillPlayerItems(gameData.getPlayer().getEquipment());
            }
        });
        itemSellButtons.get(7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellAction(7);
                updateStatusBar();
                fillPlayerItems(gameData.getPlayer().getEquipment());
            }
        });
        itemSellButtons.get(8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellAction(8);
                updateStatusBar();
                fillPlayerItems(gameData.getPlayer().getEquipment());
            }
        });
    }

    private void updateList()
    {
        Player player = gameData.getPlayer();
        int row = player.getRowLocation();
        int col = player.getColLocation();
        Area area = (Area) gameData.getMap().getArea(row,col);

        List<Item> items = area.getItems();
        fillAreaItems(items);

    }

    private void fillAreaItems(List<Item> items)
    {

        int i = 0;
        while(i<items.size())
        {
            Item currentItem = items.get(i);
            Button buyButton = itemBuyButtons.get(i);
            Button sellButton = itemSellButtons.get(i);
            TextView textBox = itemText.get(i);
            if(currentItem.getClass().getSimpleName().equals("Equipment"))
            {
                Equipment currentEqu = (Equipment) currentItem;
                buyButton.setText("$"+Integer.toString(currentEqu.getValue()));
                textBox.setText(currentEqu.getDescription()+" (Mass:"+Integer.toString(currentEqu.getMass())+")");
            }
            else
            {
                Food currentEqu = (Food) currentItem;
                buyButton.setText("$"+Integer.toString(currentEqu.getValue()));
                textBox.setText(currentEqu.getDescription());
            }

            buyButton.setVisibility(View.VISIBLE);
            sellButton.setVisibility(View.INVISIBLE);
            textBox.setVisibility(View.VISIBLE);
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

                sellButton.setText("$"+Double.toString(currentEqu.getValue() * 0.75));
                textBox.setText(currentEqu.getDescription() + " (Mass:" + Integer.toString(currentEqu.getMass()) + ")");

                buyButton.setVisibility(View.INVISIBLE);
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