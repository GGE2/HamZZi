package com.ssafy.api.controller;

import com.ssafy.api.request.PetCreateRequest;
import com.ssafy.api.service.PetService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Pet.Pet;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "pet API", tags = {"Pet"})
@RestController
//임시 주소
@RequestMapping("api/pet")
@RequiredArgsConstructor
public class PetController {

    @Autowired
    PetService petService;

    @PostMapping()
    @ApiOperation(value = "펫 생성", notes = "필요한 정보를 전부 입력한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String createPet(
            @RequestBody @ApiParam(value="펫 생성 정보", required = true) PetCreateRequest createInfo) {

        Pet pet = petService.createPet(createInfo);
        return pet.getPet_id() + " : " + pet.getPet_name() + " /// owner : " + pet.getNickname();
    }

    @GetMapping()
    @ApiOperation(value = "펫 정보", notes = "펫의 정보를 출력한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String getPetInfo(

    )


}
