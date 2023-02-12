package com.ssafy.api.service;

import com.ssafy.db.entity.Count.Count;
import com.ssafy.db.entity.Count.CountDaily;
import com.ssafy.db.entity.Count.CountWeekly;
import com.ssafy.db.entity.User.UserProfile;
import com.ssafy.db.repository.CountRepository;
import com.ssafy.db.repository.QuestRepository;
import com.ssafy.db.repository.TodoRepository;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CountServiceImpl implements CountService{
    // 퀘스트 완료 자동 체크 로직짜기
    @Autowired
    CountRepository countRepo;
    @Autowired
    TodoRepository todoRepo;
    @Autowired
    QuestRepository questRepo;

    @Autowired
    UserRepository userRepo;

    @Override
    public List<Count> getCountList(String nickname) {
        return countRepo.getCount(nickname);
    }

    @Override
    public List<CountDaily> getDailyList(String nickname) {
        return countRepo.getCountDaily(nickname);
    }

    @Override
    public List<CountWeekly> getWeeklyList(String nickname) {
        return countRepo.getCountWeekly(nickname);
    }

    @Override
    public void createCount(String nickname) {
        Count count = new Count();
        count.setNickname(nickname);
        count.setTodo(0);
        count.setQuest(0);
        countRepo.saveCount(count);
    }

    @Override
    public void createCountDaily(String nickname) {
        CountDaily count = new CountDaily();
        count.setNickname(nickname);
        count.setTodo(0);
        count.setQuest(0);
        countRepo.saveDaily(count);
    }

    @Override
    public void createCountWeekly(String nickname) {
        CountWeekly count = new CountWeekly();
        count.setNickname(nickname);
        count.setTodo(0);
        count.setQuest(0);
        countRepo.saveWeekly(count);
    }

    @Override
    public void updateCount(String nickname) {
        Count count = countRepo.findByNickname(nickname);
        int todo = countRepo.todoChecked(nickname).size();
        int daily = countRepo.questDailyChecked(nickname).size();
        int weekly = countRepo.questWeeklyChecked(nickname).size();

        count.setTodo(todo);
        count.setQuest(daily + weekly);
        countRepo.saveCount(count);
    }

    @Override
    public void updateCountDaily(String nickname, String datetime) {
        CountDaily count = countRepo.findByDailyNickname(nickname);
        int todo = countRepo.todoCheckedDate(nickname, datetime).size();
        int daily = countRepo.questDailyChecked(nickname).size();

        count.setTodo(todo);
        count.setQuest(daily);
        countRepo.saveDaily(count);
    }

    @Override
    public void updateCountWeekly(String nickname) {
        CountWeekly count = countRepo.findByWeeklyNickname(nickname);
        int weekly = countRepo.questWeeklyChecked(nickname).size();
        // Todo는 todo완료시 올라가도록 설정함
        count.setQuest(weekly);
        countRepo.saveWeekly(count);
    }

    @Override
    public void resetBeforeCreateCountDaily() {
        List<String> userProfiles = questRepo.getUserNickname();
        for (String userProfile:userProfiles) {
            createCountDaily(userProfile);
        }
    }

    @Override
    public void resetBeforeCreateCountWeekly() {
        List<String> userProfiles = questRepo.getUserNickname();
        for (String userProfile:userProfiles) {
            createCountWeekly(userProfile);
        }
    }
}
