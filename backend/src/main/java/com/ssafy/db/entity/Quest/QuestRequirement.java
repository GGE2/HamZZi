package com.ssafy.db.entity.Quest;

import com.ssafy.db.entity.User.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Getter @Setter
public class QuestRequirement {

    @OneToOne
    @JoinColumn(name="QUEST_ID")
    private UserProfile quest_id;               // Quest.java의 Quest_ID와 FK관계

    private String requirement;
    private int point;
    private boolean isdaily;
    private boolean isweekly;
}
