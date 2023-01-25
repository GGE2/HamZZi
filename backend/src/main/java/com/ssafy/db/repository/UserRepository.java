package com.ssafy.db.repository;

import com.ssafy.db.entity.User.User;
import com.ssafy.db.entity.User.UserProfile;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    // Create, Update ----------------------
    void save(User user);

    // Delete ------------------------------
    void remove(Long user_id);

    // Read --------------------------------
    // 사용자의 개인정보(로그인 정보) 리턴(ID, email)
    User findById(Long user_id);
    User findByEmail(String email);

    // 사용자의 게임 내 정보 리턴(ID, nickname)
    UserProfile findByNickname(String nickname);
    UserProfile findUPById(Long user_id);
}
