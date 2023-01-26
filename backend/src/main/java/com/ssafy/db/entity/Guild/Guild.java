package com.ssafy.db.entity.Guild;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Guild {

    @Id
    @GeneratedValue
    @Column(name = "GUILD_ID")
    private Long guild_id;
}
