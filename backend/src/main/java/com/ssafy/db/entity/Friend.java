package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FRIEND")
@Getter @Setter
public class Friend {

    @Id
    private char relation;

    @Column(name = "User_Nickname")
    private String user_nickname;           // UserProfile.java의 User_Nickname와 FK관계

    // friend_nickname은 어떻게 가져올지 생각해봐야함

}
