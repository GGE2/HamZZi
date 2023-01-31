package com.ssafy.db.entity.User;

import lombok.Getter;
import lombok.Setter;

<<<<<<< HEAD
import javax.persistence.*;
=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
>>>>>>> login

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

<<<<<<< HEAD
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = true)
    private String password;

    @Column(nullable = true)
    private String telephone;

    @Column(nullable = true)
    private String name;

    @OneToOne
    @JoinColumn(name="user_nickname")
    private UserProfile userProfile;

=======
>>>>>>> login
}
