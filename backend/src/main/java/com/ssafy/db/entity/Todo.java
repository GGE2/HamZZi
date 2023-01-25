package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "TODO")
@Getter @Setter
public class Todo {

    @Id
    private int todo_id;

    @Column(name = "User_Nickname")
    private String user_nickname;       // UserProfile.java의 User_Nickname와 FK관계

    private String content;
    private boolean ischeck;
    private LocalDateTime date;
}
