package com.ssafy.api.controller;

import com.ssafy.api.service.QuestService;
import com.ssafy.api.service.UserService;
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

    private final UserService userService;

    // QuestUser Table 초기화
    @Scheduled(cron = "0 0 0 * * *")
    public void resetTable() {
        log.info("QuestUser Table 매일 초기화");
        System.out.println("QuestUser Table 매일 초기화");
        questRepo.tableClear();
    }

    @Scheduled(cron = "5 0 0 * * *")
    public void CreateTable() {
        log.info("QuestUser Table 매일 추가");
        System.out.println("QuestUser Table 매일 추가");
        questService.resetBeforeCreateQuestUser();
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void resetRestPoint() {
        log.info("User의 Todo 포인트 3으로 매일 초기화");

    }

}
