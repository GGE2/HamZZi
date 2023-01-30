package com.ssafy.db.repository;


import com.ssafy.db.entity.Pet.Pet;
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

    @Override
    public Pet findById(Long pet_id) { return em.find(Pet.class, pet_id); }

//    // 나중에 응답이 필요하면 OK 출력 or true 출력
//    public void addStatPoint(@Param("typename") String typename, int point) {
//        int stat = jpaQueryFactory.select(qPetStat.typename).from(qPetStat);
//        stat += point;
//        stat.s
//    }
//
//    // 수학적으로 뭔소린지 까먹음.../1.5 왜있더라
//    public void addExp(int exp) {
//        int nowExp = jpaQueryFactory.select(qPetOutfitData.exp).from(qPetOutfitData);
//        int level = jpaQueryFactory.select(qPetOutfitData.level).from(qPetOutfitData);
//
//        int newExp = nowExp + exp;
//        int newLevel = level;
//
//        switch (newLevel) {
//            case 1: if(newExp >= 14) {newLevel++; newExp -= 14;} break;
//            case 2: if(newExp >= 30) {newLevel++; newExp -= 30;} break;
//            case 3: if(newExp >= 60) {newLevel++; newExp -= 60;} break;
//            case 4: if(newExp >= 66) {newLevel++; newExp -= 66;} break;
//            case 5: System.out.println("Max level"); break;
//        }
//
//        jpaQueryFactory.update(qPetOutfitData).from(qPetOutfitData);
//
//    }



}
