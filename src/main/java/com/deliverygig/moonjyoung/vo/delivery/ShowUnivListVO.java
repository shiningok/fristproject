package com.deliverygig.moonjyoung.vo.delivery;

import com.deliverygig.moonjyoung.entity.delivery.UnivInfoEntity;

import lombok.Data;

@Data
public class ShowUnivListVO {
    private Long uiSeq;
    private String uiName;

    public ShowUnivListVO(UnivInfoEntity entity) {
        this.uiSeq = entity.getUiSeq();
        this.uiName = entity.getUiName();
    }
}
