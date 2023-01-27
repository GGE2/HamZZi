package com.ssafy.api.service;

import com.ssafy.api.request.UserNicknameRequest;
import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.db.entity.User.UserProfile;


public interface UserService {

    Long registerUser(UserRegisterRequest registerInfo);

    // 리턴타입 추후 지정
//    void registerNickname(Long user_id, UserNicknameRequest NicknameInfo);

    UserProfile loginUserData(String email);
//    Long updateUser(Long user_id, UserUpdateRequest updateInfo);
    void deleteUser(Long user_id);
}
