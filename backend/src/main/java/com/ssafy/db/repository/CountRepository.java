package com.ssafy.db.repository;

import com.ssafy.db.entity.Count.Count;
import com.ssafy.db.entity.Count.CountDaily;
import com.ssafy.db.entity.Count.CountWeekly;
import com.ssafy.db.entity.Quest.QuestDaily;
import com.ssafy.db.entity.Quest.QuestWeekly;
import com.ssafy.db.entity.Todo.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountRepository {
    // create, update
    void saveCount(Count count);
    void saveDaily(CountDaily daily);
    void saveWeekly(CountWeekly weekly);

    // read
    Count findByNickname(String nickname);
    CountDaily findByDailyNickname(String nickname);
    CountWeekly findByWeeklyNickname(String nickname);

    List<Count> getCount(String nickname);
    List<CountDaily> getCountDaily(String nickname);
    List<CountWeekly> getCountWeekly(String nickname);

    List<Todo> todoChecked(String nickname);
    List<Todo> todoCheckedDate(String nickname, String datetime);
    List<QuestDaily> questDailyChecked(String nickname);
    List<QuestWeekly> questWeeklyChecked(String nickname);

    /**
     * 매일 0시에 CountDaily Table 초기화
     */
    void dailyTableClear();
    /**
     * 매일 0시에 CountDaily Table 초기화
     */
    void weeklyTableClear();
}
