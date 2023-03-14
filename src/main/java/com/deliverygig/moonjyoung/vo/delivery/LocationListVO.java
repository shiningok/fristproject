package com.deliverygig.moonjyoung.vo.delivery;

import java.util.List;

import lombok.Data;

@Data
public class LocationListVO {
    private Long uiSeq;
    private String uiName;
    private Long puaSeq;
    private String puaName;
    private List<UnivTimeVO> univTimeVOList;
}
