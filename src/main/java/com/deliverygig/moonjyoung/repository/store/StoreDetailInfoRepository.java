package com.deliverygig.moonjyoung.repository.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.store.StoreDetailInfoEntity;

@Repository
public interface StoreDetailInfoRepository extends JpaRepository<StoreDetailInfoEntity, Long> {
    public StoreDetailInfoEntity findBySdiSeq(Long SdiSeq);

    @Query(value = "select count(*) from store_detail_info where sdi_si_seq = :siSeq", nativeQuery = true)
    public Integer countBySdiSiSeq(@Param("siSeq") Long siSeq);
}
