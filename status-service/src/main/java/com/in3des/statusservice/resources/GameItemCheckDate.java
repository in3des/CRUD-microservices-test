package com.in3des.statusservice.resources;

import com.in3des.statusservice.models.GameItem;
import com.in3des.statusservice.models.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameItemCheckDate {

    @Autowired
    private GameItemServiceImpl gameItemServiceImpl;

    private static String currentDate = "01-01-2011";


    public List<GameItem> gameItemCheckDate(GameItemServiceImpl gameItemServiceImpl) {
        List<GameItem> checkList = gameItemServiceImpl.listAll();
        System.out.println("<<< --- Check Date results: --- >>>");
        List<GameItem> updateList = checkList.parallelStream()
                .filter(gameItem -> gameItem.getReleaseDate().equals(currentDate) && gameItem.getStatus().equals(Status.PRODUCTION))
//                .forEach(System.out::println);
                .collect(Collectors.toList());
        System.out.println(updateList);
        System.out.println("<<< --- Check Date END --- >>>");
        return updateList;
    }

    public void gameItemStatusUpdate(GameItemServiceImpl gameItemServiceImpl) {
        List<GameItem> updateList = gameItemCheckDate(gameItemServiceImpl);
        for (GameItem gameItem: updateList) {
            gameItem.setStatus(Status.RELEASE);
            gameItemServiceImpl.save(gameItem);
        }
        System.out.printf("<<< --- Status successfully updated for %d GameItems --- >>>", updateList.size());
    }

    @PostConstruct
    private void checkTestGameItem() {
        gameItemStatusUpdate(gameItemServiceImpl);
    }
}
