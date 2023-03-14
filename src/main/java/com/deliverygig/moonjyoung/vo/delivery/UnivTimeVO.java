package com.deliverygig.moonjyoung.vo.delivery;

import java.time.LocalTime;

import lombok.Data;

@Data
public class UnivTimeVO {
    private Long uiSeq;
    private String timeName;
    private LocalTime deliTime1;
    private LocalTime deliTime2;
    private LocalTime closeTime;
    // private UnivInfoEntity univ;
    
}
