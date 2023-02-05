package com.ssafy.api.service;

import com.ssafy.api.request.QuestFinishDatetimeRequest;
import com.ssafy.api.request.QuestRequest;
import com.ssafy.api.request.QuestUserLocationRequest;
import com.ssafy.api.request.QuestUserRequest;
import com.ssafy.db.entity.Quest.Quest;
import com.ssafy.db.entity.Quest.QuestUser;
import com.ssafy.db.entity.User.UserProfile;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface QuestService {

    // Quest 생성
    Quest createQuest(QuestRequest questInfo);

    // questUser 생성
    QuestUser createQuestUser(QuestUserRequest questUserReq, Long quest_id);

    // 위치 등록
    UserProfile registerLocation(String nickname, int latitude, int longitude);

    // 시간 등록
    QuestUser registerFinalDatetime(Long questUser_id, String Finish_datetime);

    // Quest 보여주기
    List<QuestUser> getQuests(String nickname);

    // Quest 완료 확인하기
    QuestUser checkUpdateQuest(String nickname, Long questUser_id, Long quest_id);

    // point 계산
    UserProfile questPointAssignment(String nickname, Long quest_id);
}
