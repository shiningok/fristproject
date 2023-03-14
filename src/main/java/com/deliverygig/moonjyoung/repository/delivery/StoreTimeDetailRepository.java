package com.deliverygig.moonjyoung.repository.delivery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.delivery.StoreTimeDetailEntity;
import com.deliverygig.moonjyoung.entity.store.StoreInfoEntity;

@Repository
public interface StoreTimeDetailRepository extends JpaRepository<StoreTimeDetailEntity, Long> {
    public StoreTimeDetailEntity findByStoreInfoEntity(StoreInfoEntity storeInfoEntity);
    @Query(value = "select * from store_time_detail where std_si_seq = :stdSiSeq and std_uti_seq = :stdUtiSeq", nativeQuery = true)
    public StoreTimeDetailEntity findByStdSiSeqAndStdUtiSeq(@Param("stdSiSeq") Long stdSiSeq, @Param("stdUtiSeq") Long stdUtiSeq);
    @Query(value = "select * from store_time_detail where std_si_seq = :stdSiSeq", nativeQuery = true)
    public List<StoreTimeDetailEntity> findAllByStdSiSeq(@Param("stdSiSeq") Long stdSiSeq);
    @Query(value = "select * from store_time_detail where std_uti_seq = :stdUtiSeq", nativeQuery = true)
    public List<StoreTimeDetailEntity> findAllByStdUtiSeq(@Param("stdUtiSeq") Long stdUtiSeq);
}
