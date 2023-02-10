package com.ssafy.db.repository;

import com.ssafy.db.entity.Quest.Quest;
import com.ssafy.db.entity.Quest.QuestDaily;
import com.ssafy.db.entity.Quest.QuestWeekly;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class QuestRepositorySupport implements QuestRepository {

    private final EntityManager em;

    // create, update
    @Override
    public void saveQuest(Quest quest) { em.persist(quest); }
    @Override
    public void saveQuestDaily(QuestDaily questDaily) { em.persist(questDaily); }
    @Override
    public void saveQuestWeekly(QuestWeekly questWeekly) {
        em.persist(questWeekly);
    }

    // read
    @Override
    public Quest findById(Long quest_id) {
        return em.find(Quest.class, quest_id);
    }

    @Override
    public QuestDaily findQuestDailyById(Long questDaily_id) {
        return em.find(QuestDaily.class, questDaily_id);
    }

    @Override
    public QuestWeekly findQuestWeeklyById(Long questWeekly_id) {
        return em.find(QuestWeekly.class, questWeekly_id);
    }

    @Override
    public List<QuestDaily> dailyQuestUserList(String nickname) {
        return em.createQuery("select qu from QuestDaily qu where qu.nickname=:nickname and qu.quest.type=:type", QuestDaily.class)
                .setParameter("nickname", nickname)
                .setParameter("type", "daily")
                .getResultList();
    }@Override
    public List<QuestWeekly> weeklyQuestUserList(String nickname) {
        return em.createQuery("select qu from QuestWeekly qu where qu.nickname=:nickname and qu.quest.type=:type", QuestWeekly.class)
                .setParameter("nickname", nickname)
                .setParameter("type", "weekly")
                .getResultList();
    }

    @Override
    public List<Quest> questList() {
        return em.createQuery("select q from Quest q", Quest.class)
                .getResultList();
    }

    // QuestUser에 닉네임 별로 Quest를 자동으로 부여해주기 위해
    @Override
    public List<Long> getQuestId() {
        return em.createQuery("select q.quest_id from Quest q", Long.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void dailyTableClear() {
        em.createNativeQuery("TRUNCATE TABLE quest_daily")
                .executeUpdate();
    }

    @Override
    @Transactional
    public void weeklyTableClear() {
        em.createNativeQuery("TRUNCATE TABLE quest_weekly")
                .executeUpdate();
    }

    @Override
    public List<String> getUserNickname() {
        return em.createQuery("select up.nickname from UserProfile up", String.class)
                .getResultList();
    }

}
