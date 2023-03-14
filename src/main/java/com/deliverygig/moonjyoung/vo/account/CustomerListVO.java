package com.deliverygig.moonjyoung.vo.account;

import java.time.LocalDate;

import com.deliverygig.moonjyoung.entity.account.CustomerInfoEntity;

import lombok.Data;

@Data
public class CustomerListVO {
    private Long seq;
    private String id;
    private String nickname;
    private String name;
    private String email;
    private String phone;
    private LocalDate joinDt;
    private LocalDate birthDay;
    private Long uiSeq;
    private Integer status; 

    public CustomerListVO(CustomerInfoEntity entity) {
        this.seq = entity.getCiSeq();
        this.id = entity.getCiId();
        this.nickname = entity.getCiNickName();
        this.name = entity.getCiName();
        this.email = entity.getCiEmail();
        this.phone = entity.getCiPhone();
        this.joinDt = entity.getCiJoinDt();
        this.birthDay = entity.getCiBirthday();
        this.uiSeq = entity.getCiUiSeq();
        this.status = entity.getCiStatus();
    }
}
