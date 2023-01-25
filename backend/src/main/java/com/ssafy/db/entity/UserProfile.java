package com.ssafy.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERPROFILE")
@Getter @Setter
public class UserProfile {

    @Id
    @Column(name = "User_Nickname")
    private String user_nickname;

    @Column(name = "User_ID")
    private Long user_id;       // User.java의 User_ID와 FK관계

    private int point;
}
