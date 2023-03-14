package com.deliverygig.moonjyoung.vo.review;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StoreReviewListVO {
    private Long riSeq;
    private String ciNickName;
    private String menu;
    private String menuOption;
    private Integer reviewScore;
    private String reviewContent;
    private LocalDate reviewRegDt;
}


