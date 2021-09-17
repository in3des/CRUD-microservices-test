package com.in3des.gameitemservice.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class GameItemList {

    private List<GameItem> gameItemList;

    public List<GameItem> getGameItemList() {
        return gameItemList;
    }

    public void setGameItemList(List<GameItem> gameItemList) {
        this.gameItemList = gameItemList;
    }
}
