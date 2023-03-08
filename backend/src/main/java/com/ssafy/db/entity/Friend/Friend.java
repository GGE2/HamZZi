package com.ssafy.db.entity.Friend;

import com.ssafy.db.entity.User.UserProfile;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long friend_id;

    @Column(name = "user_nickname", nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String friend_nickname;

    @ColumnDefault(value = "1")
    private int relation;      // 1 : 친구요청, 2 : 친구 요청 받음, 3 : 친구
}
