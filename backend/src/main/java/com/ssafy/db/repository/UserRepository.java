package com.ssafy.db.repository;

import com.ssafy.db.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserLogin, String> {

}
