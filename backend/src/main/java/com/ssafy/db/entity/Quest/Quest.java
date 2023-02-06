package com.ssafy.db.entity.Quest;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Quest {

    @Id
    @GeneratedValue
    private Long quest_id;

    @Column(nullable = false)
    private String content;

    @ColumnDefault("0")
    private int point;

    private String type;
}
