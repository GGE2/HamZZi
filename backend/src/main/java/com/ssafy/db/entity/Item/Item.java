package com.ssafy.db.entity.Item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Item {

    @Id
    @Column(nullable = false)
    private int item_id;        // 입력 받기

    @Column(nullable = false)
    private int cost;           // 아이템 가격

    @Column(nullable = false)
    private int level;          // level 제한

    @Column(nullable = false)
    private String type;        // 1: 모자, 2: 옷, 3: 배경
}
