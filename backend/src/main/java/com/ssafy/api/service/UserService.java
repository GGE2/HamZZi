package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.db.entity.User.User;
import com.ssafy.db.entity.User.UserProfile;


public interface UserService {

    Long registerUser(UserRegisterRequest registerInfo);
    UserProfile loginUserData();
}
