package com.deliverygig.moonjyoung.vo.store;
import com.deliverygig.moonjyoung.entity.store.StoreClosedDayEntity;

import lombok.Data;

@Data
public class StoreClosedDayInfoVO {
    private Integer scdi_day_no;
    private String scdi_day;
    
    // public StoreClosedDayInfoVO(StoreClosedDayEntity entity) {
    //     this.scdi_day_no = entity.getScdiDayNo();
    //     this.scdi_day = entity.getScdiDay();
    // }
}
