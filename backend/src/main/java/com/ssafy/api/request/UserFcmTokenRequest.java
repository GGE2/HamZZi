package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserFcmRequest")
public class UserFcmTokenRequest {

    @ApiModelProperty(name="유저 이메일", example="ssafy@email.com")
    String email;

    @ApiModelProperty(name="FCM 토큰", example="string")
    String token;
}
