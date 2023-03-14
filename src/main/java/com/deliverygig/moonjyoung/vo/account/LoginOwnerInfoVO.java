package com.deliverygig.moonjyoung.vo.account;


import com.deliverygig.moonjyoung.entity.account.OwnerEntity;

import lombok.Data;

@Data
public class LoginOwnerInfoVO {
  private String oiId;
  private String oiPwd;
  private Integer oiStatus;
  public LoginOwnerInfoVO(OwnerEntity entity) {
    this.oiId = entity.getOiId();
    this.oiPwd = entity.getOiPwd();
    this.oiStatus = entity.getOiStatus();
  }
}
