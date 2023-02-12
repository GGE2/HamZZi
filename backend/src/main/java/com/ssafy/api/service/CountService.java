package com.ssafy.api.service;

import com.ssafy.db.entity.Count.Count;
import com.ssafy.db.entity.Count.CountDaily;
import com.ssafy.db.entity.Count.CountWeekly;

import java.util.List;

public interface CountService {

    List<Count> getCountList(String nickname);
    List<CountDaily> getDailyList(String nickname);
    List<CountWeekly> getWeeklyList(String nickname);

    /**
     * Count는 유저 전체 데이터를 가지고 있는데 사용 안해도 됨
     * @param nickname
     */
    void createCount(String nickname);
    void createCountDaily(String nickname);
    void createCountWeekly(String nickname);

    void updateCount(String nickname);
    void updateCountDaily(String nickname, String datetime);
    void updateCountWeekly(String nickname);

    /**
     * 매일 초기화
     */
    void resetBeforeCreateCountDaily();

    /**
     * 매주 초기화
     */
    void resetBeforeCreateCountWeekly();
}
