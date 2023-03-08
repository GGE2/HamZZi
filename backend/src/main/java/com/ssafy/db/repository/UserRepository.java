package com.ssafy.db.repository;


import com.ssafy.db.entity.User.User;
import com.ssafy.db.entity.User.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    // Create, Update ----------------------
    void saveUser(User user);


    void saveUserProfile(UserProfile userProfile);

    // Delete ------------------------------
    void removeUser(Long user_id);
    void removeUserProfile(Long user_id);

    // Read --------------------------------
    // 사용자의 개인정보(로그인 정보) 리턴(ID, email)
    User findById(Long user_id);

    User findByUid(String uid);

    Long findIdByEmail(String email);

    String findNicknameById(Long user_id);

    // 사용자의 게임 내 정보 리턴(ID, nickname)
    UserProfile findByNickname(String nickname);

    // 모든 사용자의 이메일 리스트
    List<String> findEmailList();


}
