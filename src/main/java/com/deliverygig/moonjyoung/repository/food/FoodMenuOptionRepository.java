package com.deliverygig.moonjyoung.repository.food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.food.FoodMenuOptionEntity;

@Repository
public interface FoodMenuOptionRepository extends JpaRepository<FoodMenuOptionEntity, Long> {
    public Integer countByFmoSeq(Long fmoSeq);    
}
