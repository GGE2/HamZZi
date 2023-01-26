package com.ssafy.db.entity.Pet;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
//Pet의 스탯 - type에 영향을 주는 속성
public class PetStat {

    @Id
    @Column(name = "PET_ID")
    private Long pet_id;

    //TYPE 1
    private int physical;

    //TYPE 2
    private int artistic;

    //TYPE 3
    private int intelligent;

    //TYPE 4
    private int inactive;

    //TYPE 5
    private int energetic;

    //TYPE 6
    private int etc;
}
