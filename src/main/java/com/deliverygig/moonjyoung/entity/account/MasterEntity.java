package com.deliverygig.moonjyoung.entity.account;

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
@Table (name = "master_info")
@DynamicInsert
@Builder
public class MasterEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mi_seq")    private Long miSeq;
    @Column(name = "mi_id")     private String miId;
    @Column(name = "mi_pwd")    private String miPwd;
    @Column(name = "mi_nickname")   private String miNickName;
    @Column(name = "mi_status") private Integer miStatus;
    @Column(name = "mi_grade")  private Integer miGrade;
}
