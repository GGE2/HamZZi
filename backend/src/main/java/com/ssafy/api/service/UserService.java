package com.ssafy.api.service;


import com.ssafy.api.request.UserFcmTokenRequest;
import com.ssafy.api.request.UserNicknameRequest;
import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.db.entity.User.User;
import com.ssafy.db.entity.User.UserProfile;




public interface UserService {



    Long registerUser(UserRegisterRequest registerInfo);

    void regiserFcmToken(UserFcmTokenRequest fcmTokenRequest);
    void registerNickname(UserNicknameRequest nicknameInfo);
    UserProfile loginUserData(String email);
    void deleteUser(String email);


}
