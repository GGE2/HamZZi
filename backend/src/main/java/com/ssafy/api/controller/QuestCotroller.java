package com.ssafy.api.controller;

import com.ssafy.api.request.QuestRequest;
import com.ssafy.api.request.QuestUserLocationRequest;
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
public class QuestCotroller {

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

    // Quest 생성
    @PostMapping()
    @ApiOperation(value = "Quest 생성", notes = "Quest를 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String createQuest(@RequestBody QuestRequest questInfo) {
        Quest quest = questService.createQuest(questInfo);

        return "ID: " + quest.getQuest_id() + " CONTENT: " + quest.getContent() ;
    }

    // User에게 Quest 부여
    @PostMapping("/user/{quest_id}")
    @ApiOperation(value = "Quest를 User에게 부여", notes = "Quest를 User에게 부여한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String createQuestUser(@RequestBody QuestUserRequest questUserReq, @PathVariable Long quest_id) {
        QuestUser quest = questService.createQuestUser(questUserReq, quest_id);

        return "ID: " + quest.getQuestUser_id() + " OWNER: " +  quest.getNickname() + " QUEST: " + quest.getQuest_id() ;
    }

    // Quest IsCheck 바꾸고 계산식 추가
    @PutMapping("/{questUser_id}")
    @ApiOperation(value = "Quest 완료", notes = "해당 Quest를 완료한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String checkQuest(String nickname, Long quest_id, @PathVariable Long questUser_id) {
        QuestUser quest = questService.checkUpdateQuest(nickname, quest_id, questUser_id);

        return "IsCheck: " + quest.getIscheck() ;
    }

    // Quest 위치 등록 / 수정
    @PutMapping("/location/{latitude}/{longitude}")
    @ApiOperation(value = "Quest 위치등록", notes = "해당 Quest를 위치등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String questregisterLocation(String nickname, @PathVariable int latitude, @PathVariable int longitude) {
        UserProfile userProfile = questService.registerLocation(nickname, latitude, longitude);

        return "OWNER: " + userProfile.getNickname() + " Latitude: " + userProfile.getLatitude() + " Longitude: " + userProfile.getLongitude();
    }

    @PutMapping("/tiem/{finish_time}")
    @ApiOperation(value = "Quest 완료해야하는 시간 등록", notes = "해당 Quest를 완료해야하는 시간 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String questregisterLocation(Long questUser_id, @PathVariable String finish_time) {
        QuestUser questUser = questService.registerFinalDatetime(questUser_id, finish_time);

        return "ID: " + questUser.getQuestUser_id() + " OWNER: " + questUser.getNickname() + " FinishTime: " + questUser.getFinish_datetime();
    }
}
