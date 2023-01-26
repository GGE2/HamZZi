package com.ssafy.db.entity.Achievement;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Achievement {

    @Id
    @GeneratedValue
    @Column(name = "ACHIEVEMENT_ID")
    private Long ach_id;

    private String title;
    private String content;
}
