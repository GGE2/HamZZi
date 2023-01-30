package com.ssafy.db.repository;


import com.ssafy.db.entity.Pet.Pet;
import com.ssafy.db.entity.Pet.PetInfo;
import com.ssafy.db.entity.Pet.PetStat;
import com.ssafy.db.entity.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository @Primary
@RequiredArgsConstructor
public class PetRepositorySupport implements PetRepository {

    private final EntityManager em;

    // Create, Update ----------------------
    public void savePet(Pet pet) { em.persist(pet); }
    public void savePetInfo(PetInfo petInfo) { em.persist(petInfo); }
    public void savePetStat(PetStat petStat) { em.persist(petStat); }

    @Override
    public Pet findById(Long pet_id) { return em.find(Pet.class, pet_id); }
    @Override
    public Pet findByNickname(String nickname) { return em.find(Pet.class, nickname); }

    public PetStat findStatById(Long pet_id) { return em.find(PetStat.class, pet_id); }

//    // 나중에 응답이 필요하면 OK 출력 or true 출력
//    public void addStatPoint(@Param("typename") String typename, int point) {
//        int stat = jpaQueryFactory.select(qPetStat.typename).from(qPetStat);
//        stat += point;
//        stat.s
//    }

}
