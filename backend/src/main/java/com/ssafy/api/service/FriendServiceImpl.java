package com.ssafy.api.service;


import com.ssafy.api.request.FriendRequest;
import com.ssafy.db.entity.Friend.Friend;
import com.ssafy.db.entity.User.UserProfile;
import com.ssafy.db.repository.FriendRepository;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendRepository friendRepo;

    @Autowired
    UserRepository userRepo;
    
    // Friend List 유저가 relation 별로 가져오기
    @Override
    public List<Friend> getFriendList(String nickname, int relation) {
        List<Friend> friends = friendRepo.friendListByNickname(nickname, relation);
        List<Friend> friendList = new ArrayList<>();
        friendList.addAll(friends);

        return friendList;
    }

    // Friend 검색
    @Override
    public List<UserProfile> searchUserNickname(String nickname) {
        return friendRepo.searchUserNickname(nickname);
    }

    @Override
    public List<Friend> friendAllList() {
        return friendRepo.allFriendList();
    }

    // Friend table 생성(친구 요청 보냈을 때)
    @Override
    public void createFriend(String nickname, String friend_nickname) {
        Friend friend = new Friend();
        for (int i = 0; i <= 1; i++){
            if (i == 0){
                friend.setNickname(nickname);
                friend.setFriend_nickname(friend_nickname);
                friend.setRelation(1);
                friendRepo.saveFriend(friend);
            } else {
                friend.setNickname(friend_nickname);
                friend.setFriend_nickname(nickname);
                friend.setRelation(2);
                friendRepo.saveFriend(friend);
            }
        }
    }

    // relation 수정 > friend_id는 요청 받은 사람 것이기 때문에 -1 하면 신청한 사람도 같이 3(친구)으로 변하게
    @Override
    public void updateRequest(Long friend_id) {
        Friend friend1 = friendRepo.findById(friend_id);
        friend1.setRelation(3);
        friendRepo.saveFriend(friend1);

        // Long을 int로 바꿔서 연산하고 다시 Long으로 반환
        int idI = Math.toIntExact(friend_id);
        int downid = idI - 1;
        Long id = (long) downid;

        Friend friend2 = friendRepo.findById(id);
        friend2.setRelation(3);
        friendRepo.saveFriend(friend2);
    }

    @Override
    public void deleteFriend(Long friend_id) {
        friendRepo.removeFriend(friend_id);
    }
}
