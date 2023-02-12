package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CountRequest")
public class CountRequest {

    @ApiModelProperty(name = "User Nickname", example = "고틀린")
    String nickname;
}
