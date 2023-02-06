package com.ssafy.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserInfoRequest")
public class UserInfoRequest {

    @ApiModelProperty(name="유저 uid", example="abcdd12345")
    String uid;
}
