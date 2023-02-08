package com.ssafy.db.repository;

import com.ssafy.db.entity.Friend.Friend;
import com.ssafy.db.entity.User.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class FriendRepositorySupport implements FriendRepository {

    private final EntityManager em;

    // 저장
    @Override
    public void saveFriend(Friend friend) {
        em.persist(friend);
    }

    // 삭제
    @Override
    public void removeFriend(Long friend_id) {
        em.remove(findById(friend_id));
    }

    // id로 찾기
    @Override
    public Friend findById(Long friend_id) {
        return em.find(Friend.class, friend_id);
    }

    // 요청을 보내는 유저와 관계 리스트
    @Override
    public List<Friend> friendListByNickname(String nickname, int relation) {
        try {
            return em.createQuery("select f from Friend f where f.nickname=:nickname and f.relation=:relation", Friend.class)
                    .setParameter("nickname", nickname)
                    .setParameter("relation", relation)
                    .getResultList();
        } catch (NoResultException e) { return null; }
    }

    // Friend의 모든 리스트 가지고 오기
    @Override
    public List<Friend> allFriendList() {
        return em.createQuery("select f from Friend f", Friend.class)
                .getResultList();
    }

    // UserNickname 검색
    @Override
    public List<UserProfile> searchUserNickname(String nickname) {
        try {
            return em.createQuery("select up from UserProfile up where up.nickname like :nickname", UserProfile.class)
                    .setParameter("nickname", "%" + nickname + "%")
                    .getResultList();
        } catch (NoResultException e) { return null; }
    }
}
