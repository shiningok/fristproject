package com.deliverygig.moonjyoung.entity.delivery;

import java.time.LocalTime;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "univ_time_info")
public class UnivTimeInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "uti_seq") private Long utiSeq;
    @Column (name = "uti_ui_seq") private Long utiUiSeq;
    @Column (name = "uti_name") private String utiName;
    @Column (name = "uti_close_time") private LocalTime utiCloseTime;
    @Column (name = "uti_pickup_time_1") private LocalTime utiPickupTime1;
    @Column (name = "uti_pickup_time_2") private LocalTime utiPickupTime2;

    @ManyToOne @JoinColumn(name = "uti_ui_seq", insertable = false, updatable = false) UnivInfoEntity univInfoEntity;
    // @ManyToOne @JoinColumn(name = "uti_pua_seq") PickUpAreaEntity puckUpAreaEntity;
}
