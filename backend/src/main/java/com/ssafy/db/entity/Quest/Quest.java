package com.ssafy.db.entity.Quest;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// 우리가 랜덤으로 뿌려주기
@Entity
@Getter @Setter
public class Quest {
    @Id
    @GeneratedValue
    @Column(name = "QUEST_ID")
    private Long quest_id;

    private String content;
}
