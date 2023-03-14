package com.deliverygig.moonjyoung.entity.food;

import org.hibernate.annotations.DynamicInsert;

import com.deliverygig.moonjyoung.entity.store.StoreInfoEntity;
import com.deliverygig.moonjyoung.vo.foodCategory.CateAddVO;

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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "food_category")
public class FoodCategoryEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fc_seq")   private Long    fcSeq;
    @Column(name="fc_name")  private String  fcName;
    // @Column(name="fc_si_seq") private Long fcSiSeq;
    @Column(name="fc_order") private Integer fcOrder;

    @ManyToOne @JoinColumn(name = "fc_si_seq") private StoreInfoEntity storeInfoEntity;


}
