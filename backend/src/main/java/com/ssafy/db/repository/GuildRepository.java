package com.ssafy.db.repository;

import com.ssafy.db.entity.Guild.Guild;
import com.ssafy.db.entity.User.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuildRepository {

    // Create, Update ----------------------
    void saveGuild(Guild guild);

    // Delete ------------------------------
    void removeGuild(Long guild_id);

    // Read --------------------------------
    Guild findById(Long guild_id);
    Guild findByName(String guild_name);
    List<Guild> findListByName(String guild_name);

    List<UserProfile> findGuildAdmin(Long guild_id);
    List<UserProfile> findGuildUser(Long guild_id);
}
