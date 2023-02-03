package com.ssafy.db.repository;

import com.ssafy.db.entity.Pet.PetInfo;
import com.ssafy.db.entity.Todo.Todo;
import com.ssafy.db.entity.User.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository {
    // Create, Update
    void saveTodo(Todo todo);

    // Delete
    void removeTodo(Long todo_id);

    // Read
    Todo findById(Long todo_id);

    // 사용자의 nickname 리턴
    UserProfile findByNickname(String nickname);

    List<Todo> getTodos(String nickname);

    // content 받아오기
    Todo findByContent(String content);

    // 포인트 계산을 위해 받아 오기
    UserProfile findByPoint(int point);
    UserProfile findByRestPoint(int rest_point);
}
