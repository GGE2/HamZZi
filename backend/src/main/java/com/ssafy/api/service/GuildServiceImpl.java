package com.ssafy.api.service;

import com.ssafy.db.entity.Guild.Guild;
import com.ssafy.db.entity.User.UserProfile;
import com.ssafy.db.repository.GuildRepository;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
@RequiredArgsConstructor
public class GuildServiceImpl implements GuildService {

    private final GuildRepository guildRepo;
    private final UserRepository userRepo;

    @Override
    public boolean checkAdmin(String nickname) {
        boolean admin = userRepo.findByNickname(nickname).is_admin();
        if(admin) { return true; }
        else { return false; }
    }

    @Override
    public boolean canJoinGuild(String nickname) {
        Guild guild = userRepo.findByNickname(nickname).getGuild();
        if(guild == null) { return true; }
        else { return false; }
    }
    @Override
    public boolean grantAdmin(String nickname) {
        if(checkAdmin(nickname) || canJoinGuild(nickname)) {return false;}

        UserProfile userProfile = userRepo.findByNickname(nickname);
        userProfile.set_admin(true);
        userRepo.saveUserProfile(userProfile);

        return true;
    }

    @Override
    public List<Guild> findGuildList() { return guildRepo.findGuildList(); }
    @Override
    public List<Guild> findGuildListByName(String guild_name) { return guildRepo.findListByName(guild_name); }
    public Guild findGuild(Long guild_id) { return guildRepo.findById(guild_id); }

    @Override
    public List<UserProfile> findGuildUser(Long guild_id) { return guildRepo.findGuildUser(guild_id); }
    @Override
    public List<UserProfile> findGuildAdmin(Long guild_id) { return guildRepo.findGuildAdmin(guild_id); }

    @Override
    public Guild foundGuild(String guildName) {
        Guild guild = new Guild();

        guild.setGuild_name(guildName);
        guildRepo.saveGuild(guild);

        return guild;
    }

    @Override
    public boolean joinGuild(Long guild_id, String nickname) {
        Guild guild = guildRepo.findById(guild_id);
        UserProfile userProfile = userRepo.findByNickname(nickname);

        if(!canJoinGuild(nickname)) {return false;}

        userProfile.setGuild(guild);
        userRepo.saveUserProfile(userProfile);

        return true;
    }

    @Override
    public boolean leaveGuild(Long guild_id, String nickname) {
        UserProfile userProfile = userRepo.findByNickname(nickname);

        if(userProfile.is_admin() || userProfile.getGuild().getGuild_id() == guild_id) {return false;}

        userProfile.setGuild(null);
        userRepo.saveUserProfile(userProfile);

        return true;
    }

    @Override
    public Long belongingGuild(String nickname) {
        UserProfile userProfile = userRepo.findByNickname(nickname);
        return userProfile.getGuild().getGuild_id();
    }

    @Override
    public boolean quitAdmin(String nickname) {
        if(canJoinGuild(nickname) || !checkAdmin(nickname)) { return false; }

        UserProfile userProfile = userRepo.findByNickname(nickname);
        userProfile.set_admin(false);
        userRepo.saveUserProfile(userProfile);

        return true;
    }

    @Override
    public boolean kickUser(String nickname) {
        if(canJoinGuild(nickname) || checkAdmin(nickname)) { return false; }

        UserProfile userProfile = userRepo.findByNickname(nickname);
        userProfile.setGuild(null);
        userRepo.saveUserProfile(userProfile);

        return true;
    }

    @Override
    public boolean deleteGuild(Long guildId) {
        return false;
    }


}
