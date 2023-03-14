package com.deliverygig.moonjyoung.vo.store;
import java.time.LocalTime;

import lombok.Data;

@Data
public class UpdateStoreVO {
    private String updatesiName;
    private Integer updatesiDiscount;
    private LocalTime siRegTime;
}
