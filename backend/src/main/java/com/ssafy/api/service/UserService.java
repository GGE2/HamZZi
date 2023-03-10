package com.ssafy.api.service;



import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.api.request.UserTokenRequest;
import com.ssafy.db.entity.User.User;
import com.ssafy.db.entity.User.UserProfile;

import java.util.Map;


public interface UserService {

    User registerFcm(UserTokenRequest tokenInfo);
    boolean CheckUid(String email);

    User selectUser(String uid);
    User registerUser(UserRegisterRequest registerInfo);
    User registerNickname(String email, String nickname);
    UserProfile loginUserData(String email);
    void deleteUser(String email);


}
