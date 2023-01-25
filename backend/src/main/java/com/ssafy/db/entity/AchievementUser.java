package com.ssafy.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

//@Table
@Getter @Setter
public class AchievementUser {

    @Column(name = "Achievement_ID")
    private Long achievement;               // Achievement.java의 Achievement_ID와 FK관계

    @Column(name = "User_Nickname")
    private String user_nickname;           // UserProfile.java의 User_Nickname와 FK관계

    private boolean ischeck;
    private int num;
}
