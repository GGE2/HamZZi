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
    @ApiModelProperty(name="유저 Password", example="your_password")
    String password;
    @ApiModelProperty(name="유저 Name", example="your_name")
    String name;
    @ApiModelProperty(name="유저 telephone number", example="010-0000-1234")
    String telephone;
    @ApiModelProperty(name="유저 gender", example="M")
    char gender;

}
