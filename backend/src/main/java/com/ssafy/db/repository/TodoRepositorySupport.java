package com.ssafy.db.repository;

import com.ssafy.db.entity.Todo.Todo;
import com.ssafy.db.entity.User.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository @Primary
@RequiredArgsConstructor
public class TodoRepositorySupport implements TodoRepository {

    private final EntityManager em;

    @Override
    public void saveTodo(Todo todo){
        em.persist(todo);
    }

    @Override
    public void removeTodo(Long todo_id){
        em.remove(findById(todo_id));
    }
    
    // id 찾기
    @Override
    public Todo findById(Long todo_id) {
        return em.find(Todo.class, todo_id);
    }

    // 사용자의 ID, 닉네임 리턴
    @Override
    public UserProfile findByNickname(String nickname){
        return em.find(UserProfile.class, nickname);
    }

    // content 수정을 위한 content 받아 오기
    @Override
    public Todo findByContent(String content) {
        return em.find(Todo.class, content);
    }

    // point, rest_point
    @Override
    public UserProfile findByPoint(int point) {
        return em.find(UserProfile.class, point);
    }

    @Override
    public UserProfile findByRestPoint(int rest_point) {
        return em.find(UserProfile.class, rest_point);
    }



}
