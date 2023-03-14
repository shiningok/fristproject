package com.deliverygig.moonjyoung.entity.food;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "food_menu_info")
public class FoodMenuInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fmi_seq")           private Long fmiSeq;
    @Column(name = "fmi_name")          private String fmiName;
    @Column(name = "fmi_explanation")   private String fmiExplanation;
    @Column(name = "fmi_price")         private Integer fmiPrice;
    // @Column(name = "fmi_fc_seq") private Long fmiFcSeq;
    @Column(name = "fmi_best")          private Integer fmiBest;

    @ManyToOne @JoinColumn(name = "fmi_fc_seq") private FoodCategoryEntity foodCategoryEntity;
    @OneToMany(mappedBy = "foodMenuInfoEntity") private List<FoodOptionConnectEntity> foodOptionConnectEntity = new ArrayList<FoodOptionConnectEntity>();
}
