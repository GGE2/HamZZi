package com.ssafy.db.entity.Quest;

import com.ssafy.db.entity.User.UserProfile;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter @Setter
@Entity
public class QuestUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questUser_id;

    @Column(name="quest_id")
    private Long quest_id;      // 이걸로 quest id 뽑아오기

    @Column(name="user_nickname")
    private String nickname;

    // 완료 여부
    @ColumnDefault("false")
    @Column(nullable = false)
    private Boolean ischeck;

    
    // 완료해야하는 시간
    private int finish_datetime;
}
