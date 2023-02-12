package com.ssafy.api.service;

import com.ssafy.api.request.PetCreateRequest;
import com.ssafy.api.request.PetStatRequest;
import com.ssafy.db.entity.Pet.Pet;
import com.ssafy.db.entity.Pet.PetInfo;
import com.ssafy.db.entity.Pet.PetStat;

import java.util.List;

public interface PetService {

    /* 펫 생성 */
    Pet createPet(PetCreateRequest createInfo);

    /* 그냥 펫 정보 조회 - 졸업여부 체크 X */
    Pet petData(Long pet_id);
    PetInfo petInfoData(Long pet_id);
    PetStat petStatData(Long pet_id);

    /* 활성화된 펫 찾기 */
    Pet ActivatePet(String nickname);
    Pet graduate(Long pet_id);

    /* 트로피용 졸업 펫 리스트 - 여기서 뽑아서 조회하기 */
    List<PetInfo> graduatedPets(String nickname);

    /* 스탯, 타입과 경험치 로직 */
    Pet expLevelLogic(Long pet_id, int exp);
    PetInfo typeSettingLogic(PetStat petStat);
    PetStat statLogic(PetStatRequest petStatRequest, String nickname);
}
