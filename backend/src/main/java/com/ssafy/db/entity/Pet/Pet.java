package com.ssafy.db.entity.Pet;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
//Pet의 기본 속성 - 직접 입력받는건 pet name밖에 없음
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pet_id;

    // 펫 주인의 닉네임
    @Column(name = "USER_NICKNAME")
    private String nickname;

    // 펫 이름
    private String pet_name;

    private LocalDate create_date;

    @Column(nullable = true)
    private LocalDate graduate_date;

    @ColumnDefault("false")
    private boolean is_graduate;

}
