package com.ssafy.api.service;

import com.ssafy.db.entity.Todo.TodoDto;

import java.util.List;

public interface TodoService {
    List<TodoDto> getTodos() throws Exception;

    Long postTodo(TodoDto todoDto) throws Exception;

    Long updateTodo(TodoDto todoDto) throws Exception;

    void deleteTodo(Long id) throws Exception;

    TodoDto findTodoById(Long id) throws Exception;
}
