package com.in3des.statusservice.resources;

import com.in3des.statusservice.models.GameItem;
import com.in3des.statusservice.models.GameItemList;
import com.in3des.statusservice.models.Movie;
import com.in3des.statusservice.models.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/status")
public class GameItemCheckDate {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static String currentDate = "01-01-2011";

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {

        return new Movie(movieId, "Test Movie Name");
    }

    @RequestMapping("/update")
    public List<GameItem> updateGameItemStatus() {

        GameItemList allList = webClientBuilder.build()
                .get()
                .uri("http://gameitem-service/gameitem/listall")
//                    .uri("http://localhost:8085/gameitem/listall")
                .retrieve()
                .bodyToMono(GameItemList.class)
                .block();

        List<GameItem> updateList = allList.getGameItemList().stream()
                .filter(gameItem -> gameItem.getReleaseDate().equals(currentDate) && gameItem.getStatus().equals(Status.PRODUCTION))
                .collect(Collectors.toList());

        for (GameItem gameItem: updateList) {
            gameItem.setStatus(Status.RELEASE);
        }

        for (GameItem item: updateList) {
            webClientBuilder.build()
                    .put()
                    .uri("http://gameitem-service/gameitem/update")
                    .body(Mono.just(item), GameItem.class)
                    .retrieve()
                    .bodyToMono(GameItem.class)
                    .block();
        }

            return updateList;

    }


}
