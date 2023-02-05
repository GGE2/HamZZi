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

    @ManyToOne
    @JoinColumn(name="user_nickname")
    private UserProfile userProfile;      // UserProfile.java의 User_Nickname와 FK관계

//    @OneToOne
//    @JoinColumn(name="USER_NICKNAME")
//    private UserProfile friendProfile;        // findById 사용하면 쉽다는데 https://bcp0109.tistory.com/325

}
