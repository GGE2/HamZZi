package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.db.entity.User.UserPrivacy;


public interface UserService {

    UserPrivacy registerUser(UserRegisterRequest registerInfo);
}
