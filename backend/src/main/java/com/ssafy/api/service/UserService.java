package com.ssafy.api.service;

import com.ssafy.db.entity.UserLogin;
import org.springframework.http.ResponseEntity;


public interface UserService {

    ResponseEntity registerUser(UserLogin user);
}
