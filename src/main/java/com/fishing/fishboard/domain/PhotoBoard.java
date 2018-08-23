package com.fishing.fishboard.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/*
id, title, content, files, regdate, editdate, user, location 칼람을 주기로하고..
  - Member와는 1:n 관계로 주고 단방향을 주기로하자.(게시물에서 회원을 가지게)
 */
@Entity
@Getter @Setter
@Table(name = "photobards")
public class PhotoBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    private String filename;

    @CreationTimestamp
    private Timestamp regdate;
    @UpdateTimestamp
    private Timestamp editdate;

    private String location;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private User user;
}
