package com.blank.epicfserver.payload;

import java.util.HashMap;
import java.util.Map;

public class AreaPayload {
    private String name;
    private Map<String, RoomPayload> roomMap = new HashMap<>();

    public AreaPayload(String name) {
        this.name = name;
    }
    public void addRoom(RoomPayload room) {
        roomMap.put(room.getName(), room);
    }

    public String getName() {
        return name;
    }

    public Map<String, RoomPayload> getRoomMap() {
        return roomMap;
    }
}
