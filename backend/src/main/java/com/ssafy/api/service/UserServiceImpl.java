package com.ssafy.api.service;



import com.ssafy.api.request.UserFcmTokenRequest;
import com.ssafy.api.request.UserNicknameRequest;
import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.db.entity.User.User;
import com.ssafy.db.entity.User.UserProfile;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepo;

    @Override
    public Long registerUser(UserRegisterRequest registerInfo) {
        User user = new User();
        user.setDtype("User");
        user.setEmail(registerInfo.getEmail());
        user.setUid(registerInfo.getUid());

        System.out.println(user.toString());
        userRepo.saveUser(user);
        return user.getUser_id();
    }

    @Override
    public void regiserFcmToken(UserFcmTokenRequest fcmTokenRequest) {

        userRepo.insertFcmToken(fcmTokenRequest.getEmail(),fcmTokenRequest.getToken());

    }

    @Override
    public void registerNickname(UserNicknameRequest nicknameInfo) {
        User user = userRepo.findById(nicknameInfo.getUser_id());
        UserProfile userProfile = new UserProfile();

        userProfile.setNickname(nicknameInfo.getNickname());
        user.setUserProfile(userProfile);

        userRepo.saveUserProfile(userProfile);
        userRepo.saveUser(user);
    }


    @Override
    public UserProfile loginUserData(String email) {
        Long user_id = userRepo.findIdByEmail(email);
        String nickname = userRepo.findNicknameById(user_id);
        return userRepo.findByNickname(nickname);
    }

//    // 수정할 수 없는 항목 주석처리 - UpdateReq에서도 수정필요
//    @Override
//    public Long updateUser(Long user_id, UserUpdateRequest updateInfo) {
//        User user = userRepo.findById(user_id);
//
//        user.setPassword(updateInfo.getPassword());
//        user.setTelephone(updateInfo.getTelephone());
//        user.setName(updateInfo.getName());
//        user.setGender(updateInfo.getGender());
//
//        userRepo.saveUser(user);
//        return user.getUser_id();
//    }

    @Override
    public void deleteUser(String email) {
        Long user_id = userRepo.findIdByEmail(email);

        userRepo.removeUserProfile(user_id);
        userRepo.removeUser(user_id);


    }
}
