package com.deliverygig.moonjyoung.entity.account;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicInsert;

import com.deliverygig.moonjyoung.vo.account.CustomerAddVO;
import com.deliverygig.moonjyoung.vo.account.LoginVO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_info")
@DynamicInsert
@Builder
public class CustomerInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ci_seq")   private Long ciSeq;
    @Column(name = "ci_id")    private String ciId;
    @Column(name = "ci_pwd")   private String ciPwd; 
    @Column(name = "ci_nickname")  private String ciNickName;
    @Column(name = "ci_name")  private String ciName;
    @Column(name = "ci_email")  private String ciEmail;
    @Column(name = "ci_phone")  private String ciPhone;
    @Column(name = "ci_join_dt")  private LocalDate ciJoinDt;
    @Column(name = "ci_birthday")  private LocalDate ciBirthday;
    @Column(name = "ci_ui_seq")  private Long ciUiSeq;
    @Column(name = "ci_status")  private Integer ciStatus; 
    
    public CustomerInfoEntity(LoginVO data) {
    this.ciId = data.getCiId();
    this.ciPwd = data.getCiPwd();
    }

    public CustomerInfoEntity(CustomerAddVO data) {
        this.ciId = data.getCiId();
        this.ciPwd = data.getCiPwd();
        this.ciNickName = data.getCiNickName();
        this.ciName = data.getCiName();
        this.ciEmail = data.getCiEmail();
        this.ciPhone = data.getCiPhone();
        this.ciJoinDt = data.getCiJoinDt();
        // this.birthday = data.getBirthday();
        this.ciUiSeq = data.getCiUiSeq();
        this.ciStatus = data.getCiStatus(); 
    }

    // public CustomerInfoEntity(UpdateCustomerInfoVo data) {
    //     this.pwd = data.getUpdatepwd();
    //     this.nickName = data.getNickName();
    //     this.name = data.getName();
    //     this.email = data.getEmail();
    // }
}
