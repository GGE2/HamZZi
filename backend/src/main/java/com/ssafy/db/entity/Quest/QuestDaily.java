package com.ssafy.db.entity.Quest;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter @Setter
@Entity
public class QuestDaily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questDaily_id;

//    @Column(name="quest_id")
//    private Long quest_id;      // 이걸로 quest id 뽑아오기
    @OneToOne
    @JoinColumn(name="quest_id")
    private Quest quest;

    @Column(name="user_nickname", nullable = false)
    private String nickname;

    // 완료 여부
    @ColumnDefault("false")
    @Column(nullable = false)
    private Boolean ischeck;

}
