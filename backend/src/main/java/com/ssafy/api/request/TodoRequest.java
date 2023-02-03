package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel("TodoRequest")        // swagger에서 잡아달라고 쓰는 거
public class TodoRequest {
    @ApiModelProperty(name = "Todo내용", example = "content")
    String content;

    @ApiModelProperty(name = "생성시간/수정시간", example = "2023-02-01")
    String datetiem;
}
