package com.ssafy.api.service;


import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.api.request.UserTokenRequest;
import com.ssafy.db.entity.User.User;
import com.ssafy.db.entity.User.UserProfile;




public interface UserService {

    User registerFcm(UserTokenRequest tokenInfo);
    User CheckUid(String email);

    User registerUser(UserRegisterRequest registerInfo);
    User registerNickname(String email, String nickname);

    UserProfile loginUserData(String email);
    void deleteUser(String email);


}
