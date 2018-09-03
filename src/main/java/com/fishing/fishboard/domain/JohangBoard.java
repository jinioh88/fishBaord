package com.fishing.fishboard.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tbl_johang")
@Getter @Setter
@ToString
@EqualsAndHashCode(of="jno")
public class JohangBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jno;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String writer;
    private String images;  // 나중에 파일로 수정..
    private int likes;  // 좋아요

    @CreationTimestamp
    private Timestamp regdate;
    @UpdateTimestamp
    private Timestamp updatedate;
}
