package com.ssafy.db.repository;

import com.ssafy.db.entity.Count.Count;
import com.ssafy.db.entity.Count.CountDaily;
import com.ssafy.db.entity.Count.CountWeekly;
import com.ssafy.db.entity.Quest.QuestDaily;
import com.ssafy.db.entity.Quest.QuestWeekly;
import com.ssafy.db.entity.Todo.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class CountRepositorySupport implements CountRepository {

    private final EntityManager em;

    @Override
    public void saveCount(Count count) {
        em.persist(count);
    }

    @Override
    public void saveDaily(CountDaily daily) {
        em.persist(daily);
    }

    @Override
    public void saveWeekly(CountWeekly weekly) {
        em.persist(weekly);
    }

    @Override
    public Count findByNickname(String nickname) {      // 닉네임 검색은 find로 못찾는다! .find는 ID만 찾을 수 있음!!
        return em.createQuery("select c from Count c where c.nickname=:nickname", Count.class)
                .setParameter("nickname", nickname)
                .getSingleResult();
    }

    @Override
    public CountDaily findByDailyNickname(String nickname) {
        return em.createQuery("select c from CountDaily c where c.nickname=:nickname", CountDaily.class)
                .setParameter("nickname", nickname)
                .getSingleResult();
    }

    @Override
    public CountWeekly findByWeeklyNickname(String nickname) {
        return em.createQuery("select c from CountWeekly c where c.nickname=:nickname", CountWeekly.class)
                .setParameter("nickname", nickname)
                .getSingleResult();
    }

    @Override
    public List<Count> getCount(String nickname) {
        return em.createQuery("select c from Count c where c.nickname=:nickname", Count.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }

    @Override
    public List<CountDaily> getCountDaily(String nickname) {
        return em.createQuery("select c from CountDaily c where c.nickname=:nickname", CountDaily.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }

    @Override
    public List<CountWeekly> getCountWeekly(String nickname) {
        return em.createQuery("select c from CountWeekly c where c.nickname=:nickname", CountWeekly.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }


    @Override
    public List<Todo> todoChecked(String nickname) {
        return em.createQuery("select t from Todo t where t.nickname=:nickname and t.ischeck=:ischeck", Todo.class)
                .setParameter("nickname", nickname)
                .setParameter("ischeck", true)
                .getResultList();
    }

    public List<Todo> todoCheckedDate(String nickname, String datetime) {
        return em.createQuery("select t from Todo t Where t.nickname=:nickname and t.datetime=:datetime and t.ischeck=:ischeck", Todo.class)
                .setParameter("nickname", nickname)
                .setParameter("ischeck", true)
                .setParameter("datetime", datetime)
                .getResultList();
    }

    @Override
    public List<QuestDaily> questDailyChecked(String nickname) {
        return em.createQuery("select q from QuestDaily q where q.nickname=:nickname and q.ischeck=:ischeck", QuestDaily.class)
                .setParameter("nickname", nickname)
                .setParameter("ischeck", true)
                .getResultList();
    }

    @Override
    public List<QuestWeekly> questWeeklyChecked(String nickname) {
        return em.createQuery("select q from QuestWeekly q where q.nickname=:nickname and q.ischeck=:ischeck", QuestWeekly.class)
                .setParameter("nickname", nickname)
                .setParameter("ischeck", true)
                .getResultList();
    }

    @Override
    public void dailyTableClear() {
        em.createNativeQuery("TRUNCATE TABLE count_daily");
    }

    @Override
    public void weeklyTableClear() {
        em.createNativeQuery("TRUNCATE TABLE count_weekly");
    }
}
