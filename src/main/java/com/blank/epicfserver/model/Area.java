package com.blank.epicfserver.model;

import com.blank.epicfserver.payload.AreaPayload;
import com.blank.epicfserver.payload.RoomPayload;

import java.util.*;

public class Area {
    private String name;
    private Map<String, Room> roomMap = new HashMap<>();
    private Room currentRoom;

    public Area() {

    }

    public Area(String name) {
        this.name = name;
    }

    public void addRoom(Room room) {
        roomMap.put(room.getName(), room);
    }

    public Room getRoom(String name) {
        return roomMap.get(name);
    }

    public String getName() {
        return name;
    }

    public AreaTemplate getTemplate() {
        return AreaTemplate.AREAS.get(this.name);
    }

    public AreaPayload genPayload() {
        AreaPayload areaPayload = new AreaPayload(name);
        genRoomPayload(areaPayload, "1_1");
        return areaPayload;
    }

    public void genRoomPayload(AreaPayload areaPayload, String roomKey) {
        Room room = roomMap.get(roomKey);
        areaPayload.addRoom(room.genPayload());
        if(room.getMonsterKilled()) {
            if(room.getNextRooms() != null) {
                for (String linkedRoomKey : room.getNextRooms()) {
                    genRoomPayload(areaPayload, linkedRoomKey);
                }
            }
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
