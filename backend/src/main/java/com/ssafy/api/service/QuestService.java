package com.ssafy.api.service;

import com.ssafy.api.request.QuestRequest;
import com.ssafy.api.request.QuestUserRequest;
import com.ssafy.db.entity.Quest.Quest;
import com.ssafy.db.entity.Quest.QuestUser;
import com.ssafy.db.entity.User.UserProfile;

import java.util.List;

public interface QuestService {

    // Quest 생성
    Quest createQuest(QuestRequest questInfo, int key);

    // questUser 생성
    void createQuestUser(QuestUserRequest questUserReq, String nickname);

    // 위치 등록
    UserProfile registerLocation(String nickname, double latitude, double longitude);

    // 시간 등록
    UserProfile registerFinalDatetime(String nickname, int finish_datetime);

    // User의 Quest 보여주기
    List<QuestUser> getQuests(String nickname);

    // Quest List
    List<Quest> getQuest();
    
    // Quest List의 id 값 뽑아오기
    List<Long> getQuestId();
    
    // Quest 완료 확인하기
    QuestUser checkUpdateQuest(Long questUser_id);

    // point 계산
    void questPointAssignment(String nickname, Long quest_id);

}
