package com.blank.epicfserver.payload;

import com.blank.epicfserver.model.Loot;

public class RoomPayload {
    private String name;
    private Boolean visited;
    private Boolean monsterKilled;
    private String[] nextRooms;

    private String monster;

    public RoomPayload(String name, Boolean visited, Boolean monsterKilled) {
        this.name = name;
        this.visited = visited;
        this.monsterKilled = monsterKilled;
    }

    public String getName() {
        return name;
    }

    public Boolean getVisited() {
        return visited;
    }

    public Boolean getMonsterKilled() {
        return monsterKilled;
    }

    public String getMonster() {
        return monster;
    }

    public void setMonster(String monster) {
        this.monster = monster;
    }

    public String[] getNextRooms() {
        return nextRooms;
    }

    public void setNextRooms(String[] nextRooms) {
        this.nextRooms = nextRooms;
    }
}
