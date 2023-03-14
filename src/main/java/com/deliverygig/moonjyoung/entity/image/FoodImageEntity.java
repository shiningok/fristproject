package com.deliverygig.moonjyoung.entity.image;

import org.hibernate.annotations.DynamicInsert;

import com.deliverygig.moonjyoung.entity.food.FoodMenuInfoEntity;

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
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Entity 
@Table(name="food_image")
public class FoodImageEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fi_seq")  private Long fiSeq; // 음식이미지번호
    @Column(name = "fi_file") private String fiFile; // 음식 이미자 파일
    @Column(name = "fi_fmi_seq") private Long fiFmiSeq; // 음식기본정보랑 ! 
    @Column(name = "fi_uri") private String fiUri; // URL

    // @ManyToOne @JoinColumn(name = "fi_fmi_seq") private FoodMenuInfoEntity foodMenuInfoEntity;


    
}