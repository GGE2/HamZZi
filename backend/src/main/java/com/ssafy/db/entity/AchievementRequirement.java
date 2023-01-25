package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter @Setter
public class AchievementRequirement {

    @Column(name = "Achievement_ID")
    private Long achievement;               // Achievement.java의 Achievement_ID와 FK관계

    private String requirement;
    private int amount;
}
