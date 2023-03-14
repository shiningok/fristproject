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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "food_detail_option")
public class FoodDetailOptionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fdo_seq") private Long fdoSeq;
    // @Column(name="fdo_fmo_seq") private Long fdoFmoSeq;
    @Column(name="fdo_name") private String fdoName;
    @Column(name="fdo_price") private Integer fdoPrice;
    @Column(name="fdo_order") private Integer fdoOrder;

    @ManyToOne
    @JoinColumn(name = "fdo_fmo_seq")
    private FoodMenuOptionEntity foodMenuOptionEntity;
    
    public void setFoodMenuOptionEntity(FoodMenuOptionEntity foodMenuOptionEntity) {
        this.foodMenuOptionEntity = foodMenuOptionEntity;
        if (!foodMenuOptionEntity.getFdoEntityList().contains(this)) {
            foodMenuOptionEntity.getFdoEntityList().add(this);
        }
    }
}
