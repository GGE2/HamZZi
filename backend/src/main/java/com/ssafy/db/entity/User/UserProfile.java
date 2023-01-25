package com.ssafy.db.entity.User;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nickname;

    @Column(nullable = false)
    private int point;
}
