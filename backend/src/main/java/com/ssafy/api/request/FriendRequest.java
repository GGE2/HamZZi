package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel("FriendRequest")
public class FriendRequest {

    @ApiModelProperty(name = "사용자 닉네임", example = "리진성")
    String user_nickname;

    @ApiModelProperty(name = "친구 닉네임", example = "리종길")
    String friend_nickname;
}
