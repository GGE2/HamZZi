package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.db.entity.User.UserProfile;


public interface UserService {

    Long registerUser(UserRegisterRequest registerInfo);
    UserProfile loginUserData(String email);
//    Long updateUser(Long user_id, UserUpdateRequest updateInfo);
    void deleteUser(Long user_id);
}
