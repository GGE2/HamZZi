package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PET")
@Getter @Setter
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Pet_ID")
    private Long pet_id;

    @Column(name = "User_Nickname")
    private String user_nickname;           // UserProfile.java의 User_Nickname와 FK관계

    private String pet_name;
    private String behavior;
    private int exp;
    private int level;
    private String type;
    private LocalDateTime create_date;      // 이거 맞는지 찾아봐야함
    private boolean graduated;

}
