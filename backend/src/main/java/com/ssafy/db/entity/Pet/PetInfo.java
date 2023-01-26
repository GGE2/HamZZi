package com.ssafy.db.entity.Pet;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
//Pet의 외관/동작에 영향을 주는 속성
public class PetInfo {

    @Id
    @Column(name = "PET_ID")
    private Long pet_id;

    //스탯에 따라 변하는 외형
    private int type;

    //Pet이 지금 취하고 있는 동작(anim 호출 번호)
    private int behavior;

    private int exp;

    private int level;
}