package com.fishing.fishboard;

import com.fishing.fishboard.domain.FishBoard;
import com.fishing.fishboard.domain.WebReply;
import com.fishing.fishboard.persistence.WebReplyRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class ReplyTest {
    @Autowired
    WebReplyRepository repository;

    @Test
    public void testInsertReplies() {
        Long[] arr = {101L, 103L, 104L};
        Arrays.stream(arr).forEach(num->{
            FishBoard board = new FishBoard();
            board.setBno(num);

            IntStream.range(0,5).forEach(i->{
                WebReply reply = new WebReply();
                reply.setReplyText("댓글_"+i);
                reply.setReplyer("user"+i);
                reply.setBoard(board);

                repository.save(reply);
            });
        });
    }
}
