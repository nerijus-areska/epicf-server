package com.blank.epicfserver.payload;

public class UserPayload {
    private Integer hp;
    private Integer energy;

    public UserPayload(Integer hp, Integer energy) {
        this.hp = hp;
        this.energy = energy;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getEnergy() {
        return energy;
    }
}
