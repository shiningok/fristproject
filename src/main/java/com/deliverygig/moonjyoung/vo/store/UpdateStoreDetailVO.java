package com.deliverygig.moonjyoung.vo.store;

import lombok.Data;

@Data
public class UpdateStoreDetailVO {
    private String newsdiOwnerWord;
    private String newsdiPhoneNumber;
    private String newsdiAddress;
    private String newsdiOwnerName;
    private String newsdiStoreName;
    private String newsdiBusinessNumber;
    private String newsdiOrigin;
    private Integer newsdiMinOrderPrice;
    private Integer newsdiDeliveryPrice;
}
