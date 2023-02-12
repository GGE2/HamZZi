package com.ssafy.api.service;

import com.ssafy.api.request.TodoRequest;
import com.ssafy.db.entity.Count.CountWeekly;
import com.ssafy.db.entity.Todo.Todo;
import com.ssafy.db.entity.User.UserProfile;
import com.ssafy.db.repository.CountRepository;
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

    @Autowired
    CountRepository countRepo;

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

    @Override
    public List<Todo> searchTodo(String nickname, String content) {
        return todoRepo.searchTodo(nickname, content);
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
    public Todo updateTodo(TodoRequest todoInfo, Long id) {
        Todo updatetodo = todoRepo.findById(id);
        String content = todoInfo.getContent();
        updatetodo.setContent(content);

        System.out.println(updatetodo.toString());
        todoRepo.saveTodo(updatetodo);
        return updatetodo;
    }

    // todo check
    @Override
    public Todo checkUpdateTodo(String nickname, Long id) {
        Todo todo = todoRepo.findById(id);
        Boolean ischeck = todo.getIscheck();
        todo.setIscheck(!ischeck);
        pointAssignment(nickname);      // point 계산(현재 rest_point가 1이상이면 계산 잘됨)
        todoRepo.saveTodo(todo);
        return todo;
    }

    // todo 삭제
    @Override
    public void deleteTodo(Long id) {
        todoRepo.removeTodo(id);
    }

    // todo 완료 시 rest_point가 0이상이면 point에 1을 더해줌
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
        userRepo.saveUserProfile(userProfile);

        // todo 완료 시 weekly todo 포인트 올라가기
        CountWeekly countWeekly = countRepo.findByWeeklyNickname(nickname);
        int todo = countWeekly.getTodo();
        countWeekly.setTodo(todo + 1);
        countRepo.saveWeekly(countWeekly);

        return userProfile;
    }


}


