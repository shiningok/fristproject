package com.deliverygig.moonjyoung.entity.mycart;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;

import com.deliverygig.moonjyoung.entity.delivery.StoreTimeDetailEntity;
import com.deliverygig.moonjyoung.entity.delivery.UnivTimeInfoEntity;
import com.deliverygig.moonjyoung.entity.food.FoodMenuInfoEntity;

import jakarta.persistence.CascadeType;
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

@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "basket_menu_options_combine")
public class BasketMenuOptionsCombineEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bmoc_seq") private Long bmocSeq;
    @Column(name = "bmoc_bi_seq") private Long bmocBiSeq;
    @Column(name = "bmoc_std_seq") private Long bmocStdSeq;
    @Column(name = "bmoc_si_name") private String bmocSiName;
    @Column(name = "bmoc_fmi_seq") private Long bmocFmiSeq;
    @Column(name = "bmoc_fmi_name") private String bmocFmiName;
    @Column(name = "bmoc_option_all") private String bmocOptionAll;
    @Column(name = "bmoc_count") private Integer bmocCount;
    @Column(name = "bmoc_price") private Integer bmocPrice;
    @Column(name = "bmoc_reg_dt") private LocalDateTime bmocRegDt;

    @ManyToOne @JoinColumn(name = "bmoc_bi_seq", insertable=false, updatable=false) private BasketInfoEntity basketInfoEntity;
    @ManyToOne @JoinColumn(name = "bmoc_std_seq", insertable=false, updatable=false) private StoreTimeDetailEntity storeTimeDetailEntity;
    @ManyToOne @JoinColumn(name = "bmoc_fmi_seq", insertable=false, updatable=false) private FoodMenuInfoEntity foodMenuInfoEntity;

    public void setBasketInfoEntity(BasketInfoEntity basketInfoEntity) {
        this.basketInfoEntity = basketInfoEntity;
        if (!basketInfoEntity.getBmocEntityList().contains(this)) {
            basketInfoEntity.getBmocEntityList().add(this);
        }
    }

    public BasketMenuOptionsCombineEntity(Long biSeq, Long stdSeq, Long fmiSeq, String optionAll, Integer price, Integer count) {
        this.bmocBiSeq = biSeq;
        this.bmocStdSeq = stdSeq;
        this.bmocFmiSeq = fmiSeq;
        this.bmocOptionAll = optionAll;
        this.bmocPrice = price;
        this.bmocCount = count;
    }
}
