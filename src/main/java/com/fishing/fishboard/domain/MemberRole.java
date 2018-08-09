package com.fishing.fishboard.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="tlb_members")
@EqualsAndHashCode(of = "uid")
@ToString
public class MemberRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;
    private String roleName;
}
