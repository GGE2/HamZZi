//package com.ssafy.api.service;
//
//import com.ssafy.db.entity.Todo.Todo;
//import com.ssafy.db.repository.TodoRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.List;
//
///*
//@Transactional이 수행되는 계층이다.
//@RequiredArgsConstructor를 사용하는 이유는
//@Autowired로 Repository를 생성자 주입(필드 주입방식)을 하는 것은
//권장하지 않는 방법이라하여 이를 해결하고자 final 필드를 자동으로
//주입해주는 @RequiredArgsConstructor를 사용하였다.
// */
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class TodoServiceImpl implements TodoService{
//
//    private final TodoRepository repository;
//
//    //    @Override
////    public List<TodoDto> getTodos() throws Exception {
////        List<TodoEntity> entityList = repository.findAll();
////        List<TodoDto> dtoList = new ArrayList<>();
////        // user_nickname과 로그인한 유저 확인 if문으로 하면 될듯
////        for (TodoEntity entity : entityList) {
////            dtoList.add(entity.toDto());
////        }
////        return dtoList;
////    }
//    @Override
//    public List<TodoDto> getTodos() throws Exception {
//        List<Todo> entityList = repository.findUserProfileByNickname();
//        List<TodoDto> dtoList = new ArrayList<>();
//        // user_nickname과 로그인한 유저 확인 if문으로 하면 될듯
//        for (Todo entity : entityList) {
//            dtoList.add(entity.toDto());
////            if (entity.getUserProfile().getNickname().equals(repository.findUserProfileByNickname)){
////                dtoList.add(entity.toDto());
////            }
//        }
//        return dtoList;
//    }
//
//    @Override
//    @Transactional
//    public Long postTodo(TodoDto todoDto) throws Exception {
//        return repository.save(todoDto.toEntity()).getTodo_id();
//    }
//
//    @Override
//    @Transactional
//    public Long updateTodo(TodoDto updateTodo) throws Exception {
//        return repository.save(updateTodo.toEntity()).getTodo_id();
//    }
//
//    @Override
//    @Transactional
//    public void deleteTodo(Long id) throws Exception {
//        repository.deleteById(id);
//    }
//
//    @Override
//    public Todo findTodoById(Long id) throws Exception {
//        return repository.findById(id).orElseThrow().toDto();
//    }
//}
//
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
    public List<Todo> getTodos(String nickname) {
        List<Todo> todos = todoRepo.getTodos(nickname);

        return todos;
    }
    
    // 아래부터 하기
    @Override
    public Todo createTodo(TodoRequest todoReq) {
        Todo todo = new Todo();

        todo.setContent(todoReq.getContent());
        todo.setDatetime(todoReq.getDatetiem());

        System.out.println(todo.toString());
        todoRepo.saveTodo(todo);

        return todo;
    }

    @Override
    public Long updateTodo(Todo todo) {
        return null;
    }

    @Override
    public void deleteTodo(Long id) {

    }

    @Override
    public UserProfile pointAssignment(int point, int rest_point) {
        return null;
    }


}


