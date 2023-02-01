package com.ssafy.db.entity.Guild;

import com.ssafy.db.entity.User.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter @Setter
public class GuildUser {

    /* UserProfile로 이관예정 */
    @OneToOne
    @JoinColumn(name="USER_NICKNAME")
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "GUILD_ID")          // Guild.java에 Guild_ID와 연결된다.
    private Guild guild;

    // char로 바꿔도 됨
    private boolean isadmin;
}
