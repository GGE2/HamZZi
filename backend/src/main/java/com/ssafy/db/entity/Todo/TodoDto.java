package com.ssafy.db.entity.Todo;

import com.ssafy.db.entity.User.UserProfile;
import lombok.*;

import java.time.LocalDateTime;

/*
Dto 클래스는 특별한 로직을 가지지 않는 순수한 데이터 객체로,
계층 간 데이터 교환 역할을 하기 때문에 DB에서 꺼낸 Entity를
가져와 앞서 만든 toDto()를 통해 Dto로 변환하여 데이터를 교환하면 된다.
이 역시 마찬가지로 Entity로 변환하기 위해 toEntity()를 구현

DB 테이블에 칼럼으로 생성되지 않는 필드의 경우 @Transient 적용(임시로 중간 값을 저장하는 용도로 사용가능)
 */

@Data
@NoArgsConstructor      // 매개변수를 받지 않는 생성자 생성
@AllArgsConstructor     // 생성자 자동 생성
@ToString
@Builder        // 객체를 생성할 수 있도록 처리
public class TodoDto {
    private Long todo_id;
    private String content;
    private String datetime;
    private Boolean ischeck;
    private UserProfile userProfile;

    public TodoEntity toEntity() {
        return TodoEntity.builder()
                .todo_id(todo_id)
                .content(content)
                .datetime(datetime)
                .ischeck(ischeck)
                .userProfile(userProfile)
                .build();
    }
}