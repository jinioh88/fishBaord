package com.fishing.fishboard;

import com.fishing.fishboard.domain.Member;
import com.fishing.fishboard.domain.PhotoBoard;
import com.fishing.fishboard.persistence.MemberRepository;
import com.fishing.fishboard.persistence.PhotoBoardRepository;
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
public class PhotoboardTest {
    @Autowired
    PhotoBoardRepository photoBoardRepository;
    @Autowired
    MemberRepository memberRepository;

    int i = 1;

    @Test
    public void insertTest() throws Exception {
        Member member = memberRepository.findMemberByUid("user0");
        IntStream.range(0,10).forEach(board->{
            PhotoBoard photoBoard = new PhotoBoard();
            photoBoard.setContent("낚시 조행기_"+(i++));
            photoBoard.setFilename("사진.jpg");
            photoBoard.setLocation("강화도");
            photoBoard.setMember(member);
            photoBoard.setTitle("배스 잡음");
        });
    }

}
