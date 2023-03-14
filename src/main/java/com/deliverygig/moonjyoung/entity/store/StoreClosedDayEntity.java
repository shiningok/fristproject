package com.deliverygig.moonjyoung.entity.store;

import java.util.List;

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
@Table(name = "store_closed_day_info")
public class StoreClosedDayEntity {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="scdi_seq") private Long scdiSeq;
     @ManyToOne
     @JoinColumn(name="scdi_si_seq") private StoreInfoEntity storeInfoEntity;
     @Column(name="scdi_day_no") private Integer scdiDayNo;
     @Column(name="scdi_day") private String scdiDay;
}
