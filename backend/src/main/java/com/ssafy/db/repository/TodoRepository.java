package com.ssafy.db.repository;

import com.ssafy.db.entity.Todo.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
/*
SpringBoot에서 Entity의 기본적인 CRUD가 가능하도록
JpaRepository Interface를 제공하는데 이를 상속하여 만든 인터페이스
JpaRepository<Entity 클래스 이름, Id 필드 타입> 으로 작성하여 생성
 */