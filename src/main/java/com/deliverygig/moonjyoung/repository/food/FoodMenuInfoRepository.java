package com.deliverygig.moonjyoung.repository.food;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.food.FoodMenuInfoEntity;

@Repository
public interface FoodMenuInfoRepository extends JpaRepository<FoodMenuInfoEntity, Long> {
    @Query(value = "select * from food_menu_info where fmi_fc_seq = :fmiFcSeq", nativeQuery = true)
    public List<FoodMenuInfoEntity> findAllByFmiFcSeq(@Param("fmiFcSeq") Long fmiFcSeq);
    public List<FoodMenuInfoEntity> findAllByFmiBest(Integer fmiBest);
    public Integer countByFmiSeq (Long fmiSeq);
    public FoodMenuInfoEntity findByFmiSeq(Long fmiSeq);
    public Integer countByFmiName(String fmiName);
    public void deleteByFmiSeq(Long fmiSeq);
    
}
