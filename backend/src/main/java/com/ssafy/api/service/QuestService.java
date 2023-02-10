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
    void createQuestUser(String nickname);

    // 위치 등록
    UserProfile registerLocation(String nickname, double latitude, double longitude, String location);

    // 시간 등록
    UserProfile registerFinalDatetime(String nickname, int finish_datetime);

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

    // QuestUser Table 초기화 후에 다시 모든 유저에게 퀘스트 만들어주기
    void resetBeforeCreateQuestUser();

    void resetRestPoint();
}
