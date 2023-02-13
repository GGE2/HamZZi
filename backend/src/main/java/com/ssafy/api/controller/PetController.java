package com.ssafy.api.controller;

import com.ssafy.api.request.PetCreateRequest;
import com.ssafy.api.request.PetStatRequest;
import com.ssafy.api.service.PetService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Pet.Pet;
import com.ssafy.db.entity.Pet.PetData;
import com.ssafy.db.entity.Pet.PetInfo;
import com.ssafy.db.entity.Pet.PetStat;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "pet API", tags = {"Pet"})
@RestController
//임시 주소
@RequestMapping("api/pet")
@RequiredArgsConstructor
public class PetController {

    @Autowired
    PetService petService;

    //////////////////////////////////////////////////////////////////////////////////////////////////////
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
        if(petService.createPet(createInfo) == null) {return "ERROR: EXIST Activated Pet";}

        return "ID: " + pet.getPet_id() + " NAME: " + pet.getPet_name() + " OWNER: " + pet.getNickname() ;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/{nickname}")
    @ApiOperation(value = "펫 정보", notes = "펫의 정보를 출력한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    /* Pet-조회 API: 로그인한 사용자의 활성화된 펫을 조회한다 */
    public PetData getActivatePet(@PathVariable String nickname) {

        Pet pet = petService.ActivatePet(nickname);
        if(pet == null) {return null;}

        // PetStat 과 PetInfo도 받아오자.
        Long pet_id = pet.getPet_id();
        PetInfo petInfo = petService.petInfoData(pet_id);
        PetStat petStat = petService.petStatData(pet_id);

        PetData petData = new PetData();
        petData.setPet(pet);
        petData.setPetInfo(petInfo);
        petData.setPetStat(petStat);


        return petData;
    }

    @GetMapping("trophy/{nickname}")
    @ApiOperation(value = "졸업 펫 정보", notes = "펫의 정보를 출력한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    /* Pet-조회 API: 로그인한 사용자의 졸업한 펫 목록을 조회한다 */
    public List<PetInfo> getGraduatedPets(@PathVariable String nickname) {
        // info의 type을 받아와서 출력함(외형정보. behavior: idle 고정)
        List<PetInfo> graduatedPetList = petService.graduatedPets(nickname);
        // 트로피 개수제한 추가예정

        return graduatedPetList;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("/exp")
    @ApiOperation(value = "펫 경험치 증가 - Type 1부터 순서대로 physical, artistic, intelligent, inactive, energetic, etc", notes = "입력받은 pet_id를 가진 펫의 경험치와 레벨을 증가시킨다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    /* Pet-경험치 API: 펫을 조회하고 입력받은 경험치만큼 올려준다(레벨도!!) */
    public String increaseExpLevel(Long pet_id, int exp, @RequestParam String nickname) {
        Pet pet = petService.expLevelLogic(pet_id, exp);
        PetInfo petInfo = petService.typeSettingLogic(nickname);
        return pet.getPet_name() + ": Lv" + pet.getLevel() + " Exp:" + pet.getExp() + " PetType: " + petInfo.getType();
    }

    @PutMapping("/stat")
    @ApiOperation(value = "펫 스탯 증가", notes = "입력받은 pet_id를 가진 펫의 스탯을 증가시킨다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    /* Pet-스탯 API: 기존 펫 스탯 + 새로 입력받은 스탯만큼 더해준다 */
    public String increaseStat(
            @RequestBody @ApiParam(value="펫 스탯 정보", required = true) PetStatRequest petStatRequest, @RequestParam String nickname) {
        PetStat petStat = petService.statLogic(petStatRequest, nickname);
        return petStat.getPet().getPet_name() + "\n"
                + "(physical, artistic, intelligent, inactive, energetic, etc)" + "\n"
                + petStat.getPhysical() +", "+ petStat.getArtistic() +", "+ petStat.getIntelligent() +", "
                + petStat.getInactive() +", "+ petStat.getEnergetic() +", "+ petStat.getEtc();
    }

    //Pet 졸업로직 만들기
    @PutMapping("/graduate")
    @ApiOperation(value = "펫 졸업", notes = "해당 펫을 졸업시킨다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    /* Pet-졸업 API: 펫을 졸업 상태로 만들어준다(Activated pet의 id를 가져와야함) */
    public String graduatePet(Long pet_id) {
        Pet pet = petService.petData(pet_id);
        String petName = pet.getPet_name();

        if( petService.graduate(pet_id) == null ) {
            return "ERROR: CHECK level: \"5\"= " + pet.getLevel() + " OR is_graduate: \"False\"= " + pet.is_graduate();
        }

        return petName + " graduated";
    }



    // PetInfo type 몇 이상&레벨 반영
    // behavior 스킵/랜덤
}
