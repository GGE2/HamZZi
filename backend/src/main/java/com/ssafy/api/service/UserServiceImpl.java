package com.ssafy.api.service;

import com.ssafy.db.entity.UserLogin;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public ResponseEntity registerUser(UserLogin user) {
        //존재하는 user 인지 찾아오기
        Optional<UserLogin> existUser = userRepository.findByEmail(user.getEmail());
        if(existUser.isEmpty()) {
            UserLogin newUser = UserLogin.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .telephone(user.getTelephone())
                    .name(user.getName())
                    .gender(user.getGender())
                    .build();

            userRepository.save(newUser);
            return new ResponseEntity("success", HttpStatus.OK);
        } else {return new ResponseEntity("fail", HttpStatus.OK);}
    }
}
