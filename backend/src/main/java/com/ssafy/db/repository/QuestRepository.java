package com.ssafy.db.repository;

import com.ssafy.db.entity.Quest.Quest;
//import com.ssafy.db.entity.Quest.QuestRequirement;
import com.ssafy.db.entity.Quest.QuestDaily;
import com.ssafy.db.entity.Quest.QuestWeekly;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestRepository {
    // create, update
    void saveQuest(Quest quest);
    void saveQuestDaily(QuestDaily questDaily);
    void saveQuestWeekly(QuestWeekly questWeekly);

    // Read
    Quest findById(Long quest_id);

    QuestDaily findQuestDailyById(Long questDaily_id);
    QuestWeekly findQuestWeeklyById(Long questWeekly_id);

//    List<QuestUser> questUserList(String nickname);
    List<QuestDaily> dailyQuestUserList(String nickname);
    List<QuestWeekly> weeklyQuestUserList(String nickname);

    List<Quest> questList();

    List<Long> getQuestId();

    /**
     * 매일 0시에 QuestDaily Table 초기화
     */
    void dailyTableClear();

    /**
     * 매주 월요일 0시에 QuestWeekly Table 초기화
     */
    void weeklyTableClear();

    /**
     * 초기화 후에 자동으로 생성해주기 위해 유저 닉네임 전체 가져오기 / 초기화 후에 퀘스트 자동생성을 위해
     * @return 전체 유저 닉네임 리스트
     */
    List<String> getUserNickname();
}
