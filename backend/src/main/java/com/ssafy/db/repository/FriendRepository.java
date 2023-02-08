package com.ssafy.db.repository;

import com.ssafy.db.entity.Friend.Friend;
import com.ssafy.db.entity.User.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository {
    // Create, Update
    void saveFriend(Friend friend);

    // Read
    Friend findById(Long friend_id);

    // Delete
    void removeFriend(Long friend_id);

    // 유저의 relation 리스트 반환(1, 2, 3)으로 상태 반영하면 됨(Service 에서)
    List<Friend> friendListByNickname(String nickname, int relation);

    // Friend List 전체 반환
    List<Friend> allFriendList();

    // Friend 검색(UserProfile AllUserNickname)
    List<UserProfile> searchUserNickname(String nickname);
}
