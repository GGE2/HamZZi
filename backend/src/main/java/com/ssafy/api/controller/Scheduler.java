package com.ssafy.api.controller;

import com.ssafy.api.service.CountService;
import com.ssafy.api.service.CountServiceImpl;
import com.ssafy.api.service.QuestService;
import com.ssafy.db.repository.CountRepository;
import com.ssafy.db.repository.QuestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class Scheduler {

    private final QuestService questService;
    private final QuestRepository questRepo;
    private final CountService countService;
    private final CountRepository countRepo;

    // 스케줄러 사용하려면 BackendApplication 에 @EnableScheduling annotation 달아야 함
    // QuestUser Table 초기화
    @Scheduled(cron = "0 0 0 * * *")        // 초 분 시간 일 월 요일
    public void resetDailyTable() {
        log.info("매일 초기화");
        questRepo.dailyTableClear();
        countRepo.dailyTableClear();
        questService.resetRestPoint();
    }

    @Scheduled(cron = "0 0 0 * * 1")        // 초(0~59), 분(0~59), 시간(0~23), 일(1~31), 월(1, 12), 요일(0: 일요일,  1: 월요일 ...)
    public void resetWeeklyTable() {
        log.info("매주 초기화");
        questRepo.weeklyTableClear();
        countRepo.weeklyTableClear();
    }

    @Scheduled(cron = "10 0 0 * * *")        // 테이블 리셋 후에 생성하기 위해 좀 더 시간을 줬음(더미 데이터 넣어보고 얼마나 걸리는지 체크하면 좋을듯)
    public void CreateTable() {
        log.info("매일 추가");
        questService.resetBeforeCreateQuestUser();
        countService.resetBeforeCreateCountDaily();
    }

    @Scheduled(cron = "10 0 0 * * 1")
    public void createWeekly() {
        log.info("매주 추가");
        countService.resetBeforeCreateCountWeekly();
    }
}
