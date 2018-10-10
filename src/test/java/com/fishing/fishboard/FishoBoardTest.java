package com.fishing.fishboard;

import com.fishing.fishboard.domain.FishBoard;
import com.fishing.fishboard.persistence.FishBoardRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

//    @Test
//    public void testList() {
//        Pageable pageable = PageRequest.of(0,20,Sort.Direction.DESC,"bno");
//        Page<FishBoard> result = repository.findAll(repository.makePredicate(null,null),pageable);
//        log.info("page : "+result.getPageable());
//        log.info("------");
//        result.getContent().forEach(board->{
//            log.info(""+board);
//        });
//    }
//
//    @Test
//    public void testList2() {
//        Pageable pageable = PageRequest.of(0,10,Sort.Direction.DESC,"bno");
//        Page<FishBoard> result = repository.findAll(repository.makePredicate("t","10"),pageable);
//        log.info("page : "+result.getPageable());
//        log.info("------");
//        result.getContent().forEach(board->{
//            log.info(""+board);
//        });
//    }
}
