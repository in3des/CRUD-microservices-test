package com.in3des.gameitemservice.repository;

import com.in3des.gameitemservice.model.GameItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameItemRepository extends JpaRepository<GameItem, Long> {

    List<GameItem> findAllByOrderByIdAsc();

//    void update(GameItem gameItem);
}
