package com.ssafy.api.service;

import com.ssafy.api.request.QuestRequest;
import com.ssafy.api.request.QuestUserRequest;
import com.ssafy.db.entity.Quest.Quest;
import com.ssafy.db.entity.Quest.QuestUser;
import com.ssafy.db.entity.User.UserProfile;
import com.ssafy.db.repository.QuestRepository;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public Quest createQuest(QuestRequest questInfo, int key) {
        Quest quest = new Quest();

        quest.setContent(questInfo.getContent());
        quest.setPoint(questInfo.getPoint());
        if (key == 1) {
            quest.setType("daily");
        } else {
            quest.setType("weekly");
        }
        questRepo.saveQuest(quest);

        return quest;
    }
    
    // questUser 생성
    @Override
    public void createQuestUser(QuestUserRequest questUserReq, String nickname) {
        List<Long> quest_ids = getQuestId();
        for (Long quest_id:quest_ids) {
            QuestUser questUser = new QuestUser();
            UserProfile userProfile = userRepo.findByNickname(nickname);

            questUser.setNickname(userProfile.getNickname());
            questUser.setQuest(questRepo.findById(quest_id));
            questUser.setIscheck(false);
            questRepo.saveQuestUser(questUser);
        }
    }

    // 위치 등록(수정)
    @Override
    public UserProfile registerLocation(String nickname, double latitude, double longitude) {
        
        UserProfile userProfile = userRepo.findByNickname(nickname);

        userProfile.setLatitude(latitude);
        userProfile.setLongitude(longitude);

        userRepo.saveUserProfile(userProfile);

        return userProfile;
    }

    // 시간 등록(수정)
    @Override
    public UserProfile registerFinalDatetime(String nickname, int finish_datetime) {
        UserProfile userProfile = userRepo.findByNickname(nickname);

        userProfile.setFinish_datetime(finish_datetime);
        userRepo.saveUserProfile(userProfile);

        return userProfile;
    }

    // 유저의 QuestList 보여주기
    @Override
    public List<QuestUser> getQuests(String nickname) {
        List<QuestUser> quests = questRepo.questUserList(nickname);
        List<QuestUser> questList = new ArrayList<>();
        questList.addAll(quests);

        return questList;
    }
    
    // quest list 보여주기
    @Override
    public List<Quest> getQuest() {
        return questRepo.questList();
    }
    
    // quesr id 다 따오기
    @Override
    public List<Long> getQuestId() {
        List<Long> quest = questRepo.getQuestId();
        List<Long> questList = new ArrayList<>();
        questList.addAll(quest);
        return questList;
    }

    // quest 완료 및 UserProfile point 업데이트
    @Override
    public QuestUser checkUpdateQuest(Long questUser_id) {
        QuestUser questUser = questRepo.findQuestUserById(questUser_id);
        Boolean isCheck = questUser.getIscheck();
        questUser.setIscheck(!isCheck);
        questRepo.saveQuestUser(questUser);
        return questUser;
    }

    // UserProfile point 업데이트
    @Override
    public void questPointAssignment(String nickname, Long quest_id) {
        UserProfile userProfile = userRepo.findByNickname(nickname);
        int nowPoint = userProfile.getPoint();
        int questPoint = questRepo.findById(quest_id).getPoint();

        userProfile.setPoint(nowPoint + questPoint);
    }

}
