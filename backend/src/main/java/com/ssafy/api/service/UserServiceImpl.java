package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.db.entity.User.UserLogin;
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
    UserRepository userRepository;

    @Override
    public UserLogin registerUser(UserRegisterRequest registerInfo) {
        UserLogin newUser = new UserLogin();
//      newUser.setUser_id(registerInfo.getUser_id());
        newUser.setEmail(registerInfo.getEmail());
        newUser.setPassword(registerInfo.getPassword());
        newUser.setTelephone(registerInfo.getTelephone());
        newUser.setName(registerInfo.getName());
        newUser.setGender(registerInfo.getGender());
        return userRepository.save(newUser);
    }
}
