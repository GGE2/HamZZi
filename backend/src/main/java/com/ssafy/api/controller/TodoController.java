package com.ssafy.api.controller;

import com.ssafy.db.entity.Todo.Todo;
import com.ssafy.db.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/todo")
public class TodoController {
    private TodoRepository todoRep;

    @Autowired
    public TodoController(TodoRepository todoRep){
        this.todoRep = todoRep;
    }

    @GetMapping
    public Iterable<Todo> list(){
        return todoRep.findAll();       // todo리스트 가지고 오기
    }
    
    @GetMapping(value = "/{id}")     // id로 todo값 가지고 오기
    public Optional<Todo> findOne(@PathVariable Long id){
        return todoRep.findById(id);
    }

    @PostMapping
    public Todo put(@RequestParam String title, @RequestParam String content){
        return todoRep.save(new Todo(title, content));      // 글 등록
    }

    @PutMapping(value = "/{id}")
    public Todo update(@PathVariable Long id, @RequestParam String title, @RequestParam String content){
        Optional<Todo> todo = todoRep.findById(id);
        todo.get().setTitle(title);
        todo.get().setContent(content);
        return todoRep.save(todo.get());
    }

    @DeleteMapping
    public void delete(@RequestParam Long id){
        todoRep.deleteById(id);
    }
}
