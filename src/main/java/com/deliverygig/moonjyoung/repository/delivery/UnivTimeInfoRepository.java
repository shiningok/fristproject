package com.deliverygig.moonjyoung.repository.delivery;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.delivery.UnivTimeInfoEntity;

@Repository
public interface UnivTimeInfoRepository extends JpaRepository<UnivTimeInfoEntity, Long>{
    public Integer countByUtiSeq(Long utiSeq);
    public UnivTimeInfoEntity findByUtiSeq(Long utiSeq);
    public Integer countByUtiCloseTime(LocalTime utiCloseTime);
    public Page<UnivTimeInfoEntity> findByUtiNameContains(String utiName, Pageable pageable);
    public Integer countByUtiName(String utiName);
    
    @Query(value = "select * from univ_time_info where uti_ui_seq = :utiUiSeq", nativeQuery = true)
    public List<UnivTimeInfoEntity> findAllByUtiUiSeq(@Param("utiUiSeq") Long utiUiSeq);
}
