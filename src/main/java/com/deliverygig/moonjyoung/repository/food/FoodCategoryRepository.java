package com.deliverygig.moonjyoung.repository.food;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.food.FoodCategoryEntity;
import com.deliverygig.moonjyoung.repository.store.StoreInfoRepository;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategoryEntity, Long> {
    @Query(value = "select * from food_category where fc_si_seq = :siSeq order by fc_order", nativeQuery = true)
    public List<FoodCategoryEntity> findAllBySiSeq(@Param("siSeq") Long siSeq);
    
    public FoodCategoryEntity findByFcSeq(Long fcSeq);
    public Integer countByFcName(String fcName);
    public void deleteByFcSeq(Long fcSeq);

    public Integer countByFcSeq(Long fcSeq);
    
}
