package com.deliverygig.moonjyoung.repository.store;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.store.StoreInfoEntity;

@Repository
public interface StoreInfoRepository extends JpaRepository<StoreInfoEntity, Long> {
    public Integer countBySiName(String siName);
    public StoreInfoEntity findBySiOiSeq(Long siOiSeq);
    public StoreInfoEntity findBySiSeq(Long siSeq);
    public List<StoreInfoEntity> findAllBySiNameContaining (String siName);

    @Query(value = "select * from store_info order by si_discount desc", nativeQuery = true)
    public List<StoreInfoEntity> findAllByOrderBySiDiscount();

    public Page<StoreInfoEntity> findBySiNameContains(String SiName, Pageable pageable);

}
