package com.deliverygig.moonjyoung.vo.food;

import com.deliverygig.moonjyoung.entity.food.FoodMenuInfoEntity;

import lombok.Data;

@Data

public class ShowMenuInfoVO {
    private Long menuSeq;
    private String name;
    private Integer price;
    private Integer discountPrice;
    private String fiUri;

    public ShowMenuInfoVO(FoodMenuInfoEntity entity) {
        this.menuSeq = entity.getFmiSeq();
        this.name = entity.getFmiName();
        this.price = entity.getFmiPrice();
        this.discountPrice = (int) (entity.getFmiPrice()
                * (1 - entity.getFoodCategoryEntity().getStoreInfoEntity().getSiDiscount() / 100.0));
    }
    
}
