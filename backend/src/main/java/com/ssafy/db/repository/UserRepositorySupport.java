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
    public void save(User user) {em.persist(user);}

    // Delete ------------------------------
    @Override
    public void remove(Long user_id) {em.remove(findById(user_id));}

    // Read --------------------------------
    // 사용자의 개인정보(로그인 정보) 리턴
    @Override
    public User findById(Long user_id) {
        return em.find(User.class, user_id);
    }

    // 사용자의 게임 내 정보 리턴
    @Override
    public UserProfile findByNickname(String nickname) {
        return em.find(UserProfile.class, nickname);
    }

    // 사용자의 게임 닉네임 리턴(UserProfile FK 찾기)
    @Override
    public String findNicknameById(Long user_id) {
        return em.f
    }

//    @Autowired
//    private JPAQueryFactory jpaQueryFactory;
//
//    QUser qUser = QUser.user;
//    QUserPrivacy qUserPrivacy = QUserPrivacy.userPrivacy;
//    QUserProfile qUserProfile = QUserProfile.userProfile;
//
//    public Optional<UserPrivacy> findByEmail(String email) {
//        UserPrivacy userPrivacy = jpaQueryFactory.select(qUserPrivacy).from(qUserPrivacy)
//                .where(qUserPrivacy.email.eq(email)).fetchOne();
//        if(userPrivacy == null) return Optional.empty();
//        return Optional.ofNullable(userPrivacy);
//    }
//
//    public String findNicknameById(Long user_id) {
//        String nickname = jpaQueryFactory.select(qUserProfile.nickname).from(qUserProfile)
//                .where(qUserProfile.user_id.eq(qUserProfile.user_id)).fetchOne();
//        return nickname;
//    }
//
//    public int findPointByNick(String nickname) {
//        int point = jpaQueryFactory.select(qUserProfile.point).from(qUserProfile)
//                .where(qUserProfile.nickname.eq(nickname)).fetchOne();
//        return point;
//    }
}
