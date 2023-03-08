package com.ssafy.db.repository;

import com.ssafy.db.entity.Item.Item;
import com.ssafy.db.entity.Item.ItemUser;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemRepository {

    // Create, Update
    void saveItem(Item item);

    void saveItemUser(ItemUser itemUser);

    // Read
    Item findById(int item_id);

    ItemUser findItemUserById(Long itemUser_id);

    List<Item> itemAllList();

    List<ItemUser> itemUserAllList();

    /**
     * Nickname으로 ItemUser에서 리스트 가져오기
     * @param nickname
     * @return
     */
    List<ItemUser> itemUserNicknameList(String nickname);

    /**
     * Id로 Item에서 리스트 가져오기
     * @param item_id
     * @return List
     */
    List<Item> itemByIdList(int item_id);
}
