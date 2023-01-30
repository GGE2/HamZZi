package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("PetCreateRequest")
public class PetCreateRequest {

    // 나머지는 입력 필요없음. 로그인한 유저의 닉네임+기본값 체크 필요

    @ApiModelProperty(name = "햄스터 이름", example = "hamzi")
    String name;

}
