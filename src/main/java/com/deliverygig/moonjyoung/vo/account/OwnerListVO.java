package com.deliverygig.moonjyoung.vo.account;

import java.time.LocalDate;

import com.deliverygig.moonjyoung.entity.account.OwnerEntity;

import lombok.Data;

@Data
public class OwnerListVO {
    private Long seq;
    private String id;
    private String pwd;
    private String nickname;
    private String email;
    private String phone;
    private LocalDate joinDt;
    private Integer status;

    public OwnerListVO(OwnerEntity entity) {
        this.seq = entity.getOiSeq();
        this.id = entity.getOiId();
        this.nickname = entity.getOiNickName();
        this.email = entity.getOiEmail();
        this.phone = entity.getOiPhone();
        this.joinDt = entity.getOiJoinDt();
        this.status = entity.getOiStatus();
    }
}
