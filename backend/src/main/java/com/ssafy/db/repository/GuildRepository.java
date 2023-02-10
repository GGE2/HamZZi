package com.ssafy.db.repository;

import com.ssafy.db.entity.Guild.Guild;
import com.ssafy.db.entity.Guild.GuildUser;
import com.ssafy.db.entity.User.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuildRepository {

    // Create, Update ----------------------
    void saveGuild(Guild guild);
    void saveGuildUser(GuildUser guildUser);
    // Delete ------------------------------
    void removeGuild(Long guild_id);
    void removeGuildUser(String nickname);
    // Read --------------------------------
    Guild findById(Long guild_id);
    GuildUser findByNickname(String nickname);

    List<Guild> findGuildList();
    List<Guild> findListByName(String guild_name);


    List<GuildUser> findGuildAdmin(Long guild_id);
    List<GuildUser> findGuildUser(Long guild_id);

    /**
     * 길드의 모든 name 뽑아오기
     * @return Long name
     */
    List<Long> getGuildId();

    /**
     * 길드 유저들의 수를 셈
     * @param guild_id
     * @return
     */
    List<GuildUser> getGuildUserSize(Long guild_id);
}
