package com.ssafy.db.repository;

import com.ssafy.db.entity.User.User;
import com.ssafy.db.entity.User.UserPrivacy;
import com.ssafy.db.entity.User.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<UserPrivacy> findByEmail(String email);
    String findNicknameById(Long user_id);
}
