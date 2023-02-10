package com.ssafy.db.repository;

import com.ssafy.db.entity.Item.Item;
import com.ssafy.db.entity.Item.ItemUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class ItemRepositorySupport implements ItemRepository{

    private final EntityManager em;

    @Override
    public void saveItem(Item item) {
        em.persist(item);
    }

    @Override
    public void saveItemUser(ItemUser itemUser) {
        em.persist(itemUser);
    }

    @Override
    public Item findById(int item_id) {
        return em.find(Item.class, item_id);
    }

    @Override
    public ItemUser findItemUserById(Long itemUser_id) {
        return em.find(ItemUser.class, itemUser_id);
    }

    @Override
    public List<Item> itemAllList() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    @Override
    public List<ItemUser> itemUserAllList() {
        return em.createQuery("select iu from ItemUser iu", ItemUser.class)
                .getResultList();
    }

    @Override
    public List<Item> itemByIdList(int item_id) {
        return em.createQuery("select i from Item i where i.item_id=:item_id", Item.class)
                .setParameter("item_id", item_id)
                .getResultList();
    }

    @Override
    public List<ItemUser> wearItem(String nickname) {
        return em.createQuery("select iu from ItemUser iu where iu.nickname=:nickname and iu.wear=:wear", ItemUser.class)
                .setParameter("nickname", nickname)
                .setParameter("wear", true)
                .getResultList();
    }

    @Override
    public List<ItemUser> itemUserNicknameList(String nickname) {
        return em.createQuery("select iu from ItemUser iu where iu.nickname=:nickname", ItemUser.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }


}
