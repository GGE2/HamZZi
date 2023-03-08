package com.ssafy.db.entity.Count;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CountDaily {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long daily_id;

    @Column(nullable = false)
    private String nickname;
    private int todo;
    private int quest;
}
