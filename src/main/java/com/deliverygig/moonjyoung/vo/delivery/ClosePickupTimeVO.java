package com.deliverygig.moonjyoung.vo.delivery;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.deliverygig.moonjyoung.entity.delivery.StoreTimeDetailEntity;

import lombok.Data;

// 가게 상세정보 배달시간 목록에 사용 by 문주영
@Data
public class ClosePickupTimeVO {
    private Long utiSeq;
    private String name;
    private String closeTime;
    private String pickupTime;
    private Boolean thisTime;

    public ClosePickupTimeVO(StoreTimeDetailEntity entity) {
        this.utiSeq = entity.getUnivTimeInfoEntity().getUtiSeq();
        this.name = entity.getUnivTimeInfoEntity().getUtiName();
        this.closeTime = entity.getStdCloseTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        this.pickupTime = entity.getUnivTimeInfoEntity().getUtiPickupTime1().format(DateTimeFormatter.ofPattern("HH:mm"));
        this.thisTime = false;
    }
}
