package com.ssafy.api.service;

import com.ssafy.db.entity.Guild.Guild;

public interface GuildService {
    Guild foundGuild(String guildName);

    void grantAdmin(String founderName);
}
