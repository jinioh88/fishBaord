package com.fishing.fishboard.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "tbl_imagevo")
@EqualsAndHashCode(of = "ino")
public class ImageVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ino;
    String filename;

    @Lob
    @Column(length = 5120)
    private byte[] data;
}
