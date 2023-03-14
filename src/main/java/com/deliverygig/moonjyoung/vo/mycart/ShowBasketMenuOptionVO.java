package com.deliverygig.moonjyoung.vo.mycart;

import java.time.LocalTime;

import lombok.Data;

@Data
public class ShowBasketMenuOptionVO {
    private Long biSeq;
    private String siName;
    private LocalTime closeTime;
    private LocalTime deliveryTime;
    private String menuName;
    private String optionAll;
    private Integer price;
    private Integer count;

    // public BasketMenuOptionVO(StoreTimeDetailEntity stdEntity, FoodMenuInfoEntity fmiEntity) {
    //     this.deliveryTime = stdEntity.getUnivTimeInfoEntity().getUtiPickupTime1();
    //     this.menuName = fmiEntity.getFmiName();
    //     this.price = fmiEntity.getFmiPrice();
    // }
}
