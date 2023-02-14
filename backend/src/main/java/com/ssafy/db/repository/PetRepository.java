package com.ssafy.db.repository;

import com.ssafy.db.entity.Pet.Pet;
import com.ssafy.db.entity.Pet.PetInfo;
import com.ssafy.db.entity.Pet.PetStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository {

    // Create, Update ----------------------
    void savePet(Pet pet);
    void savePetInfo(PetInfo petInfo);
    void savePetStat(PetStat petStat);

    // Read --------------------------------
    /* Pet 엔티티 검색 */
    Pet findById(Long pet_id);
    Pet findByNickname(String nickname);
    PetInfo findByInfoNickname(String nickname);
    PetStat findByStatNickname(String nickname);
    List<PetInfo> graduatePetList(String nickname);

    /* PetStat 엔티티 검색 */
    PetStat findStatById(Long pet_id);

    /* PetInfo 엔티티 검색 */
    PetInfo findInfoById(Long pet_id);
}
