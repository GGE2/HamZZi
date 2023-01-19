package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.db.entity.UserLogin;
import org.springframework.http.ResponseEntity;


public interface UserService {

    UserLogin registerUser(UserRegisterRequest registerInfo);
}
