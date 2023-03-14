package com.deliverygig.moonjyoung.entity.food;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "food_menu_option")
public class FoodMenuOptionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fmo_seq") private Long fmoSeq;
    @Column(name="fmo_name") private String fmoName;
    @Column(name="fmo_duplicated") private Integer fmoDuplicated;
    @Column(name="fmo_required_option") private Integer fmoRequiredOption;
    
    @OneToMany(mappedBy = "foodMenuOptionEntity")
    private List<FoodDetailOptionEntity> fdoEntityList = new ArrayList<FoodDetailOptionEntity>();

    public void addFoodDetailOptionEntity(FoodDetailOptionEntity foodDetailOptionEntity) {
        this.fdoEntityList.add(foodDetailOptionEntity);
        if (foodDetailOptionEntity.getFoodMenuOptionEntity()!=this) {
            foodDetailOptionEntity.setFoodMenuOptionEntity(this);
        }
    }
}
