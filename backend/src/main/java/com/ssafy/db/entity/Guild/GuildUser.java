package com.ssafy.db.entity.Guild;

import com.ssafy.db.entity.User.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter @Setter
public class GuildUser {

    @OneToOne
    @JoinColumn(name="user_nickname")
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "guild_id")
    private Guild guild;

    // char로 바꿔도 됨
    private boolean isadmin;
}
