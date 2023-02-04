package com.ssafy.db.entity.Guild;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class GuildUser {

    /*fk 없이 처리함*/
    @Id
    @Column(name = "user_nickname")
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "guild_id")
    private Guild guild;

    // char로 바꿔도 됨
    @ColumnDefault("false")
    private boolean admin;
}
