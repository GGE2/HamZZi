package com.ssafy.api.controller;

import com.ssafy.api.request.TodoRequest;
import com.ssafy.api.service.TodoService;
import com.ssafy.db.entity.Todo.Todo;
import com.ssafy.db.entity.User.UserProfile;
import com.ssafy.db.repository.TodoRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "Todo API", tags = {"Todo"})
@RestController
@RequestMapping("api/todo")
@RequiredArgsConstructor
public class TodoController {

    @Autowired
    TodoService todoService;

    @Autowired
    TodoRepository todoRepository;

    // 작성자와 날짜가 맞는 Todo리스트 조회
    @GetMapping("/{nickname}/{datetime}")
    @ApiOperation(value = "Todo 조회", notes = "Todo 정보를 출력한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> getTodos(@PathVariable String nickname, @PathVariable String datetime){
        List<Todo> todo = todoService.getTodos(nickname, datetime);

        return ResponseEntity.status(200).body(todo);
    }

    // Todo 생성 API
    @PostMapping
    @ApiOperation(value = "Todo 생성", notes = "필요한 정보를 전부 입력한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public Long createTodo(
            @RequestBody @ApiParam(value = "todo 생성 웹", required = true) TodoRequest todoInfo) {
        Todo todo = todoService.createTodo(todoInfo);

        return todo.getTodo_id();
    }

    @PostMapping("/Mobile")
    @ApiOperation(value = "Todo 생성 모바일", notes = "필요한 정보를 전부 입력한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public Todo createTodoM(@RequestBody @ApiParam(value = "todo 생성", required = true) TodoRequest todoInfo) {
        return todoService.createTodo(todoInfo);
    }

    // Todo ischeck 바꾸기(완료) / 계산식 (완료)
    @PutMapping("/check/{nickname}/{id}")
    @ApiOperation(value = "Todo 완료", notes = "해당 Todo를 완료한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "Todo 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String checkTodo(@PathVariable String nickname, @PathVariable Long id){
        Todo todo = todoService.checkUpdateTodo(nickname, id);
        return "IsCheck: " + todo.getIscheck() ;
    }

    // todo content 수정(완료)
    @PutMapping("/{id}")
    @ApiOperation(value = "Todo 수정", notes = "해당 Todo를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "Todo 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String updateTodo(@PathVariable Long id, @RequestBody TodoRequest todoInfo) {
        Todo todo = todoService.updateTodo(todoInfo, id);

        return "Content : " + todo.getContent() ;
    }

    // todo 삭제(완료)
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Todo 삭제", notes = "해당 Todo를 삭제한다.")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return "ID: " + id;
    }

    @GetMapping("/list/search")
    @ApiOperation(value = "Todo 목록 - 이름 검색", notes = "Todo 을 포함하는 전체 목록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public List<Todo> searchTodoByNicknameContent(@RequestParam String nickname, @RequestParam String content) {
        List<Todo> list = todoService.searchTodo(nickname, content);
        int listSize = list.size();
        return list;
    }
}
