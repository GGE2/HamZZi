package com.ssafy.api.controller;

import com.ssafy.api.service.CountService;
import com.ssafy.db.entity.Count.CountDaily;
import com.ssafy.db.entity.Count.CountWeekly;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Count API", tags = {"Count"})
@RestController
@RequiredArgsConstructor
@RequestMapping("api/count")
public class CountController {

    @Autowired
    CountService countService;

    @GetMapping("/daily")
    @ApiOperation(value = "Daily Count 조회", notes = "Daily Count를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> getDaily(@RequestParam String nickname) {
        List<CountDaily> count = countService.getDailyList(nickname);
        return ResponseEntity.status(200).body(count);
    }

    @GetMapping("/weekly")
    @ApiOperation(value = "Weekly Count 조회", notes = "Weekly Count를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> getWeekly(@RequestParam String nickname) {
        List<CountWeekly> count = countService.getWeeklyList(nickname);
        return ResponseEntity.status(200).body(count);
    }

    @PostMapping()
    @ApiOperation(value = "모든 카운트 생성", notes = "생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String create(@RequestParam String nickname) {
        countService.createCount(nickname);
        countService.createCountDaily(nickname);
        countService.createCountWeekly(nickname);

        return "Create 200 OK";
    }

    @PutMapping
    @ApiOperation(value = "모든 카운트 업데이트", notes = "업데이트한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String update(@RequestParam String nickname, @RequestParam String datetime) {
        countService.updateCount(nickname);
        countService.updateCountDaily(nickname, datetime);
        countService.updateCountWeekly(nickname);

        return "Update 200 OK";
    }














}
