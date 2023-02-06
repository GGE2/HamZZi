package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel("QuestRequest")
public class QuestRequest {

    @ApiModelProperty(name = "퀘스트 내용", example = "회사/학교에 지각하지 않기")
    String content;

    @ApiModelProperty(name = "퀘스트 point", example = "1")
    int point;

}
