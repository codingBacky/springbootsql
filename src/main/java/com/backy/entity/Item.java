package com.backy.entity;

import com.backy.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@Table(name = "shopItem")
@Getter @Setter @ToString

//시퀀스로 일련번호 부여
@SequenceGenerator(
        name ="ITEM_SEQUENCE_JEN",
        sequenceName = "ITEM_SEQUENCE",
        initialValue = 1,
        allocationSize = 1



)
public class Item {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "ITEM_SEQUENCE_JEN")
    private Long id;
    @Column(nullable = false,length = 50)
    private String itemNm;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private int stockNumber;
    //@Lob
    @Column(nullable = false, length = 4000)
    private String itemDetail;
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
