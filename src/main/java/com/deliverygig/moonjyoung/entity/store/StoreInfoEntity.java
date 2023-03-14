package com.deliverygig.moonjyoung.entity.store;
import java.time.LocalDateTime;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name = "store_info")
public class StoreInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @OneToMany(mappedBy = "storeInfoEntity") @JoinColumn(name = "si_seq")
    // private List<StoreDetailInfoEntity> sdiList = new ArrayList<>();
    @Column(name = "si_seq")
    private Long siSeq;
    @Column(name = "si_name")
    private String siName;
    @Column(name = "si_discount")
    @ColumnDefault("0")
    private Integer siDiscount;
    @Column(name = "si_regdt")
    @ColumnDefault("now()")
    private LocalDateTime siRegdt;
    @Column(name = "si_oi_seq")
    private Long siOiSeq;
    @Column(name = "si_status")
    @ColumnDefault("9")
    private Integer siStatus;

    // @OneToOne(mappedBy = "store_info")
    // private StoreDetailInfoEntity storeDetailInfoEntity;

    // public void setSiSeqq(Long num) {
    //     this.siSeq = num;

}


