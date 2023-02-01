package com.ssafy.db.repository;

import com.ssafy.db.entity.Guild.Guild;
import com.ssafy.db.entity.User.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository @Primary
@RequiredArgsConstructor
public class GuildRepositorySupport implements GuildRepository {

    private final EntityManager em;

    // Create, Update ----------------------
    @Override
    public void saveGuild(Guild guild) { em.persist(guild); }
    // Delete ------------------------------
    @Override
    public void removeGuild(Long guild_id) { em.remove(findById(guild_id)); }
    // Read --------------------------------
    @Override
    public Guild findById(Long guild_id) { return em.find(Guild.class, guild_id); }
    @Override
    public Guild findByName(String guild_name) {
        return em.createQuery("select g from Guild g where g.guild_name=:guild_name", Guild.class)
                .setParameter("guild_name", guild_name).getSingleResult();
    }
    @Override
    public List<Guild> findListByName(String guild_name) {
        return em.createQuery("select g from Guild g where g.guild_name=:guild_name", Guild.class)
                .setParameter("guild_name", guild_name).getResultList();
    }

    @Override
    public List<UserProfile> findGuildAdmin(Long guild_id) {
        return em.createQuery("select g from Guild g left join UserProfile u");
                //isadmin UserProfile로 이관 후
    }
    @Override
    public List<UserProfile> findGuildUser(Long guild_id) {
        return null;
    }
}
