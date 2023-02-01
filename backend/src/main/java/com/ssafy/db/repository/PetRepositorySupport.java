package com.ssafy.db.repository;


import com.ssafy.db.entity.Pet.Pet;
import com.ssafy.db.entity.Pet.PetInfo;
import com.ssafy.db.entity.Pet.PetStat;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository @Primary
@RequiredArgsConstructor
public class PetRepositorySupport implements PetRepository {

    private final EntityManager em;

    // Create, Update ----------------------
    public void savePet(Pet pet) { em.persist(pet); }
    public void savePetInfo(PetInfo petInfo) { em.persist(petInfo); }
    public void savePetStat(PetStat petStat) { em.persist(petStat); }

    // Read --------------------------------
    /* Pet 엔티티 검색 */
    @Override
    public Pet findById(Long pet_id) { return em.find(Pet.class, pet_id); }
    @Override
    public Pet findByNickname(String nickname) {
        try { return em.createQuery("select p from Pet p where p.nickname=:nickname and p.is_graduate=:is_graduate", Pet.class)
                .setParameter("nickname", nickname)
                .setParameter("is_graduate", false)
                .getSingleResult();
        } catch (NoResultException e) {return null;}
    }
    @Override
    public List<PetInfo> graduatePetList(String nickname) {
        return em.createQuery("select pi from PetInfo pi LEFT JOIN pi.pet p "
            + "ON p.nickname=:nickname where p.is_graduate=:is_graduate", PetInfo.class)
                .setParameter("nickname", nickname)
                .setParameter("is_graduate", true)
                .getResultList();
    }

    /* PetStat 엔티티 검색 */
    @Override
    public PetStat findStatById(Long pet_id) { return em.find(PetStat.class, pet_id); }

    /* PetInfo 엔티티 검색 */
    @Override
    public PetInfo findInfoById(Long pet_id) { return em.find(PetInfo.class, pet_id); }

}
