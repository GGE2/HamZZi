package com.ssafy.db.repository;

import com.ssafy.db.entity.Pet.Pet;
import com.ssafy.db.entity.Pet.PetInfo;
import com.ssafy.db.entity.Pet.PetStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepository {

    void savePet(Pet pet);
    void savePetInfo(PetInfo petInfo);
    void savePetStat(PetStat petStat);

    Pet findById(Long pet_id);
    Pet findByNickname(String nickname);

//    void addStatPoint(@Param("typename") String typename, int point);
}
