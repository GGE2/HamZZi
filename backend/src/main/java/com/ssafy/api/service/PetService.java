package com.ssafy.api.service;

import com.ssafy.api.request.PetCreateRequest;
import com.ssafy.api.request.PetStatRequest;
import com.ssafy.db.entity.Pet.Pet;
import com.ssafy.db.entity.Pet.PetInfo;
import com.ssafy.db.entity.Pet.PetStat;

import java.util.List;

public interface PetService {

    Pet createPet(PetCreateRequest createInfo);

    Pet petData(String nickname);
    PetInfo petInfoData(String nickname);
    PetStat petStatData(String nickname);

    List<Pet> graduatedPets(String nickname);

    Pet expLevelLogic(Long pet_id, int exp);
    PetStat statLogic(PetStatRequest petStatRequest);
    Pet graduate(Long pet_id);
}
