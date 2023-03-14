package com.deliverygig.moonjyoung.entity.delivery;

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
import lombok.ToString;


//@ToString.Exclude private 
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name = "univ_info")
public class UnivInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ui_seq") private Long uiSeq;
    @Column(name = "ui_name") private String uiName;

    @OneToMany(mappedBy = "univInfoEntity") private List<PickUpAreaEntity> puaEntityList = new ArrayList<PickUpAreaEntity>();

    public void addPickUpAreaEntity(PickUpAreaEntity pickUpAreaEntity) {
        this.puaEntityList.add(pickUpAreaEntity);
        if (pickUpAreaEntity.getUnivInfoEntity()!=this) {
            pickUpAreaEntity.setUnivInfoEntity(this);
        }
    }
}
