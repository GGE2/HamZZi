package com.ssafy.api.service;

import com.ssafy.api.request.PetCreateRequest;
import com.ssafy.api.request.PetStatRequest;
import com.ssafy.db.entity.Pet.Pet;
import com.ssafy.db.entity.Pet.PetInfo;
import com.ssafy.db.entity.Pet.PetStat;
import com.ssafy.db.entity.User.UserProfile;
import com.ssafy.db.repository.PetRepository;
import com.ssafy.db.repository.UserRepository;
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

    @Autowired
    UserRepository userRepo;

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

    @Override
    public Pet activePetData(String email) {
        String nickname = userRepo.findNicknameById(userRepo.findIdByEmail(email));
        Pet pet = petRepo.findByNickname(nickname);
        return pet;
    }

    @Override
    public Pet expLevelLogic(Long pet_id, int exp) {
        Pet pet = petRepo.findById(pet_id);
        int nowExp = pet.getExp();
        int nowLevel = pet.getLevel();

        int newExp = nowExp + exp;
        int newLevel = nowLevel;

        switch (newLevel) {
            case 1: if(newExp >= 14) {newLevel++; newExp -= 14;} break;
            case 2: if(newExp >= 30) {newLevel++; newExp -= 30;} break;
            case 3: if(newExp >= 60) {newLevel++; newExp -= 60;} break;
            case 4: if(newExp >= 66) {newLevel++; newExp -= 66;} break;
            case 5: System.out.println("Max level"); break;
        }

        pet.setExp(newExp);
        pet.setLevel(newLevel);

        return pet;
    }

    @Override
    public PetStat statLogic(PetStatRequest petStatRequest) {
        PetStat petStat = petRepo.findStatById(petStatRequest.getPet_id());

        petStat.setPhysical(petStat.getPhysical() + petStatRequest.getPhysical());
        petStat.setArtistic(petStat.getArtistic() + petStatRequest.getArtistic());
        petStat.setIntelligent(petStat.getIntelligent() + petStatRequest.getIntelligent());
        petStat.setInactive(petStat.getInactive() + petStatRequest.getInactive());
        petStat.setEnergetic(petStat.getEnergetic() + petStatRequest.getEnergetic());
        petStat.setEtc(petStat.getEtc() + petStatRequest.getEtc());

        petRepo.savePetStat(petStat);
        return petStat;
    }


}
