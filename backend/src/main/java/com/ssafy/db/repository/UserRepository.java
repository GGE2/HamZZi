package com.ssafy.db.repository;


import com.ssafy.db.entity.User.User;
import com.ssafy.db.entity.User.UserProfile;
import org.springframework.stereotype.Repository;

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
    Long findIdByEmail(String email);

    String findNicknameById(Long user_id);

    // 사용자의 게임 내 정보 리턴(ID, nickname)
    UserProfile findByNickname(String nickname);

}
