package com.ssafy.db.entity.Count;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Count {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long count_id;

    @Column(nullable = false)
    private String nickname;
    private int todo;
    private int quest;
}
