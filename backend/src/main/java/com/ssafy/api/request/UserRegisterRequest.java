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
    @ApiModelProperty(name="유저 이메일", example="ssafy@email.com")
    String email;
    @ApiModelProperty(name="유저 비밀번호", example="pw1234!")
    String password;
    @ApiModelProperty(name="유저 이름", example="kim")
    String name;
    @ApiModelProperty(name="유저 전화번호", example="010-0000-1234")
    String telephone;

}
