package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("QuestDatetimeRequest")
public class QuestFinishDatetimeRequest {

    @ApiModelProperty(name = "완료해야하는 시간", example = "09:00")
    String finish_datetime;
}
