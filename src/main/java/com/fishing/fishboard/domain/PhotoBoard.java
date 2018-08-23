package com.fishing.fishboard.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;

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
    private Member member;
}
