package com.ssafy.db.entity.Item;

import lombok.Getter;
import lombok.Setter;

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

    private String nickname;

}
