package com.deliverygig.moonjyoung.repository.store;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.store.StoreClosedDayEntity;

@Repository
public interface StoreClosedDayRepository extends JpaRepository<StoreClosedDayEntity, Long> {
    public StoreClosedDayEntity findByScdiSeqAndScdiDay(Long scdiSeq, String scdiDay);
    public Long findByScdiDayNo(Integer scdiDayNo);
     @Query(value = "select * from store_closed_day_info where scdi_si_seq = :scdiSiSeq", nativeQuery = true)
    public List<StoreClosedDayEntity> findByScdiSiSeq(@Param("scdiSiSeq") Long scdiSiSeq);
}       
