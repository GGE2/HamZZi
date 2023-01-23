package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.db.entity.User.UserPrivacy;
import com.ssafy.db.entity.User.UserProfile;


public interface UserService {

    UserPrivacy registerUser(UserRegisterRequest registerInfo);
    UserProfile loginUserData();
}
