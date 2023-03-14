package com.deliverygig.moonjyoung.vo.food;

import lombok.Data;

@Data
public class FoodListVO {
    private Long    fmiSeq;
    private String  fmiName;
    private String  fmiExplanation;
    private Integer fmiPrice;
    private Long    fmiFcSeq;
    private Integer fmiBest;
    
    
}
