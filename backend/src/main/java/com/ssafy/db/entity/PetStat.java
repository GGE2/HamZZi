package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

// @Entity를 하면 Id를 설정해야해서 일단 안넣음
@Getter @Setter
@Table(name = "PETSTAT")
public class PetStat {

    @Column(name = "Pet_ID")
    private Long pet_id;        // Pet.java의 Pet_ID와 FK관계

    private int physical;
    private int artistic;
    private int intelligent;
    private int inactive;
    private int energetic;
    private int etc;
}
