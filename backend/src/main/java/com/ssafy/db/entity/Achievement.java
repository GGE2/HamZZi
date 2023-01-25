package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ACHIEVEMENT")
@Getter @Setter
public class Achievement {

    @Id
    @GeneratedValue
    @Column(name = "Achievement_ID")
    private Long achievement;

    private String title;
    private String content;
}
