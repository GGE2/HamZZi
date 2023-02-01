package com.ssafy.api.controller;

import com.ssafy.api.service.TodoService;
import com.ssafy.api.service.TodoServiceImpl;
import com.ssafy.db.entity.Todo.TodoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/todo")
public class TodoController {

    private final TodoService todoService;

    // 전체 조회
    // 여기서 userProfile의 point랑 rest_point가 잡히면 point줄 수 있음
    // 안뜨면 userController에서 해야함
    @GetMapping
    public ResponseEntity<?> getTodos() throws Exception {
        List<TodoDto> dtoList = todoService.getTodos();
        return ResponseEntity.ok(dtoList);
    }

    //등록
    @PostMapping
    public ResponseEntity<String> postTodo(@RequestBody TodoDto todoDto) throws Exception {
        todoDto.setIscheck(false);
        todoService.postTodo(todoDto);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    @PutMapping("/check/{id}")
    public ResponseEntity<String> putTodo(@PathVariable("id") Long id) throws Exception {
        // ischeck 바꾸기
        TodoDto todoDto = todoService.findTodoById(id);
        Boolean ischeck = todoDto.getIscheck();
        todoDto.setIscheck(!ischeck);
// 여기서 userProfile의 데이터가 안가져와짐
//        // rest_point 1--, point 1++
//        int rest_point = todoDto.getUserProfile().getRest_point();
//        int point = todoDto.getUserProfile().getPoint();
//        if(rest_point > 0){
//            rest_point -= 1;
//            point += 1;
//        }
        todoService.postTodo(todoDto);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    // content 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTodo(@RequestBody TodoDto updateTodo, @PathVariable("id") Long id) throws Exception {
        updateTodo.setContent(updateTodo.getContent());
        todoService.updateTodo(updateTodo);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) throws Exception {
        todoService.deleteTodo(id);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    //단일 조회
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getTodo(@PathVariable("id") Long id) throws Exception {
        TodoDto todo = todoService.findTodoById(id);
        return ResponseEntity.ok(todo);
    }
}
