package com.deliverygig.moonjyoung.entity.delivery;

import java.time.LocalTime;

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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "store_time_detail")
public class StoreTimeDetailEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "std_seq") private Long stdSeq;
    // @Column(name = "std_si_seq") private Long stdSiSeq;
    // @Column(name = "std_uti_seq") private Long stdUtiSeq;
    @Column(name = "std_close_time") private LocalTime stdCloseTime;

    @ManyToOne @JoinColumn(name = "std_si_seq") StoreInfoEntity storeInfoEntity;
    @ManyToOne @JoinColumn(name = "std_uti_seq") UnivTimeInfoEntity univTimeInfoEntity;
    // @ManyToOne @JoinColumn(name = "std_pua_seq") PickUpAreaEntity pickUpAreaEntity;
}
