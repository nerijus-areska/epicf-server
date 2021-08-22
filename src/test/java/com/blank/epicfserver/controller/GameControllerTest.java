package com.blank.epicfserver.controller;

import com.blank.epicfserver.model.Item;
import com.blank.epicfserver.model.User;
import com.blank.epicfserver.security.services.UserDetailsImpl;
import com.blank.epicfserver.security.services.UserDetailsServiceImpl;
import com.blank.epicfserver.service.GameService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = GameController.class)
@AutoConfigureMockMvc(addFilters = false)
public class GameControllerTest {
    @MockBean
    private GameService gameService;
    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should list all items for default user, id: 1L, via /getItems")
    public void shouldGetItems() throws Exception {
        Item item1 = new Item();
        Item item2 = new Item();

        given(gameService.getItems(1L)).willReturn(Arrays.asList(item1, item2));

        mockMvc.perform(MockMvcRequestBuilders.get("/game/getItems"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(item1.getId())));
    }
}
