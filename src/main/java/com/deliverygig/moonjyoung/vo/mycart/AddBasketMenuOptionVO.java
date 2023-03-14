package com.deliverygig.moonjyoung.vo.mycart;

import java.util.List;

import lombok.Data;

@Data
public class AddBasketMenuOptionVO {
    private Long ciSeq;
    private Long stdSeq;
    private Long fmiSeq;
    private List<Long> fdoSeqList;
    private Integer count;
}
