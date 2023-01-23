package com.ssafy.db.entity.User;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class UserPrivacy extends User{

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = true)
    private String password;

    @Column(nullable = true)
    private String telephone;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private char gender;
}
