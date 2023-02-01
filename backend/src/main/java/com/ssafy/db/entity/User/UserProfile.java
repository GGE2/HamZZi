package com.ssafy.db.entity.User;


import com.ssafy.db.entity.Pet.Pet;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class UserProfile {

    @Id
    @Column(name = "user_nickname")
    private String nickname;

    @OneToOne(mappedBy = "userProfile")
    private User user;

    @Column(nullable = false)
    private int point;

    @Column(nullable = false)
    private int rest_point;

}
