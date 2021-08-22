package com.blank.epicfserver.service;

import com.blank.epicfserver.model.Item;
import com.blank.epicfserver.model.ItemTemplate;
import com.blank.epicfserver.model.User;
import com.blank.epicfserver.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<Item> itemArgumentCaptor;

    GameService gameService;

    @BeforeEach
    public void setup() {
        gameService = new GameService(userRepository);
    }

    @Test
    public void shouldGetUserItems() {
        User user = new User();
        Item item = new Item(ItemTemplate.itemMap.get(ItemTemplate.ITEM_WOODEN_HELMET));
        user.addItem(item);
        Mockito.when(userRepository.findById(111L)).thenReturn(Optional.of(user));

        assertThat(gameService.getItems(111L)).contains(item);
    }

    @Test
    public void shouldEquipItem() {
        User user = Mockito.mock(User.class);
        Item item = new Item();
        Mockito.when(user.findItemById(item.getId())).thenReturn(Optional.of(item));
        Mockito.when(userRepository.findById(111L)).thenReturn(Optional.of(user));

        gameService.equipItem(111L, item.getId());

        //Sample with checking if an object with a Item class was passed
//        Mockito.verify(user, Mockito.times(1)).equipUserItem(ArgumentMatchers.any(Item.class));

        //If we want to capture the actual object was passed( in this we assert id, but we could also equals the object)
        Mockito.verify(user, Mockito.times(1)).equipUserItem(itemArgumentCaptor.capture());
        assertThat(itemArgumentCaptor.getValue().getId()).isEqualTo(item.getId());
    }
}