package com.deliverygig.moonjyoung.entity.mycart;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;

import com.deliverygig.moonjyoung.entity.account.CustomerInfoEntity;
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

@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "basket_info")
public class BasketInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bi_seq") private Long biSeq;
    @Column(name = "bi_ci_seq") private Long biCiSeq;
    @Column(name = "bi_number") private String biNumber;
    @Column(name = "bi_reg_dt") private LocalDateTime biRegDt;
    @Column(name = "bi_pua_name") private String biPuaName;
    @Column(name = "bi_price") private Integer biPrice;
    @Column(name = "bi_status") private Integer biStatus;

    @ManyToOne @JoinColumn(name = "bi_ci_seq", insertable=false, updatable=false) private CustomerInfoEntity customerInfoEntity;

    @OneToMany(mappedBy = "basketInfoEntity") private List<BasketMenuOptionsCombineEntity> bmocEntityList = new ArrayList<BasketMenuOptionsCombineEntity>();

    public void addbmocEntity(BasketMenuOptionsCombineEntity entity) {
        this.bmocEntityList.add(entity);
        if (entity.getBasketInfoEntity()!=this) {
            entity.setBasketInfoEntity(this);
        }
    }

    public BasketInfoEntity(Long ciSeq) {
        this.biCiSeq = ciSeq;
    }
}
