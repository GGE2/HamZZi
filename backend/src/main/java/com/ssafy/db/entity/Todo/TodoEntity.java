package com.ssafy.db.entity.Todo;

import com.ssafy.db.entity.User.UserProfile;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
DB와 도메인 클래스(Entity)를 연결
기본키 id값, 투두에 들어갈 내용,
작성일자, 완료여부를 투두 테이블에 들어간다.
시작할 때 유저, 유저프로필, 투두 테이블 다 지우고 
*/

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Todo")
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todo_id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String datetime;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean ischeck;

    @ManyToOne
    @JoinColumn(name="user_nickname")
    private UserProfile userProfile;

    public TodoDto toDto() {        //toDto() 메소드는 Entity -> Dto로 변환
        return TodoDto.builder()
                .todo_id(todo_id)
                .content(content)
                .datetime(datetime)
                .ischeck(ischeck)
                .userProfile(userProfile)
                .build();
    }
}