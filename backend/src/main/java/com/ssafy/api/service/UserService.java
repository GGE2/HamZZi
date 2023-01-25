package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterRequest;


public interface UserService {

    UserPrivacy registerUser(UserRegisterRequest registerInfo);
}
