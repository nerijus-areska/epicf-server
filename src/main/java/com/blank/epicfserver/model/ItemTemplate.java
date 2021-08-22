package com.blank.epicfserver.model;

import java.util.HashMap;
import java.util.Map;

public class ItemTemplate {
    private int armor;
    private int damage;
    private ItemSlot itemSlot;
    private String name;

    public final static String ITEM_WOODEN_HELMET = "Wooden helmet";
    public final static String ITEM_MILITARY_HELMET = "Military helmet";

    public ItemTemplate(String name, int armor, int damage, ItemSlot itemSlot) {
        this.name = name;
        this.armor = armor;
        this.damage = damage;
        this.itemSlot = itemSlot;
    }

    public final static Map<String, ItemTemplate> itemMap = new HashMap<>();

    static {
        createItemTemplate(ITEM_WOODEN_HELMET, 3, 0, ItemSlot.HEAD);
        createItemTemplate(ITEM_MILITARY_HELMET, 5, 0, ItemSlot.HEAD);
    }

    public static void createItemTemplate(String name, int armor, int damage, ItemSlot slot) {
        itemMap.put(name, new ItemTemplate(name, armor, damage, slot));
    }

    public int getArmor() {
        return armor;
    }

    public int getDamage() {
        return damage;
    }

    public ItemSlot getItemSlot() {
        return itemSlot;
    }

    public String getName() {
        return name;
    }

    public Item createItem() {
        return new Item(this);
    }

}
