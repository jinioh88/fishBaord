package com.fishing.fishboard;

import com.fishing.fishboard.domain.Member;
import com.fishing.fishboard.domain.MemberRole;
import com.fishing.fishboard.persistence.MemberRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class MemberTest {
    @Autowired
    MemberRepository repository;

    @Test
    public void insertTest() {
        for(int i=0;i<=10;i++) {
            Member member = new Member();
            member.setUid("user"+i);
            member.setUpw("pw"+i);
            member.setUname("강태공"+i);
            member.setUemail("user"+i+"@email.com");
            member.setUphone("010123123"+i);

            MemberRole role = new MemberRole();
            if(i<9){
                role.setRoleName("USER");
            }else{
                role.setRoleName("ADMIN");
            }
            member.setRoles(Arrays.asList(role));

            repository.save(member);
        }
    }
}
