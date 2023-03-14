package com.deliverygig.moonjyoung.entity.account;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicInsert;

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
@Table(name="owner_info")
@DynamicInsert
@Builder
public class OwnerEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "oi_seq")      private Long      oiSeq; 
    @Column(name = "oi_id")       private String    oiId; 
    @Column(name = "oi_pwd")      private String    oiPwd; 
    @Column(name = "oi_nickname") private String    oiNickName; 
    @Column(name = "oi_email")    private String    oiEmail; 
    @Column(name = "oi_phone")    private String    oiPhone; 
    @Column(name = "oi_join_dt")  private LocalDate oiJoinDt;  
    @Column(name = "oi_status")   private Integer   oiStatus; // (1.정상 2 블라인드 3. 가입대기)
}
