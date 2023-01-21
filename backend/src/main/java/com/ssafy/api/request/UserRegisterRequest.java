package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserRegisterRequest")
public class UserRegisterRequest {

//    @ApiModelProperty(name="유저 id", example="1")
//    int user_id;
    @ApiModelProperty(name="유저 이메일", example="ssafy_web")
    String email;
    @ApiModelProperty(name="유저 비밀번호", example="your_password")
    String password;
    @ApiModelProperty(name="유저 이름", example="your_name")
    String name;
    @ApiModelProperty(name="유저 전화번호", example="010-0000-1234")
    String telephone;
    @ApiModelProperty(name="유저 성별", example="M")
    char gender;

}
