package com.deliverygig.moonjyoung.vo.store;

import java.time.LocalTime;

import lombok.Data;

@Data
public class StoreListInfoVO {
    private String siName;
    private String univTimeName;
    private LocalTime utiCloseTime;
    private LocalTime utiPickupTime;
    private LocalTime siCloseTime;
    private Integer siDiscount;
}
