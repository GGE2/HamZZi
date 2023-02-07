package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("QuestUserListRequest")
public class QuestUserListRequest {

    @ApiModelProperty(name = "사용자 닉네임", example = "Questjuin")
    String user_nickname;

}