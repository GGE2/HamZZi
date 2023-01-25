package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "GUILD")
@Getter @Setter
public class Guild {

    @Id
    @GeneratedValue
    @Column(name = "Guild_ID")
    private Long guild_id;
}
