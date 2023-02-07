package com.ssafy.db.repository;

import com.ssafy.db.entity.Guild.Guild;

import com.ssafy.db.entity.Guild.GuildUser;
import com.ssafy.db.entity.User.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository @Primary
@RequiredArgsConstructor
public class GuildRepositorySupport implements GuildRepository {

    private final EntityManager em;

    // Create, Update ----------------------
    @Override
    public void saveGuild(Guild guild) { em.persist(guild); }
    @Override
    public void saveGuildUser(GuildUser guildUser) { em.persist(guildUser); }

    // Delete ------------------------------
    @Override
    public void removeGuild(Long guild_id) { em.remove(findById(guild_id)); }
    @Override
    public void removeGuildUser(String nickname) { em.remove(findByNickname(nickname)); }

    // Read --------------------------------
    @Override
    public Guild findById(Long guild_id) {
        try { return em.find(Guild.class, guild_id); }
        catch (NoResultException e) { return null; }
    }
    @Override
    public GuildUser findByNickname(String nickname) { return em.find(GuildUser.class, nickname); }

//    @Override
//    public UserProfile findUserProfile(String nickname) {
//        try {
//            return em.createQuery("select u from UserProfile u left join GuildUser g " +
//                        "on (g.nickname=u.nickname) where u.nickname=:nickname", UserProfile.class)
//                .setParameter("nickname", nickname).getSingleResult();
//        } catch (NoResultException e) {return null;}
//    }

    @Override
    public List<Guild> findGuildList() {
        return em.createQuery("select g from Guild g", Guild.class).getResultList();
    }
    @Override
    public List<Guild> findListByName(String guild_name) {
        return em.createQuery("select g from Guild g where g.guild_name like :guild_name", Guild.class)
                .setParameter("guild_name", "%"+guild_name+"%").getResultList();
    }

    @Override
    public GuildUser findGuildAdmin(Long guild_id) {
        return em.createQuery("select u from GuildUser u left join Guild g on (g.guild_id=u.guild.guild_id) " +
                        "where g.guild_id=:guild_id and u.admin=:admin", GuildUser.class)
                .setParameter("guild_id", guild_id)
                .setParameter("admin", true).getSingleResult();
    }
    @Override
    public List<GuildUser> findGuildUser(Long guild_id) {
        return em.createQuery("select u from GuildUser u left join Guild g on (g.guild_id=u.guild.guild_id) " +
                        "where g.guild_id=:guild_id", GuildUser.class)
                .setParameter("guild_id", guild_id).getResultList();
    }
}
