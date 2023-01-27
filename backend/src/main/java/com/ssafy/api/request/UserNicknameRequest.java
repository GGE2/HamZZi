package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserRegisterRequest")
public class UserNicknameRequest {

//    @ApiModelProperty(name="유저 id", example="1")
//    int user_id;
    @ApiModelProperty(name="유저 닉네임", example="hamjuin") String Nickname;
}
