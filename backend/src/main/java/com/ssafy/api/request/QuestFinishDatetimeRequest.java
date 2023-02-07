package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("QuestDatetimeRequest")
public class QuestFinishDatetimeRequest {

    @ApiModelProperty(name = "완료해야하는 시간(분으로 계산해서)", example = "540")  // 9시를 분으로 나타낸 것
    int finish_datetime;
}
