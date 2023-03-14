package com.deliverygig.moonjyoung.vo.account;

import java.time.LocalDate;

import com.deliverygig.moonjyoung.entity.account.CustomerInfoEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserVO {
    private Long ciSeq;
    private String ciId;
    private String ciPwd;
    private String ciNickName;
    private String ciName;
    private String ciEmail;
    private String ciPhone;
    private LocalDate ciJoinDt;
    private LocalDate ciBirthday;
    private Long ciUiSeq;
    private Integer ciStatus;

    public LoginUserVO(CustomerInfoEntity data) {
        this.ciSeq = data.getCiSeq();
        this.ciId = data.getCiId();
        this.ciPwd = data.getCiPwd();
        this.ciNickName = data.getCiNickName();
        this.ciName = data.getCiName();
        this.ciEmail = data.getCiEmail();
        this.ciPhone = data.getCiPhone();
        this.ciJoinDt = data.getCiJoinDt();
        this.ciBirthday = data.getCiBirthday();
        this.ciUiSeq = data.getCiUiSeq();
        this.ciStatus = data.getCiStatus();
    }
}
