//package com.ssafy.db.entity.Quest;
//
//import com.ssafy.db.entity.User.UserProfile;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//
//@Getter @Setter
//@Entity
//public class QuestRequirement {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long questReq_id;
//
//    @OneToOne
//    @JoinColumn(name="quest_id")
//    private Quest quest_id;               // Quest.java의 Quest_ID와 FK관계
//
//    private String requirement;
//
//}
