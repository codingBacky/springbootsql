package com.backy.repository;

import com.backy.constant.ItemSellStatus;
import com.backy.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품저장 테스트")
    public void createItemTest(){
        Item item = new Item();
        item.setItemNm("테스트상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
    }
    @Test
    @DisplayName("상품조회 테스트")
    public void findItemTest(){
    //    createItemTest();
        List<Item> items = itemRepository.findAll();
        System.out.println(items.get(0).toString());
        List<Item> items2 = itemRepository.findByItemNm("테스트상품");
        System.out.println(items2.get(0).toString());
        List<Item> items3 = itemRepository.findByPrice(10000);
        System.out.println(items2.get(0).toString());
        System.out.println("전체자료의 개수" + itemRepository.count());

    }
}