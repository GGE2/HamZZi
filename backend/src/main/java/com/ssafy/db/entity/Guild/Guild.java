package com.ssafy.db.entity.Guild;

import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Guild {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "guild_id")
    private Long guild_id;

    @Column(unique = true, name = "guild_name")
    private String guild_name;


    // Guild 정원
    @ColumnDefault(value = "1")
    private int personnel;

    // Guild Admin
    private String admin_nickname;
}
