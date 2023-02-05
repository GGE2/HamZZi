package com.ssafy.db.entity.Quest;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

// 우리가 랜덤으로 뿌려주기
@Entity
@Getter @Setter
public class Quest {
    @Id
    @GeneratedValue
    private Long quest_id;

    @Column(nullable = false)
    private String content;

    @ColumnDefault("0")
    private int point;
}
