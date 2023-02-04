package com.ssafy.db.repository;

import com.ssafy.api.request.TodoRequest;
import com.ssafy.db.entity.Todo.Todo;
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

    Todo findByNickname(String nickname);
    List<Todo> todoList(String nickname);

}
