package com.deliverygig.moonjyoung.repository.image;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverygig.moonjyoung.entity.delivery.PickUpAreaEntity;
import com.deliverygig.moonjyoung.entity.image.PickUpAreaImageEntity;

@Repository
public interface PickUpAreaImageRepository extends JpaRepository <PickUpAreaImageEntity , Long>{
    // public List<PickUpAreaEntity> findByPuaiUri (String puaiUri);
    public void deleteByPuaiSeq (Long puaiSeq);
    public Integer countByPuaiSeq(Long puaiSeq );
    
    public PickUpAreaImageEntity findTopByPuaiUri (String puaiUri);

    public String findByPuaiUri(String puaiUri);

    


    //@Query(value = "select * from pick_up_area_image where puai_pua_seq = :puai_pua_seq", nativeQuery = true)
    public PickUpAreaImageEntity findByPuaiPuaSeq(Long puaiPuaSeq);
     //void findAll(Long puaiSeq);
    
}


