package com.blank.epicfserver.model;

import java.util.*;

public class MonsterTemplate {

    public static Map<String, MonsterTemplate> MONSTERS = new HashMap<>();

    private String name;
    private Integer hp;
    private Integer damage;
    private Integer lootCoins;
    private Map<String, Integer> lootMap = new HashMap<>();
    private static Random random = new Random();

    public MonsterTemplate(String name, Integer hp, Integer damage, Integer lootCoins) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.lootCoins = lootCoins;
    }

    static {
        MonsterTemplate monster = createMonsterTemplate("rat", 100, 10, 10);
        monster.addLootProb(100, ItemTemplate.ITEM_WOODEN_HELMET);
        createMonsterTemplate("bat", 120, 13, 15);
        createMonsterTemplate("spider", 150, 20, 20);
        createMonsterTemplate("spider queen", 200, 30, 40);
    }

    public void addLootProb(int prob, String itemName) {
        lootMap.put(itemName, prob);
    }

    private static MonsterTemplate createMonsterTemplate(String name, Integer hp, Integer damage, Integer lootCoins) {
        MonsterTemplate monsterTemplate = new MonsterTemplate(name, hp, damage, lootCoins);
        MONSTERS.put(name, monsterTemplate);
        return monsterTemplate;
    }

    public static void main(String[] args) {
        try {
            MonsterTemplate m2 = (MonsterTemplate)MONSTERS.get("rat").clone();
            int i = 1;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Monster createMonster() {
        return new Monster(this, hp, damage);
    }

    public Loot generateLoot() {
        int coins = random.nextInt(this.lootCoins - 1) + 1;
        List<String> itemLoot = new ArrayList<>();
        for(String itemName: lootMap.keySet()) {
            if(Math.random() * 100 < lootMap.get(itemName)) {
                itemLoot.add(itemName);
            }
        }
        return new Loot(coins, itemLoot);
    }

    public String getName() {
        return name;
    }
}
