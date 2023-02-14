package com.ssafy.api.service;

import com.ssafy.api.request.ItemRequest;
import com.ssafy.api.request.ItemUserRequest;
import com.ssafy.db.entity.Item.Item;
import com.ssafy.db.entity.Item.ItemUser;
import com.ssafy.db.entity.User.UserProfile;

import java.util.List;

public interface ItemService {

    // Item 생성
    Item createItem(ItemRequest itemReq, int type);

    // ItemUser 생성
    ItemUser createItemUser(ItemUserRequest itemUserReq, int item_id);

    // Item List
    List<Item> itemList(int item_id);       // (Put 수정으로 사용)

    List<Item> itemAllList();

    // User Item List
    List<ItemUser> itemUserList(String nickname);
    List<ItemUser> itemUserAllList();

    /**
     * User 포인트로 Item 사기(포인트 차감)
     * @param nickname
     * @param item_id
     * @return
     */
    Boolean buyItem(String nickname, int item_id);

    /**
     * 가지고 있는 아이템을 장착시키거나 해제하는 put 요청
     * @param nickname
     * @param item_id
     * @return
     */
    UserProfile updateWear(String nickname, int item_id);
    UserProfile clearWear(String nickname);
}
