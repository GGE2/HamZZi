package com.ssafy.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class UserLogin extends User{

    @Column(name = "email", unique = true)
    private String email;
    private String password;
    private String telephone;
    private String name;
    private char gender;
}
