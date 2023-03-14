package com.deliverygig.moonjyoung.entity.food;

import org.hibernate.annotations.DynamicInsert;

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
@DynamicInsert
@Entity
@Table(name = "food_option_connect")
public class FoodOptionConnectEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foc_seq") private Long focSeq;
    // @Column(name = "foc_fmi_seq") private Long focFmiSeq;
    // @Column(name = "foc_fmo_seq") private Long focFmoSeq;
    @Column(name = "foc_fmo_order") private Integer focFmoOrder;

    @ManyToOne @JoinColumn(name = "foc_fmi_seq") private FoodMenuInfoEntity foodMenuInfoEntity;
    @ManyToOne @JoinColumn(name = "foc_fmo_seq") private FoodMenuOptionEntity foodMenuOptionEntity;
}
