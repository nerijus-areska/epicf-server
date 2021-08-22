package com.blank.epicfserver.payload;

import com.blank.epicfserver.model.Loot;

public class MonsterPayload {
    private String name;
    private Integer hp;
    private Loot loot;

    public MonsterPayload(String name, Integer hp, Loot loot) {
        this.name = name;
        this.loot = loot;
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public Integer getHp() {
        return hp;
    }

    public Loot getLoot() {
        return loot;
    }
}
