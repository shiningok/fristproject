package com.deliverygig.moonjyoung.vo.store;

import java.util.List;

import com.deliverygig.moonjyoung.vo.delivery.ClosePickupTimeVO;

import lombok.Data;

@Data
public class StoreDetailInfoVO {
    private Long sdiSiSeq;
    private String sdiOwnerWord;
    private String sdiPhoneNumber;
    private String sdiAddress;
    private String sdiOwnerName;
    private String sdiStoreName;
    private String sdiBusinessNumber;
    private String sdiOrigin;
    private Integer sdiMinOrderPrice;
    private Integer sdiDeliveryPrice;
    private List<ClosePickupTimeVO> closePickupTimeVoList;
}
