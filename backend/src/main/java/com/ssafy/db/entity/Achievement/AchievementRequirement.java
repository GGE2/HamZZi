package com.ssafy.db.entity.Achievement;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter @Setter
public class AchievementRequirement {

    @Column(name = "ACHIEVEMENT_ID")
    private Long ach_id;               // Achievement.java의 Achievement_ID와 FK관계

    private String requirement;

    //2^(n-1)
    private int level;
}
