package com.ssafy.api.service;

import com.ssafy.db.entity.Guild.Guild;
import com.ssafy.db.entity.User.UserProfile;

public interface GuildService {

    /* 길드 관련 U/D 권한 체크 */
    boolean checkAdmin(UserProfile userProfile);
    /* 길드가 없으면 join 가능 */
    boolean canJoinGuild(UserProfile userProfile);


    Guild foundGuild(String guildName);
    void grantAdmin(UserProfile userProfile);

    Guild joinGuild(Long guild_id, UserProfile userProfile);
    UserProfile leaveGuild(Long guild_id, UserProfile userProfile);

}
