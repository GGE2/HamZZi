package com.ssafy.api.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.api.response.UserRes;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User.User;
import com.ssafy.db.entity.User.UserProfile;
import com.ssafy.db.repository.UserRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "user API", tags = {"User"})
@RestController
//임시 주소...
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    //오류발생시 1순위 주석처리대상 -> config.FirebaseInitializer 도 주석
//    @Autowired
//    FirebaseAuth firebaseAuth;

    @Autowired
    UserService userService;

    UserRepository userRepo;

    @PostMapping()
    @ApiOperation(value = "회원 가입", notes = "필요한 정보를 전부 입력한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> registerUser(
            @RequestBody @ApiParam(value="회원가입 정보", required = true) UserRegisterRequest registerInfo) {

        Long user_id = userService.registerUser(registerInfo);
        User user = userRepo.findById(user_id);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
//
//    @PostMapping("/my")
//    @ApiOperation(value = "회원 정보 조회", notes = "로그인한 회원의 프로필 정보 조회")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공"),
//            @ApiResponse(code = 401, message = "인증 실패"),
//            @ApiResponse(code = 404, message = "사용자 없음"),
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
//    public ResponseEntity<UserRes> getUserInfo() {
//        String user_id; //현재 로그인한 사용자의 user_id(PK)를 가져와야 함
//        UserProfile user = userService.loginUserData(user_id);
//        return null;
//    }


}
