package com.fishing.fishboard;

import com.fishing.fishboard.domain.JohangBoard;
import com.fishing.fishboard.domain.Member;
import com.fishing.fishboard.persistence.JohangRepository;
import com.fishing.fishboard.persistence.MemberRepository;
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
@Log    @Commit
public class JohangTest {
    @Autowired
    JohangRepository johangRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void insertOne(){
        IntStream.range(1,10).forEach(n->{
            JohangBoard board = new JohangBoard();
            board.setContent("경기도 배스 잡았어요~!!!");
            board.setImages("images"+n+".jpg");
            board.setLikes(n);

            Member member = memberRepository.findMemberByUid("oh");
            board.setMember(member);

            johangRepository.save(board);
        });
    }
}
