package com.deliverygig.moonjyoung.vo.food;

import java.util.List;

import com.deliverygig.moonjyoung.entity.food.FoodOptionConnectEntity;

import lombok.Data;

@Data
public class ShowFoodOptionVO {
    private String optionCateName;
    private Integer isDuplicated;
    private Integer isRequired;
    private List<ShowFoodDetailOptionVO> detailOptionList;

    public ShowFoodOptionVO(FoodOptionConnectEntity entity) {
        this.optionCateName = entity.getFoodMenuOptionEntity().getFmoName();
        this.isDuplicated = entity.getFoodMenuOptionEntity().getFmoDuplicated();
        this.isRequired = entity.getFoodMenuOptionEntity().getFmoRequiredOption();
    }
}
