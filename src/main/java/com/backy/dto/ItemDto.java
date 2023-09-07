package com.backy.dto;

import com.backy.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter @Setter
public class ItemDto {
    private Long id;
    private String itemNm;
    private Integer price;
    private String itemDetail;
    private String itemSellStatus;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
