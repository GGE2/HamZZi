package com.ssafy.api.service;

import com.ssafy.api.request.FriendRequest;
import com.ssafy.db.entity.Friend.Friend;
import com.ssafy.db.entity.User.UserProfile;

import java.util.List;

public interface FriendService {

    // Friend 가지고 오기
    List<Friend> getFriendList(String nickname, int relation);

    // Friend 검색
    List<UserProfile> searchUserNickname(String nickname);

    // Friend Table 전체 조회
    List<Friend> friendAllList();

    // Friend table 생성(친구 요청 보냈을 때)
    void createFriend(String nickname, String friend_nickname);

    // relation 수정
    // 1 > 2 > 3 3이후엔 삭제만 되게 해줘 진성아 ㅎ
    void updateRequest(Long friend_id);

    // 삭제
    void deleteFriend(Long friend_id);
}
