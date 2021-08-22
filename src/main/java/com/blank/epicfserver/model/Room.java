package com.blank.epicfserver.model;

import com.blank.epicfserver.payload.RoomPayload;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

public class Room implements Cloneable {
    private String name;
    private Boolean visited = false;
    private Boolean monsterKilled = false;

    private String[] nextRooms;
    private Monster monster;

    public Room(String name, String[] nextRooms) {
        this.name = name;
        this.nextRooms = nextRooms;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public void setMonsterKilled(Boolean monsterKilled) {
        this.monsterKilled = monsterKilled;
    }

    public String getName() {
        return name;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Room(name, nextRooms.clone());
    }

    public String[] getNextRooms() {
        return nextRooms;
    }

    public Boolean getVisited() {
        return visited;
    }

    public Boolean getMonsterKilled() {
        return monsterKilled;
    }

    public RoomPayload genPayload() {
        RoomPayload roomPayload = new RoomPayload(name, visited, monsterKilled);
        if(visited) {
            roomPayload.setMonster(monster.getTemplate().getName());
        }
        if(monsterKilled) {
            roomPayload.setNextRooms(nextRooms);
        }
        return roomPayload;
    }
}
