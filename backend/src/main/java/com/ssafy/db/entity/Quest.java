package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "QUEST")
@Getter @Setter
public class Quest {
    @Id
    @GeneratedValue
    @Column(name = "Quest_ID")
    private Long quest_id;

    private String content;
}
