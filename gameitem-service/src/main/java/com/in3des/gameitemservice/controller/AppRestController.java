package com.in3des.gameitemservice.controller;

import com.in3des.gameitemservice.model.GameItem;
import com.in3des.gameitemservice.model.GameItemList;
import com.in3des.gameitemservice.services.impl.GameItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.net.URI;

@RestController
@RequestMapping("/gameitem")
public class AppRestController {

    private final GameItemServiceImpl service;

    @Autowired
    public AppRestController(GameItemServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<GameItem> getGameItemAll() {
        return service.listAll();
    }

    @GetMapping("/listall")
    public GameItemList getGameItemList() {
        return new GameItemList(service.listAll());
    }

    @RequestMapping("/{Id}")
    public ResponseEntity<GameItem> getGameItemInfo(@PathVariable("Id") Long id) {
        try {
            GameItem gameItem = service.get(id);
            return new ResponseEntity<>(gameItem, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateGameItemInfo(@RequestBody GameItem gameItem) {
        try {
            service.save(gameItem);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateGameItemInfo(@RequestBody GameItem gameItem, @PathVariable Long id) {
        try {
            GameItem existGameItem = service.get(id);
            service.save(gameItem);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
