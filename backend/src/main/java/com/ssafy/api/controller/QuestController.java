package com.ssafy.api.controller;

import com.ssafy.api.request.QuestRequest;
import com.ssafy.api.service.QuestService;
import com.ssafy.db.entity.Quest.Quest;
import com.ssafy.db.entity.Quest.QuestDaily;
import com.ssafy.db.entity.Quest.QuestWeekly;
import com.ssafy.db.entity.User.UserProfile;
import com.ssafy.db.repository.QuestRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*", allowedHeaders = "*")     // 프론트 cors 에러 잡아주는 코드
@Api(value = "Quest API", tags = {"Quest"})
@RestController
@RequestMapping("api/quest")
@RequiredArgsConstructor
public class QuestController {

    @Autowired
    QuestService questService;

    @Autowired
    QuestRepository questRepo;

    @GetMapping("/daily/{nickname}")
    @ApiOperation(value = "Daily Quest 조회", notes = "Daily Quest를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> getDailyQuestList(@PathVariable String nickname) {
        List<QuestDaily> questDailyList = questService.getDailyQuests(nickname);

        return ResponseEntity.status(200).body(questDailyList);
    }

    @GetMapping("/weekly/{nickname}")
    @ApiOperation(value = "Weekly Quest 조회", notes = "Weekly Quest를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> getWeeklyQuestList(@PathVariable String nickname) {
        List<QuestWeekly> questWeeklyList = questService.getWeeklyQuests(nickname);

        return ResponseEntity.status(200).body(questWeeklyList);
    }

    // Quest 생성 // key 1 : daily / key 2: weekly
    @PostMapping("/{key}")
    @ApiOperation(value = "Quest 생성", notes = "Quest를 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String createQuest(@RequestBody QuestRequest questInfo, @PathVariable int key) {
        Quest quest = questService.createQuest(questInfo, key);

        return "ID: " + quest.getQuest_id() + " CONTENT: " + quest.getContent() ;
    }

    // 전체 Quest List
    @GetMapping()
    @ApiOperation(value = "All Quest List", notes = "Quest List Get.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> getQuestList() {
        List<Quest> questList = questService.getQuest();

        return ResponseEntity.status(200).body(questList);
    }

    // User에게 Quest 부여 List
    @PostMapping("/user/{nickname}")
    @ApiOperation(value = "Quest를 User에게 부여", notes = "Quest를 User에게 부여한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String createQuestUser(@PathVariable String nickname) {
        questService.createQuestUser(nickname);

        return "200 OK!" ;
    }

    // Quest 완료
    @PutMapping("/daily")
    @ApiOperation(value = "Daily Quest 완료", notes = "해당 Quest를 완료한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String checkDailyQuest(@RequestParam String nickname, @RequestParam Long questDaily_id, @RequestParam Long quest_id) {
        QuestDaily quest = questService.checkDailyQuest(questDaily_id);
        questService.questPointAssignment(nickname, quest_id);

        return "IsCheck: " + quest.getIscheck() ;
    }

    // Quest 완료
    @PutMapping("/weekly")
    @ApiOperation(value = "Weekly Quest 완료", notes = "해당 Quest를 완료한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String checkWeeklyQuest(@RequestParam String nickname, @RequestParam Long questWeekly_id, @RequestParam Long quest_id) {
        QuestWeekly quest = questService.checkWeeklyQuest(questWeekly_id);
        questService.questPointAssignment(nickname, quest_id);

        return "IsCheck: " + quest.getIscheck() ;
    }

    @PutMapping("/location")
    @ApiOperation(value = "Quest 위치등록", notes = "해당 Quest를 위치등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String questRegisterLocation(String nickname, @RequestParam double latitude, @RequestParam double longitude, @RequestParam String location) {
        UserProfile userProfile = questService.registerLocation(nickname, latitude, longitude, location);

        return "OWNER: " + userProfile.getNickname() + " Latitude: " + userProfile.getLatitude() + " Longitude: " + userProfile.getLongitude() + " Location: " + userProfile.getLocation();
    }

    @PutMapping("/time")
    @ApiOperation(value = "Quest 완료해야하는 시간 등록", notes = "해당 Quest를 완료해야하는 시간 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String questRegisterLocation(String nickname, @RequestParam int finish_time) {
        UserProfile userProfile = questService.registerFinalDatetime(nickname, finish_time);

        return "OWNER: " + userProfile.getNickname() + " FinishTime: " + userProfile.getFinish_datetime();
    }

    @PutMapping("/pedometer")
    @ApiOperation(value = "Quest 만보기 등록", notes = "만보기를 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String questRegisterPedometer(String nickname, @RequestParam int pedometer) {
        UserProfile userProfile = questService.registerPedometer(nickname, pedometer);

        return "OWNER: " + userProfile.getNickname() + " Pedometer: " + userProfile.getPedometer();
    }

//    // Quest Object 뽑아 올수 있는지 테스트
//    @GetMapping("/test")
//    @ApiOperation(value = "Test", notes = "Quest List Get.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공"),
//            @ApiResponse(code = 401, message = "인증 실패"),
//            @ApiResponse(code = 404, message = "사용자 없음"),
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
//    public List<Long> test() {
//        return questService.getQuestId();
//    }
    
//    아래 3개 매핑은 Scheduler에서 자동화 완료!

//    @DeleteMapping("/reset")
//    @ApiOperation(value = "매일 00시에 실행 - QuestUser Table 초기화", notes = "QuestUser Table의 모든 값을 지운다.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공"),
//            @ApiResponse(code = 401, message = "인증 실패"),
//            @ApiResponse(code = 404, message = "사용자 없음"),
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
//    public String reset() {
//        questRepo.tableClear();
//        return "200 OK ALL Clear";
//    }

//    @PostMapping("/beforeReset")
//    @ApiOperation(value = "매일 00시에 실행 - QuestUser Table 생성", notes = "QuestUser Table의 모든 값을 만든다.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공"),
//            @ApiResponse(code = 401, message = "인증 실패"),
//            @ApiResponse(code = 404, message = "사용자 없음"),
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
//    public String resetBeforeCreate() {
//        questService.resetBeforeCreateQuestUser();
//        return "200 OK ALL Create";
//    }
//
//    @PutMapping("/reset")
//    @ApiOperation(value = "UserProfile RestPoint 3으로 초기화", notes = "UserProfile RestPoint 3으로 초기화.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공"),
//            @ApiResponse(code = 401, message = "인증 실패"),
//            @ApiResponse(code = 404, message = "사용자 없음"),
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
//    public String resetRestPoint(){
//        questService.resetRestPoint();
//        return "200 OK Reset RestPoint";
//    }
}
