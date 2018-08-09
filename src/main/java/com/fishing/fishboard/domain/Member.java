package com.fishing.fishboard.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@Entity
@Table(name="tbl_members")
@EqualsAndHashCode(of = "uid")
@ToString
public class Member {
    @Id
    private String uid;
    private String upw;
    private String uname;
    private String uphone;
    private String uemail;
    @CreationTimestamp private LocalDateTime regdate;
    @UpdateTimestamp private LocalDateTime updatedate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="member")
    private List<MemberRole> roles;
}
