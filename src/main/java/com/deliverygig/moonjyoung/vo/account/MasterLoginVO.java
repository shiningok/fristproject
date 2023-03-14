package com.deliverygig.moonjyoung.vo.account;

import com.deliverygig.moonjyoung.entity.account.MasterEntity;

import lombok.Data;

@Data
public class MasterLoginVO {
    private String id;
    private String nickname;
    private Integer grade;
    private Integer status; 
    public MasterLoginVO(MasterEntity account) {
        this.id = account.getMiId();
        this.nickname = account.getMiNickName();
        this.grade = account.getMiGrade();
        this.status = account.getMiStatus();
    }
}
