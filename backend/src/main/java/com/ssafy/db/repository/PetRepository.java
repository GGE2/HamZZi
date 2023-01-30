package com.ssafy.db.repository;

import com.ssafy.db.entity.Pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepository {

    void savePet(Pet pet);

    Pet findById(Long pet_id);

//    void addStatPoint(@Param("typename") String typename, int point);
}
