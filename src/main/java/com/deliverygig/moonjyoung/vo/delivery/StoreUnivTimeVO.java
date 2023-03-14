package com.deliverygig.moonjyoung.vo.delivery;

import java.time.LocalTime;

import lombok.Data;

@Data
public class StoreUnivTimeVO {
    private String uiName;
    private LocalTime puaCloseTime;
    private LocalTime puaDeliTime1;
    private LocalTime puaDeliTime2;
    private String siName;
    private LocalTime stdCloseTime;
}
