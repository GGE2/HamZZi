package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.db.entity.User.UserLogin;


public interface UserService {

    UserLogin registerUser(UserRegisterRequest registerInfo);
}
