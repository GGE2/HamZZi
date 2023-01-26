package com.ssafy.db.entity.Quest;

import com.ssafy.db.entity.User.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter @Setter
public class QuestUser {

    @Column(name = "QUEST_ID")
    private Long quest_id;                  // Quest.java의 Quest_ID와 FK관계

    @OneToOne
    @JoinColumn(name="USER_NICKNAME")
    private UserProfile userProfile;

    private boolean ischeck;
}
