package com.ssafy.api.service;

import com.ssafy.api.request.UserNicknameRequest;
import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.db.entity.User.User;
import com.ssafy.db.entity.User.UserProfile;


public interface UserService {

    Long registerUser(UserRegisterRequest registerInfo);
    void registerNickname(UserNicknameRequest nicknameInfo);
    UserProfile loginUserData(String email);
//    Long updateUser(Long user_id, UserUpdateRequest updateInfo);
    void deleteUser(String email);
}
