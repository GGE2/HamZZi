package com.ssafy.db.entity.User;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    /* for Firebase */
    @Column(nullable = false)
    private String dtype;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String uid;

    @Column(nullable = true)
    private String fcmToken;

    public User(String email, String uid) {
        this.email = email;
        this.uid = uid;
    }
    public User(){}

    @OneToOne
    @JoinColumn(name="user_nickname")
    private UserProfile userProfile;


}
