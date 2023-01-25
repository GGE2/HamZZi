package com.ssafy.db.repository;

import com.ssafy.db.entity.User.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
@RequiredArgsConstructor
public class UserRepositorySupport implements UserRepository {

    private final EntityManager em;

    // Create, Update ----------------------
    @Override
    public void save(User user) {
        em.persist(user);
    }

    // Delete ------------------------------
    @Override
    public void remove(Long user_id) {
        em.remove(findById(user_id));
    }

    // Read --------------------------------
    // 사용자의 개인정보(로그인 정보) 리턴(ID, email)
    @Override
    public User findById(Long user_id) {
        return em.find(User.class, user_id);
    }
    @Override
    public User findByEmail(String email) {
        return em.find(User.class, email);
    }

    // 사용자의 게임 내 정보 리턴(ID, nickname)
    @Override
    public UserProfile findByNickname(String nickname) {
        return em.find(UserProfile.class, nickname);
    }
    @Override
    public UserProfile findUPById(Long user_id) {
        return em.find(UserProfile.class, user_id);
    }
}