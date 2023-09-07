package com.backy.repository;

import com.backy.constant.ItemSellStatus;
import com.backy.entity.Item;
import com.backy.entity.QItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("상품저장 테스트")
    public void createItemTest(){
        for(int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.setItemNm("테스트상품" + i);
            item.setPrice(10000 + 1);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }
    }
    @Test
    @DisplayName("상품조회 테스트")
    public void findItemTest(){
    //    createItemTest();
        List<Item> items = itemRepository.findAll();
        System.out.println(items.get(0).toString());
        List<Item> items2 = itemRepository.findByItemNm("테스트상품1");
        System.out.println(items2.get(0).toString());
        List<Item> items3 = itemRepository.findByPrice(10000);
        System.out.println(items2.get(0).toString());
        System.out.println("전체자료의 개수" + itemRepository.count());

    }
    @Test
    @DisplayName("Querydsl 조회테스트1")
    public void queryDslTest(){
        this.createItemTest();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = QItem.item;
        JPAQuery<Item> query = queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%"+"테스트 상품 상세 설명"+"%"))
                .orderBy(qItem.price.desc());

        List<Item> itemList = query.fetch();

        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }


    public void createItemList2(){
        for(int i = 1; i < 11; i++){
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            item.setStockNumber(100);
            item.setItemSellStatus((i < 6)?ItemSellStatus.SELL : ItemSellStatus.SOLD_OUT);
            itemRepository.save(item);
        }
//        for(int i = 6; i <= 10; i++){
//            Item item = new Item();
//            item.setItemNm("테스트 상품" + i);
//            item.setPrice(10000 + i);
//            item.setItemDetail("테스트 상품 상세 설명" + i);
//            item.setRegTime(LocalDateTime.now());
//            item.setUpdateTime(LocalDateTime.now());
//            item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
//            item.setStockNumber(0);
//            itemRepository.save(item);
//        }
    }
    @Test
    @DisplayName("상품명 or 상품상세설명 조회 테스트")
    public void findByItemNmOrItemDetailTest(){
        this.createItemTest();
        this.createItemList2();
        for(Item item : itemRepository.findByItemNmOrItemDetail("테스트 상품1","테스트 상품 상세 설명5")){
            System.out.println(item);
        }
    }


    @Test
    @DisplayName("상품  Querydsl 조회 테스트 2")
    public void queryDslTest2(){
//        spring.jpa.hibernate.ddl-auto = update    생성하고는 유지하고 있으므로 
//        this.createItemList2();                   새로 생성하지 않아도 됨

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QItem item = QItem.item;
        String itemDetail = "테스트 상품 상세 설명";
        int price = 10003;
        String itemSellStat = "SELL";

        booleanBuilder.and(item.itemDetail.like("%" + itemDetail + "%"));
        booleanBuilder.and(item.price.gt(price));

        if(StringUtils.equals(itemSellStat, ItemSellStatus.SELL)){
            booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }
        Pageable pageable = PageRequest.of(0,5);
        Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);
        System.out.println("total elements :" + itemPagingResult.getTotalElements());

        List<Item> resultItemList = itemPagingResult.getContent();
        for(Item resultItem : resultItemList){
            System.out.println(resultItem.toString());
        }

    }


}