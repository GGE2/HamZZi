package com.ssafy.api.service;

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
        user.setGender(registerInfo.getGender());

        userRepo.save(user);
        return user.getUser_id();
    }

    @Override
    public UserProfile loginUserData() {
        return null;
    }

//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public UserPrivacy registerUser(UserRegisterRequest registerInfo) {
//        UserPrivacy newUser = new UserPrivacy();
////      newUser.setUser_id(registerInfo.getUser_id());
//        newUser.setEmail(registerInfo.getEmail());
//        newUser.setPassword(registerInfo.getPassword());
//        newUser.setTelephone(registerInfo.getTelephone());
//        newUser.setName(registerInfo.getName());
//        newUser.setGender(registerInfo.getGender());
//        return userRepository.save(newUser);
//    }
}
