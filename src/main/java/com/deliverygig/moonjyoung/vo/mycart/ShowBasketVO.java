package com.deliverygig.moonjyoung.vo.mycart;

import java.time.LocalDateTime;
import java.util.List;

import com.deliverygig.moonjyoung.entity.mycart.BasketInfoEntity;

import lombok.Data;

@Data
public class ShowBasketVO {
    private String ciName;
    private String biNumber;
    private LocalDateTime biRegDt;
    private String puaName;
    private List<ShowBasketMenuVO> menuList;
    // private LocalTime pickupTime;
    // private String menuOptions;
    private Integer totalPrice;

    public ShowBasketVO (BasketInfoEntity entity) {
        this.ciName = entity.getCustomerInfoEntity().getCiName();
        this.biNumber = entity.getBiNumber();
        this.biRegDt = entity.getBiRegDt();
        this.puaName = entity.getBiPuaName();
    }
    public ShowBasketVO () {}
}
