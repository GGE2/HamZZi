package com.ssafy.api.service;

import com.ssafy.api.request.QuestFinishDatetimeRequest;
import com.ssafy.api.request.QuestRequest;
import com.ssafy.api.request.QuestUserLocationRequest;
import com.ssafy.api.request.QuestUserRequest;
import com.ssafy.db.entity.Quest.Quest;
import com.ssafy.db.entity.Quest.QuestUser;
import com.ssafy.db.entity.User.User;
import com.ssafy.db.entity.User.UserProfile;
import com.ssafy.db.repository.QuestRepository;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestServiceImpl implements QuestService {

    @Autowired
    QuestRepository questRepo;

    @Autowired
    UserRepository userRepo;
    
    // quest 생성
    @Override
    public Quest createQuest(QuestRequest questInfo) {
        Quest quest = new Quest();

        quest.setContent(questInfo.getContent());
        quest.setPoint(questInfo.getPoint());

        questRepo.saveQuest(quest);

        return quest;
    }
    
    // questuser 생성
    @Override
    public QuestUser createQuestUser(QuestUserRequest questUserReq, Long quest_id) {
        QuestUser questUser = new QuestUser();
        UserProfile userProfile = userRepo.findByNickname(questUserReq.getUser_nickname());

        //fireBase에서 UserProfile 가져오기
        questUser.setNickname(userProfile.getNickname());
        questUser.setQuest_id(quest_id);;
        questUser.setIscheck(false);

        questRepo.saveQuestUser(questUser);

        return questUser;
    }

    // 위치 등록(수정)
    @Override
    public UserProfile registerLocation(String nickname, int latitude, int longitude) {
        
        UserProfile userProfile = userRepo.findByNickname(nickname);

        userProfile.setLatitude(latitude);
        userProfile.setLongitude(longitude);

        userRepo.saveUserProfile(userProfile);

        return userProfile;
    }

    // 시간 등록(수정)
    @Override
    public QuestUser registerFinalDatetime(Long questUser_id, String Finish_datetime) {
        QuestUser questUser = questRepo.findQuestUserById(questUser_id);
        questUser.setFinish_datetime(Finish_datetime);
        questRepo.saveQuestUser(questUser);

        return questUser;
    }


    // 유저의 QuestList 보여주기
    @Override
    public List<QuestUser> getQuests(String nickname) {
        List<QuestUser> quests = questRepo.questUserList(nickname);
        List<QuestUser> questList = new ArrayList<>();
        questList.addAll(quests);

        return questList;
    }

    // quest 완료 및 UserProfile point 업데이트
    @Override
    public QuestUser checkUpdateQuest(String nickname, Long questUser_id, Long quest_id) {
        QuestUser questUser = questRepo.findQuestUserById(questUser_id);
        Boolean isCheck = questUser.getIscheck();
        questUser.setIscheck(!isCheck);
        questPointAssignment(nickname, quest_id);
        questRepo.saveQuestUser(questUser);
        return questUser;
    }

    // UserProfile point 업데이트
    @Override
    public UserProfile questPointAssignment(String nickname, Long quest_id) {
        UserProfile userProfile = userRepo.findByNickname(nickname);
        int nowPoint = userProfile.getPoint();
        int questPoint = questRepo.findById(quest_id).getPoint();

        userProfile.setPoint(nowPoint + questPoint);
        return userProfile;
    }
}
