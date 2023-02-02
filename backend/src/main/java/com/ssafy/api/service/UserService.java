package com.ssafy.api.service;


import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.api.request.UserTokenRequest;
import com.ssafy.db.entity.User.User;
import com.ssafy.db.entity.User.UserProfile;




public interface UserService {



    Long registerUser(UserRegisterRequest registerInfo);
    void registerNickname(UserTokenRequest tokenInfo, String nickname);

    User registerFcm(UserTokenRequest tokenInfo);
    boolean CheckUid(UserTokenRequest tokenInfo);

    UserProfile loginUserData(String email);
    void deleteUser(String email);


}
