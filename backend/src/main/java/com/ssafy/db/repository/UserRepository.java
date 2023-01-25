package com.ssafy.db.repository;

import com.ssafy.db.entity.User.User;
import com.ssafy.db.entity.User.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.Optional;

@Repository

public interface UserRepository {

    private final EntityManager em;

    // Create, Update ----------------------
    void save(User user) {}

    // Delete ------------------------------
    void remove(Long user_id);

    // Read --------------------------------
    // 사용자의 개인정보(로그인 정보) 리턴
    User findById(Long user_id);
    // 사용자의 게임 내 정보 리턴
    UserProfile findByNickname(String nickname);

    // 사용자의 게임 닉네임 리턴(UserProfile FK 찾기)
    String findNicknameById(Long user_id);

//    Optional<User> findByEmail(String email);

}
