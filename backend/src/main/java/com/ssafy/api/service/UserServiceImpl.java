package com.ssafy.api.service;

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

        user.setEmail(registerInfo.getEmail());
        user.setPassword(registerInfo.getPassword());
        user.setTelephone(registerInfo.getTelephone());
        user.setName(registerInfo.getName());

        userRepo.saveUser(user);
        return user.getUser_id();
    }

    @Override
    public String registerNickname(Long user_id, String nickname) {
        UserProfile user = userRepo.findById(user_id).getUserProfile();
        user.setNickname(nickname);
        userRepo.saveUserProfile(user);

        return user.getNickname();
    }

    @Override
    public UserProfile loginUserData(String email) {
        Long user_id = userRepo.findIdByEmail(email);
        return userRepo.findUserProfileById(user_id);
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
    public void deleteUser(Long user_id) {
        userRepo.removeUser(user_id);
        // 리턴타입 고민좀 해봐야함 스웨거 쓰려면 resentity ??
    }
}
