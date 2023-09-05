package com.backy.repository;

import com.backy.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//<entity class,primary_key의 데이터 타입>
public interface ItemRepository extends JpaRepository<Item, Long> {
    //persist->save 메서드를 가지고 있음
    public List<Item> findByItemNm(String itemNm);
    public List<Item> findByPrice(int price);



}
