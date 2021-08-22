package com.blank.epicfserver.service;

import com.blank.epicfserver.model.*;
import com.blank.epicfserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Service
public class GameService {

    UserRepository userRepository;

    @Autowired
    public GameService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<Item> getItems(Long userId) {
        User user = userRepository.findById(userId).get();
//        List<UserItem> items = user.getItems();
//        items.forEach(userItem -> {
//            userItem.setItem(Item.itemMap.get(userItem.getItemName()));
////            userItem.setUser(null);
//        });
//        return items;
        return user.getItems();
    }

    public Map<ItemSlot, Item> getEquippedItems(Long userId) {
        User user = userRepository.findById(userId).get();
        return user.getEquippedItems();
    }

    public void equipItem(Long userId, String itemId) {
        User user = userRepository.findById(userId).get();
        Item userItem = user.findItemById(itemId).get();
        user.equipUserItem(userItem);
    }

    public Area enterArea(String name) {
        AreaTemplate areaTemplate = AreaTemplate.AREAS.get(name);
        // We generate it now always - but it should be moved to persistence layer and not be generated for subsequent runs
        Area area = areaTemplate.generateArea();

        return area;
    }

    public void attack(User user, Room currentRoom) {
        Monster monster = currentRoom.getMonster();
        user.setHp(user.getHp() - 10);
        monster.setHp(monster.getHp() - 40);

        if(monster.getHp() <= 0) {
            handleMonsterKilled(user, currentRoom);
        }
    }

    private void handleMonsterKilled(User user, Room room) {
        room.setMonsterKilled(true);
        Loot loot = room.getMonster().getTemplate().generateLoot();
        room.getMonster().setLoot(loot);
        user.setCoins(loot.getCoins());
    }

    public void grabItem(User user, Monster monster, String itemName) {
        // monster is for validation ( and you can remove the item from loot list )
        Item item = ItemTemplate.itemMap.get(itemName).createItem();
        user.addItem(item);
    }
}
