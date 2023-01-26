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
    private int todo_id;

    @OneToOne
    @JoinColumn(name="USER_NICKNAME")
    private UserProfile userProfile;

    private String content;
    private boolean ischeck;
    private LocalDateTime datetime;
}
