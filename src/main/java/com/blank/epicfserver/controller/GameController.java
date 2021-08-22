package com.blank.epicfserver.controller;

import com.blank.epicfserver.model.Item;
import com.blank.epicfserver.payload.EquipItemRequest;
import com.blank.epicfserver.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping("/getItems")
    public ResponseEntity<?> getItems() {
//        Long userId = Util.getUserId();
        Long userId = 1L;
        return new ResponseEntity<>(gameService.getItems(userId), HttpStatus.OK);
    }

    @GetMapping("/getEquippedItems")
    public ResponseEntity<?> getEquippedItems() {
//        Long userId = Util.getUserId();
        Long userId = 1L;
        return new ResponseEntity<>(gameService.getEquippedItems(userId), HttpStatus.OK);
    }

    @PostMapping("/equipItem")
    public ResponseEntity<?> equipItem(@RequestBody EquipItemRequest item) {
        gameService.equipItem(1L, item.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
