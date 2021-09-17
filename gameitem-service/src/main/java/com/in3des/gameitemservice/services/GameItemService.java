package com.in3des.gameitemservice.services;

import com.in3des.gameitemservice.model.GameItem;

import java.util.List;

public interface GameItemService {

    GameItem create(GameItem item);

    List<GameItem> listAll();

    void save(GameItem gameItem);

    GameItem get(Long id);

    void delete(Long id);

}
