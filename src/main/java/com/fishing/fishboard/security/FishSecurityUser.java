package com.fishing.fishboard.security;

import com.fishing.fishboard.domain.Member;
import com.fishing.fishboard.domain.MemberRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter @Setter
public class FishSecurityUser extends User {
    private static final String ROLE_PREFIX="ROLE_";
    private Member member;

    public FishSecurityUser(Member member) {
        super(member.getUid(),"{noop}"+member.getUpw(),makeGrantedAuthrity(member.getRoles()));
        this.member = member;
    }

    private static List<GrantedAuthority> makeGrantedAuthrity(List<MemberRole> roles) {
        List<GrantedAuthority> list = new ArrayList<>();
        roles.forEach(role->list.add(new SimpleGrantedAuthority((ROLE_PREFIX+role.getRoleName()))));
        return list;
    }
}
