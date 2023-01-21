package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.User.QUserPrivacy;

import com.ssafy.db.entity.User.UserPrivacy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class UserRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QUserPrivacy qUserPrivacy = QUserPrivacy.userPrivacy;

    public Optional<UserPrivacy> findByEmail(String email) {
        UserPrivacy userPrivacy = jpaQueryFactory.select(qUserPrivacy).from(qUserPrivacy)
                .where(qUserPrivacy.email.eq(email)).fetchOne();
        if(userPrivacy == null) return Optional.empty();
        return Optional.ofNullable(userPrivacy);
    }
}
