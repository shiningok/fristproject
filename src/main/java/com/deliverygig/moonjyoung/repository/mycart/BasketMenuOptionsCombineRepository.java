package com.deliverygig.moonjyoung.repository.mycart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.mycart.BasketMenuOptionsCombineEntity;

@Repository
public interface BasketMenuOptionsCombineRepository extends JpaRepository<BasketMenuOptionsCombineEntity, Long> {
    public Integer countByBmocSeq(Long bmocSeq);
    public List<BasketMenuOptionsCombineEntity> findAllByBmocBiSeq(Long bmocBiSeq);
}
