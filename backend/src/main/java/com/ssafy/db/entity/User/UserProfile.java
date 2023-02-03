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

    /* About Guild */ //이제 Guild User 삭제가능
//    @ManyToOne
//    @JoinColumn(name = "guild_id")
//    private Guild guild;
//
//    private boolean is_admin;

}
