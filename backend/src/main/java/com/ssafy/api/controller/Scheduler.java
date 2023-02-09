package com.ssafy.api.controller;

import com.ssafy.api.service.QuestService;
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

    // 스케줄러 사용하려면 BackendApplication 에 @EnableScheduling annotation 달아야 함
    // QuestUser Table 초기화
    @Scheduled(cron = "0 0 0 * * *")        // 초 분 시간 일 월 요일
    public void resetTable() {
        log.info("QuestUser Table 매일 초기화");
        questRepo.tableClear();
    }

    @Scheduled(cron = "5 0 0 * * *")        // 테이블 리셋 후에 생성하기 위해 좀 더 시간을 줬음(더미 데이터 넣어보고 얼마나 걸리는지 체크하면 좋을듯)
    public void CreateTable() {
        log.info("QuestUser Table 매일 추가");
        questService.resetBeforeCreateQuestUser();
    }

//    @Scheduled(cron = "0 0 0 * * *")
    @Scheduled(cron = "0 0 0 * * *")
    public void resetRestPoint() {
        log.info("User Todo 포인트 3으로 매일 초기화");
        questService.resetRestPoint();
    }

}
