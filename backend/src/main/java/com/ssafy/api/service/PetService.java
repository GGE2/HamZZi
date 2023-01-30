package com.ssafy.api.service;

import com.ssafy.api.request.PetCreateRequest;
import com.ssafy.db.entity.Pet.Pet;

public interface PetService {

    Long createPet(PetCreateRequest createInfo);
}
