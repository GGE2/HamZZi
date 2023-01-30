package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserNicknameRequest")
public class UserNicknameRequest {
    @ApiModelProperty(name="유저 닉네임", example="hamjuin")
    String nickname;

    @ApiModelProperty(name="유저 id", example="1")
    Long user_id;
}
