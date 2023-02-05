package com.ssafy.api.service;

import com.ssafy.api.request.TodoRequest;
import com.ssafy.db.entity.Todo.Todo;
import com.ssafy.db.entity.User.UserProfile;
import com.ssafy.db.repository.TodoRepository;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    @Autowired
    TodoRepository todoRepo;

    @Autowired
    UserRepository userRepo;

    @Override
    public Todo todoData(Long todo_id) {
        Todo todo = todoRepo.findById(todo_id);

        return todo;
    }

    // todo 리스트 가져오기(작성자와 날짜가 동일할 때)
    @Override
    public List<Todo> getTodos(String nickname, String datetime) {
        List<Todo> todos = todoRepo.todoList(nickname, datetime);
        List<Todo> todoList = new ArrayList<>();
        todoList.addAll(todos);

        return todoList;
    }

    // todo 생성
    @Override
    public Todo createTodo(TodoRequest todoInfo) {
        Todo todo = new Todo();
        UserProfile userProfile = userRepo.findByNickname(todoInfo.getUser_nickname());

        //fireBase에서 UserProfile 가져오기
        todo.setNickname(userProfile.getNickname());
        todo.setContent(todoInfo.getContent());
        todo.setDatetime(todoInfo.getDatetime());
        todo.setIscheck(false);

        todoRepo.saveTodo(todo);

        return todo;
    }
    
    // todo 수정
    @Override
    public Todo updateTodo(Long id) {
        Todo updatetodo = todoRepo.findById(id);
        String content = updatetodo.getContent();
        updatetodo.setContent(content);

        System.out.println(updatetodo.toString());
        todoRepo.saveTodo(updatetodo);
        return updatetodo;
    }

    // todo check
    @Override
    public Todo checkUpdateTodo(Long id) {
        Todo todo = todoRepo.findById(id);
        Boolean ischeck = todo.getIscheck();
        todo.setIscheck(!ischeck);
        todoRepo.saveTodo(todo);
        return todo;
    }

    // todo 삭제
    @Override
    public void deleteTodo(Long id) {
        todoRepo.removeTodo(id);
    }

    // todo 완료 시 rest_point가 3이상이면 point에 1을 더해줌
    @Override
    public UserProfile pointAssignment(String nickname) {
        UserProfile userProfile = userRepo.findByNickname(nickname);

        int nowPoint = userProfile.getPoint();
        int nowRestPoint = userProfile.getRest_point();

        if (userProfile.getRest_point() > 0){
            nowPoint++;
            nowRestPoint--;
        }
        userProfile.setPoint(nowPoint);
        userProfile.setRest_point(nowRestPoint);

        return userProfile;
    }


}


