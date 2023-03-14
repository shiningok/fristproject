package com.deliverygig.moonjyoung.vo.food;

import java.util.List;

import com.deliverygig.moonjyoung.entity.food.FoodMenuInfoEntity;

import lombok.Data;

@Data
public class ShowMenuDetailVO {
    private Long menuSeq;
    private String name;
    private String explain;
    private Integer price;
    private Integer discountPrice;
    private List<ShowFoodOptionVO> optionList;

    public ShowMenuDetailVO(FoodMenuInfoEntity entity) {
        this.menuSeq = entity.getFmiSeq();
        this.name = entity.getFmiName();
        this.explain = entity.getFmiExplanation();
        this.price = entity.getFmiPrice();
        this.discountPrice = (int)(entity.getFmiPrice()*(1-entity.getFoodCategoryEntity().getStoreInfoEntity().getSiDiscount()/100.0));
    }
}
