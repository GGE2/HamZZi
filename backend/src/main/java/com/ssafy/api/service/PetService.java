package com.ssafy.api.service;

import com.ssafy.api.request.PetCreateRequest;
import com.ssafy.api.request.PetStatRequest;
import com.ssafy.db.entity.Pet.Pet;
import com.ssafy.db.entity.Pet.PetStat;

public interface PetService {

    Pet createPet(PetCreateRequest createInfo);
    Pet activePetData(String email);
    Pet expLevelLogic(Long pet_id, int exp);
    PetStat statLogic(PetStatRequest petStatRequest);
}
