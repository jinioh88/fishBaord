package com.fishing.fishboard;

import com.fishing.fishboard.domain.FishBoard;
import com.fishing.fishboard.persistence.FishBoardRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class FishoBoardTest {
    @Autowired
    FishBoardRepository repository;

    @Test
    public void insertBoards() {
        IntStream.range(0,300).forEach(n->{
            FishBoard board = new FishBoard();
            board.setTitle("배스 낚시"+n);
            board.setContent("배스낙씨 임니다.");
            board.setWriter("강태공"+(n%7));

            repository.save(board);
        });
    }
}
