package com.ssafy.db.repository;

import com.ssafy.db.entity.Quest.Quest;
//import com.ssafy.db.entity.Quest.QuestRequirement;
import com.ssafy.db.entity.Quest.QuestUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestRepository {
    // create, update
    void saveQuest(Quest quest);
    void saveQuestUser(QuestUser questUser);
//    void saveQuestReq(QuestRequirement questReq);

    // Read
    Quest findById(Long quest_id);

    QuestUser findQuestUserById(Long questUser_id);

    List<QuestUser> questUserList(String nickname);

    List<Quest> questList();

    List<Quest> getQuestList(Long quest_id);

    List<Long> getQuestId();
}
