package com.deliverygig.moonjyoung.vo.account;

import com.deliverygig.moonjyoung.entity.account.MasterEntity;

import lombok.Data;

@Data
public class MasterVO {
    private Long seq;
    private String id;
    private String nickname;
    private Integer status;
    private Integer grade;
    public MasterVO(MasterEntity entity) {
        this.seq = entity.getMiSeq();
        this.id = entity.getMiId();
        this.nickname = entity.getMiNickName();
        this.status = entity.getMiStatus();
        this.grade = entity.getMiGrade();
    }
}
