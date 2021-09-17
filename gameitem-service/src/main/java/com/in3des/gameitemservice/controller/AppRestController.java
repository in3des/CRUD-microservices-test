package com.in3des.gameitemservice.controller;

import com.in3des.gameitemservice.model.GameItem;
import com.in3des.gameitemservice.services.impl.GameItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/gameitem")
public class AppRestController {

    private final GameItemServiceImpl service;

    @Autowired
    public AppRestController(GameItemServiceImpl service) {
        this.service = service;
    }

    @RequestMapping("/{Id}")
    public ResponseEntity<GameItem> getGameItemInfo(@PathVariable("Id") Long id) {
        try {
            GameItem gameItem = service.get(id);
            return new ResponseEntity<>(gameItem, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    @PutMapping("/update")
    public ResponseEntity<GameItem> updateGameItemInfo(@RequestBody GameItem gameItem) {
        try {
            service.save(gameItem);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//        return new GameItem(id, "Test Name", Status.RELEASE, "11-11-2021");
    }

}
