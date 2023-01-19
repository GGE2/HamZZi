package com.ssafy.db.repository;

import com.ssafy.db.entity.User.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserLogin, String> {

    Optional<UserLogin> findByEmail(String email);
}
