package com.ssafy.api.service;

import com.ssafy.api.request.QuestRequest;
import com.ssafy.db.entity.Quest.Quest;
import com.ssafy.db.entity.Quest.QuestDaily;
import com.ssafy.db.entity.Quest.QuestWeekly;
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
    public void createQuestUser(String nickname) {
        List<Long> quest_ids = getQuestId();
        for (Long quest_id:quest_ids) {
            QuestDaily questDaily = new QuestDaily();
            QuestWeekly questWeekly = new QuestWeekly();

            UserProfile userProfile = userRepo.findByNickname(nickname);

            questDaily.setNickname(userProfile.getNickname());
            questWeekly.setNickname(userProfile.getNickname());

            String type = questRepo.findById(quest_id).getType();
            if (type.equals("daily")) {
                questDaily.setQuest(questRepo.findById(quest_id));
                questDaily.setIscheck(false);
                questRepo.saveQuestDaily(questDaily);
            } else {
                questWeekly.setQuest(questRepo.findById(quest_id));
                questWeekly.setIscheck(false);
                questRepo.saveQuestWeekly(questWeekly);
            }
        }
    }

    @Override
    public UserProfile registerLocation(String nickname, double latitude, double longitude, String location) {
        
        UserProfile userProfile = userRepo.findByNickname(nickname);

        userProfile.setLatitude(latitude);
        userProfile.setLongitude(longitude);
        userProfile.setLocation(location);

        userRepo.saveUserProfile(userProfile);

        return userProfile;
    }

    @Override
    public UserProfile registerFinalDatetime(String nickname, int finish_datetime) {
        UserProfile userProfile = userRepo.findByNickname(nickname);

        userProfile.setFinish_datetime(finish_datetime);
        userRepo.saveUserProfile(userProfile);

        return userProfile;
    }

    @Override
    public UserProfile registerPedometer(String nickname, int pedometer) {
        UserProfile userProfile = userRepo.findByNickname(nickname);

        userProfile.setPedometer(pedometer);
        userRepo.saveUserProfile(userProfile);

        return userProfile;
    }

    @Override
    public List<QuestDaily> getDailyQuests(String nickname) {
        List<QuestDaily> quests = questRepo.dailyQuestUserList(nickname);
        List<QuestDaily> questList = new ArrayList<>();
        questList.addAll(quests);

        return questList;
    }
    @Override
    public List<QuestWeekly> getWeeklyQuests(String nickname) {
        List<QuestWeekly> quests = questRepo.weeklyQuestUserList(nickname);
        List<QuestWeekly> questList = new ArrayList<>();
        questList.addAll(quests);

        return questList;
    }

    // quest list 보여주기
    @Override
    public List<Quest> getQuest() {
        return questRepo.questList();
    }
    
    // quest id 다 따오기
    @Override
    public List<Long> getQuestId() {
        List<Long> quest = questRepo.getQuestId();
        List<Long> questList = new ArrayList<>();
        questList.addAll(quest);
        return questList;
    }

    // quest 완료 및 UserProfile point 업데이트
    @Override
    public QuestDaily checkDailyQuest(Long questDaily_id) {
        QuestDaily questDaily = questRepo.findQuestDailyById(questDaily_id);
        Boolean isCheck = questDaily.getIscheck();
        questDaily.setIscheck(!isCheck);
        questRepo.saveQuestDaily(questDaily);
        return questDaily;
    }

    @Override
    public QuestWeekly checkWeeklyQuest(Long questWeekly_id) {
        QuestWeekly questWeekly = questRepo.findQuestWeeklyById(questWeekly_id);
        Boolean isCheck = questWeekly.getIscheck();
        questWeekly.setIscheck(!isCheck);
        questRepo.saveQuestWeekly(questWeekly);
        return questWeekly;
    }

    // UserProfile point 업데이트
    @Override
    public void questPointAssignment(String nickname, Long quest_id) {
        UserProfile userProfile = userRepo.findByNickname(nickname);
        int nowPoint = userProfile.getPoint();
        int questPoint = questRepo.findById(quest_id).getPoint();

        userProfile.setPoint(nowPoint + questPoint);
    }

    // QuestUser Table 초기화 후에 다시 모든 유저에게 퀘스트 만들어주기
    @Override
    public void resetBeforeCreateQuestUser() {
        List<String> userProfiles = questRepo.getUserNickname();
        for (String userProfile:userProfiles) {
            createQuestUser(userProfile);
        }
    }

    // UserProfile RestPoint 3으로 매일 초기화 해주기
    @Override
    public void resetRestPoint() {
        List<String> userProfiles = questRepo.getUserNickname();
        for (String userProfile:userProfiles) {
            UserProfile user = userRepo.findByNickname(userProfile);
            user.setRest_point(3);
        }
    }
}
