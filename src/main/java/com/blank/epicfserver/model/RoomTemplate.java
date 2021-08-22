package com.blank.epicfserver.model;

public class RoomTemplate {
    private String name;
    private String[] nextRooms;

    public RoomTemplate(String name, String[] nextRooms) {
        this.name = name;
        this.nextRooms = nextRooms;
    }

    public String getName() {
        return name;
    }

    protected Room createRoom() {
        return new Room(name, nextRooms.clone());
    }

    public String[] getNextRooms() {
        return nextRooms;
    }

}
