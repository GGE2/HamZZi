package com.ssafy.common.auth;

import com.ssafy.db.entity.User.User;
import com.ssafy.db.entity.User.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginUserData {

//    @Autowired
//    User user;
    @Autowired
    UserProfile user;

    public LoginUserData(UserProfile user) {
        super();
        this.user = user;
    }

    public User getUser() {return this.user;}
    public String get
}
