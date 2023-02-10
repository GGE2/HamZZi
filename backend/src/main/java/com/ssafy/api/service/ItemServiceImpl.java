package com.ssafy.api.service;

import com.ssafy.api.request.ItemRequest;
import com.ssafy.api.request.ItemUserRequest;
import com.ssafy.db.entity.Item.Item;
import com.ssafy.db.entity.Item.ItemUser;
import com.ssafy.db.entity.Pet.Pet;
import com.ssafy.db.entity.User.UserProfile;
import com.ssafy.db.repository.ItemRepository;
import com.ssafy.db.repository.PetRepository;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    PetRepository petRepo;

    @Override
    public Item createItem(ItemRequest itemReq, int type) {
        Item item = new Item();
        item.setItem_id(itemReq.getItem_id());
        item.setCost(itemReq.getCost());
        item.setLevel(itemReq.getLevel());
        if (type == 1) {
            item.setType("모자");
        } else if (type == 2) {
            item.setType("옷");
        } else if (type == 3) {
            item.setType("배경");
        }
        itemRepo.saveItem(item);

        return item;
    }

    @Override
    public ItemUser createItemUser(ItemUserRequest itemUserReq, int item_id) {
        // 포인트와 레벨을 충족해야 하기 때문에 계산식 들어가야 함!
        String nickname = itemUserReq.getNickname();
        if (buyItem(nickname, item_id)) {
            ItemUser itemUser = new ItemUser();
            itemUser.setNickname(nickname);
            itemUser.setItem(itemRepo.findById(item_id));
            itemUser.setWear(false);
            itemRepo.saveItemUser(itemUser);

            return itemUser;
        } else {
            return null;
        }
    }

    @Override
    public List<Item> itemList(int item_id) {
        return itemRepo.itemByIdList(item_id);
    }

    @Override
    public List<Item> itemAllList() {
        return itemRepo.itemAllList();
    }

    @Override
    public List<ItemUser> itemUserList(String nickname) {
        return itemRepo.itemUserNicknameList(nickname);
    }

    @Override
    public List<ItemUser> itemUserAllList() {
        return itemRepo.itemUserAllList();
    }


    // Item 을 살 수 있는 유저인지 확인!
    // 살 수 있다면 point 차감 후 true 반환
    // 살 수 없다면 false 반환
    @Override
    public Boolean buyItem(String nickname, int item_id) {
        UserProfile userProfile = userRepo.findByNickname(nickname);
        Item item = itemRepo.findById(item_id);
        Pet pet = petRepo.findByNickname(nickname);

        int point = userProfile.getPoint();
        int cost = item.getCost();

        int pet_level = pet.getLevel();
        int item_level = item.getLevel();

        if (point >= cost && pet_level >= item_level) {
            userProfile.setPoint(point - cost);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ItemUser updateWear(Long itemUser_id) {
        ItemUser itemUser = itemRepo.findItemUserById(itemUser_id);
        Boolean wear = itemUser.getWear();
        itemUser.setWear(!wear);
        itemRepo.saveItemUser(itemUser);
        return itemUser;
    }

    @Override
    public List<ItemUser> findByTrue(String nickname) {
        return itemRepo.wearItem(nickname);
    }
}
