package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "QUESTUSER")
@Getter @Setter
public class QuestUser {

    @Column(name = "Quest_ID")
    private Long quest_id;                  // Quest.java의 Quest_ID와 FK관계

    @Column(name = "User_Nickname")
    private String user_nickname;           // UserProfile.java의 User_Nickname와 FK관계

    private boolean ischeck;
    private boolean isdaily;
    private boolean isweekly;
}
