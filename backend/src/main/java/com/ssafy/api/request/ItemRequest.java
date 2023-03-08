package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel("ItemRequest")
public class ItemRequest {

    @ApiModelProperty(name = "Item Id", example = "1")
    int item_id;

    @ApiModelProperty(name = "Item 가격", example = "10")
    int cost;

    @ApiModelProperty(name = "Item Level 제한", example = "1")
    int level;
}
