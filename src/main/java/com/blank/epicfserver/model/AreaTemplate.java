package com.blank.epicfserver.model;

import java.util.*;

public class AreaTemplate {
    public static Map<String, AreaTemplate> AREAS = new HashMap<>();

    private String name;
    private Map<String, RoomTemplate> rooms = new HashMap<>();
    private SortedMap<Integer, Tier> tiers = new TreeMap<>();
    private static Random random = new Random();

    static {
        AreaTemplate area = new AreaTemplate("Rat cave");
        area.createRoom("7_1",null);
        area.createRoom("6_1", new String[]{"7_1"});
        area.createRoom("5_1", new String[]{"6_1"});
        area.createRoom("4_2", new String[]{"6_1"});
        area.createRoom("4_1", new String[]{"5_1"});
        area.createRoom("3_2", new String[]{"4_1"});
        area.createRoom("3_1", new String[]{"4_2"});
        area.createRoom("2_2", new String[]{"3_1", "3_2"});
        area.createRoom("2_1", new String[]{"4_2"});
        area.createRoom("1_1", new String[]{"2_1", "2_2"});

        Tier tier = area.createTier(1);
        tier.addProb(100, "rat");
        tier = area.createTier(2);
        tier.addProb(80, "rat");
        tier.addProb(100, "bat");
        tier = area.createTier(3);
        tier.addProb(50, "rat");
        tier.addProb(100, "bat");
        tier = area.createTier(4);
        tier.addProb(10, "rat");
        tier.addProb(60, "spider");
        tier.addProb(100, "bat");
        tier = area.createTier(5);
        tier.addProb(50, "bat");
        tier.addProb(100, "spider");
        tier = area.createTier(6);
        tier.addProb(100, "spider");
        tier = area.createTier(7);
        tier.addProb(100, "spider queen");


        AREAS.put(area.getName(), area);
    }

    public Area generateArea() {
        Area newArea = new Area(name);
        for(String roomKey: rooms.keySet()) {
            RoomTemplate roomTemplate = rooms.get(roomKey);
            Room room = new Room(roomTemplate.getName(), roomTemplate.getNextRooms());
            newArea.addRoom(room);
            Integer tierName = Integer.valueOf(roomTemplate.getName().split("_")[0]);
            Tier tier = tiers.get(tierName);
            Map<Integer, String> monsterProbs = tier.getMonsterProbs();
            int randVal = random.nextInt(100);
            for(Integer prob: monsterProbs.keySet()) {
                if(prob > randVal) {
                    String monsterName = monsterProbs.get(prob);
                    Monster monster = MonsterTemplate.MONSTERS.get(monsterName).createMonster();
                    room.setMonster(monster);
                    break;
                }
            }
        }
        newArea.getRoom("1_1").setVisited(true);
        newArea.getRoom("1_1").setMonsterKilled(true);
        newArea.getRoom("2_1").setVisited(true);
        newArea.setCurrentRoom(newArea.getRoom("1_1"));
        return newArea;
    }

    public static void main(String[] args) {
        AreaTemplate areaTemplate = AREAS.get("Rat cave");
        Area genArea = areaTemplate.generateArea();
        int i = 1;
    }

    private Tier createTier(Integer level) {
        Tier tier = new Tier(level);
        tiers.put(level, tier);
        return tier;
    }

    private RoomTemplate createRoom(String name, String[] nextRooms) {
        RoomTemplate room = new RoomTemplate(name, nextRooms);
        rooms.put(room.getName(), room);
        return room;
    }


    public RoomTemplate getRoom(String name) {
        return rooms.get(name);
    }

    public AreaTemplate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
