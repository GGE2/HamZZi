package com.ssafy.db.entity.Friend;

import com.ssafy.db.entity.User.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Friend {

    @Id
    private char relation;

    @OneToOne
    @JoinColumn(name="USER_NICKNAME")
    private UserProfile userProfile;      // UserProfile.java의 User_Nickname와 FK관계

    @OneToOne
    @JoinColumn(name="USER_NICKNAME")
    private UserProfile friendProfile;

}
