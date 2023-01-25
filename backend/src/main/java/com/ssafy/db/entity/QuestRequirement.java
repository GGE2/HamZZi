package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "QUESTREQUIRMENT")
@Getter @Setter
public class QuestRequirement {

    @Column(name = "Quest_ID")
    private Long quest_id;                  // Quest.java의 Quest_ID와 FK관계

    private String requirement;
    private int amount;
}
