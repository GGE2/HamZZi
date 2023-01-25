package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter @Setter
public class GuildUser {

    @Column(name = "User_Nickname")
    private String user_nickname;           // UserProfile.java의 User_Nickname와 FK관계

    @ManyToOne
    @JoinColumn(name = "Guild_ID")          // Guild.java에 Guild_ID와 연결된다.
    private Guild guild;

    private boolean isadmin;
}
