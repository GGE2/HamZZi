package com.ssafy.db.entity.Todo;

import com.ssafy.db.entity.User.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Todo {

    @Id
    private Long todo_id;

    @OneToOne
    @JoinColumn(name="USER_NICKNAME")
    private UserProfile userProfile;

    @Column(nullable = false)
    private String title;       // title 추가
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private boolean ischeck;
    private LocalDateTime datetime;

    public Todo(){
    }

    public Todo(String title, String content){
        this.title = title;
        this.content = content;
    }
}
