package com.blank.epicfserver.payload;

public class RoomStatusPayload {
    private UserPayload user;
    private MonsterPayload monster;
    private AreaPayload area;

    public UserPayload getUser() {
        return user;
    }

    public MonsterPayload getMonster() {
        return monster;
    }

    public AreaPayload getArea() {
        return area;
    }

    public RoomStatusPayload(UserPayload userPayload, MonsterPayload monsterPayload, AreaPayload areaPayload) {
        this.user = userPayload;
        this.monster = monsterPayload;
        this.area = areaPayload;
    }
}
