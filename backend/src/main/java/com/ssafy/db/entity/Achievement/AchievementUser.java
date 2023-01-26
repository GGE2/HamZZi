package com.ssafy.db.entity.Achievement;


import com.ssafy.db.entity.User.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//@Table
@Getter @Setter
public class AchievementUser {

    @Column(name = "ACHIEVEMENT_ID")
    private Long ach_id;               // Achievement.java의 Achievement_ID와 FK관계

    @OneToOne
    @JoinColumn(name="USER_NICKNAME")
    private UserProfile userProfile;

    private boolean ischeck;
}
