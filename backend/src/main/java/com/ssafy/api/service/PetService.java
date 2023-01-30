package com.ssafy.api.service;

import com.ssafy.api.request.PetCreateRequest;
import com.ssafy.db.entity.Pet.Pet;

public interface PetService {

    Pet createPet(PetCreateRequest createInfo);
    Pet activePetData(String email);
    Pet expLevelLogic(Long pet_id);
}
