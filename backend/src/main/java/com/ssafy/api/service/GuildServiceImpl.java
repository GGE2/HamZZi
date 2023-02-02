package com.ssafy.api.service;

import com.ssafy.db.entity.Guild.Guild;
import com.ssafy.db.entity.User.UserProfile;
import com.ssafy.db.repository.GuildRepository;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service @Transactional
@RequiredArgsConstructor
public class GuildServiceImpl implements GuildService {

    private final GuildRepository guildRepo;
    private final UserRepository userRepo;

    @Override
    public boolean checkAdmin(UserProfile userProfile) {
        boolean admin = userProfile.is_admin();
        if(admin) { return true; }
        else { return false; }
    }

    @Override
    public boolean canJoinGuild(UserProfile userProfile) {
        Guild guild = userProfile.getGuild();
        if(guild == null) { return true; }
        else { return false; }
    }

    @Override
    public Guild foundGuild(String guildName) {
        Guild guild = new Guild();

        guild.setGuild_name(guildName);
        guildRepo.saveGuild(guild);

        return guild;
    }

    @Override
    public void grantAdmin(UserProfile userProfile) {
        if(!(checkAdmin(userProfile)) && !(canJoinGuild(userProfile))) {
            userProfile.set_admin(true);
            userRepo.saveUserProfile(userProfile);
        }
    }

    @Override
    public Guild joinGuild(Long guild_id, UserProfile userProfile) {

        Guild guild = guildRepo.findById(guild_id);
        userProfile.setGuild(guild);
        userRepo.saveUserProfile(userProfile);

        return guild;
    }

    @Override
    public UserProfile leaveGuild(Long guild_id, UserProfile userProfile) {
        if(userProfile.getGuild().getGuild_id() == guild_id) {
            userProfile.setGuild(null);
            userRepo.saveUserProfile(userProfile);
        }
        return userProfile;
    }


}
