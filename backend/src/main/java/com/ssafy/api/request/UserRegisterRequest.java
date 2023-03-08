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
    @ApiModelProperty(name="Firebase Uid", example="abcd123456")
    String uid;


}
