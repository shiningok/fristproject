package com.deliverygig.moonjyoung.vo.delivery;

import com.deliverygig.moonjyoung.entity.delivery.PickUpAreaEntity;
import com.deliverygig.moonjyoung.entity.delivery.UnivInfoEntity;

import lombok.Data;

@Data
public class PickUpAreaVO {
    private String uiName;
    private String puaName;
    private UnivInfoEntity univ;
    // private Long puaUiSeq;


    public UnivInfoEntity toUnivInfoEntity() {
        return new UnivInfoEntity(null, uiName, null);
    }

    public PickUpAreaEntity toPickupEntity() {
        return new PickUpAreaEntity(null, puaName, univ);
    }
}
