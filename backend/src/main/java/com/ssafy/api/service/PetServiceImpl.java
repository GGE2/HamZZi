package com.ssafy.api.service;

import com.ssafy.api.request.PetCreateRequest;
import com.ssafy.db.entity.Pet.Pet;
import com.ssafy.db.entity.Pet.PetInfo;
import com.ssafy.db.entity.Pet.PetStat;
import com.ssafy.db.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    @Autowired
    PetRepository petRepo;

    //수정필요 : Pet 으로 객체생성, FindByPetId로 PetOutfit과 PetStat 생성
    //User도 같은 방법으로 수정해야 하나......
    @Override
    public Pet createPet(PetCreateRequest createInfo) {
        Pet pet = new Pet();
        PetInfo petInfo = new PetInfo();
        PetStat petStat = new PetStat();

        //fireBase에서 UserProfile 가져오기
        pet.setNickname("tmp ham owner");
        pet.setPet_name(createInfo.getName());
        pet.setCreate_date(LocalDate.now());

        petInfo.setPet(pet);
        petStat.setPet(pet);

        petRepo.savePet(pet);
        petRepo.savePetInfo(petInfo);
        petRepo.savePetStat(petStat);

        return pet;
    }


}
