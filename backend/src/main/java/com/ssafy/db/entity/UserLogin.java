package com.ssafy.db.entity;

import lombok.Data;

@Data
public class UserLogin {
    private int user_id;
    private String email;
    private String password;
    private String telephone;
    private String name;
    private char gender;
}
