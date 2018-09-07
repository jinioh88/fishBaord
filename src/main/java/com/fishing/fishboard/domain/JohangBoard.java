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

    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imagevo_id")
    private ImageVO imagevo;

    private int likes;  // 좋아요

    @CreationTimestamp
    private Timestamp regdate;
    @UpdateTimestamp
    private Timestamp updatedate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
