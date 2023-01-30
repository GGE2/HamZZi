package com.ssafy.db.entity.User;

import com.ssafy.db.entity.Pet.Pet;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class UserProfile {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_nickname")
    private String nickname;

    @OneToOne(mappedBy = "userProfile")
    private User user;

    @OneToOne
    @JoinColumn(name="active_pet")
    private Pet pet;

    @Column(nullable = false)
    private int point;
}
