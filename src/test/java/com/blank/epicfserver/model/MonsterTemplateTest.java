package com.blank.epicfserver.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class MonsterTemplateTest {

    @Test
    void shouldGenerateLootOneItem() {
        MonsterTemplate template = new MonsterTemplate("asd", 0, 0, 10);
        template.addLootProb(100, "item1");
        Loot loot = template.generateLoot();
        assertThat(loot.getCoins()).isBetween(0, 10);
        assertThat(loot.getItems().size()).isEqualTo(1);

        assertThat(loot.getItems().get(0)).isEqualTo("item1");
    }

    @Test
    void shouldGenerateLootThreeItems() {
        MonsterTemplate template = new MonsterTemplate("asd", 0, 0, 10);
        template.addLootProb(100, "item1");
        template.addLootProb(100, "item2");
        template.addLootProb(100, "item3");
        Loot loot = template.generateLoot();

        assertThat(loot.getItems().size()).isEqualTo(3);
    }

    @Test
    void shouldGenerateLootNoItems() {
        MonsterTemplate template = new MonsterTemplate("asd", 0, 0, 10);
        template.addLootProb(0, "item1");
        template.addLootProb(0, "item2");
        template.addLootProb(0, "item3");
        Loot loot = template.generateLoot();

        assertThat(loot.getItems().size()).isEqualTo(0);
    }


}
