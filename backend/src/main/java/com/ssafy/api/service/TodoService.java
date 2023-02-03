package com.ssafy.api.service;

import com.ssafy.api.request.TodoRequest;
import com.ssafy.db.entity.Todo.Todo;
import com.ssafy.db.entity.User.UserProfile;

import java.util.List;

public interface TodoService {
    
    // todo 가지고 오기
    List<Todo> getTodos(String nickname);
    
    // todo 생성
    Todo createTodo(TodoRequest todoReq);

    Long updateTodo(Todo todo);

    void deleteTodo(Long id);

    
    // point 계산
    UserProfile pointAssignment(int point, int rest_point);
}
