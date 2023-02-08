package com.ssafy.api.controller;

import com.ssafy.api.service.FriendService;
import com.ssafy.db.entity.Friend.Friend;
import com.ssafy.db.entity.User.UserProfile;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/friend")
@RequiredArgsConstructor
public class FriendController {

    @Autowired
    FriendService friendService;

    @GetMapping("/{nickname}")
    @ApiOperation(value = "친구를 관계별로 조회", notes = "친구 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> getFriends(@PathVariable String nickname, @RequestParam int relation) {
        List<Friend> friendList = friendService.getFriendList(nickname, relation);
        return ResponseEntity.status(200).body(friendList);
    }

    @GetMapping("/search")
    @ApiOperation(value = "유저 닉네임 검색", notes = "유저 닉네임 리스트 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public List<UserProfile> searchUser(@RequestParam String nickname) {
        List<UserProfile> nicknameList = friendService.searchUserNickname(nickname);
        int listSize = nicknameList.size();
        return nicknameList;
    }

    @GetMapping("/search/all")
    @ApiOperation(value = "친구 테이블 전체 조회", notes = "친구테이블 전체 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> AllFriendsList() {
        List<Friend> allList = friendService.friendAllList();
        return ResponseEntity.status(200).body(allList);
    }
    
    @PostMapping
    @ApiOperation(value = "친구 요청", notes = "친구 요청")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
//    public String requestFriend(@RequestParam FriendRequest friendReq, @RequestParam String nickname) {
    public String requestFriend(@RequestParam String nickname, @RequestParam String friend_nickname) {
        friendService.createFriend(nickname, friend_nickname);
        return "CREATE 200 OK";
    }

    @PutMapping
    @ApiOperation(value = "관계 수정(요청받은 친구만 누를 수 있게!)", notes = "관계 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String updateRelation(@RequestParam Long friend_id) {
        friendService.updateRequest(friend_id);
        return "UPDATE 200 OK";
    }

    @DeleteMapping
    @ApiOperation(value = "관계 삭제", notes = "관계 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String deleteFriend(Long friend_id) {
        friendService.deleteFriend(friend_id);
        return "DELETE";
    }
}
