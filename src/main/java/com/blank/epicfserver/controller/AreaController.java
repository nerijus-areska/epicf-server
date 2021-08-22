package com.blank.epicfserver.controller;

import com.blank.epicfserver.model.*;
import com.blank.epicfserver.payload.RoomStatusPayload;
import com.blank.epicfserver.service.GameService;
import com.blank.epicfserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    GameService gameService;

    @Autowired
    UserService userService;

    @Autowired
    SimpMessagingTemplate msgTemplate;


    @GetMapping("/enter")
    public ResponseEntity<?> enterArea(@RequestParam String areaName, HttpSession session) {
        User user = userService.getUser(1L);
        user.setEnergy(user.getEnergy() - 25)   ;
        Area area = gameService.enterArea(areaName);
        session.setAttribute("current_area", area);
        msgTemplate.convertAndSend("/message", "hihihi you bastard");
        return new ResponseEntity<>(area.genPayload(), HttpStatus.OK);
    }

    @GetMapping("/enterRoom")
    public ResponseEntity<?> enterRoom(@RequestParam String roomName, HttpSession session) {
        Area currentArea = (Area)session.getAttribute("current_area");
        Room room = currentArea.getRoom(roomName);
        currentArea.setCurrentRoom(room);
        room.setVisited(true);
        Monster monster = room.getMonster();
        User user = userService.getUser(1L);

        return new ResponseEntity<>(
                new RoomStatusPayload(user.genPayload(), monster.genPayload(), currentArea.genPayload()),
                HttpStatus.OK);
    }

    @GetMapping("/attack")
    public ResponseEntity<?> attack(HttpSession session) {
        Area currentArea = (Area)session.getAttribute("current_area");
        Room currentRoom = currentArea.getCurrentRoom();
        Monster monster = currentRoom.getMonster();
        User user = userService.getUser(1L);

        gameService.attack(user, currentRoom);

        return new ResponseEntity<>(
                new RoomStatusPayload(user.genPayload(), monster.genPayload(), currentArea.genPayload()),
                HttpStatus.OK);
    }

    @GetMapping("/grab_loot_item")
    public ResponseEntity<?> grabLootItem(@RequestParam String itemName, HttpSession session) {
        Area currentArea = (Area)session.getAttribute("current_area");
        Room currentRoom = currentArea.getCurrentRoom();
        Monster monster = currentRoom.getMonster();
        User user = userService.getUser(1L);

        gameService.grabItem(user, monster, itemName);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
