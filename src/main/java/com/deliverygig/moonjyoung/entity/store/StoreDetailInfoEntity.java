package com.deliverygig.moonjyoung.entity.store;
import org.hibernate.annotations.DynamicInsert;

import com.deliverygig.moonjyoung.vo.store.StoreDetailInfoVO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToOne;
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
@Table(name = "store_detail_info")
public class StoreDetailInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sdi_seq") private Long sdiSeq;
    // @Column(name="sdi_si_seq") private Long sdiSiSeq;
    @Column(name="sdi_owner_word") private String sdiOwnerWord;
    @Column(name="sdi_phone_number") private String sdiPhoneNumber;
    @Column(name="sdi_address") private String sdiAddress;
    @Column(name="sdi_owner_name") private String sdiOwnerName;
    @Column(name="sdi_store_name") private String sdiStoreName;
    @Column(name="sdi_business_number") private String sdiBusinessNumber;
    @Column(name="sdi_origin") private String sdiOrigin;
    @Column(name="sdi_min_order_price") private Integer sdiMinOrderPrice;
    @Column(name="sdi_delivery_price") private Integer sdiDeliveryPrice;
    @ManyToOne
    @JoinColumn(name = "sdi_si_seq")
    private StoreInfoEntity storeInfoEntity;

     // @Builder
     // public void Setsdinum (Long num){
    //     this.storeInfoEntity.setSiSeqq(num);
    //  }
    
    // @OneToOne
    // @JoinColumn(name = "sdi_si_seq")
    // private StoreInfoEntity storeInfoEntity;
    
}
