package com.deliverygig.moonjyoung.vo.store;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.deliverygig.moonjyoung.entity.delivery.StoreTimeDetailEntity;

import lombok.Data;

@Data
public class ShowStoreListVO {
    private Long storeSeq;
    private String storeName;
    private String storeCloseTime;
    private Integer discount;
    private Double scoreAvg;
    private Integer reviewCount;
    private Integer storeStatus;
    private String simgUriLogo;

    public ShowStoreListVO(StoreTimeDetailEntity entity) {
        this.storeSeq = entity.getStoreInfoEntity().getSiSeq();
        this.storeName = entity.getStoreInfoEntity().getSiName();
        this.storeCloseTime = entity.getStdCloseTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        this.discount = entity.getStoreInfoEntity().getSiDiscount();
        this.storeStatus = entity.getStoreInfoEntity().getSiStatus();
    }

    public Integer getdiscount() {
        return discount;
    }

    public ShowStoreListVO() {
    }

    
    
}
