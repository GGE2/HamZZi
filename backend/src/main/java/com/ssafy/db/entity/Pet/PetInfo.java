package com.ssafy.db.entity.Pet;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Entity
//Pet의 외관/동작에 영향을 주는 속성
public class PetInfo {

    @Id
    @Column(name = "pet_info_id")
    private Long petInfo_id;

    @OneToOne
    @JoinColumn(name="pet_id")
    private Pet pet;

    //스탯에 따라 변하는 외형
    private int type;

    //Pet이 지금 취하고 있는 동작(anim 호출 번호)
    private int behavior;

    @Column(nullable = true)
    private int exp;

    @ColumnDefault("1")
    private int level;
}