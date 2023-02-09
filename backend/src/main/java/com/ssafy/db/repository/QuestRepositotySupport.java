package com.ssafy.db.repository;

import com.ssafy.db.entity.Quest.Quest;
import com.ssafy.db.entity.Quest.QuestUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
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

    // QuestUser에 닉네임 별로 Quest를 자동으로 부여해주기 위해
    @Override
    public List<Long> getQuestId() {
        return em.createQuery("select q.quest_id from Quest q", Long.class)
                .getResultList();
    }

    // 00시에 QuestUser Table 초기화
    @Override
    @Transactional
    public void tableClear() {
        em.createNativeQuery("TRUNCATE TABLE quest_user")
                .executeUpdate();
    }

    // 초기화 후에 자동으로 생성해주기 위해 유저 닉네임 전체 가져오기
    @Override
    public List<String> getUserNickname() {
        return em.createQuery("select up.nickname from UserProfile up", String.class)
                .getResultList();
    }

}
