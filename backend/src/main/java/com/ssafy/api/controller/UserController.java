package com.ssafy.api.controller;

import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.api.request.UserTokenRequest;
import com.ssafy.api.response.UserProfileRes;
import com.ssafy.api.response.UserRes;
import com.ssafy.api.service.UserService;
import com.ssafy.db.entity.User.User;
import com.ssafy.db.entity.User.UserProfile;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Api(value = "user API", tags = {"User"})
@RestController
//임시 주소...
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /* FireBase */ /////////////////////////////////////////////////////////////////////////////////
    /* 단순히 DB에 FCM 토큰 전달 */
    @PutMapping("/fcm")
    @ApiOperation(value = "FCM 토큰", notes = "FCM 토큰을 입력받는다")
    public String registerFcm(
            @RequestBody @ApiParam(value="token 등록", required = true) UserTokenRequest tokenInfo) {
        User user = userService.registerFcm(tokenInfo);
        return user.getEmail() + " Register FCM Token";
    }
    /* Uid의 존재 여부로 검증 */
    @GetMapping("/uid/{email}")
    @ApiOperation(value = "UID 체크", notes = "기존 사용자라면 true를 리턴한다")
    public ResponseEntity<Boolean> CheckUid(@PathVariable String email) {
//        return userService.CheckUid(email) + "";
        boolean result = userService.CheckUid(email);
        return ResponseEntity.status(200).body(result);
    }

    /* CREATE UPDATE */ ////////////////////////////////////////////////////////////////////////////
    /* User 가입 API: 가입한 사용자의 PK를 리턴해준다 */
    @PostMapping("/register")
    @ApiOperation(value = "회원 가입", notes = "필요한 정보를 전부 입력한다")
    public String registerUser(
            @RequestBody @ApiParam(value="회원가입 정보", required = true) UserRegisterRequest registerInfo) {
        User user = userService.registerUser(registerInfo);
        return user.getEmail() + " REGISTER " + user.getUser_id() + " OK";
    }

    /* UserProfile 닉네임 등록 API: 프롤로그시 최초 1회 실행 */

    @PutMapping("/nickname")
    @ApiOperation(value = "닉네임 등록", notes = "닉네임 등록")
    public String registerNickname(@RequestParam String nickname, @RequestParam String email) {
        // 페이지에 임의 접근시 닉네임 변경 가능한 문제(좀 중요함...)
        User user = userService.registerNickname(email, nickname);
        return user.getEmail() + " REGISTER " + user.getUserProfile().getNickname() + " OK";
    }

    /* GET */ //////////////////////////////////////////////////////////////////////////////////////
    /* UserProfile 회원 정보 조회 */
    @GetMapping("/mypage")
    @ApiOperation(value = "회원 정보 조회", notes = "로그인한 회원의 프로필 정보 조회")
    public ResponseEntity<UserProfileRes> getUserInfo(@RequestParam String email) {
        //오류 때문에 email을 id로 변환 후 넣어야 함(PK만 받을 수 있음)
        UserProfile userProfile = userService.loginUserData(email);
        return ResponseEntity.status(200).body(UserProfileRes.of(userProfile));
    }

    /* DELETE */ ///////////////////////////////////////////////////////////////////////////////////
    /* UserProfile 회원 정보 조회 */
    @DeleteMapping("/delete")
    @ApiOperation(value = "회원 탈퇴", notes = "로그인한 회원 정보 삭제")
    public String deleteUser(@RequestParam String email) {
        userService.deleteUser(email);
        return email + " DELETE OK";
    }

}
