package com.fishing.fishboard;

import com.fishing.fishboard.domain.ImageVO;
import com.fishing.fishboard.domain.JohangBoard;
import com.fishing.fishboard.domain.Member;
import com.fishing.fishboard.persistence.JohangRepository;
import com.fishing.fishboard.persistence.MemberRepository;
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
@Log    @Commit
public class JohangTest {
    @Autowired
    JohangRepository johangRepository;
    @Autowired
    MemberRepository memberRepository;
    int n = 1;
    @Test
    public void insertOne(){
        IntStream.range(1,20).forEach(n->{
            byte[] b = new byte[1024];
            String ss = "df;lkasdf;lkasdjfl;kasdjflasdkfj"+n;
            b = ss.getBytes();
            ImageVO vo = new ImageVO();
            vo.setFilename("image"+n);
//            vo.setData(b);
            JohangBoard board = new JohangBoard();
            board.setTitle("배스 잡이"+(n++));
            board.setContent("경기도 배스 잡았어요~!!!");
            board.setImagevo(vo);
            board.setLikes(n);

            Member member = memberRepository.findMemberByUid("oh");
            board.setMember(member);

            johangRepository.save(board);
        });
    }

//    @Test
//    public void list() {
//        Pageable pageable = PageRequest.of(0,20, Sort.Direction.DESC,"jno");
//        Page<JohangBoard> result = johangRepository.findAll(johangRepository.makePredicate(null,null),pageable);
//        log.info("======================================");
//        result.getContent().forEach(board->{
//            log.info(""+board);
//        });
//    }
}
