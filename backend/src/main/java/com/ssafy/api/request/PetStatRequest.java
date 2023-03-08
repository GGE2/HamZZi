package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("PetStatRequest")
public class PetStatRequest {

    @ApiModelProperty(name = "pet_id", example = "1")
    Long pet_id;

    @ApiModelProperty(name = "physical", example = "0")
    int physical;

    @ApiModelProperty(name = "artistic", example = "0")
    int artistic;

    @ApiModelProperty(name = "intelligent", example = "0")
    int intelligent;

    @ApiModelProperty(name = "inactive", example = "0")
    int inactive;

    @ApiModelProperty(name = "energetic", example = "0")
    int energetic;

    @ApiModelProperty(name = "etc", example = "0")
    int etc;

}
