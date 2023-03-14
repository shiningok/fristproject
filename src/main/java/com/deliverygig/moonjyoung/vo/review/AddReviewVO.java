package com.deliverygig.moonjyoung.vo.review;

import lombok.Data;

@Data
public class AddReviewVO {
    private Long order_seq;
    private String ri_contents;
    private Integer ri_score;
}