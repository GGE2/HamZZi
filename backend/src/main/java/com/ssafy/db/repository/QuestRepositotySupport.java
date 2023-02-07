package com.ssafy.db.repository;

import com.ssafy.db.entity.Quest.Quest;
import com.ssafy.db.entity.Quest.QuestUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class QuestRepositotySupport implements QuestRepository {

    private final EntityManager em;

    // create, update
    @Override
    public void saveQuest(Quest quest) { em.persist(quest); }
    @Override
    public void saveQuestUser(QuestUser questUser) { em.persist(questUser); }

    // read
    @Override
    public Quest findById(Long quest_id) {
        return em.find(Quest.class, quest_id);
    }

    @Override
    public QuestUser findQuestUserById(Long questUser_id) {
        return em.find(QuestUser.class, questUser_id);
    }

    @Override
    public List<QuestUser> questUserList(String nickname) {
        return em.createQuery("select qu from QuestUser qu where qu.nickname=:nickname", QuestUser.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }

    @Override
    public List<Quest> questList() {
        return em.createQuery("select q from Quest q", Quest.class)
                .getResultList();
    }

    @Override
    public List<Quest> getQuestList(Long quest_id) {
        return em.createQuery("select q from Quest q", Quest.class)
                .getResultList();
    }

    @Override
    public List<Long> getQuestId() {
        return em.createQuery("select q.quest_id from Quest q", Long.class)
                .getResultList();
    }
}
