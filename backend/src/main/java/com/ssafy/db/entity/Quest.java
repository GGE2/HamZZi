package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// 우리가 랜덤으로 뿌려주기
@Entity
@Table(name = "QUEST")
@Getter @Setter
public class Quest {
    @Id
    @GeneratedValue
    @Column(name = "Quest_ID")
    private Long quest_id;

    private String content;
    
    private int level;      // point를 주기 위해 작성함
}
