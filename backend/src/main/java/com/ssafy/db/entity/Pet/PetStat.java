package com.ssafy.db.entity.Pet;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Entity
//Pet의 스탯 - type에 영향을 주는 속성
public class PetStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_stat_id")
    private Long petStat_id;

    @OneToOne
    @JoinColumn(name="pet_id")
    private Pet pet;

    //TYPE 1
    @ColumnDefault(value = "0")
    private int physical;

    //TYPE 2
    @ColumnDefault(value = "0")
    private int artistic;

    //TYPE 3
    @ColumnDefault(value = "0")
    private int intelligent;

    //TYPE 4
    @ColumnDefault(value = "0")
    private int inactive;

    //TYPE 5
    @ColumnDefault(value = "0")
    private int energetic;

    //TYPE 6
    @ColumnDefault(value = "0")
    private int etc;
}
