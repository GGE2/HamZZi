package com.ssafy.api.service;

import com.ssafy.api.request.TodoRequest;
import com.ssafy.db.entity.Todo.Todo;
import com.ssafy.db.entity.User.UserProfile;

import java.util.List;

public interface TodoService {

    Todo todoData(Long id);

    // todo 가지고 오기
    List<Todo> getTodos(String nickname, String datetime);
    
    // todo 생성
    Todo createTodo(TodoRequest todoInfo);

    // todo 수정
    Todo updateTodo(TodoRequest todoInfo, Long id);

    // todo check
    Todo checkUpdateTodo(Long id);

    // todo 삭제
    void deleteTodo(Long id);

    // point 계산
    UserProfile pointAssignment(String nickname);

}
