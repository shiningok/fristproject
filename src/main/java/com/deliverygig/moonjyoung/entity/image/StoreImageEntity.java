package com.deliverygig.moonjyoung.entity.image;

import org.hibernate.annotations.DynamicInsert;

import com.deliverygig.moonjyoung.entity.store.StoreInfoEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Entity
@Builder
@Table(name = "store_image")
public class StoreImageEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "simg_seq")  private Long simgSeq; // 가게이미지 번호
    @Column(name = "simg_image") private String simgImage; // 가게이미지파이ㅏㄹ
    @Column(name = "simg_si_seq") private Long simgSiSeq; // 기본정보와연결
    @Column(name = "simg_uri") private String simgUri; // url
    @Column(name = "simg_division") private Integer simgDivision;
    // @ManyToOne @JoinColumn(name = "simg_si_seq") private StoreInfoEntity storeInfoEntity;


    
}
