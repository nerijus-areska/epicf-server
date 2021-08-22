package com.blank.epicfserver.model;

import com.blank.epicfserver.payload.MonsterPayload;

public class Monster {
    private MonsterTemplate template;
    private Integer maxHp;
    private Integer damage;

    private Integer hp;
    private Loot loot;

    public Monster(MonsterTemplate template, Integer maxHp, Integer damage) {
        this.template = template;
        this.maxHp = maxHp;
        this.hp = this.maxHp;
        this.damage = damage;
    }

    public Integer getMaxHp() {
        return maxHp;
    }

    public Integer getDamage() {
        return damage;
    }

    public void respawn() {
        this.hp = this.maxHp;
    }

    public MonsterPayload genPayload() {
        return new MonsterPayload(template.getName(), hp, loot);
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public MonsterTemplate getTemplate() {
        return template;
    }

    public Loot getLoot() {
        return loot;
    }

    public void setLoot(Loot loot) {
        this.loot = loot;
    }
}
