package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserTokenRequest")
public class UserTokenRequest {

    @ApiModelProperty(name="유저 이메일", example="ssafy@email.com")
    String email;

    @ApiModelProperty(name="uid", example="String type uid")
    String uid;

    @ApiModelProperty(name="token", example="String type token")
    String fcmToken;
}
