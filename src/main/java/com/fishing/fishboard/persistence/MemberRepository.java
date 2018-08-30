package com.fishing.fishboard.persistence;

import com.fishing.fishboard.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member,String> {
    public Member findMemberByUid(String id);
}
