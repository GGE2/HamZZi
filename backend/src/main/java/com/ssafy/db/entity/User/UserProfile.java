package com.ssafy.db.entity.User;


import com.ssafy.db.entity.Guild.Guild;
import com.ssafy.db.entity.Pet.Pet;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class UserProfile {

    @Id
    @Column(name = "user_nickname")
    private String nickname;

    /* About Todo/Quest Point */
    @ColumnDefault("0")
    private int point;
    @ColumnDefault("3")
    private int rest_point;

    // 위도
    private double latitude;
    // 경도
    private double longitude;

    // 완료해야하는 시간
    private int finish_datetime;
}
