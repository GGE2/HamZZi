package com.ssafy.api.controller;

import com.ssafy.api.request.QuestRequest;
import com.ssafy.api.request.QuestUserRequest;
import com.ssafy.api.service.QuestService;
import com.ssafy.db.entity.Quest.Quest;
import com.ssafy.db.entity.Quest.QuestUser;
import com.ssafy.db.entity.User.UserProfile;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/quest")
@RequiredArgsConstructor
public class QuestController {

    @Autowired
    QuestService questService;

    // 유저의 QuestList 보여주기
    @GetMapping("/{nickname}")
    @ApiOperation(value = "Quest 조회", notes = "Quest를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> getQuestUserList(@PathVariable String nickname) {
        List<QuestUser> questUserList = questService.getQuests(nickname);

        return ResponseEntity.status(200).body(questUserList);
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
//
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

    // User에게 Quest 부여 List
    @PostMapping("/user/{nickname}")
    @ApiOperation(value = "Quest를 User에게 부여", notes = "Quest를 User에게 부여한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String createQuestUser(QuestUserRequest questUserReq, @PathVariable String nickname) {
        questService.createQuestUser(questUserReq, nickname);

        return "200 OK!" ;
    }

    // Quest 완료 체크 계산식 추가
    @PutMapping("/check")
    @ApiOperation(value = "Quest 완료", notes = "해당 Quest를 완료한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String checkQuest(@RequestParam String nickname, @RequestParam Long questUser_id, @RequestParam Long quest_id) {
        QuestUser quest = questService.checkUpdateQuest(questUser_id);
        questService.questPointAssignment(nickname, quest_id);

        return "IsCheck: " + quest.getIscheck() ;
    }

    // Quest 위치 등록 / 수정
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
}
