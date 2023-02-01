package com.ssafy.db.entity.User;


import com.ssafy.db.entity.Pet.Pet;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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

    @ColumnDefault("0")
    private int point;
    private int rest_point;

}
