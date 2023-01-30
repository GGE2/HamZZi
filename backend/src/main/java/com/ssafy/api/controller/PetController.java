package com.ssafy.api.controller;

import com.ssafy.api.request.PetCreateRequest;
import com.ssafy.api.request.PetStatRequest;
import com.ssafy.api.service.PetService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Pet.Pet;
import com.ssafy.db.entity.Pet.PetStat;
import com.ssafy.db.entity.User.UserProfile;
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
    /* Pet-생성 API: 입력한 이름으로 로그인한 사용자의 펫을 생성한다 */
    public String createPet(
            @RequestBody @ApiParam(value="펫 생성 정보", required = true) PetCreateRequest createInfo) {
        Pet pet = petService.createPet(createInfo);
        return pet.getPet_id() + " : " + pet.getPet_name() + " /// owner : " + pet.getNickname();
    }

    @GetMapping("/{email}")
    @ApiOperation(value = "펫 정보", notes = "펫의 정보를 출력한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    /* Pet-조회 API: 로그인한 사용자의 활성화된 펫을 조회한다 */
    public String getPetInfo(@PathVariable String email) {
        Pet pet = petService.activePetData(email);
        return "pet owner : " + pet.getNickname() + "///// petname : " + pet.getPet_name();
    }

    @PutMapping("/exp")
    @ApiOperation(value = "펫 경험치 증가", notes = "입력받은 pet_id를 가진 펫의 경험치와 레벨을 증가시킨다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    /* Pet-생성 API: 입력한 이름으로 로그인한 사용자의 펫을 생성한다 */
    public String increaseExpLevel(Long pet_id, int exp) {
        Pet pet = petService.expLevelLogic(pet_id, exp);
        return pet.getPet_name() + ": Lv" + pet.getLevel() + " Exp:" + pet.getExp();
    }

    @PutMapping("/stat")
    @ApiOperation(value = "펫 스탯 증가", notes = "입력받은 pet_id를 가진 펫의 스탯을 증가시킨다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    /* Pet-생성 API: 입력한 이름으로 로그인한 사용자의 펫을 생성한다 */
    public String increaseStat(
            @RequestBody @ApiParam(value="펫 스탯 정보", required = true) PetStatRequest petStatRequest) {
        PetStat petStat = petService.statLogic(petStatRequest);
        return petStat.getPet().getPet_name() + "\n"
                + "(physical, artistic, intelligent, inactive, energetic, etc)" + "\n"
                + petStat.getPhysical() +", "+ petStat.getArtistic() +", "+ petStat.getIntelligent() +", "
                + petStat.getInactive() +", "+ petStat.getEnergetic() +", "+ petStat.getEtc();
    }

}
