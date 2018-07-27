package com.fishing.fishboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="tbl_webreplies")
@EqualsAndHashCode(of="rno")
@ToString
public class WebReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String replyText;
    private String replyer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private FishBoard board;

    @CreationTimestamp
    private Timestamp regdate;
    @UpdateTimestamp
    private Timestamp updatedate;
}
