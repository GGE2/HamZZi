package com.ssafy.api.service;

import com.ssafy.db.entity.Guild.Guild;
import com.ssafy.db.entity.Guild.GuildUser;

import java.util.List;

public interface GuildService {

    /* 길드 관련 U/D 권한 체크 */
    boolean checkAdmin(String nickname);
    /* 길드가 없으면 join 가능 */
    boolean canJoinGuild(String nickname);
    boolean grantAdmin(String nickname);

    List<Guild> findGuildList();
    List<Guild> findGuildListByName(String guild_name);
    Guild findGuild(Long guild_id);

    List<GuildUser> findGuildUser(Long guild_id);
    List<GuildUser> findGuildAdmin(Long guild_id);

    Guild foundGuild(String guildName, String nickname);

    boolean joinGuild(Long guild_id, String nickname);
    boolean leaveGuild(Long guild_id, String nickname);

    Long getUserGuild(String nickname);

    boolean quitAdmin(String nickname);

    boolean kickUser(String nickname);

    void deleteGuild(Long guildId);
}
