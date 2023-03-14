package com.deliverygig.moonjyoung.repository.food;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.food.FoodDetailOptionEntity;

@Repository
public interface FoodDetailOptionRepository extends JpaRepository<FoodDetailOptionEntity, Long> {
    @Query(value = "select * from food_detail_option where fdo_fmo_seq = :fmoSeq", nativeQuery = true)
    public List<FoodDetailOptionEntity> findAllByFdoFmoSeq(@Param("fmoSeq") Long fdoFmoSeq);

    public Integer countByFdoSeq(Long fdoSeq);
}
