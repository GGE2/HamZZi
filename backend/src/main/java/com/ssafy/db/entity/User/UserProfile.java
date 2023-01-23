package com.ssafy.db.entity.User;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class UserProfile extends User {

    private String nickname;

    @Column(nullable = false)
    private int point;
}
