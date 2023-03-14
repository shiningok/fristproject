package com.deliverygig.moonjyoung.vo.delivery;
import java.time.format.DateTimeFormatter;

import com.deliverygig.moonjyoung.entity.delivery.UnivTimeInfoEntity;

import lombok.Data;

@Data
public class ShowUnivTimeVO {
    private Long utiSeq;
    private String utiName;
    private String utiCloseTime;
    private String utiDeliveryTime;

    public ShowUnivTimeVO(UnivTimeInfoEntity entity) {
        this.utiSeq = entity.getUtiSeq();
        this.utiName = entity.getUtiName();
        this.utiCloseTime = entity.getUtiCloseTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        this.utiDeliveryTime = entity.getUtiPickupTime1().format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
