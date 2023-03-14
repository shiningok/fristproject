package com.deliverygig.moonjyoung.entity.review;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;

import com.deliverygig.moonjyoung.entity.mycart.BasketMenuOptionsCombineEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "review_info")
public class ReviewEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ri_seq") private Long riSeq;
    @ManyToOne
    @JoinColumn(name = "ri_bmoc_seq")
    private BasketMenuOptionsCombineEntity BasketMenuOptionsCombineEntity;
    @Column(name = "ri_contents") private String riContents;
    @Column(name = "ri_score") private Integer riScore;
    @Column(name = "ri_status") private Integer riStatus;
    @Column(name = "ri_reg_dt") private LocalDateTime riRegDt;
}
