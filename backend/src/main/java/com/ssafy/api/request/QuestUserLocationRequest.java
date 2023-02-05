package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("QuestUserLocationRequest")
public class QuestUserLocationRequest {

    @ApiModelProperty(name="사용자 닉네임", example="Questjuin")
    String nickname;

    @ApiModelProperty(name="위도", example="111111")
    int latitude;

    @ApiModelProperty(name="경도", example="222222")
    int longitude;
}
