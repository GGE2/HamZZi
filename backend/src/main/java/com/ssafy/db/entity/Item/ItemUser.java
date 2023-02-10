package com.ssafy.db.entity.Item;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ItemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemUser_id;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(nullable = false)
    private String nickname;

    @ColumnDefault(value = "false")
    @Column(nullable = false)
    private Boolean wear;

}
