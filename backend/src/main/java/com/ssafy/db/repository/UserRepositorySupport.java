package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.User.QUserLogin;

import com.ssafy.db.entity.User.UserLogin;
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
    QUserLogin qUserLogin = QUserLogin.userLogin;

    public Optional<UserLogin> findByEmail(String email) {
        UserLogin userLogin = jpaQueryFactory.select(qUserLogin).from(qUserLogin)
                .where(qUserLogin.email.eq(email)).fetchOne();
        if(userLogin == null) return Optional.empty();
        return Optional.ofNullable(userLogin);
    }
}
