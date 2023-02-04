package com.ssafy.api.service;

import com.ssafy.db.entity.Guild.Guild;
import com.ssafy.db.entity.Guild.GuildUser;
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
        GuildUser guildUser = guildRepo.findByNickname(nickname);
        if(guildUser.isAdmin()) { return true; }
        return false;
    }
    @Override
    public boolean canJoinGuild(String nickname) {
        UserProfile userProfile = userRepo.findByNickname(nickname);
        GuildUser guildUser = guildRepo.findByNickname(nickname);
        if( guildUser == null ) { return true; }
        else { return false; }
    }
    @Override
    public boolean grantAdmin(String nickname) {
        if(checkAdmin(nickname) || canJoinGuild(nickname)) {return false;}

        GuildUser guildUser = guildRepo.findByNickname(nickname);
        guildUser.setAdmin(true);
        guildRepo.saveGuildUser(guildUser);

        return true;
    }

    @Override
    public List<Guild> findGuildList() { return guildRepo.findGuildList(); }
    @Override
    public List<Guild> findGuildListByName(String guild_name) { return guildRepo.findListByName(guild_name); }
    public Guild findGuild(Long guild_id) { return guildRepo.findById(guild_id); }

    @Override
    public List<GuildUser> findGuildUser(Long guild_id) { return guildRepo.findGuildUser(guild_id); }
    @Override
    public List<GuildUser> findGuildAdmin(Long guild_id) { return guildRepo.findGuildAdmin(guild_id); }

    @Override
    public Guild foundGuild(String guildName, String nickname) {
        Guild guild = new Guild();
        GuildUser guildUser = new GuildUser();

        guild.setGuild_name(guildName);
        guildRepo.saveGuild(guild);

        guildUser.setNickname(nickname);
        guildUser.setGuild(guild);
        guildRepo.saveGuildUser(guildUser);

        return guild;
    }

    @Override
    public boolean joinGuild(Long guild_id, String nickname) {
        Guild guild = guildRepo.findById(guild_id);
        GuildUser guildUser = new GuildUser();

        String user_nickname = userRepo.findByNickname(nickname).getNickname();

        if(!canJoinGuild(nickname)) {return false;}

        guildUser.setNickname(user_nickname);
        guildUser.setGuild(guild);
        guildRepo.saveGuildUser(guildUser);

        return true;
    }

    @Override
    public boolean leaveGuild(Long guild_id, String nickname) {
        GuildUser guildUser = guildRepo.findByNickname(nickname);

        if(checkAdmin(nickname) || guildUser.getGuild().getGuild_id() != guild_id) {return false;}
        guildRepo.removeGuildUser(nickname);

        return true;
    }

    @Override
    public Long getUserGuild(String nickname) {
        GuildUser guildUser = guildRepo.findByNickname(nickname);
        return guildUser.getGuild().getGuild_id();
    }

    @Override
    public boolean quitAdmin(String nickname) {
        if(canJoinGuild(nickname) || !checkAdmin(nickname)) { return false; }

        GuildUser guildUser = guildRepo.findByNickname(nickname);
        guildUser.setAdmin(false);
        guildRepo.saveGuildUser(guildUser);

        return true;
    }

    @Override
    public boolean kickUser(String nickname) {
        /* 어드민이나 길드 미소속 유저는 강퇴 불가능 */
        if(canJoinGuild(nickname) || checkAdmin(nickname)) { return false; }

        guildRepo.removeGuildUser(nickname);

        return true;
    }

    @Override
    public void deleteGuild(Long guildId) {
        List<GuildUser> userList = guildRepo.findGuildUser(guildId);
        for(int i = 0; i < userList.size(); i++) {
            GuildUser guildUser = userList.get(i);
            guildRepo.removeGuildUser(guildUser.getNickname());
        }

        List<GuildUser> adminList = guildRepo.findGuildAdmin(guildId);
        for (int i = 0; i < adminList.size(); i++) {
            GuildUser guildUser = adminList.get(i);
            guildRepo.removeGuildUser(guildUser.getNickname());
        }
        guildRepo.removeGuild(guildId);
    }
}
