package com.blank.epicfserver.model;

import java.util.HashMap;
import java.util.Map;

public class Tier {
    private Integer level;

    private Map<Integer, String> monsterProbs = new HashMap<>();

    public Tier(Integer level) {
        this.level = level;
    }

    public void addProb(Integer prob, String monster) {
        monsterProbs.put(prob, monster);
    }

    public Map<Integer, String> getMonsterProbs() {
        return monsterProbs;
    }
}
