package com.ssafy.api.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User.UserPrivacy;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowedHeaders = "*")
@Api(value = "user API", tags = {"User"})
@RestController
//임시 주소...
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    FirebaseAuth firebaseAuth;

    @Autowired
    UserService userService;

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

        UserPrivacy user = userService.registerUser(registerInfo);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));

    }


}
