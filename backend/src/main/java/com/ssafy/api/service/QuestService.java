package com.ssafy.api.service;

import com.ssafy.api.request.QuestRequest;
import com.ssafy.db.entity.Quest.Quest;
import com.ssafy.db.entity.Quest.QuestDaily;
import com.ssafy.db.entity.Quest.QuestWeekly;
import com.ssafy.db.entity.User.UserProfile;

import java.util.List;

public interface QuestService {

    // Quest 생성
    Quest createQuest(QuestRequest questInfo, int key);

    // questUser 생성
    void createDailyUser(String nickname);
    void createWeeklyUser(String nickname);

    /**
     * 위치 등록
     * @param nickname
     * @param latitude
     * @param longitude
     * @param location
     * @return
     */
    UserProfile registerLocation(String nickname, double latitude, double longitude, String location);

    /**
     * 시간 등록
     * @param nickname
     * @param finish_datetime
     * @return
     */
    UserProfile registerFinalDatetime(String nickname, int finish_datetime);

    /**
     * 만보기 등록
     * @param nickname
     * @param pedometer
     * @return
     */
    UserProfile registerPedometer(String nickname, int pedometer);

    // User의 Quest 보여주기
//    List<QuestUser> getQuests(String nickname);
    List<QuestDaily> getDailyQuests(String nickname);
    List<QuestWeekly> getWeeklyQuests(String nickname);

    // Quest List
    List<Quest> getQuest();
    
    // Quest List의 id 값 뽑아오기
    List<Long> getQuestId();
    
    // Quest 완료 확인하기
    QuestDaily checkDailyQuest(Long questDaily_id);
    QuestWeekly checkWeeklyQuest(Long questWeekly_id);

    // point 계산
    void questPointAssignment(String nickname, Long quest_id);

    /**
     * QuestUser Table 초기화 후에 다시 모든 유저에게 Daily 퀘스트 만들어주기
     */
    void resetBeforeCreateDailyUser();
    void resetBeforeCreateWeeklyUser();

    void resetRestPoint();
}
