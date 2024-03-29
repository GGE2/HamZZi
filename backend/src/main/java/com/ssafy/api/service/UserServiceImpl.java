package com.ssafy.api.service;



import com.ssafy.api.request.UserRegisterRequest;
import com.ssafy.api.request.UserTokenRequest;
import com.ssafy.db.entity.User.User;
import com.ssafy.db.entity.User.UserProfile;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @Override
    public User registerUser(UserRegisterRequest registerInfo) {
        User user = new User();
        user.setEmail(registerInfo.getEmail());
        user.setUid(registerInfo.getUid());

        System.out.println(user.toString());
        userRepo.saveUser(user);
        return user;
    }

    @Override
    public User registerNickname(String email, String nickname) {
        User user = userRepo.findById(userRepo.findIdByEmail(email));
        UserProfile userProfile = new UserProfile();

        userProfile.setNickname(nickname);
        userProfile.setRest_point(3);
        userProfile.setPoint(50);
        user.setUserProfile(userProfile);

        userRepo.saveUserProfile(userProfile);
        userRepo.saveUser(user);

        return user;
    }

    @Override
    public User registerFcm(UserTokenRequest tokenInfo) {
        String email = tokenInfo.getEmail();
        String token = tokenInfo.getFcmToken();

        User user = userRepo.findById(userRepo.findIdByEmail(email));

        user.setFcm_token(token);
        userRepo.saveUser(user);

        return user;
    }

    @Override
    public User selectUser(String uid) {
        User user = userRepo.findByUid(uid);

        return user;
    }

    @Override
    public boolean CheckUid(String email) {
        List<String> emailList = userRepo.findEmailList();
        Map<String, Object> map = new HashMap<>();

        for (int i = 0; i < emailList.size(); i++) {
            if(email.equals(emailList.get(i))) {
                return true;
            }
        }
//        String uid = userRepo.findById(userRepo.findIdByEmail(email)).getUid();
//        if(userRepo.findByUid(uid) == null) {return false;}
        return false;
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
