package com.blank.epicfserver.model;

import java.util.List;

public class Loot {
    private int coins;
    private List<String> items;

    public Loot(int coins, List<String> items) {
        this.coins = coins;
        this.items = items;
    }

    public int getCoins() {
        return coins;
    }

    public List<String> getItems() {
        return items;
    }
}
